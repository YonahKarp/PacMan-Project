package com.pacman.gameObjects;

/**
 * Created by YonahKarp on 3/6/17.
 */
public class Player {
    float x;
    float y;

    float rotation;

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

    public boolean pathIsClear(){return true;}
}
