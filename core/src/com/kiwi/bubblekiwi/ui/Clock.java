package com.kiwi.bubblekiwi.ui;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.Align;
import com.kiwi.bubblekiwi.BubbleKiwiGame;
import com.kiwi.bubblekiwi.controllers.Assets;
import com.kiwi.bubblekiwi.controllers.LevelController;

public class Clock extends Actor {
    private LevelController levelController;
    private Label time;

    public Clock(Assets assets, LevelController levelController) {
        this.levelController = levelController;
        initializeClockLabel(assets);
    }

    private void initializeClockLabel(Assets assets) {
        Label.LabelStyle labelStyle = new Label.LabelStyle();
        labelStyle.font = assets.get(Assets.arialMedium);
        labelStyle.fontColor = Color.BLUE;
        time = new Label(String.format("%d", Math.round(levelController.getStateTime())), labelStyle);
        time.setAlignment(Align.center);
        time.setWrap(true);
        time.setWidth(BubbleKiwiGame.WIDTH / 4.0f);
        time.setPosition((BubbleKiwiGame.WIDTH - time.getWidth()) / 2.0f, BubbleKiwiGame.HEIGHT - 75.0f);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        time.draw(batch, parentAlpha);
    }

    @Override
    public void act(float delta) {
        time.setText(createTimeString());
    }

    private String createTimeString() {
        int time = levelController.getTimeToEnd();
        int minutes = time / 60;
        int seconds = time % 60;
        return String.format("%d:%02d", minutes, seconds);
    }
}
