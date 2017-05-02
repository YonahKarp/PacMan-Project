package com.pacman.Services;

/**
 * Created by YonahKarp on 3/27/17.
 */
public class SoundService {
    private static boolean kaIsPlaying, sirenIsPlaying = false;

    private static boolean introIsPlaying = false;
    private static boolean introHasPlayed = false;



    public static void setSirenIsPlaying(){ //replaces nomnom
        sirenIsPlaying = true;
        AssetLoader.siren.play();
        new java.util.Timer().schedule(
                new java.util.TimerTask() {
                    @Override
                    public void run() {
                        sirenIsPlaying = false;
                        this.cancel();
                    }
                }, 10350);
    }


    public static void setKaIsPlaying(){ //replaces nomnom
        kaIsPlaying = true;
        AssetLoader.ka.play();
        new java.util.Timer().schedule(
                new java.util.TimerTask() {
                    @Override
                    public void run() {
                        kaIsPlaying = false;
                        this.cancel();
                    }
                }, 200);
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

    public static boolean getSirenIsPlaying(){ return sirenIsPlaying; }

    public static boolean getKaIsPlaying(){ return kaIsPlaying; }

    public static boolean getIntroIsPlaying() {
        return introIsPlaying;
    }

    public static boolean getIntroHasPlayed(){
        return introHasPlayed;
    }
}
