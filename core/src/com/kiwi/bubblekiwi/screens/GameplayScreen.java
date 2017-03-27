package com.kiwi.bubblekiwi.screens;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.kiwi.bubblekiwi.BubbleKiwiGame;
import com.kiwi.bubblekiwi.entities.ParallaxBackground;
import com.kiwi.bubblekiwi.entities.ParallaxLayer;

public class GameplayScreen extends AbstractScreen {
    private ParallaxBackground background;

    public GameplayScreen(BubbleKiwiGame game) {
        super(game);
    }

    @Override
    protected void initialize() {
        backgroundColor = Color.BLACK;
        initializeBackground();
    }

    private void initializeBackground() {
        ParallaxLayer clouds = new ParallaxLayer(new Texture("clouds.png"), 0.01f);
        ParallaxLayer ground = new ParallaxLayer(new Texture("ground.png"), 0.0f);
        background = new ParallaxBackground(new ParallaxLayer[]{clouds, ground});
        stage.addActor(background);
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
    }
}