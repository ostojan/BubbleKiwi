package com.kiwi.bubblekiwi.entities;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import java.util.Map;

public abstract class ActorAnimationsStates<State extends Enum<State>> {
    private Map<State, Animation<TextureRegion>> animations;
    protected State currentState;


    public ActorAnimationsStates(State initialState, Map<State, Animation<TextureRegion>> animations) {
        currentState = initialState;
        this.animations = animations;
    }

    void setState(State state) {
        currentState = state;
    }

    State getState() {
        return currentState;
    }

    TextureRegion getFrame(float time) {
        TextureRegion frame = animations.get(currentState).getKeyFrame(time, isAnimationLooped());
        flipFrameIfNeeded(frame);
        return frame;
    }

    protected abstract boolean isAnimationLooped();

    private void flipFrameIfNeeded(TextureRegion frame) {
        if (needFlipInX() && !frame.isFlipX()) {
            frame.flip(true, false);
        }
        if (needFlipInY() && !frame.isFlipY()) {
            frame.flip(false, true);
        }
    }

    protected abstract boolean needFlipInX();

    protected abstract boolean needFlipInY();

    boolean isAnimationFinished(float time) {
        return animations.get(currentState).isAnimationFinished(time);
    }

    public abstract boolean isTimeResetNeeded();
}
