package com.pacman.gameObjects;

import com.badlogic.gdx.math.Rectangle;

/**
 * Created by YonahKarp on 3/6/17.
 */
public class Pacman extends Player {



    public Pacman(){}

    public Pacman(float x, float y, float rotation){
        super(x,y,rotation);

    }

    public void update(float delta) {
        //continue along his way

        //prevent pacman from going out of screen view (moved to pathIsClear method)

        int speed = 32;

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

    public void move(char direction){
        System.out.println("move");
        System.err.println("pac     "+getRect().getX()+" "+getRect().getY());
        switch (direction){
            case 'l':
                if (pathIsClear('l')) { //prevent direction change if blocked (like classic game)
                    this.direction = direction;
                    rotation = 180;
                }
                break;
            case 'u':
                if (pathIsClear('u')) {
                    this.direction = direction;
                    rotation = 270;
                }
                break;
            case 'r':
                if (pathIsClear('r')) {
                    this.direction = direction;
                    rotation = 0;
                }
                break;
            case 'd':
                if (pathIsClear('d')) {
                    this.direction = direction;
                    rotation = 90;
                }
                break;
        }
    }

}
