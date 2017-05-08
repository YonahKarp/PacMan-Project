package com.pacman.gameObjects;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by MNA on 3/31/2017.
 */
public class RedGhost extends Ghost {

    final static int startingX = 70;
    final static int startingY = 90;
    public RedGhost(Animation<TextureRegion> animation) {
        super(startingX, startingY, animation);
        restTimer = 0;
    }

    public Vector2 getTarget(Pacman pacman)
    {
        return pacman.getCoord();
    }

    @Override
    public void resetGhost()
    {
        super.resetGhost(startingX, startingY);
    }
}