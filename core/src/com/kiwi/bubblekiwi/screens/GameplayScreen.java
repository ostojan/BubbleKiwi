package com.kiwi.bubblekiwi.screens;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.kiwi.bubblekiwi.BubbleKiwiGame;
import com.kiwi.bubblekiwi.controllers.Assets;
import com.kiwi.bubblekiwi.controllers.GameplayContactListener;
import com.kiwi.bubblekiwi.controllers.LevelController;
import com.kiwi.bubblekiwi.controllers.MoveController;
import com.kiwi.bubblekiwi.data.Background;
import com.kiwi.bubblekiwi.data.PlayerConfiguration;
import com.kiwi.bubblekiwi.entities.*;
import com.kiwi.bubblekiwi.ui.GameOver;
import com.kiwi.bubblekiwi.ui.Lives;
import com.kiwi.bubblekiwi.ui.Points;

import java.util.ArrayList;
import java.util.List;

public class GameplayScreen extends AbstractScreen {
    private Player player;
    private LevelController levelController;
    private World world;
    private GameOver gameOver;
    private MoveController moveController;
    private GameplayBoundary ground;

    public GameplayScreen(BubbleKiwiGame game, Assets assets, LevelController levelController) {
        super(game, assets);
        this.levelController = levelController;
        initialize();
    }

    private void initialize() {
        initializeAssets();
        world = new World(new Vector2(0, -10.0f), true);
        initializeWorld();
        initializeUI();
        world.setContactListener(new GameplayContactListener(player, ground, levelController));
        moveController = new MoveController(player);
    }

    private void initializeAssets() {
        for (Background background : levelController.getBackgrounds()) {
            assets.load(background.getAssetDescriptor());
        }
        assets.loadGameplayScreen();
    }

    private void initializeWorld() {
        List<ParallaxLayer> layers = new ArrayList<ParallaxLayer>();
        for (Background background : levelController.getBackgrounds()) {
            Texture layerTexture = assets.get(background.getAssetDescriptor());
            layers.add(new ParallaxLayer(layerTexture, BubbleKiwiGame.WIDTH, BubbleKiwiGame.HEIGHT, background.getSpeed()));
        }
        ParallaxImage background = new ParallaxImage(layers);

        new GameplayBoundary(world, GameplayBoundaryType.UP);
        new GameplayBoundary(world, GameplayBoundaryType.LEFT);
        new GameplayBoundary(world, GameplayBoundaryType.RIGHT);
        this.ground = new GameplayBoundary(world, GameplayBoundaryType.DOWN);

        player = new Player(world, assets, new PlayerConfiguration(0.05f, 1.8f));
        BubblesController bubblesController = new BubblesController(world, assets);

        worldStage.addActor(background);
        worldStage.addActor(player);
        worldStage.addActor(bubblesController);
    }

    private void initializeUI() {
        Points points = new Points(assets, levelController);
        Lives lives = new Lives(assets, levelController);
        gameOver = new GameOver(assets);
        stage.addActor(points);
        stage.addActor(lives);
    }

    @Override
    public void render(float delta) {
        super.render(delta);

        update(delta);
        worldStage.draw();
        stage.draw();
    }

    private void update(float delta) {
        world.step(delta, 6, 2);
        levelController.addTime(delta);
        switch (levelController.getState()) {
            case PLAY:
                moveController.move();
                if (levelController.getLives() <= 0) {
                    stage.addActor(gameOver);
                    levelController.setState(LevelController.LevelState.GAME_OVER);
                }
                break;
            case GAME_OVER:
                if (levelController.getStateTime() > 3.0f) {
                    goToScreen(new MenuScreen(game, assets));
                }
                break;
        }

        worldStage.act(delta);
        stage.act(delta);
    }
}
