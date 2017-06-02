package com.pacman.gameObjects;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.pacman.Services.AssetLoader;

import javax.xml.soap.Text;

import static com.pacman.gameObjects.FruitGenerator.Fruits.CHERRY;

/**
 * Created by MNA on 6/2/2017.
 */
public class FruitGenerator {
    public enum Fruits{CHERRY, STRAWBERRY, ORANGE, APPLE, PINEAPPLE, SPACESHIP, BELL, KEY }
    private TextureRegion textureRegion;
    private Fruits fruit;
    private int points;

    public FruitGenerator(Fruits fruit){
        this.fruit = fruit;
       switch (fruit){
           case CHERRY:
               textureRegion = AssetLoader.cherry;
               points = 100;
               break;
           case STRAWBERRY:
               textureRegion = AssetLoader.strawberry;
               points = 300;
       }
    }

    public FruitGenerator(int level){

        switch (level){
            case 0:
                fruit = Fruits.CHERRY;
                textureRegion = AssetLoader.cherry;
                points = 100;
                break;
            case 1:
                fruit = Fruits.STRAWBERRY;
                textureRegion = AssetLoader.strawberry;
                points = 300;
            case 2:
            case 3:
                fruit = Fruits.ORANGE;
                textureRegion = AssetLoader.orange;
                points = 500;
                break;
            case 4:
            case 5:
                fruit = Fruits.APPLE;
                textureRegion = AssetLoader.apple;
                points = 700;
            case 6:
            case 7:
                fruit = Fruits.PINEAPPLE;
                textureRegion = AssetLoader.pineapple;
                points = 1000;
                break;
            case 8:
            case 9:
                fruit = Fruits.SPACESHIP;
                textureRegion = AssetLoader.spaceship;
                points = 2000;
            case 10:
            case 11:
                fruit = Fruits.BELL;
                textureRegion = AssetLoader.bell;
                points = 3000;
                break;
            default:
                fruit = Fruits.KEY;
                textureRegion = AssetLoader.key;
                points = 5000;
        }
    }

    public int getPoints()
    {
        return points;
    }

    public TextureRegion getTexture()
    {
        return textureRegion;
    }
}
