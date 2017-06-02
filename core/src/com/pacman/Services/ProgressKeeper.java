package com.pacman.Services;

import com.badlogic.gdx.graphics.Texture;
import com.pacman.gameObjects.Map;

/**
 * Created by YonahKarp on 4/27/17.
 */
public class ProgressKeeper {
    private static int dotsEaten = 0;  //to keep track of how many dots were eaten to end level
    private static int energizerDotsEaten = 0;
    private static int score = 0;
    private static int lives = 3;
    private static int highScore = 0;
    private static int level = 0;

    private static int ghostsEaten = 0;


    private static boolean extraLifeGiven = false;

    public static void addToDotsEaten(){
        dotsEaten+=1;
        addToScore(10);
    }

    public static void addToEnergizerEaten(){
        energizerDotsEaten+=1;
        addToScore(40);
    }

    public static void addToScore(int val){
        score += val;

        if (score > highScore)
            highScore = score;


        if (!extraLifeGiven && score > 10000) {
            lives += 1;
            extraLifeGiven = true;
        }
    }

    public static void eatGhost(){
        ghostsEaten += 1;
        addToScore(200*ghostsEaten);
    }

    public static int getDotsEaten() {return  dotsEaten; }

    public static int getDotAndEnergEaten() {return  dotsEaten+energizerDotsEaten; }

    public static int getScore(){
        return score;
    }

    public static int getLives() {
        return lives;
    }

    public static void loseALife(){
        lives -= 1;
        if (lives < 0) {
            Map.resetMap();
        }
    }

    public static void resetData(){
        energizerDotsEaten = 0;
        dotsEaten = 0;
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

    public static int getLevel() {
        return level;
    }

    public static void resetLevel() {
        level = 0;
    }

    public static void goUpLevel() {
        level++;
        dotsEaten = 0;
        energizerDotsEaten = 0;
    }

    public static String progressString(){
        String tmpLives ="";

        for (int i = 0; i < lives; i++) {
            tmpLives += "@";
        }

        return "Level: " + (level +1) +" score: " + score + " Lives: " + tmpLives + " \n HighScore: " + highScore;

    }
}
