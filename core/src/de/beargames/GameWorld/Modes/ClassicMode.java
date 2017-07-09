package de.beargames.GameWorld.Modes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.input.GestureDetector;
import de.beargames.GameWorld.GameWorld;
import de.beargames.Helpers.Constants;
import de.beargames.Helpers.InputHandler;
import de.beargames.gameobjects.Bubble;
import de.beargames.gameobjects.Field;

/**
 * Created by Michi on 11.06.2017.
 */
public class ClassicMode implements GameWorld {

    private Field[][] grid;
    private State state;
    private Bubble dummyBubble;
    private int score;

    public enum State {
        WAITING, MOVING, FALLING;
    }

    public ClassicMode() {
        grid = new Field[Constants.C_BUBBLECOUNT][Constants.C_BUBBLECOUNT];
        this.state = State.WAITING;
        this.score = 0;
        dummyBubble = new Bubble(-Constants.C_BUBBLESIZE*5, -Constants.C_BUBBLESIZE*5);
        Gdx.input.setInputProcessor(new GestureDetector(new InputHandler(this)));

        for(int i=0; i<=Constants.C_BUBBLECOUNT-1; i++) {
            for(int j=0; j<=Constants.C_BUBBLECOUNT-1; j++) {
                grid[i][j] = new Field(Constants.C_SIDEBORDER+i*Constants.C_FIELDSIZE, Constants.C_TOPDOWNBORDER + j*Constants.C_FIELDSIZE, true);
            }
        }
        System.out.println("Hoehe: "+ Gdx.graphics.getHeight()+" Breite: "+Gdx.graphics.getWidth());
        System.out.println("Konstanten: Feld: "+Constants.C_FIELDSIZE+" Seite: "+Constants.C_SIDEBORDER+" Oben: "+Constants.C_TOPDOWNBORDER);
    }

    private void handleScores() {
        for(int i=0; i<Constants.C_BUBBLECOUNT; i++) {
            for(int j=0; j<Constants.C_BUBBLECOUNT; j++) {
                if(grid[i][j].getScore()>0) {
                    this.score += grid[i][j].getScore()*grid[i][j].getScore();
                    grid[i][j].resetScore();
                    grid[i][j].setBubble(dummyBubble);
                    grid[i][j].setFree();
                };
            }
        }
    }

    public int getScore() {
        return this.score;
    }

    @Override
    public void update() {
        handleStates();
        for(int i=0; i<=Constants.C_BUBBLECOUNT-1; i++) {
            for(int j=0; j<=Constants.C_BUBBLECOUNT-1; j++) {
                grid[i][j].getBubble().update();
            }
        }
    }

    public void handleStates() {
        if(state == State.MOVING && !isMoving()) {
            handleScores();
            state = State.FALLING;
            fallDown();
            occupyAll();
        } else if (state == State.FALLING && !isMoving()) {
            state = State.WAITING;
        }
    }

    public void moveRight() {
        if(this.state == State.WAITING) {
            this.state = State.MOVING;
            for (int i = 0; i <= Constants.C_BUBBLECOUNT - 1; i++) {
                for (int j = Constants.C_BUBBLECOUNT - 1; j > 0; j--) {
                    for (int k = j - 1; k >= 0; k--) {
                        if(i ==0) {System.out.println("i: "+i+" j: "+j+" k: "+k);}
                        if (grid[k][i].getBubble().getColorInt() == grid[j][i].getBubble().getColorInt()) {
                            grid[k][i].getBubble().setMoving(1, 0, grid[j][i].getDestination());
                            grid[j][i].increaseScore();
                            grid[k][i].setFree();
                            grid[j][i].setOccupied();
                            if(k==0) {
                                j=0;
                            }
                        } else {
                            j = k + 1;
                            break;
                        }
                    }
                }
            }
        }
    }

    public void moveDown() {
        if(this.state == State.WAITING) {
            this.state = State.MOVING;
            for (int i = 0; i <= Constants.C_BUBBLECOUNT - 1; i++) {
                for (int j = Constants.C_BUBBLECOUNT - 1; j > 0; j--) {
                    for (int k = j - 1; k >= 0; k--) {
                        if(i ==0) {System.out.println("i: "+i+" j: "+j+" k: "+k);}
                        if (grid[i][k].getBubble().getColorInt() == grid[i][j].getBubble().getColorInt()) {
                            grid[i][k].getBubble().setMoving(0, 1, grid[i][j].getDestination());
                            grid[i][j].increaseScore();
                            grid[i][k].setFree();
                            grid[i][j].setOccupied();
                            if(k==0) {
                                j=0;
                            }
                        } else {
                            j = k + 1;
                            break;
                        }
                    }
                }
            }
        }
    }

    public void moveLeft() {
        if(this.state == State.WAITING) {
            this.state = State.MOVING;
            for (int i = 0; i <= Constants.C_BUBBLECOUNT - 1; i++) {
                for (int j = 0; j <= Constants.C_BUBBLECOUNT - 1; j++) {
                    for (int k = j + 1; k <= Constants.C_BUBBLECOUNT - 1; k++) {
                        if (grid[k][i].getBubble().getColorInt() == grid[j][i].getBubble().getColorInt()) {
                            grid[k][i].getBubble().setMoving(-1, 0, grid[j][i].getDestination());
                            grid[j][i].increaseScore();
                            grid[k][i].setFree();
                            grid[j][i].setOccupied();
                            if(k==Constants.C_BUBBLECOUNT-1) {
                                j=Constants.C_BUBBLECOUNT-1;
                            }
                        } else {
                            j = k - 1;
                            break;
                        }
                    }
                }
            }
        }
    }

    public void fallDown() {
        boolean moved = false;
        for (int i = 0; i <= Constants.C_BUBBLECOUNT -1; i++) {
            for (int j = Constants.C_BUBBLECOUNT -1; j >= 0; j--) {
                if(!grid[i][j].isOccupied()) {
                    for (int k = j - 1; k >= 0; k--) {
                        if(grid[i][k].isOccupied()) {
                            grid[i][k].getBubble().setMoving(0, 1, grid[i][j].getDestination());
                            grid[i][j].setBubble(grid[i][k].getBubble());
                            grid[i][k].setFree();
                            grid[i][j].setOccupied();
                            moved = true;
                            break;
                        }
                    }
                    if(!moved) {
                        grid[i][j].setBubble(new Bubble(Constants.C_SIDEBORDER+i*Constants.C_FIELDSIZE, -Constants.C_BUBBLESIZE*2));
                        grid[i][j].getBubble().setMoving(0, 1, grid[i][j].getDestination());
                        grid[i][j].setOccupied();
                    }
                    moved = false;
                }
            }
        }
    }

    @Override
    public Field[][] getGrid() {
        return this.grid;
    }

    public boolean isMoving() {
        for(int i = 0; i<Constants.C_BUBBLECOUNT; i++) {
            for(int j=0; j<Constants.C_BUBBLECOUNT; j++) {
                if(grid[i][j].getBubble().isMoving()) {
                    return true;
                }
            }
        }
        return false;
    }

    public void occupyAll() {
        for(int i = 0; i<Constants.C_BUBBLECOUNT; i++) {
            for(int j=0; j<Constants.C_BUBBLECOUNT; j++) {
                grid[i][j].setOccupied();
            }
        }
    }

}
