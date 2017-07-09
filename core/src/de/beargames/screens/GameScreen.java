package de.beargames.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;
import de.beargames.GameWorld.GameRenderer;
import de.beargames.GameWorld.GameWorld;
import de.beargames.GameWorld.Modes.ClassicMode;

/**
 * Created by Michi on 12.06.2017.
 */
public class GameScreen implements Screen {

    private GameWorld world;
    private GameRenderer renderer;

    public GameScreen(Game game) {
        world = new ClassicMode();
        renderer = new GameRenderer(world);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        world.update();
        renderer.render();
    }

    @Override
    public void resize(int width, int height) {

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
