package com.pacman.screens;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.pacman.game.GameRenderer;
import com.pacman.game.GameEnvironment;
import com.pacman.Services.InputHandler;


/**
 * Created by YonahKarp on 2/22/17.
 * The screen seems to be where where all the real magic happens
 */
public class PacmanGameScreen implements Screen {
    private GameEnvironment environment;
    private GameRenderer renderer;

    private float runtime = 0;


    public PacmanGameScreen(){
        environment = new GameEnvironment();
        renderer = new GameRenderer(environment);

        Gdx.input.setInputProcessor(new InputHandler(environment.getPacman()));

    }

    @Override
    public void show() {
        System.err.println("show called");
    }

    @Override
    public void render(float delta) {

        /**
         * Every frame: we update, then render
         */
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
