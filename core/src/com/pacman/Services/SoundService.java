package com.pacman.Services;

/**
 * Created by YonahKarp on 3/27/17.
 */
public class SoundService {
    private static boolean kaIsPlaying, sirenIsPlaying = false;



    public static void setSirenIsPlaying(){
        //check if siren is already playing from a previously eaten power pellet
        if(!sirenIsPlaying) {
            AssetLoader.siren.stop();
            AssetLoader.siren.play();

        }
        sirenIsPlaying = true;
        new java.util.Timer().schedule(
                new java.util.TimerTask() {
                    @Override
                    public void run() {
                        sirenIsPlaying = false;
                        this.cancel();
                    }
                }, 10350);
    }

    public static void stopSiren(){
        sirenIsPlaying = false;
        AssetLoader.siren.stop();
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
        AssetLoader.introMusic.play();
    }

    public static boolean getSirenIsPlaying(){ return sirenIsPlaying; }

    public static boolean getKaIsPlaying(){ return kaIsPlaying; }


}
