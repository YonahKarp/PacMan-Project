package com.pacman.gameObjects;

/**
 * Created by YonahKarp on 3/6/17.
 */
public class Player {
    float x;
    float y;

    float rotation;

    char direction = ' ';

    public Player(){}

    public Player(float x, float y, float rotation){
        this.x = x;
        this.y = y;
        this.rotation = rotation;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public float getRotation() {
        return rotation;
    }

    public boolean pathIsClear(){

        //board bounds
        if(x < -7) {
            x= 150;
            //direction = ' '; tunneling
            return false;
        }
        if(x > 150) {
            x = 0;
            //direction = ' ';
            return false;
        }

        if(y<23) {
            y = 23;
            direction = ' ';
            return false;
        }
        if(y>176) {
            y = 176;
            direction = ' ';
            return false;
        }


        return true;}
}
