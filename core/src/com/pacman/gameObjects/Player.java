package com.pacman.gameObjects;
import com.badlogic.gdx.math.*;
import com.pacman.Services.AssetLoader;
import com.pacman.Services.SoundService;

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
                }
            case '.':
                if(this instanceof Pacman) {
                    Map.currMap.setCharAt(mapX + mapY, ' ');
                    if(!SoundService.getNomnomIsPlaying())
                        SoundService.setNomnomIsPlaying();

                    Map.textureMap[mapY / 28][mapX] = AssetLoader.mazeTiles[2][13]; //set tile empty on eat
                }
                //todo add invinsability
            case ' ':
                //for debugging:
                //System.err.println(newDirection+": x:"+mapX+" y:"+mapY+" clear");
                return true;
            case '&':  //dont allow back into ghosthouse
                if(newDirection=='u'){
                    return true;}
                else
                    return false;
            case '!':  //send out of ghost house
                if(newDirection=='l'||newDirection=='r'||newDirection=='d')
                    return false;
                else
                    return true;
            default:
                //for debugging:
                //System.err.println(newDirection+": x:"+mapX+" y:"+mapY+" blocked");
                return false;
        }
    }


        public Vector2 getCoord()
        {
            return playerLocation.set(x,y);
        }




}
