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
                if(this instanceof Pacman) {
                    ((Pacman) (this)).setInvincibleTrue();
                    AssetLoader.powerPacman.play();
                    ProgressKeeper.addToScore(50);
                }
            case '.':
                if(this instanceof Pacman) {
                    Map.currMap.setCharAt(mapX + mapY, ' ');

                    if(!SoundService.getKaIsPlaying())
                        SoundService.setKaIsPlaying();
                    else
                        AssetLoader.wa.play();

                    Map.textureMap[mapY / 28][mapX] = AssetLoader.mazeTiles[2][13]; //set tile empty on eat
                    ProgressKeeper.addToScore(10);
                }
            case ' ':

                //keep pacman / ghosts in middle
                if(newDirection == 'l' || newDirection == 'r')
                    return y*100 % 535f < 85 || y*100 % 535f > 450; //modulo doesn't work well with decimal numbers, so multiplying out decimals solves issue
                else
                    return x*100 % 535f < 85|| x*100 % 535f > 450;

            case '&':  //dont allow back into ghosthouse
                return newDirection == 'u';
            case '!':  //send out of ghost house
                return !(newDirection == 'l' || newDirection == 'r' || newDirection == 'd');
            case '>':  //send out of ghost house
                return !(newDirection == 'l' || newDirection == 'd');
            case '<':  //send out of ghost house
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
