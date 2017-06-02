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
    public enum Fruits{CHERRY, STRAWBERRY }
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

    public int getPoints()
    {
        return points;
    }

    public TextureRegion getTexture()
    {
        return textureRegion;
    }
}
