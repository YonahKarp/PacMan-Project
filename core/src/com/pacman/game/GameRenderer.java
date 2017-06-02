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
import com.pacman.Services.SoundService;
import com.pacman.gameObjects.*;
import com.pacman.screens.PacmanGameScreen;
import com.pacman.screens.SplashScreen;

import javax.swing.*;
import javax.xml.soap.Text;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Time;

/**
 * This class does our rendering
 */
public class GameRenderer {

    private GameEnvironment environment;
    private OrthographicCamera cam;

    private SpriteBatch batcher;
    private final float boxsize = 5.35f;
    FruitGenerator fruit;

    //used for when pacman dies (singular looping of animation)
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

    /**
        Main method of renderer
     */
    public void render(float runTime) {

        Pacman pacman = environment.getPacman();
        Map map = environment.getMap();
        Ghost[] ghosts = environment.getGhosts();

        // Fill screen with black, prevents flickering.
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batcher.begin();
        batcher.enableBlending();

        drawScore();

        handleMapCollisions(pacman,ghosts,map);

        drawMap(pacman, ghosts, runTime);
        drawPacman(pacman, runTime);
        drawGhosts(ghosts, pacman, runTime);

        drawFruit();

        batcher.end();
    }

    private void drawScore(){
        BitmapFont score = AssetLoader.gameFont;
        score.getRegion().getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear); //fix blur
        score.setUseIntegerPositions(false); //fix kerning (space btw letters)
        score.getData().setScale(.1f, -.1f);
        score.draw(batcher, ProgressKeeper.progressString(), 20, 0);
    }

    private void drawPacman(Pacman pacman, float runTime){
        TextureRegion currentFrame;

        //see which pacman we are drawing: dead or alive.
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
    }

    private void drawMap(Pacman pacman, Ghost[] ghosts, float runTime){
        final int MAP_COLS = 28, MAP_ROWS = 31;
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
    }

    public void drawGhosts(Ghost[] ghosts, Pacman pacman,  float runTime){
        for (Ghost ghost : ghosts) {

            TextureRegion currFrame;

            if (ghost.isEaten())
                currFrame = AssetLoader.ghostEyes;
            else if (ghost.isEdible())
                currFrame = ghost.getEdibleAnimation().getKeyFrame(runTime, true);
            else
                currFrame = ghost.getAnimation().getKeyFrame(runTime, true);

            batcher.draw(
                    currFrame,
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

                if(ghost.isEdible()) {

                    //ghost.setIsEaten(true);
                    ghost.setEdibleFalse();
                    ghost.resetGhost(); //if pacman is invincible, only eat that ghost
                    ProgressKeeper.eatGhost();
                }else if(ghost.isEaten()){} //do nothing if ghost already eaten
                else{
                    pacmanDeath(pacman, ghosts, runTime);
                }
            }
        }
    }


    //show fruit for 10 seconds when 70 or 170 dots eaten
    private void drawFruit() {
        if (ProgressKeeper.getDotAndEnergEaten() == 70 || ProgressKeeper.getDotAndEnergEaten() == 170) {
            fruit = new FruitGenerator(ProgressKeeper.getLevel());
            TextureRegion fruitTexture = fruit.getTexture();
            Map.textureMap[17][14] = fruitTexture;

            Map.currMap.setCharAt(490, '$');

            new java.util.Timer().schedule(
                    new java.util.TimerTask() {
                        @Override
                        public void run() {
                            Map.currMap.setCharAt(490, ' ');
                            Map.textureMap[17][14] = AssetLoader.mazeTiles[2][13]; //set tile empty on eat
                        }
                    }, 10000);
        }
    }

    private void pacmanDeath(Pacman pacman, Ghost[] ghosts, float runTime){
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

    //moved collisions here (from Player) to set ghosts edible (didn't have access to ghosts in Player)
    private void handleMapCollisions(Pacman pacman, Ghost[] ghosts, Map map){
        int charRows = 28;
        float vertOffset =21.4f;

        int mapX = Math.round(pacman.getX()/boxsize);
        int mapY = (charRows*Math.round((pacman.getY() - vertOffset)/boxsize));


        switch (Map.currMap.charAt(mapX+mapY)) {
            case 'o':
                pacman.setInvincibleTrue();

                for (Ghost ghost: ghosts) {
                    ghost.setGhostsEdibleTrue();
                }
                AssetLoader.powerPacman.play();
                ProgressKeeper.addToEnergizerEaten();
            case '.':
                Map.currMap.setCharAt(mapX + mapY, ' ');

                if (!SoundService.getKaIsPlaying())
                    SoundService.setKaIsPlaying();
                else
                    AssetLoader.wa.play();

                Map.textureMap[mapY / 28][mapX] = AssetLoader.mazeTiles[2][13]; //set tile empty on eat
                ProgressKeeper.addToDotsEaten();
                break;
            case '$':
                Map.currMap.setCharAt(mapX+mapY, ' ');
                Map.textureMap[mapY / 28][mapX] = AssetLoader.mazeTiles[2][13]; //set tile empty on eat
                ProgressKeeper.addToScore(fruit.getPoints());
            default:

        }
    }


    public void dispose()
    {
        this.batcher.dispose();
    }
}
