package com.pacman.gameObjects;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Rectangle;

/**
 * Created by YonahKarp on 3/6/17.
 */
public class Pacman extends Player
{

    private boolean isDead = false;
    private boolean _isInvincible = false;
    private char currentDirection = ' ';
    private int speed = 40;
    private Rectangle playerRect;  //to encapsulate pacman for collision detection

    public Pacman(){}

    public Pacman(float x, float y, float rotation){
        super(x,y,rotation);
        playerRect= new Rectangle(x,y,7,7);
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
        //System.err.println("pac "+getRect().getX()+" "+getRect().getY());
        currentDirection = direction;
        switch (direction) {
            case 'l':
                if (pathIsClear('l')) { //prevent direction change if blocked (like classic game)
                    this.direction = direction;

                    y = 5.35f*(Math.round(y/5.35)); //todo fix arbitraries
                    rotation = 180;
                }
                break;
            case 'u':
                if (pathIsClear('u')) {
                    this.direction = direction;
                    x = 5.35f*(Math.round(x/5.35)); //adjust pacman so he's in middle (should find out a way to get rid of arbitrary number )
                    rotation = 270;
                }
                break;
            case 'r':
                if (pathIsClear('r')) {
                    this.direction = direction;
                    y = 5.35f*(Math.round(y/5.35)); //todo fix arbitraries
                    rotation = 0;
                }
                break;
            case 'd':
                if (pathIsClear('d')) {
                    this.direction = direction;
                    x = 5.35f*(Math.round(x/5.35));
                    rotation = 90;
                }
                break;
        }
    }

    public void resetPacman() {
        x=75;
        y=144;
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

    public void setInvincibleTrue() {
        _isInvincible = true;

        //after 10000 seconds we set invincible false
        new java.util.Timer().schedule(
                new java.util.TimerTask() {
                    @Override
                    public void run() {
                        _isInvincible = false;
                        this.cancel();
                    }
                }, 10000);
    }

    public boolean isInvincible() {
        return _isInvincible;
    }

    public char getDirection(){return currentDirection;}

    //get rectangle to check if intersects with ghost's rectangle
    public Rectangle getRect()
    {
        playerRect.setPosition(x,y);
        return  playerRect;
    }
}
