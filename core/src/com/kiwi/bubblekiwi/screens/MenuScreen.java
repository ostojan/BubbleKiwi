package com.kiwi.bubblekiwi.screens;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.kiwi.bubblekiwi.BubbleKiwiGame;

public class MenuScreen extends AbstractScreen {
    private TextButton newGameButton;
    private TextButton highScoreButton;
    private TextButton.TextButtonStyle buttonStyle;

    public MenuScreen(BubbleKiwiGame game) {
        super(game);
    }

    @Override
    protected void initialize() {
        backgroundColor = Color.BLACK;
        initializeButtonsStyle();
        initializeNewGameButton();
        initializeHighScoreButton();
    }

    private void initializeButtonsStyle() {
        buttonStyle = new TextButton.TextButtonStyle();
        buttonStyle.font = new BitmapFont();
    }

    private void initializeNewGameButton() {
        newGameButton = new TextButton("Start game", buttonStyle);
        newGameButton.setSize(150.0f, 50.0f);
        newGameButton.setOrigin(75.0f, 25.0f);
        newGameButton.setPosition(BubbleKiwiGame.WIDTH / 2.0f - 160.0f, BubbleKiwiGame.HEIGHT / 2.0f - 25.0f);
        newGameButton.setDebug(true);
        newGameButton.addListener(new ClickListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                game.setScreen(new GameplayScreen(game));
                return super.touchDown(event, x, y, pointer, button);
            }
        });
        stage.addActor(newGameButton);
    }

    private void initializeHighScoreButton() {
        TextButton.TextButtonStyle style = new TextButton.TextButtonStyle();
        style.font = new BitmapFont();
        highScoreButton = new TextButton("High score", style);
        highScoreButton.setSize(150.0f, 50.0f);
        highScoreButton.setOrigin(75.0f, 25.0f);
        highScoreButton.setPosition(BubbleKiwiGame.WIDTH / 2.0f + 10.0f, BubbleKiwiGame.HEIGHT / 2.0f - 25.0f);
        highScoreButton.setDebug(true);
        stage.addActor(highScoreButton);
    }

    @Override
    public void render(float delta) {
        super.render(delta);

        spriteBatch.begin();
        stage.draw();
        spriteBatch.end();
    }
}
