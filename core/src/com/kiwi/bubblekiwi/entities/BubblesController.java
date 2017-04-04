package com.kiwi.bubblekiwi.entities;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Timer;
import com.kiwi.bubblekiwi.BubbleKiwiGame;
import com.kiwi.bubblekiwi.controllers.Assets;

import java.util.ArrayList;
import java.util.List;

public class BubblesController extends Actor {
    private List<Bubble> bubbles;
    private List<Bubble> bubblesToRemove;
    private int spawnTime;
    private World world;
    private Assets assets;

    public BubblesController(World world, Assets assets) {
        this.world = world;
        this.assets = assets;
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
                .animation(Bubble.BubbleState.FALLING, createBubbleFallAnimation())
                .animation(Bubble.BubbleState.DYING, createBubbleDeathAnimation())
                .radius(radius)
                .startX(MathUtils.random(radius, BubbleKiwiGame.WIDTH - radius))
                .world(world)
                .bubblesController(this)
                .build();
        bubbles.add(bubble);
    }

    private Animation<TextureRegion> createBubbleFallAnimation() {
        return new Animation<TextureRegion>(1.0f / 4.0f, getRandomBubbleTextureRegion());
    }

    private TextureRegion getRandomBubbleTextureRegion() {
        String regionName = String.format("bubble%d", MathUtils.random(1, 5));
        return assets.get(Assets.bubbles).findRegion(regionName);
    }

    private Animation<TextureRegion> createBubbleDeathAnimation() {
        //TODO: Add death animation
        return createBubbleFallAnimation();
    }

    private void randomizeSpawnTime() {
        spawnTime = MathUtils.random(2, 4);
    }


    @Override
    public void act(float delta) {
        super.act(delta);
        actBubbles(delta);
        removeUnusedBubbles();
    }

    private void actBubbles(float delta) {
        for (Bubble bubble : bubbles) {
            bubble.act(delta);
        }
    }

    private void removeUnusedBubbles() {
        for (Bubble bubble : bubblesToRemove) {
            bubble.dispose();
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

    public List<Bubble> getBubbles() {
        return bubbles;
    }
}
