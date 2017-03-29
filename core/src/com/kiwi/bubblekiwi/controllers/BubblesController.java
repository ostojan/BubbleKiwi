package com.kiwi.bubblekiwi.controllers;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Timer;
import com.kiwi.bubblekiwi.BubbleKiwiGame;
import com.kiwi.bubblekiwi.entities.Bubble;

import java.util.ArrayList;
import java.util.List;

public class BubblesController extends Actor {
    private List<Bubble> bubbles;
    private List<Bubble> bubblesToRemove;
    private int spawnTime;
    private World world;

    public BubblesController(World world) {
        this.world = world;
        initialize();
    }

    private void initialize() {
        bubbles = new ArrayList<Bubble>();
        bubblesToRemove = new ArrayList<Bubble>();
        scheduleCreationOfNewBubble();
    }

    private void scheduleCreationOfNewBubble() {
        randomizeSpawnTime();
        Timer.schedule(new Timer.Task() {
            @Override
            public void run() {
                createNewBubble();
                scheduleCreationOfNewBubble();
            }
        }, spawnTime);
    }

    private void createNewBubble() {
        float radius = MathUtils.random(25.0f, 50.0f);
        Bubble bubble = new Bubble.Builder()
                .texture(getRandomBubbleTexture())
                .radius(radius)
                .startX(MathUtils.random(radius, BubbleKiwiGame.WIDTH - radius))
                .world(world)
                .bubblesController(this)
                .build();
        bubbles.add(bubble);
    }

    private Texture getRandomBubbleTexture() {
        // TODO: Create and use resources manager
        int textureId = MathUtils.random(1, 5);
        String textureName = String.format("bubble%d.png", textureId);
        return new Texture(textureName);
    }

    private void randomizeSpawnTime() {
        spawnTime = MathUtils.random(2, 4);
    }


    public void update(float delta) {
        updateBubbles(delta);
        removeUnusedBubbles();
    }

    private void updateBubbles(float delta) {
        for (Bubble bubble : bubbles) {
            bubble.update(delta);
        }
    }

    private void removeUnusedBubbles() {
        for (Bubble bubble : bubblesToRemove) {
            bubbles.remove(bubble);
        }
        bubblesToRemove.clear();
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        for (Bubble bubble : bubbles) {
            bubble.draw(batch, parentAlpha);
        }
    }

    public void removeBubble(Bubble bubble) {
        bubblesToRemove.add(bubble);
    }
}
