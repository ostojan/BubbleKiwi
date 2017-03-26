package com.kiwi.bubblekiwi.screens;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
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
        newGameButton.setSize(150, 50);
        newGameButton.setOrigin(75, 25);
        newGameButton.setPosition(BubbleKiwiGame.WIDTH / 2 - 160, BubbleKiwiGame.HEIGHT / 2 - 25);
        newGameButton.setDebug(true);
        stage.addActor(newGameButton);
    }

    private void initializeHighScoreButton() {
        TextButton.TextButtonStyle style = new TextButton.TextButtonStyle();
        style.font = new BitmapFont();
        highScoreButton = new TextButton("High score", style);
        highScoreButton.setSize(150, 50);
        highScoreButton.setOrigin(75, 25);
        highScoreButton.setPosition(BubbleKiwiGame.WIDTH / 2 + 10, BubbleKiwiGame.HEIGHT / 2 - 25);
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
