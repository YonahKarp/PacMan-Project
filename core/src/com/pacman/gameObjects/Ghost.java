package com.pacman.gameObjects;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Plane;
import com.badlogic.gdx.math.Rectangle;
import com.pacman.Services.AssetLoader;

import java.util.Random;

/**
 * Created by MNA on 3/22/2017.
 */
public class Ghost extends Player {
    private Random rand = new Random();
    private int currMove = 3; //start by moving up

    private Rectangle redG;  //encapsulate ghost in rectangle for collision detection
    //float x,y;

    private int speed = 38;

    private Animation<TextureRegion> animation; //todo split ghosts into different classes with different AIs
    private Animation<TextureRegion> edibleAnimation = AssetLoader.edibleGhost;

    public Ghost(float x, float y, Animation<TextureRegion> animation)
    {
        super(x,y,0);

        this.animation = animation;
        redG = new Rectangle(x,y,7,7); //to encapsulate ghost for collision detection

    }
    public void update(float delta) {
        //rand = new Random();
        //System.err.println(getRect().getX()+" "+getRect().getY());


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
        redG.setPosition(x,y);
        return redG;
    }
    public void resetGhost()
    {
        x=70;
        y=90;
    }

    public Animation<TextureRegion> getAnimation() {
        return animation;
    }

    public Animation<TextureRegion> getEdibleAnimation() {
        return edibleAnimation;
    }
}
