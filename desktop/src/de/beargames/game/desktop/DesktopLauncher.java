package de.beargames.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import de.beargames.game.ColorClash;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "Color Clash";
		config.width = 400;
		config.height = 600;
		new LwjglApplication(new ColorClash(), config);
	}
}
