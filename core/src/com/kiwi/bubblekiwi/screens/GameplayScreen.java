package com.kiwi.bubblekiwi.screens;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.kiwi.bubblekiwi.BubbleKiwiGame;
import com.kiwi.bubblekiwi.controllers.BubblesController;
import com.kiwi.bubblekiwi.entities.ParallaxBackground;
import com.kiwi.bubblekiwi.entities.ParallaxLayer;
import com.kiwi.bubblekiwi.entities.Player;

public class GameplayScreen extends AbstractScreen {
    private ParallaxBackground background;
    private Player player;
    private BubblesController bubblesController;
    private Button leftButton;
    private Button rightButton;
    private Button jumpButton;
    private World world;
    private Box2DDebugRenderer debugRenderer;

    public GameplayScreen(BubbleKiwiGame game) {
        super(game, true);
    }

    @Override
    protected void initialize() {
        backgroundColor = Color.BLACK;
        initializeWorld();
        initializeBackground();
        initializePlayer();
        initializeBubblesController();
        initializePlayerControlButtons();
        debugRenderer = new Box2DDebugRenderer();
    }

    private void initializeWorld() {
        world = new World(new Vector2(0, -10.0f), true);
    }

    private void initializeBackground() {
        ParallaxLayer clouds = new ParallaxLayer(new Texture("clouds.png"), 0.01f);
        ParallaxLayer ground = new ParallaxLayer(new Texture("ground.png"), 0.0f);
        background = new ParallaxBackground(new ParallaxLayer[]{clouds, ground});
        stage.addActor(background);
    }

    private void initializePlayer() {
        player = new Player();
        stage.addActor(player);
    }

    private void initializeBubblesController() {
        bubblesController = new BubblesController(world);
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
        leftButton.addListener(new ClickListener() {
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                super.touchUp(event, x, y, pointer, button);
                player.stopMoving();
            }
        });
        stage.addActor(leftButton);
    }

    private void initializeRightControlButton() {
        rightButton = new Button(new Button.ButtonStyle());
        rightButton.setSize(120.0f / BubbleKiwiGame.PPM, 120.0f / BubbleKiwiGame.PPM);
        rightButton.setPosition(160.0f / BubbleKiwiGame.PPM, 20.0f / BubbleKiwiGame.PPM);
        rightButton.setDebug(true);
        rightButton.addListener(new ClickListener() {
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                super.touchUp(event, x, y, pointer, button);
                player.stopMoving();
            }
        });
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
