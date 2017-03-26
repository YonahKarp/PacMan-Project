package com.pacman.gameObjects;

import com.badlogic.gdx.math.Rectangle;

import java.util.Random;

/**
 * Created by MNA on 3/22/2017.
 */
public class RedGhost {
    Random rand;
    Rectangle redG;  //encapsulate ghost in rectangle for collision detection
    float x,y;
    public RedGhost(float x, float y)
    {
        redG = new Rectangle(); //to encapsulate ghost for collision detection
        redG.setSize(10,11);
        this.x=x;
        this.y=y;
    }
    public void update(float delta) {
        rand = new Random();
        //System.err.println(getRect().getX()+" "+getRect().getY());
        int move = rand.nextInt(4);
        switch(move){
            case 0:
                x+=32*delta;
                break;
            case 1:
                x-=32*delta;
                break;
            case 2:
                y+=32*delta;
                break;
            case 3:
                y-=32*delta;
                break;
        }


    }
    public float getX()
    {
        return x;
    }
    public float getY()
    {
        return y;
    }

    //get rectangle to check if intersects with pacman's rectangle
    public Rectangle getRect()
    {
        redG.setPosition(x,y);
        return redG;
    }
    public void resetGhost()
    {
        x=70;
        y=90;
    }
}
