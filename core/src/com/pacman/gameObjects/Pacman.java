package com.pacman.gameObjects;

/**
 * Created by YonahKarp on 3/6/17.
 */
public class Pacman extends Player {

    char direction = ' ';

    public Pacman(){}

    public Pacman(float x, float y, float rotation){
        super(x,y,rotation);

    }

    public void update(float delta) {
        //continue along his way
        //prevent pacman from going out of screen view
        if(x<0)
            x=0;
        if(x>136)
            x=136;
        if(y<0)
            y=1;
        if(y>186)
            y=186;
        switch (direction){
            case 'l':
                if(pathIsClear())
                    x -= 40*delta;
                break;
            case 'u':
                if(pathIsClear())
                    y -= 40*delta;
                break;
            case 'r':
                if(pathIsClear())
                    x += 40*delta;
                break;
            case 'd':
                if(pathIsClear())
                    y += 40*delta;
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
