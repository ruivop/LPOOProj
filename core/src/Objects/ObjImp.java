package Objects;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

/**
 * Created by Asus on 07/05/2016.
 */
public abstract class ObjImp {

    public ObjImp(MapObject object, World world) {

        Rectangle rectangle = ((RectangleMapObject) object).getRectangle();
        BodyDef bDef = new BodyDef();
        PolygonShape shape = new PolygonShape();
        FixtureDef fDef = new FixtureDef();
        Body body;

        bDef.type = BodyDef.BodyType.StaticBody;
        bDef.position.set(rectangle.getX() + rectangle.getWidth() / 2, rectangle.getY() + rectangle.getHeight() / 2);

        body = world.createBody(bDef);


        shape.setAsBox(rectangle.getWidth() / 2, rectangle.getHeight() / 2);
        fDef.shape = shape;

        body.createFixture(fDef);
    }
}
