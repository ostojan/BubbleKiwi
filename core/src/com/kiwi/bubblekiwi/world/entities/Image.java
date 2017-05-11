package com.kiwi.bubblekiwi.world.entities;

import com.kiwi.bubblekiwi.BubbleKiwiGame;

public abstract class Image extends com.badlogic.gdx.scenes.scene2d.ui.Image {
    @Override
    public void setOrigin(float originX, float originY) {
        super.setOrigin(originX / BubbleKiwiGame.PPM, originY / BubbleKiwiGame.PPM);
    }

    @Override
    public void setSize(float width, float height) {
        super.setSize(width / BubbleKiwiGame.PPM, height / BubbleKiwiGame.PPM);
    }

    @Override
    public void setPosition(float x, float y) {
        super.setPosition(x / BubbleKiwiGame.PPM, y / BubbleKiwiGame.PPM);
    }
}
