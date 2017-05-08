package com.pacman.game;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;
import com.badlogic.gdx.scenes.scene2d.ui.ProgressBar;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextArea;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.pacman.Services.AssetLoader;
import com.pacman.Services.ProgressKeeper;
import com.pacman.gameObjects.*;
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

    //used for death
    private float nonLoopingRuntime = 0;
    private float prevRuntime = 0;

    public GameRenderer(GameEnvironment environment) {
        this.environment = environment;

        cam = new OrthographicCamera();

        //Screen is 600x800 so it will scale draw size by 4
        cam.setToOrtho(true, 150, 200);

        batcher = new SpriteBatch();
        batcher.setProjectionMatrix(cam.combined);



    }


    public void render(float runTime) {

        Pacman pacman = environment.getPacman();
        Map map = environment.getMap();
        Ghost[] ghosts = environment.getGhosts();
        // Fill screen with black, prevents flickering.
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batcher.begin();
        batcher.enableBlending();

        TextureRegion currentFrame;
        if(!pacman.isDead())
            currentFrame = AssetLoader.pacmAnimation.getKeyFrame(runTime, true);
        else {
            nonLoopingRuntime += (runTime - prevRuntime); //delta
            prevRuntime = runTime;

            Animation<TextureRegion> animation = AssetLoader.dyingPacmAnimation;
            currentFrame = animation.getKeyFrame(nonLoopingRuntime, false);
            if (animation.isAnimationFinished(nonLoopingRuntime)) {
                pacman.setDead(false);
                pacman.resetPacman();
            }
        }


        BitmapFont score = new BitmapFont();
        score.getRegion().getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear); //fix blur
        score.setUseIntegerPositions(false); //fix kerning (space btw letters)
        score.getData().setScale(.3f, -.3f);
        score.draw(batcher, ProgressKeeper.progressString(), 20, 0);



        Vector2 prevPacman = new Vector2(pacman.getX(), pacman.getY());  //save pacmans previous postion if needed

        final int MAP_COLS = 28, MAP_ROWS = 31;
        final float boxsize = 5.35f;
        final float vertOffset = 21.4f;

        for (int i = 0; i < MAP_COLS * MAP_ROWS; i++) {
            
            batcher.draw(
                    (!(Map.textureMap[i / 28][i % 28] instanceof PowerPellet))?
                            Map.textureMap[i / 28][i % 28] : ((PowerPellet)(Map.textureMap[i / 28][i % 28])).getAnimation().getKeyFrame(runTime, true),
                    (i % 28) * boxsize, //x position
                    (i / 28) * boxsize + vertOffset, //y position
                    boxsize,
                    boxsize
            );
        }

        batcher.draw(
                currentFrame,
                pacman.getX(), //x position
                pacman.getY(), //y position
                boxsize / 2f, //3.5f, // x origin
                boxsize / 2f, //3.5f, // y origin
                boxsize, //7f, //width
                boxsize, // 7f height
                1f, //x scale
                1f, // y scale
                pacman.getRotation() //rotation
        );

        for (Ghost ghost : ghosts) {

            batcher.draw(
                    (!pacman.isInvincible())? ghost.getAnimation().getKeyFrame(runTime, true) : ghost.getEdibleAnimation().getKeyFrame(runTime, true),
                    ghost.getX(),
                    ghost.getY(),
                    boxsize / 2f, //3.5f,
                    boxsize / 2f, //3.5f,
                    boxsize, //7f,
                    boxsize, //7f,
                    1f,
                    1f,
                    0
            );


            //check if pacman collides with ghost
            if (Intersector.overlaps(pacman.getRect(), ghost.getRect())) {


                //pacman.dyingPacman(prevPacman.x,prevPacman.y);  //not used for now

                if(pacman.isInvincible()) {
                    ghost.resetGhost(); //if pacman is invincible, only eat that ghost
                    ProgressKeeper.eatGhost();
                }
                else{
                    for (Ghost g:ghosts) {
                        g.resetGhost();  //put ghost back in starting position
                        if(g instanceof RedGhost)
                            g.setRestTimer(0); //red ghost gets no rest timer
                    }

                    pacman.setDead(true);
                    pacman.setRotation(0);

                    nonLoopingRuntime = 0;
                    prevRuntime = runTime;

                    ProgressKeeper.loseALife();

                }
            }
        }
        batcher.end();

    }

    public void dispose()
    {
        this.batcher.dispose();
    }
}
