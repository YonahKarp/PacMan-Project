package com.pacman.game;

import com.pacman.Services.AssetLoader;
import com.pacman.gameObjects.*;


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
        RedGhost redGhost = new RedGhost(AssetLoader.redGhost);
        ghosts = new Ghost[]{
                redGhost,
                new BlueGhost(redGhost, AssetLoader.blueGhost),
                new PinkGhost(AssetLoader.pinkGhost),
                new OrangeGhost(AssetLoader.orangeGhost)
        };

    }


    public void update(float delta) {
        pacman.update(delta);
        for (Ghost ghost: ghosts) {
            ghost.update(delta, pacman);
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
