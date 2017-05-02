package com.pacman.gameObjects;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Plane;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.pacman.Services.AssetLoader;
import com.pacman.Services.SoundService;
import org.omg.CORBA.Environment;

import java.util.Date;
import java.util.Random;

/**
 * Created by MNA on 3/22/2017.
 */
public class Ghost extends Player {
    private Random rand = new Random();
    private Rectangle ghostRect;  //encapsulate ghost in rectangle for collision detection
    private Animation<TextureRegion> animation;
    private Animation<TextureRegion> edibleAnimation = AssetLoader.edibleGhost;

    private int currMove = 3; //start by moving up

    double restTimer = 5;


    public Ghost(float x, float y, Animation<TextureRegion> animation)
    {
        super(x,y,0);
        this.animation = animation;
        ghostRect = new Rectangle(x- (boxSize /2f),y + (boxSize /2f),boxSize ,boxSize ); //to encapsulate ghost for collision detection

    }

    public Vector2 getTarget(Pacman pacman)
    {
        return pacman.getCoord();
    }

    protected char currentDirection = 'e',preDirection;
    private int speed = 30;


    public void update(float delta, Pacman pacman) {

        if (restTimer > 0){ //ghost rests after being eaten
            restTimer -= delta;
            return;
        }

        if(!SoundService.getSirenIsPlaying())
            SoundService.setSirenIsPlaying();



        //fixme: what does this part do? the x here is the ghost's xCoord, not pacman's.
        if(x < 0.8 && direction!='r') //ensure pacman didnt just come through the tunnel
        {
            x= 142;
            return;
        }
        if(x > 142 && direction!='l') {
            x = 0;
            return;
        }


        Vector2 ghostCoord = this.getCoord();
        Vector2 target = getTarget(pacman);

        Vector2 temp, temp2 = this.getCoord();

        float closestPath = Integer.MAX_VALUE;

        int shouldRunMultiplier = (pacman.isInvincible())? -1 : 1;


//        boolean atTurningPoint;
//        if(currentDirection == 'l' || currentDirection == 'r')
//            atTurningPoint = y*100 % 535f < 85 || y*100 % 535f > 450; //modulo doesn't work well with decimal numbers, so multiplying out decimals solves issue
//        else
//            atTurningPoint = x*100 % 535f < 85|| x*100 % 535f > 450;
//
//        if (atTurningPoint) {

        if (pathIsClear('u') && currentDirection != 'd') {  //ghostCoordst isnt allowed to reverse direction

            temp = ghostCoord.cpy();
            temp = temp.add(0.0f, (-speed * delta));  //set temp vector to the position to test
            float distance = temp.dst2(target);  //check distance from that spot to pacman
            if (distance * shouldRunMultiplier < closestPath) {
                temp2 = temp;
                closestPath = distance * shouldRunMultiplier;
                preDirection = 'u';  //keep track of previous direction
            }
        }

        if (pathIsClear('l') && currentDirection != 'r') {
            temp = ghostCoord.cpy();
            temp = temp.add((-speed * delta), 0.0f);
            float distance = temp.dst2(target);
            if (distance * shouldRunMultiplier < closestPath) {
                temp2 = temp;
                closestPath = distance * shouldRunMultiplier;
                preDirection = 'l';
            }
        }
        if (pathIsClear('d') && currentDirection != 'u') {
            temp = ghostCoord.cpy();
            temp = temp.add(0.0f, (speed * delta));
            float distance = temp.dst2(target);
            if (distance * shouldRunMultiplier < closestPath) {
                temp2 = temp;
                closestPath = distance * shouldRunMultiplier;
                preDirection = 'd';
            }
        }

        if (pathIsClear('r') && currentDirection != 'l') {
            temp = ghostCoord.cpy();
            temp = temp.add(speed * delta, 0.0f);
            float distance = temp.dst2(target);
            if (distance * shouldRunMultiplier < closestPath) {
                temp2 = temp;
                closestPath = distance;
                preDirection = 'r';
            }
        }


        if((currentDirection == 'r' || currentDirection == 'l') && (preDirection == 'u' || preDirection == 'd')){
            x = boxSize *(Math.round(temp2.x/boxSize ));
            y = temp2.y;
        }
        else if((currentDirection == 'u' || currentDirection == 'd') && (preDirection == 'r' || preDirection == 'l')){
            x = temp2.x;
            y = boxSize *(Math.round(temp2.y/boxSize ));
        }else {
            x = temp2.x;
            y = temp2.y;
        }

        currentDirection = preDirection;

        //System.err.println(temp2.x+" "+temp2.y+" pac: "+pacman.getX()+","+pacman.getY());
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

    public char getDirection(){return currentDirection;}

    public void resetGhost(){}

    public void resetGhost(int x, int y) {
        currentDirection = 'e';

        this.x = x;
        this.y = y;
        restTimer = 5;
    }

    public Animation<TextureRegion> getAnimation() {
        return animation;
    }

    public Animation<TextureRegion> getEdibleAnimation() {
        return edibleAnimation;
    }

    public void setRestTimer(double restTimer) {
        this.restTimer = restTimer;
    }
}
