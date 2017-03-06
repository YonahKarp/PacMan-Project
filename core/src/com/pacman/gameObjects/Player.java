package com.pacman.gameObjects;

/**
 * Created by YonahKarp on 3/6/17.
 */
public class Player {
    private float x;
    private float y;

    private double rotation;

    public Player(){}

    public Player(float x, float y, double rotation){
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

    public double getRotation() {
        return rotation;
    }
}
