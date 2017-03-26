package com.kiwi.bubblekiwi;

import com.badlogic.gdx.Game;
import com.kiwi.bubblekiwi.screens.SplashScreen;

public class BubbleKiwiGame extends Game {
    public final static String TITLE = "Bubble Kiwi";
    public final static int WIDTH = 800;
    public final static int HEIGHT = 450;

    private boolean paused;

	@Override
	public void create () {
		setScreen(new SplashScreen(this));
	}

    public boolean isPaused() {
        return paused;
    }

    public void setPaused(boolean paused) {
        this.paused = paused;
    }
}
