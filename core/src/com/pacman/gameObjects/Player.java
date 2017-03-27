package com.pacman.gameObjects;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.pacman.Services.AssetLoader;
import com.pacman.Services.SoundService;

import static com.pacman.Services.AssetLoader.redGhost;

/**
 * Created by YonahKarp on 3/6/17.
 */
public class Player {
    float x;
    float y;

    float rotation;
    char direction = ' ';
    private Rectangle playerRect;  //to encapsulate pacman for collision detection


    public Player(){}

    public Player(float x, float y, float rotation){
        this.x = x;
        this.y = y;
        this.rotation = rotation;

        playerRect= new Rectangle(x,y,7,7);
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
        //board bounds
        if(x < 0) {
            x= 142;
            return true;
            //direction = ' '; tunneling
        }
        if(x > 142) {
            x = 0;
            return true;
            //direction = ' ';
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
            case '.':
            case 'o':
                if(this instanceof Pacman) {
                    Map.currMap.setCharAt(mapX + mapY, ' ');
                    if(!SoundService.getNomnomIsPlaying()){
                        SoundService.setNomnomIsPlaying();
                    }

                    Map.textureMap[mapY / 28][mapX] = AssetLoader.mazeTiles[2][13]; //set tile empty on eat
                }
                //todo add invinsability
            case ' ':
                return true;
            default:
                return false;
        }
    }


        //get rectangle to check if intersects with ghost's rectangle
        public Rectangle getRect()
        {
            playerRect.setCenter(x,y);
            return  playerRect;
        }






}
