package Objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Array;

import Screens.PlayScreen;

/**
 * Created by Asus on 07/05/2016.
 */
public class Bomber extends Sprite {


    public enum State{UP, DOWN, LEFT, STOP};
    public World world;
    public Body body;
    private TextureRegion bomberStand;
    private Animation up;
    private Animation down;
    private Animation left;
    private boolean isLeft;
    private State stateP;
    private boolean stateR;
    private State lastState;
    private float stateTimer;
    private Animation lastAnim;

    public Bomber(World world, PlayScreen screen) {
        super(screen.getAtlas().findRegion("player1"));
        this.world = world;

        stateP = State.DOWN;
        lastState = State.DOWN;
        stateR = false;
        isLeft = false;
        stateR = false;
        stateTimer = 0;


        //down
        Array<TextureRegion> frames = new Array<TextureRegion>();
        frames.add(new TextureRegion(getTexture(),67,0,16,27));
        frames.add(new TextureRegion(getTexture(),50,0,16,27));
        frames.add(new TextureRegion(getTexture(),86,0,16,27));
        down = new Animation(0.1f,frames);
        frames.clear();

        //left
        frames.add(new TextureRegion(getTexture(),104,0,16,27));
        frames.add(new TextureRegion(getTexture(),122,0,16,27));
        frames.add(new TextureRegion(getTexture(),141,0,16,27));
        left = new Animation(0.1f,frames);
        frames.clear();

        //UP
        frames.add(new TextureRegion(getTexture(),217,0,16,27));
        frames.add(new TextureRegion(getTexture(),235,0,16,27));
        frames.add(new TextureRegion(getTexture(),253,0,16,27));
        up = new Animation(0.1f,frames);
        frames.clear();

        lastAnim = down;


        bomberStand = new TextureRegion(getTexture(),67,0,16,27);
        setBounds(0,0,16,27);
        setRegion(bomberStand);


        BodyDef bDef = new BodyDef();
        bDef.position.set(32, 32);
        bDef.type = BodyDef.BodyType.DynamicBody;

        body = world.createBody(bDef);

        FixtureDef fDef = new FixtureDef();
        CircleShape shape = new CircleShape();
        shape.setRadius(8);

        fDef.shape = shape;

        body.createFixture(fDef);
        create();

        //System.out.println(body.getPosition().x);
        //System.out.println(body.getPosition().y);

        //System.out.println(body.getPosition().x);
    }

    public void update(float dt){
        setPosition(body.getPosition().x-getWidth()/2,body.getPosition().y-getHeight()/2+4);
        setRegion(getFrame(dt));
    }

    public TextureRegion getFrame(float dt){
        stateP = getState();

        if(body.getLinearVelocity().len() != 0){
            stateR = true;
        }
        else
            stateR = false;


        TextureRegion region;
        switch (stateP){
            case UP:
                region = up.getKeyFrame(stateTimer,stateR);
                break;
            case LEFT:
                region = left.getKeyFrame(stateTimer,stateR);
                break;
            case DOWN:
                region = down.getKeyFrame(stateTimer,stateR);
                break;
            default:
            case STOP:
                region = lastAnim.getKeyFrame(stateTimer,stateR);
                break;

        }

        if((body.getLinearVelocity().x > 0 || !isLeft) && !region.isFlipX()){
            region.flip(true,false);
            isLeft = false;
        }
        else if((body.getLinearVelocity().x < 0 || isLeft) && region.isFlipX()){
            region.flip(true,false);
            isLeft = true;
        }

        stateTimer = stateP == lastState ? stateTimer + dt: 0;

        lastState = stateP;

        return region;
    }

    public State getState(){
        if (body.getLinearVelocity().y > 0)
            return State.UP;
        else if (body.getLinearVelocity().y < 0)
            return State.DOWN;
        else if (body.getLinearVelocity().x != 0)
            return State.LEFT;
        else
            return State.STOP;
    }

    public void create() {

    }
}
