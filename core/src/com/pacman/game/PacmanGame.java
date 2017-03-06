package com.pacman.game;

import com.badlogic.gdx.Game;
import com.pacman.Services.AssetLoader;
import com.pacman.screens.PacmanGameScreen;

/**
 * Created by YonahKarp on 2/22/17.
 */

/**
 * The "Game" class is what is called by the launcher.
 * As far as i can tell, it's just a wrapper to set the screen
 */

public class PacmanGame extends Game {
    @Override
    public void create() {
        AssetLoader.load();
        setScreen(new PacmanGameScreen());

        System.err.println("game created! We're all done here");
    }

    @Override
    public void dispose(){
        AssetLoader.dispose();
    }
}
