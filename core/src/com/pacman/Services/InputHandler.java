package com.pacman.Services;

import com.badlogic.gdx.InputProcessor;
import com.pacman.gameObjects.Map;
import com.pacman.gameObjects.Pacman;

/**
 * Created by YonahKarp on 3/6/17.
 */
public class InputHandler implements InputProcessor {

    private Pacman pacman;
    private Map map;

    public InputHandler(Pacman pacman, Map map) {
        this.pacman = pacman;
        this.map = map;
    }

    @Override
    public boolean keyDown(int keycode) {

        switch (keycode){

            case 21://left

                pacman.move('l');
                break;
            case 19://up
                pacman.move('u');
                break;
            case 22://right
                pacman.move('r');
                break;
            case 20://down
                pacman.move('d');
                break;
            default:
                break;
        }
        return true; //input handled
    }

    /*private boolean pacmanCanMove(char direction)
    {
        float[] newPosition = this.pacman.getNewPositionFromDirection(direction, this.pacman.getX(), this.pacman.getY());
        float posX = ;
        float posY = this.pacman.getY();

        char nextTile = this.map.getTileFromPosition(posX, posY);
        boolean tileIsValidMovementTile = this.map.tileIsValidMovementTile(nextTile);
        System.out.println("Next Tile: " + nextTile);
        System.out.println("Tile Is Valid Movement: " + tileIsValidMovementTile);

        return tileIsValidMovementTile;
    }*/

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }
}
