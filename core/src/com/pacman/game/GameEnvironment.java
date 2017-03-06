package com.pacman.game;

import com.pacman.gameObjects.Pacman;

/**
 * This class does updates the frames
 */
public class GameEnvironment {
    private Pacman pacman;

    public GameEnvironment(){
        pacman = new Pacman(300,400,0);
    }


    public void update(float delta) {
        pacman.update(delta);
        //System.err.println("updater called");
    }


    public Pacman getPacman() {
        return pacman;
    }
}
