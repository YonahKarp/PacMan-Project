package com.pacman.gameObjects;

import com.badlogic.gdx.math.Rectangle;

/**
 * Created by YonahKarp on 3/6/17.
 */
public class Pacman extends Player
{

    private boolean isDead = false;
    private int speed = 32;


    public Pacman(){}

    public Pacman(float x, float y, float rotation){
        super(x,y,rotation);

    }

    public void update(float delta) {
        //continue along his way

        //prevent pacman from going out of screen view (moved to pathIsClear method)



        switch (direction){
            case 'l':
                if(pathIsClear(direction))
                    x -= speed*delta; // distance = speed * time
                break;
            case 'u':
                if(pathIsClear(direction))
                    y -= speed*delta;
                break;
            case 'r':
                if(pathIsClear(direction))
                    x += speed*delta;
                break;
            case 'd':
                if(pathIsClear(direction))
                    y += speed*delta;
                break;
            default:
                break;
        }
    }

    public void move(char direction) {
        //System.out.println("move");
        //System.err.println("pac "+getRect().getX()+" "+getRect().getY());
        switch (direction) {
            case 'l':
                if (pathIsClear('l')) { //prevent direction change if blocked (like classic game)
                    this.direction = direction;
                    y = 5.315f*(Math.round(y/5.315)); //todo fix arbitraries
                    rotation = 180;
                }
                break;
            case 'u':
                if (pathIsClear('u')) {
                    this.direction = direction;
                    x = 5.3f*(Math.round(x/5.3)); //adjust pacman so he's in middle (should find out a way to get rid of arbitrary number )
                    rotation = 270;
                }
                break;
            case 'r':
                if (pathIsClear('r')) {
                    this.direction = direction;
                    rotation = 0;
                }
                break;
            case 'd':
                if (pathIsClear('d')) {
                    this.direction = direction;
                    x = 5.3f*(Math.round(x/5.3));
                    rotation = 90;
                }
                break;
        }
    }

    public void resetPacman() {
        x=75;
        y=142;
        direction = ' ';
    }

    public void setDead(boolean status)
    {
        isDead=status;
    }

    //not used for now
    public void dyingPacman(float x, float y)
    {
        this.x=x;
        this.y=y;
        isDead=true;
    }

}
