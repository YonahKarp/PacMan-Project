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

    public static Texture pacmanSheet, mazeSheet, ghostSheet;

    public static Animation<TextureRegion> pacmAnimation;
    public static Animation<TextureRegion> dyingPacmAnimation;

    public static TextureRegion pacOpen, pacMid, pacClosed, redGhost;
    public static TextureRegion[][] mazeTiles;

    public static void load() {

        pacmanSheet = new Texture(Gdx.files.internal("pacmanSheet.png"));
        pacmanSheet.setFilter(TextureFilter.Nearest, TextureFilter.Nearest); //retain pixelation (no blur)
        pacOpen = new TextureRegion(pacmanSheet, 3, 1, 13, 13); //location on sprite sheet
        //pacOpen.flip(false, true); //may need to be flipped in Y down paradigm

        pacMid = new TextureRegion(pacmanSheet, 19, 1, 13, 13);;

        pacClosed = new TextureRegion(pacmanSheet, 35, 1, 13, 13);

        TextureRegion[] pacMan = { pacOpen, pacMid, pacClosed };
        pacmAnimation = new Animation<TextureRegion>(0.1f, pacMan);
        pacmAnimation.setPlayMode(Animation.PlayMode.LOOP); //nom nom nom

        ghostSheet = new Texture(Gdx.files.internal("ghostSheet.png"));
        // pacmanSheet.setFilter(TextureFilter.Nearest, TextureFilter.Nearest); //retain pixelation (no blur)
        redGhost = new TextureRegion(ghostSheet,  3, 1, 15, 16);
        redGhost.flip(false,true);

        /*
         *  Dead Pacman
         */
        TextureRegion[] dyingPacman = {
                new TextureRegion(pacmanSheet, 51, 1, 13, 13),
                new TextureRegion(pacmanSheet, 67, 1, 13, 13),
                new TextureRegion(pacmanSheet, 83, 1, 13, 13),
                new TextureRegion(pacmanSheet, 99, 1, 13, 13),
                new TextureRegion(pacmanSheet, 115, 1, 13, 13),
                new TextureRegion(pacmanSheet, 131, 1, 13, 13),
                new TextureRegion(pacmanSheet, 147, 1, 13, 13),
                new TextureRegion(pacmanSheet, 163, 1, 13, 13),
                new TextureRegion(pacmanSheet, 179, 1, 13, 13),
                new TextureRegion(pacmanSheet, 196, 1, 13, 13),
                new TextureRegion(pacmanSheet, 212, 1, 13, 13),
        };

        dyingPacmAnimation = new Animation<>(0.1f, dyingPacman);
        dyingPacmAnimation.setPlayMode(Animation.PlayMode.NORMAL); //nom nom nom

        /*
         *  Map
         */
        mazeSheet = new Texture(Gdx.files.internal("mazeSheet2.png"));


        final int FRAME_COLS = 13, FRAME_ROWS = 3;
        mazeTiles = TextureRegion.split(mazeSheet,
                8,
                8);

        System.out.println(mazeTiles.length);





    }

    public static void dispose() {
        // Dispose of texture when finished. File is 'large'
        pacmanSheet.dispose();
        mazeSheet.dispose();
    }

}
