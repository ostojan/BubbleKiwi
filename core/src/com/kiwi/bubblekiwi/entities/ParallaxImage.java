package com.kiwi.bubblekiwi.entities;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;

import java.util.List;

public class ParallaxImage extends Actor {
    private List<ParallaxLayer> layers;

    public ParallaxImage(List<ParallaxLayer> layers) {
        this.layers = layers;
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        for (ParallaxLayer layer : layers) {
            layer.act(delta);
        }
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        for (ParallaxLayer layer : layers) {
            layer.draw(batch, parentAlpha);
        }
    }
}
