package com.kiwi.bubblekiwi.controllers;

import com.badlogic.gdx.Gdx;
import com.kiwi.bubblekiwi.actors.Player;

public class MoveController {
    private Player player;

    public MoveController(final Player player) {
        this.player = player;
    }

    public void move() {
        float acceleration = Gdx.input.getAccelerometerY();
        if (Math.abs(acceleration) > 0.5f) {
            if (acceleration > 0.0f) {
                player.moveRight();
            } else {
                player.moveLeft();
            }
        }
        if (Gdx.input.justTouched()) {
            player.jump();
        }
    }
}
