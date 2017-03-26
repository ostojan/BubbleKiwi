package com.kiwi.bubblekiwi.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.kiwi.bubblekiwi.BubbleKiwiGame;

public class ParallaxLayer extends Image {
    private Texture texture;
    private Sprite sprite;
    private float speed;
    private float scrollTime;

    public ParallaxLayer(Texture texture, float speed) {
        super();
        this.texture = texture;
        this.speed = speed;
        scrollTime = 0.0f;
        initialize();
    }

    private void initialize() {
        texture.setWrap(Texture.TextureWrap.Repeat, Texture.TextureWrap.Repeat);
        sprite = new Sprite(texture);
        setDrawable(new SpriteDrawable(sprite));
        setSize(BubbleKiwiGame.WIDTH, BubbleKiwiGame.HEIGHT);
    }

    public void update(float delta) {
        scrollTime += delta * speed;
        sprite.setU(scrollTime);
        sprite.setU2(scrollTime + 1);
    }
}
