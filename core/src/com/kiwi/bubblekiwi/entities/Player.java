package com.kiwi.bubblekiwi.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.kiwi.bubblekiwi.BubbleKiwiGame;

public class Player extends Image {
    private static final int WIDTH = 120;
    private static final int HEIGHT = 157;
    private static final float MOVE_SPEED = 250.0f / BubbleKiwiGame.PPM;
    private static final float JUMP_SPEED = 500.0f / BubbleKiwiGame.PPM;
    private static final float FALL_SPEED = 520.0f / BubbleKiwiGame.PPM;

    private enum State {
        STAYING,
        MOVING_LEFT,
        MOVING_RIGHT,
        JUMPING,
        FALLING
        }
    private State state;

    public Player() {
        super(new Texture("kiwi.png"));
        setSize(WIDTH / BubbleKiwiGame.PPM, HEIGHT / BubbleKiwiGame.PPM);
        setOrigin((WIDTH / 2.0f) / BubbleKiwiGame.PPM, (HEIGHT / 2.0f) / BubbleKiwiGame.PPM);
        setPosition(((BubbleKiwiGame.WIDTH - WIDTH) / 2.0f) / BubbleKiwiGame.PPM, 20.0f / BubbleKiwiGame.PPM);
        state = State.STAYING;
    }

    public void moveRight() {
        if (state == State.STAYING) {
            state = State.MOVING_RIGHT;
        }
    }

    public void moveLeft() {
        if (state == State.STAYING) {
            state = State.MOVING_LEFT;
        }
    }

    public void jump() {
        if (state == State.STAYING) {
            state = State.JUMPING;
        }
    }

    public void stopMoving() {
        if (!isInAir()) {
            state = State.STAYING;
        }
    }

    private boolean isInAir() {
        return state == State.FALLING || state == State.JUMPING;
    }

    public void update(float delta) {
        switch (state) {
            case MOVING_LEFT:
                if (getX() > 0.0f) {
                    moveBy(-delta * MOVE_SPEED, 0);
                }
                break;
            case MOVING_RIGHT:
                if (getX() < (BubbleKiwiGame.WIDTH - WIDTH) / BubbleKiwiGame.PPM) {
                    moveBy(delta * MOVE_SPEED, 0.0f);
                }
                break;
            case FALLING:
                if (getY() <= 20.0f / BubbleKiwiGame.PPM) {
                    state = State.STAYING;
                }
                moveBy(0.0f, -delta * FALL_SPEED);
                break;
            case JUMPING:
                if (getY() >= 180.0f / BubbleKiwiGame.PPM) {
                    state = State.FALLING;
                }
                moveBy(0.0f, delta * JUMP_SPEED);
                break;
        }
    }
}
