package de.beargames.GameWorld;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector3;
import de.beargames.GameWorld.GameWorld;
import de.beargames.Helpers.Constants;

/**
 * Created by Michi on 10.06.2017.
 */
public class GameRenderer {

    private GameWorld world;
    private OrthographicCamera cam;
    private ShapeRenderer renderer;
    private SpriteBatch spriteBatch;
    private BitmapFont font;

    public GameRenderer(GameWorld world) {

        this.world = world;

        cam = new OrthographicCamera();
        cam.setToOrtho(true, Constants.C_WIDTH, Constants.C_HEIGHT);
        //cam.translate(Constants.C_WIDTH/2, Constants.C_HEIGHT/2);
        cam.update();
        renderer = new ShapeRenderer();
        renderer.setProjectionMatrix(cam.combined);
        renderer.setAutoShapeType(true);

        spriteBatch = new SpriteBatch();
        font = new BitmapFont();
        font.setColor(Color.RED);
        font.getData().setScale(5);
    }

    public void render() {
        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        renderer.begin(ShapeRenderer.ShapeType.Filled);
        for(int i=0; i<=9; i++) {
            for(int j=0; j<=9; j++) {
                world.getGrid()[i][j].render(renderer);
                world.getGrid()[i][j].getBubble().renderShape(renderer);
            }
        }
        renderer.end();

        spriteBatch.begin();
        font.draw(spriteBatch, Integer.toString(this.world.getScore()), (float) Gdx.graphics.getWidth()/2, (float) Gdx.graphics.getHeight());
        spriteBatch.end();
    }


}
