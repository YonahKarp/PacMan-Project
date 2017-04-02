package com.pacman.gameObjects;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Plane;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.pacman.Services.AssetLoader;
import org.omg.CORBA.Environment;

import java.util.Random;

/**
 * Created by MNA on 3/22/2017.
 */
public class Ghost extends Player {
    private Random rand = new Random();
    private Rectangle ghostRect;  //encapsulate ghost in rectangle for collision detection
    private Animation<TextureRegion> animation; //todo split ghosts into different classes with different AIs
    private Animation<TextureRegion> edibleAnimation = AssetLoader.edibleGhost;

    private int currMove = 3; //start by moving up


    public Ghost(float x, float y, Animation<TextureRegion> animation)
    {
        super(x,y,0);
        this.animation = animation;
        ghostRect = new Rectangle(x,y,7,7); //to encapsulate ghost for collision detection
    }

    public Vector2 getTarget(Pacman pacman)
    {
        return pacman.getCoord();
    }

    protected char currentDirection = 'e',preDirection;
    private int speed = 38;

    public void update(float delta, Pacman pacman) {
        //for debugging:
        //System.err.println("----"+this.getX()+" "+this.getY());
        if(x < 0.8 && direction!='r') //ensure pacman didnt just come through the tunnel
        {
            x= 142;
            return;
        }
        if(x > 142 && direction!='l') {
            x = 0;
            return;
        }
        Vector2 ghostCoord = this.getCoord();
        Vector2 target = getTarget(pacman);
        System.err.println(speed*delta+" pacman:"+pacman.getCoord()+" ghost:"+ghostCoord +" target:"+target);
        Vector2 temp, temp2 = this.getCoord();
        float closestPath = Integer.MAX_VALUE;
        if (pathIsClear('u') && currentDirection != 'd')  //ghostCoordst isnt allowed to reverse direction
        {
            temp = ghostCoord.cpy();
            temp = temp.add(0.0f, (-speed * delta));  //set temp vector to the position to test
            float distance = temp.dst2(target);  //check distance from that spot to pacman
            if (distance < closestPath) {
                temp2 = temp;
                closestPath = distance;
                preDirection = 'u';  //keep track of previous direction
            }
            //for debugging:
            //System.err.println("u"+temp2.x+" "+temp2.y+" "+temp.dst(pacman.getCoord()));
        }
        if (pathIsClear('l') && currentDirection != 'r') {
            temp = ghostCoord.cpy();
            temp = temp.add((-speed * delta), 0.0f);
            float distance = temp.dst2(target);
            if (distance < closestPath) {
                temp2 = temp;
                closestPath = distance;
                preDirection = 'l';
            }
            //System.err.println("l"+temp2.x+" "+temp2.y+" "+temp.dst(pacman.getCoord()));
        }
        if (pathIsClear('d') && currentDirection != 'u') {
            temp = ghostCoord.cpy();
            temp = temp.add(0.0f, (speed * delta));
            float distance = temp.dst2(target);
            if (distance < closestPath) {
                temp2 = temp;
                closestPath = distance;
                preDirection = 'd';
            }
            //System.err.println("d"+temp2.x+" "+temp2.y+" "+temp.dst(pacman.getCoord()));
        }

        if (pathIsClear('r') && currentDirection != 'l') {
            temp = ghostCoord.cpy();
            temp = temp.add(speed * delta, 0.0f);
            float distance = temp.dst2(target);
            if (distance < closestPath) {
                temp2 = temp;
                closestPath = distance;
                preDirection = 'r';
            }
            //System.err.println("r"+temp2.x+" "+temp2.y+" "+temp.dst(pacman.getCoord()));
        }

        currentDirection = preDirection;
        x = temp2.x;
        y = temp2.y;
        //for debugging: System.err.println(currentDirection+" *** "+temp2.x+" "+temp2.y);
    }


 /*   public void update(float delta, Pacman pacman) {
        switch(currMove){
            case 0:
                if (pathIsClear('r')) {
                    x += speed * delta;
                    return;
                }
                break;
            case 1:
                if(pathIsClear('l')) {
                    x -= speed * delta;
                    return;
                }
                break;
            case 2:
                if (pathIsClear('d')) {
                    y += speed * delta;
                    return;
                }
                break;
            case 3:
                if(pathIsClear('u')) {
                    y -= speed * delta;
                    return;
                }
        }
        //if path was blocked, choose new direction
        currMove = rand.nextInt(4);
    }*/
    public float getX()
    {
        return x;
    }
    public float getY()
    {
        return y;
    }

    //get rectangle to check if intersects with pacman's rectangle
    public Rectangle getRect()
    {
        ghostRect.setPosition(x,y);
        return ghostRect;
    }

    public char getDirection(){return currentDirection;}

    public void resetGhost(){}

    public void resetGhost(int x, int y)
    {
        currentDirection = 'e';
        this.x = x;
        this.y = y;
    }

    public Animation<TextureRegion> getAnimation() {
        return animation;
    }

    public Animation<TextureRegion> getEdibleAnimation() {
        return edibleAnimation;
    }
}
