package com.kiwi.bubblekiwi.screens;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.kiwi.bubblekiwi.BubbleKiwiGame;
import com.kiwi.bubblekiwi.controllers.Assets;
import com.kiwi.bubblekiwi.controllers.BubblesController;
import com.kiwi.bubblekiwi.controllers.GameplayContactListener;
import com.kiwi.bubblekiwi.entities.GameplayBoundary;
import com.kiwi.bubblekiwi.entities.ParallaxBackground;
import com.kiwi.bubblekiwi.entities.ParallaxLayer;
import com.kiwi.bubblekiwi.entities.Player;

import java.util.HashMap;

public class GameplayScreen extends AbstractScreen {
    private ParallaxBackground background;
    private Player player;
    private BubblesController bubblesController;
    private Button leftButton;
    private Button rightButton;
    private Button jumpButton;
    private World world;
    private Box2DDebugRenderer debugRenderer;
    private HashMap<GameplayBoundary.GameplayBoundaryTypes, GameplayBoundary> boundaries;

    public GameplayScreen(BubbleKiwiGame game, Assets assets) {
        super(game, assets, true);
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
        world.setContactListener(new GameplayContactListener(player,
                bubblesController,
                boundaries.get(GameplayBoundary.GameplayBoundaryTypes.DOWN)));
        debugRenderer = new Box2DDebugRenderer();
    }

    private void initializeAssets() {
        assets.loadGameplayScreen();
    }

    private void initializeWorld() {
        world = new World(new Vector2(0, -10.0f), true);
    }

    private void initializeBackground() {
        ParallaxLayer farClouds = new ParallaxLayer(assets.get(Assets.farClouds), 0.004f);
        ParallaxLayer mountains = new ParallaxLayer(assets.get(Assets.mountains), 0.0f);
        ParallaxLayer nearClouds = new ParallaxLayer(assets.get(Assets.nearClouds), 0.01f);
        ParallaxLayer fog = new ParallaxLayer(assets.get(Assets.fog), -0.0025f);
        ParallaxLayer ground = new ParallaxLayer(assets.get(Assets.ground), 0.0f);
        background = new ParallaxBackground(new ParallaxLayer[]{
                farClouds,
                mountains,
                nearClouds,
                fog,
                ground
        });
        stage.addActor(background);
    }

    private void initializeBoundaries() {
        boundaries = new HashMap<GameplayBoundary.GameplayBoundaryTypes, GameplayBoundary>();
        boundaries.put(GameplayBoundary.GameplayBoundaryTypes.LEFT,
                new GameplayBoundary(world, GameplayBoundary.GameplayBoundaryTypes.LEFT));
        boundaries.put(GameplayBoundary.GameplayBoundaryTypes.RIGHT,
                new GameplayBoundary(world, GameplayBoundary.GameplayBoundaryTypes.RIGHT));
        boundaries.put(GameplayBoundary.GameplayBoundaryTypes.DOWN,
                new GameplayBoundary(world, GameplayBoundary.GameplayBoundaryTypes.DOWN));
    }

    private void initializePlayer() {
        player = new Player(world, assets);
        stage.addActor(player);
    }

    private void initializeBubblesController() {
        bubblesController = new BubblesController(world, assets);
        stage.addActor(bubblesController);
    }

    private void initializePlayerControlButtons() {
        initializeLeftControlButton();
        initializeRightControlButton();
        initializeJumpControlButton();
    }

    private void initializeLeftControlButton() {
        leftButton = new Button(new Button.ButtonStyle());
        leftButton.setSize(120.0f / BubbleKiwiGame.PPM, 120.0f / BubbleKiwiGame.PPM);
        leftButton.setPosition(20.0f / BubbleKiwiGame.PPM, 20.0f / BubbleKiwiGame.PPM);
        leftButton.setDebug(true);
        stage.addActor(leftButton);
    }

    private void initializeRightControlButton() {
        rightButton = new Button(new Button.ButtonStyle());
        rightButton.setSize(120.0f / BubbleKiwiGame.PPM, 120.0f / BubbleKiwiGame.PPM);
        rightButton.setPosition(160.0f / BubbleKiwiGame.PPM, 20.0f / BubbleKiwiGame.PPM);
        rightButton.setDebug(true);
        stage.addActor(rightButton);
    }

    private void initializeJumpControlButton() {
        jumpButton = new Button(new Button.ButtonStyle());
        jumpButton.setSize(120.0f / BubbleKiwiGame.PPM, 120.0f / BubbleKiwiGame.PPM);
        jumpButton.setPosition((BubbleKiwiGame.WIDTH - 140.0f) / BubbleKiwiGame.PPM, 20.0f / BubbleKiwiGame.PPM);
        jumpButton.setDebug(true);
        stage.addActor(jumpButton);
    }

    @Override
    public void render(float delta) {
        super.render(delta);

        world.step(delta, 6, 2);

        update(delta);
        Matrix4 projectionMatrix = spriteBatch.getProjectionMatrix().cpy();
        spriteBatch.begin();
        stage.draw();
        spriteBatch.end();
        debugRenderer.render(world, projectionMatrix);
    }

    private void update(float delta) {
        background.update(delta);
        if (leftButton.isPressed()) {
            player.moveLeft();
        }
        if (rightButton.isPressed()) {
            player.moveRight();
        }
        if (jumpButton.isPressed()) {
            player.jump();
        }
        player.update(delta);
        bubblesController.update(delta);
    }
}
