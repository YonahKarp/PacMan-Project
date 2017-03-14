package com.pacman.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.pacman.Services.AssetLoader;
import com.pacman.gameObjects.Pacman;

/**
 * This class does our rendering
 */
public class GameRenderer {

    private GameEnvironment environment;
    private OrthographicCamera cam;

    private SpriteBatch batcher;




    public GameRenderer(GameEnvironment environment) {
        this.environment = environment;

        cam = new OrthographicCamera();

        //Screen is 600x800 so it will scale draw size by 4
        cam.setToOrtho(true, 150, 200);

        batcher = new SpriteBatch();
        batcher.setProjectionMatrix(cam.combined);



    }


    public void render(float runTime) {
        //System.err.println("renderer called");

        Pacman pacman = environment.getPacman();

        // Fill screen with black, prevents flickering.
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batcher.begin();
        batcher.disableBlending();

        TextureRegion currentFrame = AssetLoader.pacmAnimation.getKeyFrame(runTime, true);


        batcher.draw(
            currentFrame ,
            pacman.getX(), //x position
            pacman.getY(), //y position
            6.5f, // x origin
            6.5f, // y origin
            13f, //width
            13f, //height
            1f, //x scale
            1f, // y scale
            pacman.getRotation() //rotation
        );

        batcher.end();

    }
}
