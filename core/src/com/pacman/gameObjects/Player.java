package com.pacman.gameObjects;
import com.badlogic.gdx.math.*;
import com.pacman.Services.AssetLoader;
import com.pacman.Services.ProgressKeeper;
import com.pacman.Services.SoundService;

import java.math.BigDecimal;

import static com.pacman.Services.AssetLoader.redGhost;

/**
 * Created by YonahKarp on 3/6/17.
 */
public class Player {
    Vector2 startPos;


    float x;
    float y;
    Vector2 playerLocation;
    float rotation;
    char direction = ' ';

    float boxSize = 5.35f;



    public Player(){}

    public Player(float x, float y, float rotation){


        this.x = x;
        this.y = y;

        startPos = new Vector2(x, y);

        //startingX = (int)x;
        //startingY = (int)y;

        this.rotation = rotation;
        playerLocation = new Vector2(x,y);

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

    public void setRotation(float rotation) {
        this.rotation = rotation;
    }

    public boolean pathIsClear(char newDirection) {

     //tunneling
        if(x < 0.8 && direction!='r') //ensure pacman didnt just come through the tunnel
        {
            x= 142;
            return true;
        }
        if(x > 142 && direction!='l') {
            x = 0;
            return true;
        }

        float boxSize = 5.35f;

        float vertOffset =21.4f;



        int charRows = 28;

        double adjustX = (newDirection == 'l')? -0.65 :
                (newDirection == 'r')? 0.65 : 0 ;

        double adjustY = (newDirection == 'u')? -0.65 :
                (newDirection == 'd')? 0.65 : 0 ;


        int mapX = (int)Math.round(x/boxSize + adjustX);
        int mapY = (int)(charRows*Math.round((y - vertOffset)/boxSize + adjustY));


        switch (Map.currMap.charAt(mapX+mapY)) {
            case 'o':
            case '.':
            case ' ':
            case '$':
                //keep pacman / ghosts in middle
                if(newDirection == 'l' || newDirection == 'r')
                    return y*100 % 535f < 85 || y*100 % 535f > 450; //modulo doesn't work well with decimal numbers, so multiplying out decimals solves issue
                else
                    return x*100 % 535f < 85|| x*100 % 535f > 450;

            case '&':  //dont allow back into ghosthouse

                //for returning purposes
                if (this instanceof  Ghost)
                    if(((Ghost)this).isEaten())
                        return true;

                return  newDirection == 'u';
            case '>':  //send out of ghost house
                if (this instanceof  Ghost)
                    if(((Ghost)this).isEaten())
                        return true;
                return !(newDirection == 'l' || newDirection == 'd');
            case '<':  //send out of ghost house
                if (this instanceof  Ghost)
                    if(((Ghost)this).isEaten())
                        return true;
                return !(newDirection == 'r' || newDirection == 'd');
            default:
                return false;
        }
    }

        public Vector2 getCoord()
        {
            return playerLocation.set(x,y);
        }
}
