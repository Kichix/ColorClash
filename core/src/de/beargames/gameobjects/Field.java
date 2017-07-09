package de.beargames.gameobjects;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import de.beargames.Helpers.Constants;

/**
 * Created by Michi on 12.06.2017.
 */
public class Field {

    private Bubble bubble;
    private Vector2 pos;
    private boolean occupied;
    private int score;

    public Field(int x, int y, boolean occu) {

        this.pos = new Vector2(x, y);
        this.occupied = occu;
        this.bubble = new Bubble(x, y);
        this.score = 0;
    }

    public Bubble getBubble() {
        return this.bubble;
    }

    public Vector2 getPos() {
        return this.pos;
    }

    public void render(ShapeRenderer renderer) {

    }

    public void setFree() {
        this.occupied = false;
    }

    public void setOccupied() {
        this.occupied = true;
    }

    public void increaseScore() {
        this.score += 1;
    }

    public int getScore() {
        return this.score;
    }

    public void resetScore() {
        this.score = 0;
    }

    public void setBubble(Bubble bubble) {
        this.bubble = bubble;
    }

    public Vector2 getDestination() {
        return new Vector2(this.pos.x+Constants.C_FIELDSIZE/2, this.pos.y+Constants.C_FIELDSIZE/2);
    }

    public boolean isOccupied() {
        return this.occupied;
    }
    }
