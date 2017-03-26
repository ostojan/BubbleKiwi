package com.kiwi.bubblekiwi.screens;

import com.badlogic.gdx.graphics.Color;
import com.kiwi.bubblekiwi.BubbleKiwiGame;

public class GameplayScreen extends AbstractScreen {
    public GameplayScreen(BubbleKiwiGame game) {
        super(game);
    }

    @Override
    protected void initialize() {
        backgroundColor = Color.BLUE;
    }
}
