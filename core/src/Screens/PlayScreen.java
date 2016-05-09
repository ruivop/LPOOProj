package Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.maps.Map;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.lpooproj.game.Proj;

import Objects.Brick;
import Objects.ObjImp;
import Scenes.Hud;
import Objects.Bomber;

/**
 * Created by Asus on 07/05/2016.
 */
public class PlayScreen implements Screen{

    private Proj game;
    private OrthographicCamera gamecam;
    private Viewport gamePort;
    private Hud hud;
    Texture img;
    private Bomber bomber;
    private TextureAtlas atlas;

    private TmxMapLoader mapLoader;
    private Map map;
    private OrthogonalTiledMapRenderer rederer;
    World world;
    Box2DDebugRenderer b2db;


    public void handleInput(float dt){
        if(!Gdx.input.isKeyPressed(Input.Keys.ANY_KEY))
            bomber.body.applyLinearImpulse((bomber.body.getLinearVelocity()).scl(-1), bomber.body.getWorldCenter(), true);
        else {
            float velMax = 70f;
            float velX = bomber.body.getLinearVelocity().x;
            float velY = bomber.body.getLinearVelocity().y;

            if (Gdx.input.isKeyPressed(Input.Keys.UP) && velY <= velMax)
                bomber.body.applyLinearImpulse(new Vector2(-velX, velMax), bomber.body.getWorldCenter(), true);
            else if (Gdx.input.isKeyPressed(Input.Keys.DOWN) && velY >= -velMax)
                bomber.body.applyLinearImpulse(new Vector2(-velX, -velMax), bomber.body.getWorldCenter(), true);
            else if (Gdx.input.isKeyPressed(Input.Keys.RIGHT) && velX <= velMax)
                bomber.body.applyLinearImpulse(new Vector2(velMax, -velY), bomber.body.getWorldCenter(), true);
            else if (Gdx.input.isKeyPressed(Input.Keys.LEFT) && velX >= -velMax)
                bomber.body.applyLinearImpulse(new Vector2(-velMax, -velY), bomber.body.getWorldCenter(), true);
        }
    }

    public void update(float dt){
        handleInput(dt);
        //gamecam.update();
        world.step(1/60f, 6,2);
        rederer.setView(gamecam);
        bomber.update(dt);
    }


    public PlayScreen(Proj game){
        atlas = new TextureAtlas("allAssests.pack");

        this.game = game;
        gamecam = new OrthographicCamera();
        gamePort = new StretchViewport(Proj.V_WIDTH,Proj.V_HEIGHT, gamecam);
        hud = new Hud();

        mapLoader = new TmxMapLoader();
        map = mapLoader.load("level2.tmx");
        rederer = new OrthogonalTiledMapRenderer((TiledMap) map);

        gamecam.position.set(gamePort.getWorldWidth() / 2, gamePort.getWorldHeight()/2,0);

        world = new World(new Vector2(0,0),true);
        b2db = new Box2DDebugRenderer();

        Brick brick;
        for(MapObject object: map.getLayers().get(1).getObjects().getByType(RectangleMapObject.class)){
            brick = new Brick(object,world);
        }
        bomber = new Bomber(world, this);

    }

    public TextureAtlas getAtlas(){
        return atlas;
    }


    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        update(delta);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        rederer.render();
        b2db.render(world,gamecam.combined);
        game.batch.setProjectionMatrix(gamecam.combined);
        game.batch.begin();
        bomber.draw(game.batch);
        game.batch.end();

        game.batch.setProjectionMatrix(hud.stage.getCamera().combined);
        hud.stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        gamePort.update(width,height);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
