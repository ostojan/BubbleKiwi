package com.kiwi.bubblekiwi.controllers;

import com.kiwi.bubblekiwi.BubbleKiwiGame;
import com.kiwi.bubblekiwi.screens.GameplayScreen;

public class GameController {
    private BubbleKiwiGame game;
    private Assets assets;

    public GameController(BubbleKiwiGame game, Assets assets) {
        this.game = game;
        this.assets = assets;
    }

    public GameplayScreen getGameplayScreen() {
        return new GameplayScreen(game, assets, new LevelController());
    }
}
