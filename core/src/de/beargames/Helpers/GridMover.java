package de.beargames.Helpers;

import com.badlogic.gdx.math.Vector2;
import de.beargames.gameobjects.Field;

/**
 * Created by Michi on 13.06.2017.
 */
public class GridMover {

    public GridMover() {
    }

    public void moveRight(Field[][] grid) {

        for(int i=0; i<=Constants.C_BUBBLECOUNT; i++) {
            for(int j=Constants.C_BUBBLECOUNT-1; j==1; j--) {
                for(int k=j-1; k==0; k--) {
                    if(grid[k][i].getBubble().getColorInt() == grid[j][i].getBubble().getColorInt()) {
                        grid[k][i].getBubble().setMoving(1, 0, grid[j][i].getDestination());
                    } else {
                        j=k+1;
                        break;
                    }
                }
            }
        }
    }
}
