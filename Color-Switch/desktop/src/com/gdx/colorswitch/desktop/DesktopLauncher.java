package com.gdx.colorswitch.desktop;

import com.badlogic.gdx.Files;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.gdx.colorswitch.ColorSwitch;

/**
 * classe qui contient le main
 * @author Dany Brégeon, Loïs Monet, Maxime Poirier
 *
 */

public class DesktopLauncher {
	
	/**
	 * contient les paramètres de configuration de l'application sur ordinateur
	 * @param arg
	 */
	
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		if(LwjglApplicationConfiguration.getDesktopDisplayMode().height > 850) {
			config.height = 816;
			config.width = 544;
		}else {
			config.height = (int) (LwjglApplicationConfiguration.getDesktopDisplayMode().height*(5/6f));
			config.width = (int) (config.height*(2f/3f));
		}
		config.title = "Color Switch";
		config.resizable = false;
		config.addIcon("icone128.png", Files.FileType.Internal);
		config.addIcon("icone32.png", Files.FileType.Internal);
		config.addIcon("icone16.png", Files.FileType.Internal);
		config.vSyncEnabled = true;
		new LwjglApplication(new ColorSwitch(), config);
	}
}
