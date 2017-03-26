package com.pacman.gameObjects;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import static com.pacman.Services.AssetLoader.redGhost;

/**
 * Created by YonahKarp on 3/6/17.
 */
public class Player
{
    protected Map map;
    float x;
    float y;

    int speed;
    float rotation;
    boolean isDead;
    char direction = ' ';

    Rectangle playerRect;  //to encapsulate pacman for collision detection

    public Player(){}

    public Player(float x, float y, int speed, float rotation, Map map) {
        this.x = x;
        this.y = y;

        this.speed = speed;
        this.rotation = rotation;
        playerRect= new Rectangle();
        playerRect.setSize(10,10);
        isDead= false;
        this.map = map;
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

    protected boolean pathIsClear(float[] position)
    {
        char nextTile = this.map.getTileFromPosition(
                position[0],
                position[1]);

        boolean tileIsValidMovementTile = this.map.tileIsValidMovementTile(nextTile);
        System.out.println("Next Tile: " + nextTile);
        System.out.println("Tile Is Valid Movement: " + tileIsValidMovementTile);

        if (!tileIsValidMovementTile) return false;

        /* if(isDead)
            return  false;
        */
        //board bounds
        if (position[0] < -7) {
            x= 150;
            //direction = ' '; tunneling
            return false;
        }
        if (position[0] > 150) {
            x = 0;
            //direction = ' ';
            return false;
        }
        if(position[1] < 23) {
            y = 23;
            direction = ' ';
            return false;
        }
        if(position[1] > 176) {
            y = 176;
            direction = ' ';
            return false;
        }

        return true;
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
