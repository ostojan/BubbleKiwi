package com.kiwi.bubblekiwi.screens;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.kiwi.bubblekiwi.BubbleKiwiGame;
import com.kiwi.bubblekiwi.world.actors.bubble.BubblesController;
import com.kiwi.bubblekiwi.controllers.Assets;
import com.kiwi.bubblekiwi.controllers.GameplayContactListener;
import com.kiwi.bubblekiwi.controllers.LevelController;
import com.kiwi.bubblekiwi.controllers.MoveController;
import com.kiwi.bubblekiwi.data.Background;
import com.kiwi.bubblekiwi.data.PlayerConfiguration;
import com.kiwi.bubblekiwi.ui.GameOver;
import com.kiwi.bubblekiwi.ui.Lives;
import com.kiwi.bubblekiwi.ui.Points;

import java.util.ArrayList;
import java.util.List;

public class GameplayScreen extends AbstractScreen {
    private com.kiwi.bubblekiwi.world.actors.Player player;
    private LevelController levelController;
    private World world;
    private GameOver gameOver;
    private MoveController moveController;
    private com.kiwi.bubblekiwi.world.actors.GameplayBoundary ground;

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
        List<com.kiwi.bubblekiwi.world.actors.ParallaxLayer> layers = new ArrayList<com.kiwi.bubblekiwi.world.actors.ParallaxLayer>();
        for (Background background : levelController.getBackgrounds()) {
            Texture layerTexture = assets.get(background.getAssetDescriptor());
            layers.add(new com.kiwi.bubblekiwi.world.actors.ParallaxLayer(layerTexture, BubbleKiwiGame.WIDTH, BubbleKiwiGame.HEIGHT, background.getSpeed()));
        }
        com.kiwi.bubblekiwi.world.actors.ParallaxImage background = new com.kiwi.bubblekiwi.world.actors.ParallaxImage(layers);

        new com.kiwi.bubblekiwi.world.actors.GameplayBoundary(world, com.kiwi.bubblekiwi.world.actors.GameplayBoundaryType.UP);
        new com.kiwi.bubblekiwi.world.actors.GameplayBoundary(world, com.kiwi.bubblekiwi.world.actors.GameplayBoundaryType.LEFT);
        new com.kiwi.bubblekiwi.world.actors.GameplayBoundary(world, com.kiwi.bubblekiwi.world.actors.GameplayBoundaryType.RIGHT);
        this.ground = new com.kiwi.bubblekiwi.world.actors.GameplayBoundary(world, com.kiwi.bubblekiwi.world.actors.GameplayBoundaryType.DOWN);

        player = new com.kiwi.bubblekiwi.world.actors.Player(world, assets, new PlayerConfiguration(0.05f, 1.8f));
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
