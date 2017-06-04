package com.kiwi.bubblekiwi.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Touchpad;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.kiwi.bubblekiwi.controllers.Assets;

public class Joystick extends Actor {
    private Touchpad touchpad;

    public Joystick(Assets assets) {
        initializeTouchpad(assets);
    }

    private void initializeTouchpad(Assets assets) {
        touchpad = new Touchpad(10 , createTouchpadStyle(assets));
        touchpad.setBounds(35, 35, 150, 150);
    }

    private Touchpad.TouchpadStyle createTouchpadStyle(Assets assets) {
        Touchpad.TouchpadStyle touchpadStyle = new Touchpad.TouchpadStyle();
        touchpadStyle.knob = new TextureRegionDrawable(new TextureRegion(assets.get(Assets.touchpadKnob)));
        touchpadStyle.background = new TextureRegionDrawable(new TextureRegion(assets.get(Assets.touchpadBackground)));
        return touchpadStyle;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        touchpad.draw(batch, parentAlpha);
    }

    @Override
    public void act(float delta) {
        touchpad.act(delta);
    }

    @Override
    public Actor hit(float x, float y, boolean touchable) {
        return touchpad.hit(x, y, touchable);
    }

    public float getXAxis() {
        return touchpad.getKnobPercentX();
    }

    public float getYAxis() {
        return touchpad.getKnobPercentY();
    }
}
