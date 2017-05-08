package com.pacman.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.pacman.Services.AssetLoader;
import com.pacman.game.PacmanGame;

/**
 * Created by amram on 5/8/2017.
 */
public class SplashScreen implements Screen
{

    private PacmanGame game;
    private OrthographicCamera camera;

    public SplashScreen(PacmanGame game)
    {
        this.game = game;
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 600, 800);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        camera.update();
        game.batch.setProjectionMatrix(camera.combined);

        game.batch.begin();
        AssetLoader.gameFont.getData().setScale(0.5f);
        AssetLoader.gameFont.draw(game.batch, "Welcome to Pacman!!! ", 100, 400);
        AssetLoader.gameFont.draw(game.batch, "Tap anywhere to begin!", 100, 350);
        game.batch.draw(AssetLoader.bigPacman, 100, 440);
        game.batch.end();

        if (Gdx.input.isTouched()) {
            game.setScreen(new PacmanGameScreen(this.game));
            dispose();
        }
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
