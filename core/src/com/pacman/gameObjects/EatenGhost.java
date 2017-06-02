package com.pacman.gameObjects;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.pacman.Services.AssetLoader;

/**
 * Created by YonahKarp on 6/2/17.
 */
public class EatenGhost extends Ghost {
    private Animation<TextureRegion> oldAnimation;

    public EatenGhost(float x, float y, Class ghostType) {
        super(x, y, new Animation<TextureRegion>(1f,AssetLoader.ghostEyes));
    }

    public Vector2 getTarget(Pacman pacman) {
        return startPos;
    }

    @Override
    public void update(float delta, Pacman pacman) {
        super.update(delta, pacman);
    }

    @Override
    public void resetGhost()
    {
        super.resetGhost(startPos.x, startPos.y);
    }
}
