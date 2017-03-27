package com.pacman.gameObjects;

import com.badlogic.gdx.math.Rectangle;

/**
 * Created by YonahKarp on 3/6/17.
 */
public class Pacman extends Player
{
    public Pacman() {}

    public Pacman(float x, float y, int speed, float rotation, Map map)
    {
        super(x, y, speed, rotation, map);
    }

    public void update(float delta)
    {
        //continue along his way

        //prevent pacman from going out of screen view (moved to pathIsClear method)

        float[] position = this.getNewPositionFromDirection(super.direction, super.x, super.y, super.speed, delta);

        if (super.pathIsClear(position)) {
            this.x = position[0];
            this.y = position[1];
        }
    }

    private int getRotation(char direction)
    {
        int rotation = -1;
        switch (direction) {
            case 'l':
<<<<<<< HEAD
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
=======
                rotation = 180;
                break;
            case 'u':
                rotation = 270;
                break;
            case 'r':
                rotation = 0;
                break;
            case 'd':
                rotation = 90;
>>>>>>> a3a9a869cc5d1d0206579e492893077f66d08991
                break;
        }
        return rotation;
    }

    public void move(char direction)
    {
        System.out.println("move");
        System.err.println("pac     " + getRect().getX() + " " + getRect().getY());
        this.direction = direction;
        this.rotation = this.getRotation(direction);
    }

    private float[] getNewPositionFromDirection(char direction, float x, float y, int speed, float delta)
    {
        float[] position = {x,y};

        switch (direction){
            case 'l':
<<<<<<< HEAD
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
=======
                position[0] = x - speed*delta; // distance = speed * time
                break;
            case 'u':
                position[1] = y - speed*delta;
                break;
            case 'r':
                position[0] = x + speed*delta;
                break;
            case 'd':
                position[1] = y + speed*delta;
                break;
            default:
>>>>>>> a3a9a869cc5d1d0206579e492893077f66d08991
                break;
        }

        return position;
    }
}
