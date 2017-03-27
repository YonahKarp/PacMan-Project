package com.pacman.game;
import com.badlogic.gdx.graphics.TextureArray;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;
import com.pacman.Services.AssetLoader;
import com.pacman.gameObjects.Ghost;
import com.pacman.gameObjects.Map;
import com.pacman.gameObjects.Pacman;
import com.pacman.gameObjects.PowerPellet;
import com.pacman.screens.PacmanGameScreen;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.*;
import java.sql.Time;

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
        Ghost[] ghosts = environment.getGhosts();
        // Fill screen with black, prevents flickering.
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batcher.begin();
        batcher.enableBlending();

        TextureRegion currentFrame = AssetLoader.pacmAnimation.getKeyFrame(runTime, true);
        TextureRegion dyingPac = AssetLoader.dyingPacmAnimation.getKeyFrame(runTime, false);
        Vector2 prevPacman = new Vector2(pacman.getX(), pacman.getY());  //save pacmans previous postion if needed

        final int MAP_COLS = 28, MAP_ROWS = 31;
        final float boxsize = 5.35f;
        final float vertOffset = 21.4f;

        for (int i = 0; i < MAP_COLS * MAP_ROWS; i++) {
           if(!(Map.textureMap[i / 28][i % 28] instanceof PowerPellet))
                batcher.draw(
                        Map.textureMap[i / 28][i % 28],
                        (i % 28) * boxsize, //x position
                        (i / 28) * boxsize + vertOffset, //y position
                        boxsize,
                        boxsize
                );
            else {
               batcher.draw(
                       ((PowerPellet)(Map.textureMap[i / 28][i % 28])).getAnimation().getKeyFrame(runTime, true),
                       (i % 28) * boxsize, //x position
                       (i / 28) * boxsize + vertOffset, //y position
                       boxsize,
                       boxsize
               );
           }
        }
        batcher.draw(
                currentFrame,
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

        for (Ghost ghost : ghosts) {

            batcher.draw(
                    ghost.getAnimation().getKeyFrame(runTime, true),
                    ghost.getX(),
                    ghost.getY(),
                    3.5f,
                    3.5f,
                    7f,
                    7f,
                    1f,
                    1f,
                    0
            );


            //check if pacman collides with ghost
            if (Intersector.overlaps(pacman.getRect(), ghost.getRect())) {
                ghost.resetGhost();  //put ghost back in starting position
                //pacman.dyingPacman(prevPacman.x,prevPacman.y);  //not used for now
                System.err.println("dead");
                batcher.draw(dyingPac, pacman.getX(), pacman.getY());
                pacman.resetPacman();
                pacman.setDead(true);
            }
        }
        batcher.end();

    }
}
