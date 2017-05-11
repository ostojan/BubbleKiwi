package com.kiwi.bubblekiwi.world.actors.bubble;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.kiwi.bubblekiwi.world.entities.ActorAnimationsStates;
import com.kiwi.bubblekiwi.world.entities.Direction;

import java.util.Map;

public class BubbleAnimationsStates extends ActorAnimationsStates<BubbleStates> {
    BubbleAnimationsStates(BubbleStates initialState, Map<BubbleStates, Animation<TextureRegion>> animations) {
        super(initialState, animations);
    }

    @Override
    protected boolean isAnimationLooped() {
        return false;
    }

    @Override
    public boolean isTimeResetNeeded() {
        return true;
    }
}
