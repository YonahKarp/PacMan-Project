package com.pacman.Services;

import com.badlogic.gdx.graphics.Texture;
import com.pacman.gameObjects.Map;

/**
 * Created by YonahKarp on 4/27/17.
 */
public class ProgressKeeper {
    private static int score = 0;
    private static int lives = 3;
    private static int highScore = 0;
    //private static int level = 0;

    private static int ghostsEaten = 0;


    private static boolean extraLifeGiven = false;

    public static void addToScore(int val){
        score += val;

        if (score > highScore)
            highScore = score;


        if (!extraLifeGiven && score > 10000)
            lives += 1;
    }

    public static void eatGhost(){
        ghostsEaten += 1;
        addToScore(200*ghostsEaten);
    }


    public static int getScore(){
        return score;
    }

    public static int getLives() {
        return lives;
    }

    public static void loseALife(){
        lives -= 1;
        if (lives < 0) {
            resetData();
            Map.resetMap();
        }
    }

    public static void resetData(){
        score = 0;
        lives = 3;
        extraLifeGiven = false;
    }

    public static void resetGhostsEaten(){
        ghostsEaten = 0;
    }

    public static int getHighScore() {
        return highScore;
    }

    public static String progressString(){
        String tmpLives ="";

        for (int i = 0; i < lives; i++) {
            tmpLives += "@";
        }

        return "score: " + score + " Lives: " + tmpLives + " \n HighScore: " + highScore;

    }
}
