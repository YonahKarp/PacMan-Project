package com.pacman.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.pacman.Services.AssetLoader;
import com.pacman.game.PacmanGame;

/**
 * Created by amram on 5/8/2017.
 */
public class SplashScreen implements Screen
{
    private Stage stage;
    private Table table;
    private PacmanGame game;
    private OrthographicCamera camera;

    public SplashScreen(PacmanGame game)
    {
        this.game = game;
        this.stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(this.stage);
        this.table = new Table();
        this.table.setFillParent(true);
        this.stage.addActor(this.table);

        TextButton.TextButtonStyle style = new TextButton.TextButtonStyle();
        style.font = AssetLoader.gameFont;
        TextButton button = new TextButton("button1", style);
        //this.table.add(button);

        this.camera = new OrthographicCamera();
        this.camera.setToOrtho(false, 600, 800);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.act(delta);
        stage.draw();

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
        this.stage.getViewport().update(width, height, true);
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
