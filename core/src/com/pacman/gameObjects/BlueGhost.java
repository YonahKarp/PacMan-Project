package com.pacman.gameObjects;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by MNA on 4/3/2017.
 */
public class BlueGhost extends Ghost
{
    //starts at (84,95)

    private RedGhost redGhost;
    public BlueGhost (RedGhost rg, Animation<TextureRegion> animation) {
        super(84, 95, animation);
        redGhost = rg;
    }

    //set BlueGhost target to: draw line from redGhost to 2 tiles ahead of pacman (i.e 16px) and then extend that line double the length
    public Vector2 getTarget(Pacman pacman)
    {
        if (_isEaten)
            return startPos;

        Vector2 temp = new Vector2();
        Vector2 pacCoord = pacman.getCoord();
        float pacX = pacCoord.x;
        float pacY = pacCoord.y;

        char pacDirection = pacman.direction;
        switch (pacDirection) {
            case 'u':
                temp.x = pacX;
                temp.y = pacY-16;
                break;
            case 'd':
                temp.x = pacX;
                temp.y = pacY+16;
                break;
            case 'r':
                temp.x = pacX+16;
                temp.y = pacY;
                break;
            case 'l':
                temp.x = pacX-16;
                temp.y = pacY;
                break;
        }
        Vector2 rgCoord = redGhost.getCoord();
        //For Debugging:
        // System.err.println(" Two tiles in front of Pac is: "+temp.x+","+temp.y);
        float xDis = temp.x - rgCoord.x;
        temp.x = temp.x + xDis;
        float yDis = temp.y - rgCoord.y;
        temp.y = temp.y + yDis;
        //For debugging:
        // System.err.println("Red Location is: "+rgCoord.x+","+rgCoord.y+" Blue target is: "+temp.x+","+temp.y+" Pac location is: "+pacX+","+pacY);
        return new Vector2(temp.x, temp.y);
    }
    @Override
    public  void resetGhost()
    {
        super.resetGhost(startPos.x, startPos.y);
    }
}
