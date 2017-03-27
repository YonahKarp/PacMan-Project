package com.pacman.game;

import com.pacman.Services.AssetLoader;
import com.pacman.gameObjects.Ghost;
import com.pacman.gameObjects.Map;
import com.pacman.gameObjects.Pacman;


/**
 * This class does updates the frames
 */
public class GameEnvironment {
    private Pacman pacman;
    private Map map;
    //private RedGhost redGhost;

    private Ghost[] ghosts;


    public GameEnvironment(){
        map = new Map();
        pacman = new Pacman(72,144f,0); //we pass the map for collision detection
        ghosts = new Ghost[]{
                new Ghost(70, 80, AssetLoader.redGhost),
                new Ghost(70, 90, AssetLoader.blueGhost),
                new Ghost(70, 85, AssetLoader.pinkGhost),
                new Ghost(70, 75,AssetLoader.orangeGhost)
        };

    }


    public void update(float delta) {
        pacman.update(delta);
        for (Ghost ghost: ghosts) {
            ghost.update(delta);
        }


    }


    public Pacman getPacman() {
        return pacman;
    }

    public Map getMap(){
        return map;
    }

    public Ghost[] getGhosts() { return ghosts; }
}
