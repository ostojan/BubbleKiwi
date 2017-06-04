package com.kiwi.bubblekiwi.controllers;

import com.badlogic.gdx.Gdx;
import com.kiwi.bubblekiwi.ui.Joystick;
import com.kiwi.bubblekiwi.world.actors.player.Player;

public class MoveController {
    private Player player;
    private Joystick joystick;

    public MoveController(final Player player, final Joystick joystick) {
        this.player = player;
        this.joystick = joystick;
    }

    public void move() {
        if (Math.abs(joystick.getXAxis()) > 0.5f) {
            if (joystick.getXAxis() > 0.0f) {
                player.moveRight();
            } else {
                player.moveLeft();
            }
        }
        if (joystick.getYAxis() > 0.9f) {
            player.jump();
        }
    }
}
