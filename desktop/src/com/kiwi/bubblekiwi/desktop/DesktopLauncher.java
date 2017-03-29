package com.kiwi.bubblekiwi.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.kiwi.bubblekiwi.BubbleKiwiGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = BubbleKiwiGame.TITLE;
		config.width = (int) BubbleKiwiGame.WIDTH;
		config.height = (int) BubbleKiwiGame.HEIGHT;
		config.resizable = false;
		new LwjglApplication(new BubbleKiwiGame(), config);
	}
}
