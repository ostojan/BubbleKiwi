package com.kiwi.bubblekiwi.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.kiwi.bubblekiwi.BubbleKiwiGame;

public class Player extends Image {
    private static final int WIDTH = 120;
    private static final int HEIGHT = 157;

    public Player() {
        super(new Texture("kiwi.png"));
        setSize(WIDTH, HEIGHT);
        setOrigin(WIDTH / 2.0f, HEIGHT / 2.0f);
        setPosition((BubbleKiwiGame.WIDTH - WIDTH) / 2.0f, 20);
    }
}
