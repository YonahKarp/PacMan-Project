package com.pacman.game;

import com.pacman.gameObjects.Map;
import com.pacman.gameObjects.Pacman;
import com.pacman.gameObjects.RedGhost;

/**
 * This class does updates the frames
 */
public class GameEnvironment {
    private Pacman pacman;
    private Map map;
    private RedGhost redGhost;

    public GameEnvironment(){
        redGhost = new RedGhost(70,90);
        map = new Map();
        pacman = new Pacman(100,100, 32,0,map);
    }


    public void update(float delta) {
        pacman.update(delta);
        redGhost.update(delta);
    }


    public Pacman getPacman() {
        return pacman;
    }

    public Map getMap(){
        return map;
    }

    public RedGhost getRedGhost() { return redGhost; }
}
