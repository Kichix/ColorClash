package de.beargames.Helpers;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.math.Vector2;
import de.beargames.GameWorld.GameWorld;
import java.math.*;
import de.beargames.GameWorld.Modes.ClassicMode;


/**
 * Created by Michi on 18.06.2017.
 */
public class InputHandler implements GestureDetector.GestureListener {

    private GameWorld myWorld;

    public InputHandler(GameWorld gameWorld) {
        this.myWorld = gameWorld;
    }

    @Override
    public boolean touchDown(float x, float y, int pointer, int button) {
        return false;
    }

    @Override
    public boolean tap(float x, float y, int count, int button) {
        System.out.println("TAP");
        return false;
    }

    @Override
    public boolean longPress(float x, float y) {
        return false;
    }

    @Override
    public boolean fling(float velocityX, float velocityY, int button) {
        return false;
    }

    @Override
    public boolean pan(float x, float y, float deltaX, float deltaY) {
        System.out.println("deltaX: "+deltaX+" deltaY: "+deltaY+" Limit: "+Constants.C_BUBBLESIZE*3);
        if(deltaX > Gdx.graphics.getWidth()/10 && Math.abs(deltaY) <= Gdx.graphics.getHeight()/10) {
            myWorld.moveRight();
        } else if(deltaX < -Gdx.graphics.getWidth()/10 && Math.abs(deltaY) <= Gdx.graphics.getHeight()/10) {
            myWorld.moveLeft();
        } else if(deltaY > Gdx.graphics.getHeight()/20 && Math.abs(deltaX) <= Gdx.graphics.getWidth()/10) {
            myWorld.moveDown();
        }
        return true;
    }

    @Override
    public boolean panStop(float x, float y, int pointer, int button) {
        return false;
    }

    @Override
    public boolean zoom(float initialDistance, float distance) {
        return false;
    }

    @Override
    public boolean pinch(Vector2 initialPointer1, Vector2 initialPointer2, Vector2 pointer1, Vector2 pointer2) {
        return false;
    }

    @Override
    public void pinchStop() {

    }

}
