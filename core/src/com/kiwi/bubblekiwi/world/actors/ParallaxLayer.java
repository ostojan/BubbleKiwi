package com.kiwi.bubblekiwi.world.actors;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.kiwi.bubblekiwi.world.entities.Image;

public class ParallaxLayer extends Image {
    private Texture texture;
    private TextureRegion textureRegion;
    private float speed;
    private float scrollTime;
    private float step;

    public ParallaxLayer(Texture texture, float width, float height, float speed) {
        super();
        this.texture = texture;
        this.speed = speed;
        scrollTime = 0.0f;
        initialize(width, height);
    }

    private void initialize(float width, float height) {
        float textureRatio = (float) texture.getWidth() / (float) texture.getHeight();
        float backgroundRatio = width / height;
        step = backgroundRatio / textureRatio;
        texture.setWrap(Texture.TextureWrap.Repeat, Texture.TextureWrap.Repeat);
        textureRegion = new TextureRegion(texture, 0.0f, 0.0f, step, 1.0f);
        setDrawable(new TextureRegionDrawable(textureRegion));
        setSize(width, height);
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        scrollTime += delta * speed;
        textureRegion.setU(scrollTime);
        textureRegion.setU2(scrollTime + step);
    }
}
