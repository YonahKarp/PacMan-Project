package com.pacman.gameObjects;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Rectangle;
import com.pacman.Services.ProgressKeeper;

/**
 * Created by YonahKarp on 3/6/17.
 */
public class Pacman extends Player
{

    private boolean isDead = false;
    private int _isInvincible = 0;
    private char queuedDirection = ' ';
    private int speed = 40;
    private Rectangle playerRect;  //to encapsulate pacman for collision detection

    public Pacman(){}

    public Pacman(float x, float y, float rotation){
        super(x,y,rotation);
        playerRect= new Rectangle(x - (boxSize/2f),y + (boxSize/2f),boxSize,boxSize);
    }

    public void update(float delta) {
        //continue along his way

        //added for easier input
        if(queuedDirection != ' ' && pathIsClear(queuedDirection)){
            move(queuedDirection);
            queuedDirection = ' ';
        }

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
        queuedDirection = ' ';

        switch (direction) {
            case 'l':
                if (pathIsClear('l')) { //prevent direction change if blocked (like classic game)
                    this.direction = direction;

                    //y = boxSize*(Math.round(y/5.35)); //todo fix arbitraries
                    rotation = 180;
                }else
                    queuedDirection = 'l';
                break;
            case 'u':
                if (pathIsClear('u')) {
                    this.direction = direction;
                    //x = boxSize*(Math.round(x/5.35)); //adjust pacman so he's in middle (should find out a way to get rid of arbitrary number )
                    rotation = 270;
                }else
                    queuedDirection = 'u';
                break;
            case 'r':
                if (pathIsClear('r')) {
                    this.direction = direction;
                   // y = boxSize*(Math.round(y/5.35)); //todo fix arbitraries
                    rotation = 0;
                }else
                    queuedDirection = 'r';
                break;
            case 'd':
                if (pathIsClear('d')) {
                    this.direction = direction;
                    //x = boxSize*(Math.round(x/5.35));
                    rotation = 90;
                }else
                    queuedDirection = 'd';
                break;
        }
    }

    public void resetPacman() {
        x=75;
        y=144.45f;
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
        _isInvincible += 1;

        //after 10000 seconds we set invincible false
        new java.util.Timer().schedule(
                new java.util.TimerTask() {
                    @Override
                    public void run() {
                        _isInvincible -= 1;
                        ProgressKeeper.resetGhostsEaten();
                        this.cancel();
                    }
                }, 10000);
    }

    public boolean isInvincible() {
        return _isInvincible > 0;
    }

    //get rectangle to check if intersects with ghost's rectangle
    public Rectangle getRect()
    {
        playerRect.setPosition(x,y);
        return  playerRect;
    }
}
