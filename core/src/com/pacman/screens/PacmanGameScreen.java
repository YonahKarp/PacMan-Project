package com.pacman.screens;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
import com.pacman.Services.AssetLoader;
import com.pacman.Services.ProgressKeeper;
import com.pacman.Services.SoundService;
import com.pacman.game.GameRenderer;
import com.pacman.game.GameEnvironment;
import com.pacman.Services.InputHandler;
import com.pacman.game.PacmanGame;


/**
 * Created by YonahKarp on 2/22/17.
 * The screen seems to be where where all the real magic happens
 */
public class PacmanGameScreen implements Screen {
    private PacmanGame game;
    private GameEnvironment environment;
    private GameRenderer renderer;

    private float runtime = 0;


    public PacmanGameScreen(PacmanGame game){
        this.game = game;
        environment = new GameEnvironment();
        renderer = new GameRenderer(environment);

        //todo play introMusic while paused with screen behind
        SoundService.setIntroIsPlaying();

        Gdx.input.setInputProcessor(new InputHandler(this.environment.getPacman(), this.environment.getMap()));


    }

    @Override
    public void show() {
        System.err.println("show called");
    }

    /**
     * Every frame: we update, then render
     */
    @Override
    public void render(float delta) {

        System.out.println(ProgressKeeper.getDotAndEnergEaten());
        //reset when all lives lost or all dots eaten
        if (ProgressKeeper.getLives() < 0  || ProgressKeeper.getDotAndEnergEaten() == 250) {
            this.dispose();
            ProgressKeeper.resetData();
            this.game.setScreen(new SplashScreen(this.game));
            environment.getMap().resetMap();
        }

        runtime += delta;
        environment.update(delta);
        renderer.render(runtime);
    }

    @Override
    public void resize(int width, int height) {
        System.err.println("resize called");
    }

    @Override
    public void pause() {
        System.err.println("pause called");
    }

    @Override
    public void resume() {
        System.err.println("resume called");
    }

    @Override
    public void hide() {
        System.err.println("hide called");
    }

    @Override
    public void dispose() {
        System.err.println("dispose called");
    }
}
