package com.pacman.Services;

/**
 * Created by YonahKarp on 3/27/17.
 */
public class SoundService {
    private static boolean nomnomIsPlaying = false;
    private static boolean introIsPlaying = false;
    private static boolean introHasPlayed = false;


    public static void setNomnomIsPlaying(){
        nomnomIsPlaying = true;
        AssetLoader.nomnom.play();
        new java.util.Timer().schedule(
                new java.util.TimerTask() {
                    @Override
                    public void run() {
                        nomnomIsPlaying = false;
                        this.cancel();
                    }
                }, 500);
    }

    public static void setIntroIsPlaying(){
        introIsPlaying = true;
        introHasPlayed = true;
        AssetLoader.introMusic.play();
        new java.util.Timer().schedule(
                new java.util.TimerTask() {
                    @Override
                    public void run() {
                        introIsPlaying = false;
                        this.cancel();
                    }
                }, 4000);
    }

    public static boolean getNomnomIsPlaying() {
        return nomnomIsPlaying;
    }

    public static boolean getIntroIsPlaying() {
        return introIsPlaying;
    }

    public static boolean getIntroHasPlayed(){
        return introHasPlayed;
    }
}
