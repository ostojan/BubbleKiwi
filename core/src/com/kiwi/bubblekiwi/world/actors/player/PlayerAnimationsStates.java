package com.kiwi.bubblekiwi.world.actors.player;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.kiwi.bubblekiwi.world.entities.ActorAnimationsStates;
import com.kiwi.bubblekiwi.world.entities.Direction;

import java.util.Map;

public class PlayerAnimationsStates extends ActorAnimationsStates<PlayerStates> {
    private Direction direction;

    public PlayerAnimationsStates(PlayerStates initialState, Map<PlayerStates, Animation<TextureRegion>> animations) {
        super(initialState, animations);
        direction = Direction.RIGHT;
    }

    @Override
    protected boolean isAnimationLooped() {
        return true;
    }

    @Override
    protected Direction directionInX() {
        switch (currentState) {
            case MOVING_RIGHT:
                direction = Direction.RIGHT;
                break;
            case MOVING_LEFT:
                direction = Direction.LEFT;
                break;
            default:
                break;
        }
        return direction;
    }

    @Override
    protected Direction directionInY() {
        return Direction.UP;
    }

    @Override
    public boolean isTimeResetNeeded() {
        switch (currentState) {
            case JUMPING:
                return true;
        }
        return false;
    }
}
