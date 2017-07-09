package de.beargames.Helpers;

import com.badlogic.gdx.Gdx;

/**
 * Created by Michi on 12.06.2017.
 */
public class Constants {

    public static final int C_HEIGHT = Gdx.graphics.getHeight();
    public static final int C_WIDTH = Gdx.graphics.getWidth();
    public static final int C_BUBBLECOUNT = 10;
    public static final int C_FIELDSIZE = C_WIDTH/(C_BUBBLECOUNT+1);
    public static final int C_SIDEBORDER = (C_WIDTH - C_FIELDSIZE*C_BUBBLECOUNT)/2;
    public static final int C_TOPDOWNBORDER = (C_HEIGHT - C_FIELDSIZE*C_BUBBLECOUNT)/2;
    public static final int C_BUBBLESIZE = (int) ((C_FIELDSIZE/2)*0.80);
    public static final int C_BUBBLESPEED = 10;
}
