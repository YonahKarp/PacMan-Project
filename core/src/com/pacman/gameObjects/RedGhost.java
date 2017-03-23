package com.pacman.gameObjects;

import java.util.Random;

/**
 * Created by MNA on 3/22/2017.
 */
public class RedGhost {

    float x,y;
    public RedGhost(float x, float y)
    {
        this.x=x;
        this.y=y;
    }
    public void update(float delta) {
        Random rand = new Random();
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
}
