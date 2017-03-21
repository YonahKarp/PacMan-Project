package com.pacman.gameObjects;

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
                if(pathIsClear())
                    x -= speed*delta; // distance = speed * time
                break;
            case 'u':
                if(pathIsClear())
                    y -= speed*delta;
                break;
            case 'r':
                if(pathIsClear())
                    x += speed*delta;
                break;
            case 'd':
                if(pathIsClear())
                    y += speed*delta;
                break;
            default:
                break;
        }
    }

    public void move(char direction){
        System.out.println("move");

        switch (direction){
            case 'l':
                this.direction = direction;
                rotation = 180;
                break;
            case 'u':
                this.direction = direction;
                rotation = 270;
                break;
            case 'r':
                this.direction = direction;
                rotation = 0;
                break;
            case 'd':
                this.direction = direction;
                rotation = 90;
                break;
        }
    }
}
