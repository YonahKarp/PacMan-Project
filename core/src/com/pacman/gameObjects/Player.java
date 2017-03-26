package com.pacman.gameObjects;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;

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
        playerRect= new Rectangle();
        playerRect.setSize(10,10);
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

    public boolean pathIsClear(){

       /* if(isDead)
            return  false;
       */
        //board bounds
        if(x < -7) {
            x= 150;
            //direction = ' '; tunneling
            return false;
        }
        if(x > 150) {
            x = 0;
            //direction = ' ';
            return false;
        }

        if(y<23) {
            y = 23;
            direction = ' ';
            return false;
        }
        if(y>176) {
            y = 176;
            direction = ' ';
            return false;
        }

        return true;}

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
