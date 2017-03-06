package com.pacman.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.pacman.game.PacmanGame;

//This file is purely meant for running purposes. All edits should be made in the "core" directory
public class DesktopLauncher {


	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();

		config.title = "PacMan";
		config.width = 600;
		config.height = 800;



		new LwjglApplication(new PacmanGame(), config);
	}
}
