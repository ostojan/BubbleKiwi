package com.kiwi.bubblekiwi.screens;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.kiwi.bubblekiwi.BubbleKiwiGame;
import com.kiwi.bubblekiwi.controllers.Assets;
import com.kiwi.bubblekiwi.controllers.GameController;
import com.kiwi.bubblekiwi.actors.ParallaxImage;
import com.kiwi.bubblekiwi.actors.ParallaxLayer;

import java.util.ArrayList;
import java.util.List;

public class MenuScreen extends AbstractScreen {
    private TextButton.TextButtonStyle buttonStyle;
    private GameController gameController;

    public MenuScreen(BubbleKiwiGame game, Assets assets) {
        super(game, assets);
        initialize();
    }

    private void initialize() {
        assets.loadMenuScreen();
        gameController = new GameController(game, assets);
        initializeBackground();
        initializeButtonsStyle();
        initializeNewGameButton();
        initializeHighScoreButton();
    }

    private void initializeBackground() {
        List<ParallaxLayer> layers = new ArrayList<ParallaxLayer>();
        layers.add(new ParallaxLayer(assets.get(Assets.menu), BubbleKiwiGame.WIDTH, BubbleKiwiGame.HEIGHT, 0.0f));
        ParallaxImage background = new ParallaxImage(layers);
        worldStage.addActor(background);
    }

    private void initializeButtonsStyle() {
        buttonStyle = new TextButton.TextButtonStyle();
        buttonStyle.font = assets.get(Assets.arialSmall);
        buttonStyle.fontColor = Color.WHITE;
    }

    private void initializeNewGameButton() {
        TextButton newGameButton = new TextButton("Start game", buttonStyle);
        newGameButton.setSize(150.0f, 50.0f);
        newGameButton.setOrigin(75.0f, 25.0f);
        newGameButton.setPosition(BubbleKiwiGame.WIDTH / 2.0f - 160.0f, BubbleKiwiGame.HEIGHT / 2.0f - 25.0f);
        newGameButton.setDebug(true);
        newGameButton.addListener(new ClickListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                goToScreen(gameController.getGameplayScreen());
                return super.touchDown(event, x, y, pointer, button);
            }
        });
        stage.addActor(newGameButton);
    }

    private void initializeHighScoreButton() {
        TextButton highScoreButton = new TextButton("High score", buttonStyle);
        highScoreButton.setSize(150.0f, 50.0f);
        highScoreButton.setOrigin(75.0f, 25.0f);
        highScoreButton.setPosition(BubbleKiwiGame.WIDTH / 2.0f + 10.0f, BubbleKiwiGame.HEIGHT / 2.0f - 25.0f);
        highScoreButton.setDebug(true);
        stage.addActor(highScoreButton);
    }

    @Override
    public void render(float delta) {
        super.render(delta);

        worldStage.draw();
        stage.draw();
    }
}
