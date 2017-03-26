package com.kiwi.bubblekiwi.entities;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class ParallaxBackground extends Actor {
    private ParallaxLayer[] layers;

    public ParallaxBackground(ParallaxLayer[] layers) {
        this.layers = layers;
    }

    public void update(float delta) {
        for (ParallaxLayer layer : layers) {
            layer.update(delta);
        }
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        for (ParallaxLayer layer : layers) {
            layer.draw(batch, parentAlpha);
        }
    }
}