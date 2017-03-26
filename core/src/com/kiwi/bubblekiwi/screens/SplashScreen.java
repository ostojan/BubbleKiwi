package com.kiwi.bubblekiwi.screens;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.kiwi.bubblekiwi.BubbleKiwiGame;

public class SplashScreen extends AbstractScreen {
    private Image splashImage;

    public SplashScreen(BubbleKiwiGame game) {
        super(game);
    }

    @Override
    protected void initialize() {
        createSplashImage();
    }

    private void createSplashImage() {
        splashImage = new Image(new Texture("badlogic.jpg"));
        splashImage.setSize(BubbleKiwiGame.WIDTH, BubbleKiwiGame.HEIGHT);
        splashImage.setPosition(0, 0);
        stage.addActor(splashImage);
    }

    @Override
    public void render(float delta) {
        super.render(delta);

        spriteBatch.begin();
        stage.draw();
        spriteBatch.end();
    }
}
