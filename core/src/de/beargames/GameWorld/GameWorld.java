package de.beargames.GameWorld;

import de.beargames.gameobjects.Field;

/**
 * Created by Michi on 10.06.2017.
 */
public interface GameWorld {

    public void update();
    public Field[][] getGrid();

    void moveRight();

    void moveLeft();

    void moveDown();

    int getScore();
}
