package com.kiwi.bubblekiwi;

import com.badlogic.gdx.Game;
import com.kiwi.bubblekiwi.screens.SplashScreen;

public class BubbleKiwiGame extends Game {
    public final static String TITLE = "Bubble Kiwi";
    public final static float WIDTH = 800.0f;
    public final static float HEIGHT = 450.0f;
    public final static float PPM = 200.0f;

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
