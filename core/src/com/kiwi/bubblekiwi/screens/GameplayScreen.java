package com.kiwi.bubblekiwi.screens;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.Align;
import com.kiwi.bubblekiwi.BubbleKiwiGame;
import com.kiwi.bubblekiwi.controllers.Assets;
import com.kiwi.bubblekiwi.controllers.GameplayContactListener;
import com.kiwi.bubblekiwi.controllers.LevelController;
import com.kiwi.bubblekiwi.controllers.MoveController;
import com.kiwi.bubblekiwi.data.PlayerConfiguration;
import com.kiwi.bubblekiwi.entities.*;

import java.util.HashMap;

public class GameplayScreen extends AbstractScreen {
    private ParallaxBackground background;
    private Player player;
    private BubblesController bubblesController;
    private LevelController levelController;
    private World world;
    private Box2DDebugRenderer debugRenderer;
    private Label points;
    private Label lives;
    private Label gameOver;
    private MoveController moveController;
    private GameplayBoundary ground;

    public GameplayScreen(BubbleKiwiGame game, Assets assets) {
        super(game, assets);
    }

    @Override
    protected void initialize() {
        backgroundColor = Color.BLACK;
        initializeAssets();
        initializeWorld();
        initializeBackground();
        initializeBoundaries();
        initializePlayer();
        initializeBubblesController();
        levelController = new LevelController();
        initializePointsLabel();
        initializeLivesIndicator();
        initializeGameOverMessage();
        world.setContactListener(new GameplayContactListener(player, ground, levelController));
        debugRenderer = new Box2DDebugRenderer();
        moveController = new MoveController(player);
    }

    private void initializeAssets() {
        assets.loadGameplayScreen();
    }

    private void initializeWorld() {
        world = new World(new Vector2(0, -10.0f), true);
    }

    private void initializeBackground() {
        ParallaxLayer farClouds = new ParallaxLayer(assets.get(Assets.farClouds), 0.002f);
        ParallaxLayer middleClouds = new ParallaxLayer(assets.get(Assets.middleClouds), 0.004f);
        ParallaxLayer mountains = new ParallaxLayer(assets.get(Assets.mountains), 0.0f);
        ParallaxLayer nearClouds = new ParallaxLayer(assets.get(Assets.nearClouds), 0.01f);
        ParallaxLayer fog = new ParallaxLayer(assets.get(Assets.fog), -0.0025f);
        ParallaxLayer ground = new ParallaxLayer(assets.get(Assets.ground), 0.0f);
        background = new ParallaxBackground(new ParallaxLayer[]{
                farClouds,
                middleClouds,
                mountains,
                nearClouds,
                fog,
                ground
        });
        worldStage.addActor(background);
    }

    private void initializeBoundaries() {
        new GameplayBoundary(world, GameplayBoundaryType.UP);
        new GameplayBoundary(world, GameplayBoundaryType.LEFT);
        new GameplayBoundary(world, GameplayBoundaryType.RIGHT);
        ground = new GameplayBoundary(world, GameplayBoundaryType.DOWN);
    }

    private void initializePlayer() {
        player = new Player(world, assets, new PlayerConfiguration(0.05f, 1.8f));
        worldStage.addActor(player);
    }

    private void initializeBubblesController() {
        bubblesController = new BubblesController(world, assets);
        worldStage.addActor(bubblesController);
    }

    private void initializePointsLabel() {
        Label.LabelStyle labelStyle = new Label.LabelStyle();
        labelStyle.font = assets.get(Assets.arialMedium);
        labelStyle.fontColor = Color.GOLD;
        points = new Label(String.format("%d", levelController.getPoints()), labelStyle);
        points.setAlignment(Align.left);
        points.setWrap(true);
        points.setWidth(BubbleKiwiGame.WIDTH / 3.0f);
        points.setPosition(25.0f, BubbleKiwiGame.HEIGHT - 75.0f);
        stage.addActor(points);
    }

    private void initializeLivesIndicator() {
        Label.LabelStyle labelStyle = new Label.LabelStyle();
        labelStyle.font = assets.get(Assets.arialMedium);
        labelStyle.fontColor = Color.RED;
        lives = new Label(String.format("%d", levelController.getLives()), labelStyle);
        lives.setAlignment(Align.right);
        lives.setWrap(true);
        lives.setWidth(BubbleKiwiGame.WIDTH / 3.0f);
        lives.setPosition(BubbleKiwiGame.WIDTH - lives.getWidth() - 25.0f, BubbleKiwiGame.HEIGHT - 75.0f);
        stage.addActor(lives);
    }

    private void initializeGameOverMessage() {
        Label.LabelStyle labelStyle = new Label.LabelStyle();
        labelStyle.font = assets.get(Assets.arialBig);
        labelStyle.fontColor = Color.RED;
        gameOver = new Label("Game Over", labelStyle);
        gameOver.setPosition((BubbleKiwiGame.WIDTH - gameOver.getWidth()) / 2.0f, (BubbleKiwiGame.HEIGHT - gameOver.getHeight()) / 2.0f);
    }

    @Override
    public void render(float delta) {
        super.render(delta);

        world.step(delta, 6, 2);

        update(delta);
        Matrix4 projectionMatrix = worldStage.getCamera().combined.cpy();
        worldStage.draw();
        stage.draw();
        debugRenderer.render(world, projectionMatrix);
    }

    private void update(float delta) {
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

        points.setText(String.format("%d", levelController.getPoints()));
        lives.setText(String.format("%d", levelController.getLives()));
        stage.act(delta);
    }
}
