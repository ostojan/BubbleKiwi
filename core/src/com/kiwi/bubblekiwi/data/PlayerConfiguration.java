package com.kiwi.bubblekiwi.data;

import com.badlogic.gdx.math.Vector2;

public class PlayerConfiguration {
    private Vector2 moveLeftImpulse;
    private Vector2 moveRightImpulse;
    private Vector2 jumpImpulse;

    public PlayerConfiguration(float moveImpulseValue, float jumpImpulseValue) {
        moveLeftImpulse = new Vector2(-moveImpulseValue, 0.0f);
        moveRightImpulse = new Vector2(moveImpulseValue, 0.0f);
        jumpImpulse = new Vector2(0.0f, jumpImpulseValue);
    }

    public Vector2 getMoveLeftImpulse() {
        return moveLeftImpulse;
    }

    public Vector2 getMoveRightImpulse() {
        return moveRightImpulse;
    }

    public Vector2 getJumpImpulse() {
        return jumpImpulse;
    }
}
