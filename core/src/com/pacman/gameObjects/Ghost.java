package com.pacman.gameObjects;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Plane;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.pacman.Services.AssetLoader;
import org.omg.CORBA.Environment;

import java.util.Random;

/**
 * Created by MNA on 3/22/2017.
 */
public class Ghost extends Player {
    private Random rand = new Random();
    private Rectangle ghostRect;  //encapsulate ghost in rectangle for collision detection
    private Animation<TextureRegion> animation; //todo split ghosts into different classes with different AIs
    private Animation<TextureRegion> edibleAnimation = AssetLoader.edibleGhost;

    private int currMove = 3; //start by moving up
    private char currentDirection = 'd',preDirection;
    private int speed = 38;

    public Ghost(float x, float y, Animation<TextureRegion> animation)
    {
        super(x,y,0);
        this.animation = animation;
        ghostRect = new Rectangle(x,y,7,7); //to encapsulate ghost for collision detection
    }

    public void update(float delta, Pacman pacman) {
        switch(currMove){
            case 0:
                if (pathIsClear('r')) {
                    x += speed * delta;
                    return;
                }
                break;
            case 1:
                if(pathIsClear('l')) {
                    x -= speed * delta;
                    return;
                }
                break;
            case 2:
                if (pathIsClear('d')) {
                    y += speed * delta;
                    return;
                }
                break;
            case 3:
                if(pathIsClear('u')) {
                    y -= speed * delta;
                    return;
                }
        }
        //if path was blocked, choose new direction
        currMove = rand.nextInt(4);
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
        ghostRect.setPosition(x,y);
        return ghostRect;
    }
    public void resetGhost()
    {
        x=72;
        y=95;
    }

    public Animation<TextureRegion> getAnimation() {
        return animation;
    }

    public Animation<TextureRegion> getEdibleAnimation() {
        return edibleAnimation;
    }
}
