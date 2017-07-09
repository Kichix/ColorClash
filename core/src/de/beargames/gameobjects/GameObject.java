package de.beargames.gameobjects;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

/**
 * Created by Michi on 10.06.2017.
 */
public interface GameObject {

    public void renderShape(ShapeRenderer renderer);
    public void renderPic();
    public void update();

}
