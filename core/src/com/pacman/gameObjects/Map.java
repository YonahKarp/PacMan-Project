package com.pacman.gameObjects;

import com.badlogic.gdx.graphics.TextureArray;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.pacman.Services.AssetLoader;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by YonahKarp on 3/13/17.
 */
public class Map {

    public Map(){
        textureMap = createTextureMap(mapString);
    }

    //static string can be called anywhere
    //                          123456789     15   20   25 28
    static String mapString =  "╔════════════┱┲════════════╗"+
                               "║............├┤............║"+
                               "║.╭┴┴╮.╭┴┴┴╮.├┤.╭┴┴┴╮.╭┴┴╮.║"+
                               "║o┤  ├.┤   ├.├┤.┤   ├.┤  ├o║"+
                               "║.╰┬┬╯.╰┬┬┬╯.└┘.╰┬┬┬╯.╰┬┬╯.║"+
                               "║..........................║"+
                               "║.╭┴┴╮.╭╮.╭┴┴┴┴┴┴╮.╭╮.╭┴┴╮.║"+
                               "║.╰┬┬╯.┤├.╰┬┬┐┌┬┬╯.┤├.╰┬┬╯.║"+
                               "║......┤├....┤├....┤├......║"+
                               "╚════╗.┤└┴┴╮ ┤├ ╭┴┴┘├.╔════╝" +
                               "     ║.┤┌┬┬╯ ╰╯ ╰┬┬┐├.║     " +
                               "     ║.┤├          ┤├.║     " +
                               "     ║.┤├ ┏══  ══┓ ┤├.║     " +
                               "═════╝.╰╯ ║xx  xx║ ╰╯.╚═════" +
                               "      .   ║xx  xx║   .      " +
                               "═════╗.╭╮ ║xx  xx║ ╭╮.╔═════" +
                               "     ║.┤├ ┗══════┛ ┤├.║     " +
                               "     ║.┤├          ┤├.║     " +
                               "     ║.┤├ ╭┴┴┴┴┴┴╮ ┤├.║     " +
                               "╔════╝.╰╯ ╰┬┬┐┌┬┬╯ ╰╯.╚════╗" +
                               "║............┤├............║" +
                               "║.╭┴┴╮.╭┴┴┴╮.┤├.╭┴┴┴╮.╭┴┴╮.║" +
                               "║.╰┬┐├.╰┬┬┬╯.╰╯.╰┬┬┬╯.┤┌┬╯.║" +
                               "║o..┤├................┤├..o║" +
                               "┡┬┐.┤├.╭╮.╭┴┴┴┴┴┴╮.╭╮.┤├.┌┬┩" +
                               "┢┴┘.╰╯.┤├.╰┬┬┐┌┬┬╯.┤├.╰╯.└┴┪" +
                               "║......┤├....┤├....┤├......║" +
                               "║.╭┴┴┴┴┘└┴┴╮.┤├.╭┴┴┘└┴┴┴┴╮.║" +
                               "║.╰┬┬┬┬┬┬┬┬╯.╰╯.╰┬┬┬┬┬┬┬┬╯.║" +
                               "║..........................║" +
                               "╚══════════════════════════╝";

    static StringBuilder currMap = new StringBuilder(mapString);

    public static TextureRegion[][] textureMap = createTextureMap(mapString);

    public char getTileFromPosition(float x, float y)
    {
        char[] mapStringArray = mapString.toCharArray();

        //Hard coding tile size for now but this should probably be dynamically declared
        int tileSize = 8;
        int tilePosX = (int)(x / tileSize);
        int tilePosY = (int)(y / tileSize);

        System.out.println("Currently at position: " + x + ", " + y);
        System.out.println("Fetch tile at pos: " + tilePosX + ", " + tilePosY);
        System.out.println("Fetch tile from array at: " + (tilePosX * tilePosY));
        return mapStringArray[tilePosX * tilePosY];
    }

    public boolean tileIsValidMovementTile(char t)
    {
        return t == ' ' || t == '.' || t == 'o' || t == 'R';
    }

