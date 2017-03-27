package com.kiwi.bubblekiwi.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.kiwi.bubblekiwi.BubbleKiwiGame;
import com.kiwi.bubblekiwi.controllers.BubblesController;

public class Bubble extends Image {
    private int endY;
    private float fallSpeed;
    private BubblesController bubblesController;

    public void update(float delta) {
        float moveDistance = delta * fallSpeed;
        moveBy(0, -moveDistance);
        if (getY() <= endY) {
            remove();
        }
    }

    @Override
    public boolean remove() {
        bubblesController.removeBubble(this);
        return super.remove();
    }

    public static class Builder {
        private Bubble bubble;

        public Builder() {
            bubble = new Bubble();
        }

        public Builder texture(Texture texture) {
            bubble.setDrawable(new TextureRegionDrawable(new TextureRegion(texture)));
            return this;
        }

        public Builder size(float size) {
            bubble.setSize(size, size);
            return this;
        }

        public Builder startX(int startX) {
            bubble.setPosition(startX, BubbleKiwiGame.HEIGHT);
            return this;
        }

        public Builder endY(int endY) {
            bubble.endY = endY;
            return this;
        }

        public Builder fallSpeed(float speed) {
            bubble.fallSpeed = speed;
            return this;
        }

        public Builder bubblesController(BubblesController controller) {
            bubble.bubblesController = controller;
            return this;
        }

        public Bubble build() {
            return bubble;
        }
    }
}
