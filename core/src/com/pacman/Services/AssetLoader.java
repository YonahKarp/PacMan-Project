package com.pacman.Services;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * Created by YonahKarp on 3/6/17.
 */


public class AssetLoader {

    public static BitmapFont gameFont;

    public static Texture pacmanSheet, hillarySheet, mazeSheet, ghostSheet;

    public static Animation<TextureRegion> pacmAnimation, dyingPacmAnimation, powerPellet;
    public static Animation<TextureRegion> redGhost, blueGhost, pinkGhost, orangeGhost, edibleGhost ;

    public static TextureRegion pacOpen, pacMid, pacClosed, bigPacman, ghostEyes, cherry, strawberry;
    public static TextureRegion[] redGhostImages, blueGhostImages, pinkGhostImages, orangeGhostImages, edibleGhostImages, powerPelletImages;
    public static TextureRegion[][] mazeTiles;

    public static Sound introMusic, nomnom, death, powerPacman, ka, wa, siren;

    public static void load() {

        /**
         * Fonts
         */
        gameFont = new BitmapFont(Gdx.files.internal("img/gameFont.fnt"));

        /**
         * Images
         */
        pacmanSheet = new Texture(Gdx.files.internal("img/pacmanSheet.png"));
        pacmanSheet.setFilter(TextureFilter.Nearest, TextureFilter.Nearest); //retain pixelation (no blur)
        pacOpen = new TextureRegion(pacmanSheet, 3, 1, 13, 13); //location on sprite sheet
        //pacOpen.flip(false, true); //may need to be flipped in Y down paradigm

        pacMid = new TextureRegion(pacmanSheet, 19, 1, 13, 13);;

        pacClosed = new TextureRegion(pacmanSheet, 35, 1, 13, 13);
        cherry = new TextureRegion(pacmanSheet, 37, 48, 13, 13);
        cherry.flip(false, true);
        strawberry = new TextureRegion(pacmanSheet, 53, 48, 13, 13);
        strawberry.flip(false, true);
        TextureRegion[] pacMan = { pacOpen, pacMid, pacClosed };
        pacmAnimation = new Animation<TextureRegion>(0.1f, pacMan);
        pacmAnimation.setPlayMode(Animation.PlayMode.NORMAL); //nom nom nom

        bigPacman = new TextureRegion(pacmanSheet, 32, 16, 32, 32);

        //uncomment for hillary version
        /*
        hillarySheet = new Texture(Gdx.files.internal("img/hillarySheet.png"));
        hillarySheet.setFilter(TextureFilter.Nearest, TextureFilter.Nearest); //retain pixelation (no blur)

        TextureRegion[] hillary = {
                new TextureRegion(hillarySheet, 30, 10, 130, 130),
                new TextureRegion(hillarySheet, 190, 10, 130, 130),
                new TextureRegion(hillarySheet, 350, 10, 130, 130) };


        /// /pacmAnimation = new Animation<TextureRegion>(0.1f, hillary);
        //pacmAnimation.setPlayMode(Animation.PlayMode.NORMAL); //nom nom nom
        */



        ghostSheet = new Texture(Gdx.files.internal("img/ghostSheet.png"));
        //pacmanSheet.setFilter(TextureFilter.Nearest, TextureFilter.Nearest); //retain pixelation (no blur)

        redGhostImages = new TextureRegion[2];
        redGhostImages[0] = new TextureRegion(ghostSheet,  2, 1, 14, 14);
        redGhostImages[0].flip(false,true);
        redGhostImages[1] = new TextureRegion(ghostSheet,  18, 1, 14, 14);
        redGhostImages[1].flip(false,true);
        redGhost = new Animation<TextureRegion>(0.1f, redGhostImages);

        pinkGhostImages = new TextureRegion[2];
        pinkGhostImages[0] = new TextureRegion(ghostSheet,  2, 17, 14, 14);
        pinkGhostImages[0].flip(false,true);
        pinkGhostImages[1] = new TextureRegion(ghostSheet,  18, 17, 14, 14);
        pinkGhostImages[1].flip(false,true);
        pinkGhost = new Animation<TextureRegion>(0.1f, pinkGhostImages);

        blueGhostImages = new TextureRegion[2];
        blueGhostImages[0] = new TextureRegion(ghostSheet,  2, 33, 14, 14);
        blueGhostImages[0].flip(false,true);
        blueGhostImages[1] = new TextureRegion(ghostSheet,  18, 33, 14, 14);
        blueGhostImages[1].flip(false,true);
        blueGhost = new Animation<TextureRegion>(0.1f, blueGhostImages);

        orangeGhostImages = new TextureRegion[2];
        orangeGhostImages[0] = new TextureRegion(ghostSheet,  2, 49, 14, 14);
        orangeGhostImages[0].flip(false,true);
        orangeGhostImages[1] = new TextureRegion(ghostSheet,  18, 49, 14, 14);
        orangeGhostImages[1].flip(false,true);
        orangeGhost = new Animation<TextureRegion>(0.1f, orangeGhostImages);

        edibleGhostImages = new TextureRegion[2];
        edibleGhostImages[0] = new TextureRegion(ghostSheet,  130, 1, 14, 14);
        edibleGhostImages[0].flip(false,true);
        edibleGhostImages[1] = new TextureRegion(ghostSheet,  146, 1, 14, 14);
        edibleGhostImages[1].flip(false,true);
        edibleGhost = new Animation<TextureRegion>(0.1f, edibleGhostImages);

        ghostEyes = new TextureRegion(ghostSheet, 130, 17, 14, 14);

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

        dyingPacmAnimation = new Animation<TextureRegion>(0.1f, dyingPacman);
        dyingPacmAnimation.setPlayMode(Animation.PlayMode.NORMAL); //nom nom nom

        /*
         *  Map
         */
        mazeSheet = new Texture(Gdx.files.internal("img/mazeSheet3.png"));

        //hillary version
        //mazeSheet = new Texture(Gdx.files.internal("img/mazeHillary3.png"))


        final int FRAME_COLS = 13, FRAME_ROWS = 3;
        mazeTiles = TextureRegion.split(mazeSheet, 8, 8);

        powerPelletImages = new TextureRegion[]{
                new TextureRegion(mazeTiles[2][11]),
                new TextureRegion(mazeTiles[2][12])
        };

        powerPellet = new Animation<TextureRegion>(0.15f, powerPelletImages);

        /**
         * Audio
         */
        introMusic = Gdx.audio.newSound(Gdx.files.internal("audio/introMusic.ogg"));
        nomnom = Gdx.audio.newSound(Gdx.files.internal("audio/nomnom.ogg"));

        death = Gdx.audio.newSound(Gdx.files.internal("audio/death.ogg"));
        powerPacman = Gdx.audio.newSound(Gdx.files.internal("audio/ghostsTurnBlue.ogg"));
        siren = Gdx.audio.newSound(Gdx.files.internal("audio/Siren1.ogg"));
        ka = Gdx.audio.newSound(Gdx.files.internal("audio/ka.ogg"));
        wa = Gdx.audio.newSound(Gdx.files.internal("audio/wa.ogg"));

    }

    public static void dispose() {
        // Dispose of texture when finished. File is 'large'
        pacmanSheet.dispose();
        ghostSheet.dispose();
        mazeSheet.dispose();
    }

}
