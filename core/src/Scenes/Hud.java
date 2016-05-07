package Scenes;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.lpooproj.game.Proj;

/**
 * Created by Asus on 07/05/2016.
 */
public class Hud {

    private Viewport viewport;
    public Stage stage;

    private int numLives;
    private int score;

    Label laLives;
    Label laScore;

    public Hud(){
        numLives = 3;
        score = 0;

        viewport = new StretchViewport(Proj.V_WIDTH,Proj.V_HEIGHT,new OrthographicCamera());

        Table table = new Table();
        table.top();
        table.setFillParent(true);
        stage = new Stage();

        laLives = new Label(String.format("Number of lifes: %d", numLives),new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        laScore = new Label(String.format("Score: %d", score),new Label.LabelStyle(new BitmapFont(), Color.WHITE));

        table.add(laLives).expandX().padTop(10);
        table.row();
        table.add(laScore);

        stage.addActor(table);
    }


}