    /*
    String mapString2 = "╔════════════┱┲════════════╗"+
                        "║............┃┃............║"+
                        "║.╭──╮.╭───╮.┃┃.╭───╮.╭──╮.║"+
                        "║o│  │.│   │.┃┃.│   │.│  │o║"+
                        "║.╰──╯.╰───╯.┖┚.╰───╯.╰──╯.║"+
                        "║..........................║"+
                        "║.╭──╮.╭╮.╭──────╮.╭╮.╭──╮.║"+
                        "║.╰──╯.││.╰──╮╭──╯.││.╰──╯.║"+
                        "║......││....││....││......║"+
                        "╚════╗.│╰──╮ ││ ╭──╯│.╔════╝" +
                        "     ║.│╭──╯ ╰╯ ╰──╮│.║     " +
                        "     ║.││    R     ││.║     " +
                        "     ║.││ ┏══┄┄══┓ ││.║     " +
                        "═════╝.╰╯ ║      ║ ╰╯.╚═════" +
                        "      .   ║B P O ║   .      " +
                        "═════╗.╭╮ ║      ║ ╭╮.╔═════" +
                        "     ║.││ ┗══════┛ ││.║     " +
                        "     ║.││          ││.║     " +
                        "     ║.││ ╭──────╮ ││.║     " +
                        "╔════╝.╰╯ ╰──╮╭──╯ ╰╯.╚════╗" +
                        "║............││............║" +
                        "║.╭──╮.╭───╮.││.╭───╮.╭──╮.║" +
                        "║.╰─╮│.╰───╯.╰╯.╰───╯.│╭─╯.║" +
                        "║o..││................││..o║" +
                        "┡─╮.││.╭╮.╭──────╮.╭╮.││.┍━┩" +
                        "┢─╯.╰╯.││.╰──╮╭──╯.││.╰╯.┕━┪" +
                        "║......││....││....││......║" +
                        "║.╭────╯╰──╮ ││.╭──╯╰────╮.║" +
                        "║.╰────────╯.╰╯.╰────────╯.║" +
                        "║..........................║" +
                        "╚══════════════════════════╝";
    */

    private static TextureRegion[][] createTextureMap(String mapString){


        char[] letters = mapString.toCharArray();
        TextureRegion[][] map = new TextureRegion[31][28];



        for (int i = 0; i < letters.length; i++) {

            switch (letters[i]){
                //case '╧':
                    //map[i/28][i%28] = AssetLoader.mazeTiles[1][10]; //remember, it's flipped in y down
                    //break;
                case '═':
                    map[i/28][i%28] = AssetLoader.mazeTiles[0][10];
                    break;
                //case '╢':
                   // map[i/28][i%28] = AssetLoader.mazeTiles[0][3];
                    //break;
                case '║':
                    map[i/28][i%28] = AssetLoader.mazeTiles[0][2];
                    break;

                case '┴':
                    map[i/28][i%28] = AssetLoader.mazeTiles[1][11];
                    break;
                case '┬':
                    map[i/28][i%28] = AssetLoader.mazeTiles[0][11];
                    break;
                case '├':
                    map[i/28][i%28] = AssetLoader.mazeTiles[1][9];
                    break;
                case '┤':
                    map[i/28][i%28] = AssetLoader.mazeTiles[1][8];
                    break;

                case '╔':
                    map[i/28][i%28] = AssetLoader.mazeTiles[1][1];
                    break;
                case '╗':
                    map[i/28][i%28] = AssetLoader.mazeTiles[1][0];
                    break;
                case '╚':
                    map[i/28][i%28] = AssetLoader.mazeTiles[0][1];
                    break;
                case '╝':
                    map[i/28][i%28] = AssetLoader.mazeTiles[0][0];
                    break;

                case '╭':
                    map[i/28][i%28] = AssetLoader.mazeTiles[2][4]; //flipped
                    break;
                case '╮':
                    map[i/28][i%28] = AssetLoader.mazeTiles[2][5];
                    break;
                case '╰':
                    map[i/28][i%28] = AssetLoader.mazeTiles[1][4];
                    break;
                case '╯':
                    map[i/28][i%28] = AssetLoader.mazeTiles[1][5];
                    break;

                case '┏':
                    map[i/28][i%28] = AssetLoader.mazeTiles[1][12]; //flipped
                    break;
                case '┓':
                    map[i/28][i%28] = AssetLoader.mazeTiles[1][13];
                    break;
                case '┗':
                    map[i/28][i%28] = AssetLoader.mazeTiles[0][12];
                    break;
                case '┛':
                    map[i/28][i%28] = AssetLoader.mazeTiles[0][13];
                    break;

                case '┌':
                    map[i/28][i%28] = AssetLoader.mazeTiles[2][9];
                    break;
                case '┐':
                    map[i/28][i%28] = AssetLoader.mazeTiles[2][8];
                    break;
                case '└':
                    map[i/28][i%28] = AssetLoader.mazeTiles[0][9];
                    break;
                case '┘':
                    map[i/28][i%28] = AssetLoader.mazeTiles[0][8];
                    break;

                case '┱':
                    map[i/28][i%28] = AssetLoader.mazeTiles[2][6];

                    break;
                case '┲':
                    map[i/28][i%28] = AssetLoader.mazeTiles[2][7];
                    break;
                case '┩':
                    map[i/28][i%28] = AssetLoader.mazeTiles[1][6];
                    break;
                case '┪':
                    map[i/28][i%28] = AssetLoader.mazeTiles[0][6];
                    break;
                case '┡':
                    map[i/28][i%28] = AssetLoader.mazeTiles[1][7];
                    break;
                case '┢':
                    map[i/28][i%28] = AssetLoader.mazeTiles[0][7];
                    break;


                case '.':
                    map[i/28][i%28] = AssetLoader.mazeTiles[2][10];
                    break;
                case 'o':
                    map[i/28][i%28] = new PowerPellet(); //hacky, but it works
                    break;

                case ' ':
                default:
                    map[i/28][i%28] = AssetLoader.mazeTiles[2][13];
                    break;
            }



        }

        return map;
    }
}
