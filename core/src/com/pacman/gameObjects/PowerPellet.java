package com.pacman.gameObjects;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.pacman.Services.AssetLoader;

/**
 * Created by YonahKarp on 3/27/17.
 */
public class PowerPellet extends TextureRegion{
    private Animation<TextureRegion> animation = AssetLoader.powerPellet;

    public PowerPellet(){
    }

    public Animation<TextureRegion> getAnimation() {
        return animation;
    }
}
