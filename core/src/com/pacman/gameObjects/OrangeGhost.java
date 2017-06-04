package com.pacman.gameObjects;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by MNA on 4/16/2017.
 */
public class OrangeGhost extends Ghost
{

    //starts at (60,95)

    public OrangeGhost(Animation<TextureRegion> animation) {
        super(60, 95, animation);
    }

    public Vector2 getTarget(Pacman pacman)
    {
        if (_isEaten)
            return startPos;

        Vector2 ghostCoord= this.getCoord();
        ghostCoord = new Vector2(ghostCoord.x/8,ghostCoord.y/8);
        Vector2 pacCoord = pacman.getCoord();
        Vector2 pacCoord2 = new Vector2(pacCoord.x/8, pacCoord.y/8);
        float distance = ghostCoord.dst2(pacCoord2); //check distance in 'tiles' bet ghost and pacman
        //if distance is more than 8 tiles, pacman's location is its target
        if (distance > 64)
            return pacCoord;

        //if distance less than 8 tiles, the target is the lower left corner
        else
            return new Vector2(0,185);

    }
    @Override
    public  void resetGhost()
    {
        super.resetGhost(startPos.x, startPos.y);
    }
}
