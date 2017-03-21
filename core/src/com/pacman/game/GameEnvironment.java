package com.pacman.game;

import com.pacman.gameObjects.Map;
import com.pacman.gameObjects.Pacman;

/**
 * This class does updates the frames
 */
public class GameEnvironment {
    private Pacman pacman;
    private Map map;

    public GameEnvironment(){
        pacman = new Pacman(100,100,0);
        map = new Map();
    }


    public void update(float delta) {
        pacman.update(delta);
    }


    public Pacman getPacman() {
        return pacman;
    }

    public Map getMap(){
        return map;
    }
}
