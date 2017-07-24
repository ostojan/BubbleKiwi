package com.kiwi.bubblekiwi.ui;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.Align;
import com.kiwi.bubblekiwi.BubbleKiwiGame;
import com.kiwi.bubblekiwi.controllers.Assets;
import com.kiwi.bubblekiwi.controllers.LevelController;

public class Points extends Actor {
    private LevelController levelController;
    private Image bubble;
    private Label points;

    public Points(Assets assets, LevelController levelController) {
        this.levelController = levelController;
        initializeBubbleImage(assets);
        initializePointsLabel(assets);
    }

    private void initializeBubbleImage(Assets assets) {
        bubble = new Image(assets.get(Assets.bubbles).getRegions().first());
        bubble.setSize(50.0f, 50.0f);
        bubble.setPosition(BubbleKiwiGame.WIDTH - 75.0f, BubbleKiwiGame.HEIGHT - 75.0f);
    }

    private void initializePointsLabel(Assets assets) {
        Label.LabelStyle labelStyle = new Label.LabelStyle();
        labelStyle.font = assets.get(Assets.arialMedium);
        labelStyle.fontColor = Color.GOLD;
        points = new Label(String.format("%d", levelController.getPoints()), labelStyle);
        points.setAlignment(Align.right);
        points.setWrap(true);
        points.setWidth(BubbleKiwiGame.WIDTH / 4.0f);
        points.setPosition(bubble.getX() - points.getWidth() - 10.0f, BubbleKiwiGame.HEIGHT - 75.0f);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        bubble.draw(batch, parentAlpha);
        points.draw(batch, parentAlpha);
    }

    @Override
    public void act(float delta) {
        points.setText(String.format("%d", levelController.getPoints()));
    }
}
