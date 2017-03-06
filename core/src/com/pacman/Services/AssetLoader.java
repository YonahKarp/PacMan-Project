package com.pacman.Services;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * Created by YonahKarp on 3/6/17.
 */


public class AssetLoader {

    public static Texture texture;

    public static Animation<TextureRegion> pacmAnimation;
    public static TextureRegion pacOpen, pacMid, pacClosed;

    public static void load() {

        texture = new Texture(Gdx.files.internal("pacmanSheet.png"));
        texture.setFilter(TextureFilter.Nearest, TextureFilter.Nearest); //retain pixelation (no blur)


        pacOpen = new TextureRegion(texture, 3, 1, 13, 13); //location on sprite sheet
        //pacOpen.flip(false, true); //may need to be flipped in Y down paradigm

        pacMid = new TextureRegion(texture, 19, 1, 13, 13);;

        pacClosed = new TextureRegion(texture, 35, 1, 13, 13);

        TextureRegion[] pacMan = { pacOpen, pacMid, pacClosed };
        pacmAnimation = new Animation(0.15f, pacMan);
        pacmAnimation.setPlayMode(Animation.PlayMode.LOOP); //nom nom nom

    }

    public static void dispose() {
        // Dispose of texture when finished. File is 'large'
        texture.dispose();
    }

}
