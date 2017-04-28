package com.pacman.gameObjects;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by MNA on 4/1/2017.
 */
public class PinkGhost extends Ghost
{
    final static int startingX = 72;
    final static int startingY = 95;
    public PinkGhost (Animation<TextureRegion> animation) {
        super(startingX, startingY, animation);
    }

    //set pinkGhost target to 4 tiles ahead of pacman (i.e 32px)
    public Vector2 getTarget(Pacman pacman)
    {
        Vector2 target = new Vector2();
        Vector2 pacCoord = pacman.getCoord();
        float pacX = pacCoord.x;
        float pacY = pacCoord.y;
        currentDirection = pacman.getDirection();
        switch (currentDirection) {
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
        super.resetGhost(startingX, startingY);
    }
}
