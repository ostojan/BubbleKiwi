package com.kiwi.bubblekiwi.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.kiwi.bubblekiwi.BubbleKiwiGame;

public class ParallaxLayer extends Image {
    private Texture texture;
    private TextureRegion textureRegion;
    private float speed;
    private float scrollTime;
    private float step;

    public ParallaxLayer(Texture texture, float speed) {
        super();
        this.texture = texture;
        this.speed = speed;
        scrollTime = 0.0f;
        initialize();
    }

    private void initialize() {
        float textureRatio = (float) texture.getWidth() / (float) texture.getHeight();
        float backgroundRatio = (float) BubbleKiwiGame.WIDTH / (float) BubbleKiwiGame.HEIGHT;
        step = backgroundRatio / textureRatio;
        texture.setWrap(Texture.TextureWrap.Repeat, Texture.TextureWrap.Repeat);
        textureRegion = new TextureRegion(texture, 0.0f, 0.0f, step, 1.0f);
        setDrawable(new TextureRegionDrawable(textureRegion));
        setSize(BubbleKiwiGame.WIDTH / BubbleKiwiGame.PPM, BubbleKiwiGame.HEIGHT / BubbleKiwiGame.PPM);
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        scrollTime += delta * speed;
        textureRegion.setU(scrollTime);
        textureRegion.setU2(scrollTime + step);
    }
}
