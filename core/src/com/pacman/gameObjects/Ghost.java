package com.pacman.gameObjects;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.pacman.Services.AssetLoader;
import com.pacman.Services.ProgressKeeper;
import com.pacman.Services.SoundService;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by MNA on 3/22/2017.
 */
public class Ghost extends Player {
    private Rectangle ghostRect;  //encapsulate ghost in rectangle for collision detection
    private Animation<TextureRegion> animation;
    private Animation<TextureRegion> edibleAnimation = AssetLoader.edibleGhost;

    private Timer blueMode;
    private Timer blinkingMode;
    private int _isEdible = 0;
    boolean _isEaten = false;

    double restTimer = 5;

    protected char currentDirection = 'e',preDirection;
    private int baseSpeed = 30;

    public Ghost(float x, float y, Animation<TextureRegion> animation)
    {
        super(x,y,0);
        this.animation = animation;
        ghostRect = new Rectangle(x- (boxSize /2f),y + (boxSize /2f),boxSize ,boxSize ); //to encapsulate ghost for collision detection

    }

    public Vector2 getTarget(Pacman pacman) {
        return pacman.getCoord();
    }


    public void update(float delta, Pacman pacman) {

        int speed = (ProgressKeeper.getLevel() < 10)? baseSpeed + ProgressKeeper.getLevel() : 40;

        if (restTimer > 0){ //ghost rests after being eaten
            restTimer -= delta;
            return;
        }

        if(!SoundService.getSirenIsPlaying() && !(_isEaten || _isEdible > 0))
            SoundService.setSirenIsPlaying();//todo make sound shorter


        if(x < 0.8 && direction!='r') //ensure ghost didnt just come through the tunnel
        {
            x= 142;
            return;
        }
        if(x > 142 && direction!='l') {
            x = 0.8f;
            return;
        }


        Vector2 ghostCoord = this.getCoord();
        Vector2 target = getTarget(pacman);

        Vector2 temp, temp2 = this.getCoord();

        float closestPath = Integer.MAX_VALUE;

        int shouldRunMultiplier = (isEdible() && !isEaten())? -1 : 1;


        if (pathIsClear('u') && currentDirection != 'd') {  //ghostCoordst isnt allowed to reverse direction

            temp = ghostCoord.cpy();
            temp = temp.add(0.0f, -speed * delta);  //set temp vector to the position to test
            float distance = temp.dst2(target);  //check distance from that spot to pacman
            if (distance * shouldRunMultiplier < closestPath) {
                temp2 = temp;
                closestPath = distance * shouldRunMultiplier;
                preDirection = 'u';  //keep track of previous direction
            }
        }

        if (pathIsClear('l') && currentDirection != 'r') {
            temp = ghostCoord.cpy();
            temp = temp.add(-speed * delta, 0.0f);
            float distance = temp.dst2(target);
            if (distance * shouldRunMultiplier < closestPath) {
                temp2 = temp;
                closestPath = distance * shouldRunMultiplier;
                preDirection = 'l';
            }
        }
        if (pathIsClear('d') && currentDirection != 'u') {
            temp = ghostCoord.cpy();
            temp = temp.add(0.0f, speed * delta);
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
            x = boxSize *(Math.round(temp2.x/boxSize )); //keep ghost in middle of path
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

        if(_isEaten && Math.abs(startPos.x - x) + Math.abs(startPos.y - y) < 30) {
           resetGhost();
            baseSpeed = 30;
        }
    }

    public void setGhostsEdibleTrue() {
        _isEdible += 1;

        blueMode = new Timer();
        blinkingMode = new Timer();

        //after 10000 seconds we set invincible false
        blueMode.schedule(new TimerTask() {
            @Override
            public void run() {
                if (isEdible())
                    _isEdible -= 1;

                edibleAnimation = AssetLoader.edibleGhost;
                this.cancel();
            }
        },  10000);



        blinkingMode.schedule(new TimerTask() {
            @Override
            public void run() {
                        if(_isEdible==1){  //if another power pellet wasn'blueMode eaten end the effect
                            edibleAnimation = AssetLoader.endingEdibleGhost;
                            this.cancel();
                        }
                        else{
                            edibleAnimation = AssetLoader.edibleGhost;
                        }
                    }
                }, 8000);
    }

    public void setEdibleFalse(){
        _isEdible = 0;
    }

    public boolean isEdible() {
        return _isEdible > 0;
    }

    public boolean isEaten() {
        return _isEaten;
    }

    public void setIsEaten(boolean isEaten) {
        _isEaten = isEaten;
        if (isEaten)
            baseSpeed = 100;
    }

    //get rectangle to check if intersects with pacman's rectangle
    public Rectangle getRect()
    {
        ghostRect.setPosition(x,y);
        return ghostRect;
    }

    public char getDirection(){return currentDirection;}

    public void resetGhost(){}

    public void resetGhost(float x, float y) {
        currentDirection = 'e';
        //cancel timers if they are active so that if another pellet is eaten it'll start its frightened cycle anew
        if(blueMode !=null)
            blueMode.cancel();
        if(blinkingMode !=null)
             blinkingMode.cancel();

        this.x = x;
        this.y = y;
        restTimer = 3;
        _isEaten = false;
        _isEdible = 0;
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