package com.kiwi.bubblekiwi.screens;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.Timer;
import com.kiwi.bubblekiwi.BubbleKiwiGame;

public class SplashScreen extends AbstractScreen {
    private Image splashImage;

    public SplashScreen(final BubbleKiwiGame game) {
        super(game);

        // TODO: Add game initialization instead of fixed delay
        Timer.schedule(new Timer.Task() {
            @Override
            public void run() {
                game.setScreen(new MenuScreen(game));
            }
        }, 1);
    }

    @Override
    protected void initialize() {
        backgroundColor = Color.BLACK;
        createSplashImage();
    }

    private void createSplashImage() {
        splashImage = new Image(new Texture("badlogic.jpg"));
        splashImage.setSize(BubbleKiwiGame.WIDTH, BubbleKiwiGame.HEIGHT);
        splashImage.setPosition(0.0f, 0.0f);
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
