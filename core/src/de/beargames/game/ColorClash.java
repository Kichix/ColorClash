package de.beargames.game;

import com.badlogic.gdx.Game;
import de.beargames.screens.GameScreen;
import de.beargames.screens.MainMenu;

public class ColorClash extends Game {

	@Override
	public void create() {
		setScreen(new GameScreen(this));
	}
}
