package com.kiwi.bubblekiwi.entities;

import com.badlogic.gdx.math.Vector2;
import com.kiwi.bubblekiwi.BubbleKiwiGame;

public class GameplayBoundaryType {
    public static final GameplayBoundaryType UP = new GameplayBoundaryType(new Vector2((BubbleKiwiGame.WIDTH / 2.0f) / BubbleKiwiGame.PPM, (BubbleKiwiGame.HEIGHT * 2.0f + 20.0f) / BubbleKiwiGame.PPM),
            new Vector2((BubbleKiwiGame.WIDTH / 2.0f) / BubbleKiwiGame.PPM, 0.0f),
            0.0f);
    public static final GameplayBoundaryType DOWN = new GameplayBoundaryType(new Vector2((BubbleKiwiGame.WIDTH / 2.0f) / BubbleKiwiGame.PPM, 20.0f / BubbleKiwiGame.PPM),
            new Vector2((BubbleKiwiGame.WIDTH / 2.0f) / BubbleKiwiGame.PPM, 0.0f),
            0.8f);
    public static final GameplayBoundaryType LEFT = new GameplayBoundaryType(new Vector2(0.0f, (BubbleKiwiGame.HEIGHT + 20.0f) / BubbleKiwiGame.PPM),
            new Vector2(0.0f, BubbleKiwiGame.HEIGHT / BubbleKiwiGame.PPM),
            0.0f);
    public static final GameplayBoundaryType RIGHT = new GameplayBoundaryType(new Vector2(BubbleKiwiGame.WIDTH / BubbleKiwiGame.PPM, (BubbleKiwiGame.HEIGHT + 20.0f) / BubbleKiwiGame.PPM),
            new Vector2(0.0f, BubbleKiwiGame.HEIGHT / BubbleKiwiGame.PPM),
            0.0f);

    private Vector2 position;
    private Vector2 halfSizes;
    private final float friction;

    private GameplayBoundaryType(Vector2 position, Vector2 halfSizes, float friction) {
        this.position = position;
        this.halfSizes = halfSizes;
        this.friction = friction;
    }

    public Vector2 getPosition() {
        return position;
    }

    public Vector2 getHalfSizes() {
        return halfSizes;
    }

    public float getFriction() {
        return friction;
    }
}
