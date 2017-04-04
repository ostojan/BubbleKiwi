package com.kiwi.bubblekiwi.entities;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

import java.util.Map;

public abstract class AnimatedActor<ActorState extends Enum<ActorState>> extends Image{
    protected ActorState state;
    protected Map<ActorState, Animation<TextureRegion>> animations;
    protected boolean loopAnimation;
    protected float stateTime;
    protected Animation<TextureRegion> currentAnimation;

    protected abstract void initializeAnimations();

    protected void setState(ActorState newState) {
        state = newState;
        stateTime = 0.0f;
        currentAnimation = animations.get(state);
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        Animation<TextureRegion> animation = animations.get(state);
        TextureRegion keyFrame = animation.getKeyFrame(stateTime += delta, loopAnimation);
        ((TextureRegionDrawable) getDrawable()).setRegion(keyFrame);
    }
}
