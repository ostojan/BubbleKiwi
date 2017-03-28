package com.kiwi.bubblekiwi.screens;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
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

    public GameplayScreen(BubbleKiwiGame game) {
        super(game);
    }

    @Override
    protected void initialize() {
        backgroundColor = Color.BLACK;
        initializeBackground();
        initializePlayer();
        initializeBubblesController();
        initializePlayerControlButtons();
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
        bubblesController = new BubblesController();
        stage.addActor(bubblesController);
    }

    private void initializePlayerControlButtons() {
        initializeLeftControlButton();
        initializeRightControlButton();
        initializeJumpControlButton();
    }

    private void initializeLeftControlButton() {
        leftButton = new Button(new Button.ButtonStyle());
        leftButton.setSize(120, 120);
        leftButton.setPosition(20, 20);
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
        rightButton.setSize(120, 120);
        rightButton.setPosition(160, 20);
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
        jumpButton.setSize(120, 120);
        jumpButton.setPosition(BubbleKiwiGame.WIDTH - 140, 20);
        jumpButton.setDebug(true);
        stage.addActor(jumpButton);
    }

    @Override
    public void render(float delta) {
        super.render(delta);

        update(delta);

        spriteBatch.begin();
        stage.draw();
        spriteBatch.end();
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
