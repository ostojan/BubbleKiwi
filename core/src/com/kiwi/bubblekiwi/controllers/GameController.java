package com.kiwi.bubblekiwi.controllers;

import com.kiwi.bubblekiwi.BubbleKiwiGame;
import com.kiwi.bubblekiwi.data.Level;
import com.kiwi.bubblekiwi.screens.GameplayScreen;

import java.util.List;

public class GameController {
    private BubbleKiwiGame game;
    private Assets assets;
    private List<Level> levels;

    public GameController(BubbleKiwiGame game, Assets assets) {
        this.game = game;
        this.assets = assets;
        levels = assets.getLevels();
    }

    public GameplayScreen getGameplayScreen() {
        return new GameplayScreen(game, assets, new LevelController(levels.get(0)));
    }
}
