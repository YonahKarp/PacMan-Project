package com.pacman.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.pacman.Services.AssetLoader;
import com.pacman.screens.PacmanGameScreen;
import com.pacman.screens.SplashScreen;

/**
 * Created by YonahKarp on 2/22/17.
 */

/**
 * The "Game" class is what is called by the launcher.
 * As far as i can tell, it's just a wrapper to set the screen
 */

public class PacmanGame extends Game {

    private PacmanGameScreen gameScreen;
    public SpriteBatch batch;
    public BitmapFont font;

    @Override
    public void create() {
        batch = new SpriteBatch();
        font = new BitmapFont();
        AssetLoader.load();
        setScreen(new SplashScreen(this));

        System.err.println("game created! We're all done here");
    }

    @Override
    public void render()
    {
        super.render();
    }

    @Override
    public void dispose(){
        batch.dispose();
        font.dispose();
        AssetLoader.dispose();
    }
}
