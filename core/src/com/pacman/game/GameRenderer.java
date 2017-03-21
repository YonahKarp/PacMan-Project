package com.pacman.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.pacman.Services.AssetLoader;
import com.pacman.gameObjects.Map;
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
        Map map = environment.getMap();

        // Fill screen with black, prevents flickering.
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batcher.begin();
        batcher.disableBlending();

        TextureRegion currentFrame = AssetLoader.pacmAnimation.getKeyFrame(runTime, true);




        final int MAP_COLS = 28, MAP_ROWS = 31;
        final float boxsize = 5.35f;
        final int vertOffset = 20;

        for (int i = 0; i < MAP_COLS * MAP_ROWS; i++) {
            batcher.draw(
                    map.textureMap[i/28][i%28],
                    (i % 28)* boxsize,
                    (i / 28) * boxsize + vertOffset,
                    boxsize,
                    boxsize
            );
        }

        batcher.draw(
                currentFrame ,
                pacman.getX(), //x position
                pacman.getY(), //y position
                3.5f, // x origin
                3.5f, // y origin
                7f, //width
                7f, //height
                1f, //x scale
                1f, // y scale
                pacman.getRotation() //rotation
        );



        batcher.end();

    }
}
