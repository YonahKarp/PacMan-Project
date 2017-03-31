package com.pacman.gameObjects;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by MNA on 3/31/2017.
 */
public class RedGhost extends Ghost {
    public RedGhost(float x, float y, Animation<TextureRegion> animation) {
        super(x, y, animation);
    }

    private char currentDirection = 'e',preDirection;
    private int speed = 38;
    public void update(float delta, Pacman pacman) {
        //for debugging: System.err.println("----"+this.getX()+" "+this.getY());
        /*if((this.getX()>65&&this.getX()<80)&&(this.getY()>79&&this.getY()<105)){
            y-=speed*delta;
            return;}
        */
        Vector2 pacCoord = pacman.getCoord();
        Vector2 ghostCoord = this.getCoord();

        char closestDirection;
        Vector2 temp, temp2 = this.getCoord();
        float closestPath = Integer.MAX_VALUE;
        if (pathIsClear('u') && currentDirection != 'd')  //ghostCoordst isnt allowed to reverse direction
        {
            temp = ghostCoord.cpy();
            temp = temp.add(0.0f, (-speed * delta));  //set temp vector to the position to test
            float distance = temp.dst2(pacCoord);  //check distance from that spot to pacman
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
            float distance = temp.dst2(pacCoord);
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
            float distance = temp.dst2(pacCoord);
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
            float distance = temp.dst2(pacCoord);
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
    public void resetGhost()
    {
        currentDirection = 'e';
        x=72;
        y=80;
    }
}
