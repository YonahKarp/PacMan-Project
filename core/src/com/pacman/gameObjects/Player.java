package com.pacman.gameObjects;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.pacman.Services.AssetLoader;

import static com.pacman.Services.AssetLoader.redGhost;

/**
 * Created by YonahKarp on 3/6/17.
 */
public class Player {
    float x;
    float y;

    float rotation;
    boolean isDead;
    char direction = ' ';
    Rectangle playerRect;  //to encapsulate pacman for collision detection


    public Player(){}

    public Player(float x, float y, float rotation){
        this.x = x;
        this.y = y;
        this.rotation = rotation;

        playerRect= new Rectangle(x,y,10,10);
        isDead= false;

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

    public boolean pathIsClear(char newDirection){



        //board bounds
        if(x < -7) {
            x= 150;
            //direction = ' '; tunneling
        }
        if(x > 150) {
            x = 0;
            //direction = ' ';
        }

        float boxSize = 5.35f;
        int vertOffset =20;
        int charRows = 28;

        double adjustX = 0;
        double adjustY = 0;

        switch (newDirection){
            case 'l':
                adjustX = -0.5;
                break;
            case 'u':
                adjustY = -0.5; //Y down
                break;
            case 'r':
                adjustX = 0.7;
                break;
            case 'd':
                adjustY = 0.7;
                break;
            default:
                break;
        }

        int mapX = (int)Math.round(x/boxSize + adjustX);
        int mapY = (int)(charRows*Math.round((y - vertOffset)/boxSize + adjustY));


        //System.out.println(Map.mapString.charAt(mapX+mapY));


        switch (Map.currMap.charAt(mapX+mapY)) {

            case '.':
            case 'o':
                Map.currMap.setCharAt(mapX+mapY, ' ');
                Map.textureMap[mapY / 28][mapX] = AssetLoader.mazeTiles[2][13];
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

        //not used for now
        public void dyingPacman(float x, float y)
        {
            this.x=x;
            this.y=y;
            isDead=true;
        }

        public void resetPacman() {
            x=100;
            y=100;
        }

        public void setDead(boolean status)
        {
            isDead=status;
        }
}
