package com.kiwi.bubblekiwi.ui;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.Align;
import com.kiwi.bubblekiwi.BubbleKiwiGame;
import com.kiwi.bubblekiwi.controllers.Assets;
import com.kiwi.bubblekiwi.controllers.LevelController;

public class Lives extends Actor {
    private LevelController levelController;
    private Label lives;

    public Lives(Assets assets, LevelController levelController) {
        this.levelController = levelController;
        initializeLivesLabel(assets);
    }

    private void initializeLivesLabel(Assets assets) {
        Label.LabelStyle labelStyle = new Label.LabelStyle();
        labelStyle.font = assets.get(Assets.arialMedium);
        labelStyle.fontColor = Color.RED;
        lives = new Label(String.format("%d", levelController.getLives()), labelStyle);
        lives.setAlignment(Align.right);
        lives.setWrap(true);
        lives.setWidth(BubbleKiwiGame.WIDTH / 4.0f);
        lives.setPosition(BubbleKiwiGame.WIDTH - lives.getWidth() - 25.0f, BubbleKiwiGame.HEIGHT - 75.0f);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        lives.draw(batch, parentAlpha);
    }

    @Override
    public void act(float delta) {
        lives.setText(String.format("%d", levelController.getLives()));
    }
}
