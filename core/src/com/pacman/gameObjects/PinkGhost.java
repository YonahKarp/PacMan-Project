package com.pacman.gameObjects;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by MNA on 4/1/2017.
 */
public class PinkGhost extends Ghost
{
   //starts at (70,95)

    public PinkGhost (Animation<TextureRegion> animation) {
        super(70, 95, animation);
    }

    public Vector2 getTarget(Pacman pacman)
    {
        if (_isEaten)
            return startPos;

        Vector2 target = new Vector2();
        Vector2 pacCoord = pacman.getCoord();
        float pacX = pacCoord.x;
        float pacY = pacCoord.y;
        //currentDirection = pacman.direction;
        switch (pacman.direction) {
            case 'u':
                target.set(pacX, (pacY+32));
                break;
            case 'd':
                target.set(pacX, (pacY+32));
                break;
            case 'r':
                target.set((pacX+32),pacY);
                break;
            case 'l':
                target.set((pacX-32), pacY);
                break;
        }
        return target;
    }
    @Override
    public  void resetGhost()
    {
        super.resetGhost(startPos.x, startPos.y);
    }
}
