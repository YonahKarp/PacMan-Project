package com.pacman.gameObjects;

import com.badlogic.gdx.graphics.TextureArray;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.pacman.Services.AssetLoader;

import java.util.ArrayList;

/**
 * Created by YonahKarp on 3/13/17.
 */
public class Map {
    //                  123456789     15   20   25
    String mapString = "╔╧╧╧╧╧╧╧╧╧╧╧╧┱┲╧╧╧╧╧╧╧╧╧╧╧╧╗"+
                       "╢............┤├............╟"+
                       "╢.╭┴┴╮.╭┴┴┴╮.┤├.╭┴┴┴╮.╭┴┴╮.╟"+
                       "╢o┤  ├.┤   ├.┤├.┤   ├.┤  ├o╟"+
                       "╢.╰┬┬╯.╰┬┬┬╯.╰╯.╰┬┬┬╯.╰┬┬╯.╟"+
                       "╢..........................╟"+
                       "╢.╭┴┴╮.╭╮.╭┴┴┴┴┴┴╮.╭╮.╭┴┴╮.╟"+
                       "╢.╰┬┬╯.┤├.╰┬┬╮╭┬┬╯.┤├.╰┬┬╯.╟"+
                       "╢......┤├....┤├....┤├......╟"+
                       "╚╧╧╧╧╗.┤╰┴┴╮ ┤├ ╭┴┴╯├.╔╧╧╧╧╝" +
                       "     ╢.┤╭┬┬╯ ╰╯ ╰┬┬╮├.╟     " +
                       "     ╢.┤├    R     ┤├.╟     " +
                       "     ╢.┤├ ┏╧╧┄┄╧╧┓ ┤├.╟     " +
                       "╤╤╤╤╤╝.╰╯ ╢      ╟ ╰╯.╚╤╤╤╤╤" +
                       "      .   ╢B P O ╟   .      " +
                       "╧╧╧╧╧╗.╭╮ ╢      ╟ ╭╮.╔╧╧╧╧╧" +
                       "     ╢.┤├ ┗╤╤╤╤╤╤┛ ┤├.╟     " +
                       "     ╢.┤├          ┤├.╟     " +
                       "     ╢.┤├ ╭┴┴┴┴┴┴╮ ┤├.╟     " +
                       "╔╤╤╤╤╝.╰╯ ╰┬┬╮╭┬┬╯ ╰╯.╚╤╤╤╤╗" +
                       "╢............┤├............╟" +
                       "╢.╭┴┴╮.╭┴┴┴╮.┤├.╭┴┴┴╮.╭┴┴╮.╟" +
                       "╢.╰┬╮├.╰┬┬┬╯.╰╯.╰┬┬┬╯.┤╭┬╯.╟" +
                       "╢o..┤├............... ┤├..o╟" +
                       "┡┴╮.┤├.╭╮.╭┴┴┴┴┴┴╮.╭╮.┤├.╭┴┩" +
                       "┢┬╯.╰╯.┤├.╰┬┬╮╭┬┬╯.┤├.╰╯.╰┬┪" +
                       "╢......┤├....┤├....┤├......╟" +
                       "╢.╭┴┴┴┴╯╰┴┴╮.┤├.╭┴┴╯╰┴┴┴┴╮.╟" +
                       "╢.╰┬┬┬┬┬┬┬┬╯.╰╯.╰┬┬┬┬┬┬┬┬╯.╟" +
                       "╢..........................╟" +
                       "╚╤╤╤╤╤╤╤╤╤╤╤╤╤╤╤╤╤╤╤╤╤╤╤╤╤╤╝";

    TextureRegion[][] textureMap = createTextureMap(mapString);

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
                        "║o..││............... ││..o║" +
                        "┡─╮.││.╭╮.╭──────╮.╭╮.││.┍━┩" +
                        "┢─╯.╰╯.││.╰──╮╭──╯.││.╰╯.┕━┪" +
                        "║......││....││....││......║" +
                        "║.╭────╯╰──╮ ││.╭──╯╰────╮.║" +
                        "║.╰────────╯.╰╯.╰────────╯.║" +
                        "║..........................║" +
                        "╚══════════════════════════╝";
    */

    private TextureRegion[][] createTextureMap(String mapString){

        char[] letters = mapString.toCharArray();
        TextureRegion[][] map = new TextureRegion[28][31];


        for (int i = 0; i < letters.length; i++) {

            switch (letters[i]){
                case '╧':
                    break;
                case '╤':
                    break;
                case '╢':
                    break;
                case '╟':
                    break;

                case '┬':
                    break;
                case '┴':
                    break;
                case '├':
                    break;
                case '┤':
                    break;

                case '╔':
                    break;
                case '╗':
                    break;
                case '╚':
                    break;
                case '╝':
                    break;

                case '╭':
                    break;
                case '╮':
                    break;
                case '╰':
                    break;
                case '╯':
                    break;

                case '┏':
                    break;
                case '┓':
                    break;
                case '┗':
                    break;
                case '┛':
                    break;

                case '┱':
                    break;
                case '┲':
                    break;
                case '┩':
                    break;
                case '┪':
                    break;
                case '┡':
                    break;
                case '┢':
                    break;


                case '.':
                    break;
                case 'o':
                    break;


            }
            //AssetLoader.mazeTiles;

           //map[i/28][i%28] = ;
        }

        return map;
    }
}
