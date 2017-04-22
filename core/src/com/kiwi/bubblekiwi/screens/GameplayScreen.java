package com.kiwi.bubblekiwi.screens;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.kiwi.bubblekiwi.BubbleKiwiGame;
import com.kiwi.bubblekiwi.controllers.Assets;
import com.kiwi.bubblekiwi.controllers.LevelController;
import com.kiwi.bubblekiwi.entities.*;
import com.kiwi.bubblekiwi.controllers.GameplayContactListener;
import com.kiwi.bubblekiwi.ui.MoveButton;

import java.util.HashMap;

public class GameplayScreen extends AbstractScreen {
    private ParallaxBackground background;
    private Player player;
    private BubblesController bubblesController;
    private LevelController levelController;
    private MoveButton leftButton;
    private MoveButton rightButton;
    private MoveButton jumpButton;
    private World world;
    private Box2DDebugRenderer debugRenderer;
    private HashMap<GameplayBoundaryType, GameplayBoundary> boundaries;
    private Label points;

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
        initializePlayerControlButtons();
        initializePointsLabel();
        levelController = new LevelController();
        world.setContactListener(new GameplayContactListener(player,
                boundaries.get(GameplayBoundaryType.DOWN), levelController));
        debugRenderer = new Box2DDebugRenderer();
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
        boundaries = new HashMap<GameplayBoundaryType, GameplayBoundary>();
        boundaries.put(GameplayBoundaryType.LEFT,
                new GameplayBoundary(world, GameplayBoundaryType.LEFT));
        boundaries.put(GameplayBoundaryType.RIGHT,
                new GameplayBoundary(world, GameplayBoundaryType.RIGHT));
        boundaries.put(GameplayBoundaryType.UP,
                new GameplayBoundary(world, GameplayBoundaryType.UP));
        boundaries.put(GameplayBoundaryType.DOWN,
                new GameplayBoundary(world, GameplayBoundaryType.DOWN));
    }

    private void initializePlayer() {
        player = new Player(world, assets);
        worldStage.addActor(player);
    }

    private void initializeBubblesController() {
        bubblesController = new BubblesController(world, assets);
        worldStage.addActor(bubblesController);
    }

    private void initializePlayerControlButtons() {
        initializeLeftControlButton();
        initializeRightControlButton();
        initializeJumpControlButton();
    }

    private void initializePointsLabel() {
        Label.LabelStyle labelStyle = new Label.LabelStyle();
        labelStyle.font = assets.get(Assets.arialMedium);
        points = new Label("0", labelStyle);
        points.setPosition(25.0f, BubbleKiwiGame.HEIGHT - 75.0f);
        stage.addActor(points);
    }

    private void initializeLeftControlButton() {
        leftButton = new MoveButton(MoveButton.MoveButtonTypes.LEFT, assets);
        stage.addActor(leftButton);
    }

    private void initializeRightControlButton() {
        rightButton = new MoveButton(MoveButton.MoveButtonTypes.RIGHT, assets);
        stage.addActor(rightButton);
    }

    private void initializeJumpControlButton() {
        jumpButton = new MoveButton(MoveButton.MoveButtonTypes.JUMP, assets);
        stage.addActor(jumpButton);
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
        if (leftButton.isPressed()) {
            player.moveLeft();
        }
        if (rightButton.isPressed()) {
            player.moveRight();
        }
        if (jumpButton.isPressed()) {
            player.jump();
        }
        worldStage.act(delta);

        points.setText(String.format("%d", levelController.getPoints()));
        stage.act(delta);
    }
}
