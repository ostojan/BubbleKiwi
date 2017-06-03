package com.kiwi.bubblekiwi.ui;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.kiwi.bubblekiwi.BubbleKiwiGame;
import com.kiwi.bubblekiwi.controllers.Assets;

public class TimesUp extends Actor {
    private Label gameOver;

    public TimesUp(Assets assets) {
        initializeGameOverLabel(assets);
    }

    private void initializeGameOverLabel(Assets assets) {
        Label.LabelStyle labelStyle = new Label.LabelStyle();
        labelStyle.font = assets.get(Assets.arialBig);
        labelStyle.fontColor = Color.YELLOW;
        gameOver = new Label("Time's up", labelStyle);
        gameOver.setPosition((BubbleKiwiGame.WIDTH - gameOver.getWidth()) / 2.0f, (BubbleKiwiGame.HEIGHT - gameOver.getHeight()) / 2.0f);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        gameOver.draw(batch, parentAlpha);
    }
}
