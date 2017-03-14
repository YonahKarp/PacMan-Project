package com.pacman.game;

import com.pacman.gameObjects.Pacman;

/**
 * This class does updates the frames
 */
public class GameEnvironment {
    private Pacman pacman;

    public GameEnvironment(){
        pacman = new Pacman(100,100,0);
    }


    public void update(float delta) {
        pacman.update(delta);
    }


    public Pacman getPacman() {
        return pacman;
    }
}
