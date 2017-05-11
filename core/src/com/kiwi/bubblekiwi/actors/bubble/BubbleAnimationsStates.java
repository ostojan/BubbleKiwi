package com.kiwi.bubblekiwi.actors.bubble;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.kiwi.bubblekiwi.entities.ActorAnimationsStates;

import java.util.Map;

public class BubbleAnimationsStates extends ActorAnimationsStates<BubbleStates> {
    BubbleAnimationsStates(BubbleStates initialState, Map<BubbleStates, Animation<TextureRegion>> animations) {
        super(initialState, animations);
    }

    @Override
    protected boolean isAnimationLooped() {
        switch (currentState) {
            case FALLING:
                return true;
            case DYING:
                return false;
        }
        return false;
    }

    @Override
    protected boolean needFlipInX() {
        return false;
    }

    @Override
    protected boolean needFlipInY() {
        return false;
    }

    @Override
    public boolean isTimeResetNeeded() {
        return true;
    }
}
