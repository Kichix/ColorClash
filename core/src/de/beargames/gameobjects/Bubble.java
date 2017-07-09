package de.beargames.gameobjects;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import de.beargames.Helpers.Constants;

import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by Michi on 10.06.2017.
 */
public class Bubble implements GameObject {

    private Vector2 pos;
    private Vector2 speed;
    private Vector3 color;
    private int colorInt;
    private Vector2 destination;

    public Bubble(int x, int y) {

        this.pos = new Vector2(x+Constants.C_FIELDSIZE/2, y+Constants.C_FIELDSIZE/2);
        this.speed = new Vector2(0,0);
        this.colorInt = ThreadLocalRandom.current().nextInt(1, 4);
        this.destination = getCollisionPoint();

        switch (colorInt) {
            case 1: color = new Vector3(1,0,0);
                    break;
            case 2: color = new Vector3(0,1,0);
                    break;
            case 3: color = new Vector3(0,0,1);
                    break;
        }
    }

    @Override
    public void renderShape(ShapeRenderer renderer) {
        renderer.setColor(color.x, color.y, color.z, 1);
        renderer.circle(pos.x, pos.y, Constants.C_BUBBLESIZE);
    }

    @Override
    public void renderPic() {

    }

    @Override
    public void update() {
        checkDestinationReached();
        this.pos.add(speed);
    }

    public void checkDestinationReached() {
        if((speed.x == Constants.C_BUBBLESPEED && this.destination.x < getCollisionPoint().x) || (speed.x == -Constants.C_BUBBLESPEED && this.destination.x > getCollisionPoint().x)) {
            this.speed.x = 0;
        }

        if((speed.y == Constants.C_BUBBLESPEED && this.destination.y <= getCollisionPoint().y) || (speed.y == -Constants.C_BUBBLESPEED && this.destination.y > getCollisionPoint().y)) {
            this.speed.y = 0;
        }


    }

    public Vector2 getCollisionPoint() {
        return new Vector2(this.pos.x, this.pos.y);
    }

    public int getColorInt() {
        return this.colorInt;
    }

    public void setMoving(int x, int y, Vector2 destination) {
        this.speed.x = x*Constants.C_BUBBLESPEED;
        this.speed.y = y*Constants.C_BUBBLESPEED;
        this.destination = destination;
    }

    public boolean isMoving() {
        if(this.speed.x != 0 || this.speed.y != 0) {
            return true;
        }
        return false;
    }
}
