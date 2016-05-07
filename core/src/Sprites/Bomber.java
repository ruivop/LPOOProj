package Sprites;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.ChainShape;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.Shape;
import com.badlogic.gdx.physics.box2d.World;

/**
 * Created by Asus on 07/05/2016.
 */
public class Bomber extends Sprite{

    public World world;
    public Body body;

    public Bomber(World world){
        this.world = world;

        BodyDef bDef = new BodyDef();
        bDef.position.set(32,32);
        bDef.type = BodyDef.BodyType.DynamicBody;

        body = world.createBody(bDef);

        FixtureDef fDef = new FixtureDef();
        CircleShape shape = new CircleShape();
        shape.setRadius(8);

        fDef.shape = shape;

        body.createFixture(fDef);

        //System.out.println(body.getPosition().x);
        //System.out.println(body.getPosition().y);

        //System.out.println(body.getPosition().x);
    }

}
