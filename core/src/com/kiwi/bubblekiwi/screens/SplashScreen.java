package com.kiwi.bubblekiwi.screens;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.Timer;
import com.kiwi.bubblekiwi.BubbleKiwiGame;
import com.kiwi.bubblekiwi.controllers.Assets;

public class SplashScreen extends AbstractScreen {
    private Image splashImage;

    public SplashScreen(final BubbleKiwiGame game, final Assets assets) {
        super(game, assets);
        initialize();
        // TODO: Add game initialization instead of fixed delay
        Timer.schedule(new Timer.Task() {
            @Override
            public void run() {
                goToScreen(new MenuScreen(game, assets));
            }
        }, 1);
    }

    private void initialize() {
        splashImage = new Image(new Texture("badlogic.jpg"));
        splashImage.setSize(BubbleKiwiGame.WIDTH, BubbleKiwiGame.HEIGHT);
        splashImage.setPosition(0.0f, 0.0f);
        stage.addActor(splashImage);
    }

    @Override
    public void render(float delta) {
        super.render(delta);

        stage.draw();
    }
}
