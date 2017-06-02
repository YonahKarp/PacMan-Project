package com.pacman.gameObjects;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by MNA on 3/31/2017.
 */
public class RedGhost extends Ghost {

    //starts at (70,90)
    private Vector2 retreatPos = new Vector2(60, 95);

    public RedGhost(Animation<TextureRegion> animation) {

        super(70, 90, animation);
        restTimer = 0;
    }

    public Vector2 getTarget(Pacman pacman) {
        if (_isEaten)
            return retreatPos;

        return pacman.getCoord();
    }

    @Override
    public void resetGhost()
    {
        super.resetGhost(startPos.x, startPos.y);
    }
}
