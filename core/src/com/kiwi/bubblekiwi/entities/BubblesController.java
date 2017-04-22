package com.kiwi.bubblekiwi.entities;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Timer;
import com.kiwi.bubblekiwi.controllers.Assets;

import java.util.ArrayList;
import java.util.List;

public class BubblesController extends Actor {
    private List<Bubble> bubbles;
    private List<Bubble> bubblesToRemove;
    private float spawnTime;
    private World world;
    private Assets assets;
    private float time;

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
        Bubble bubble = new Bubble.Builder()
                .world(world)
                .addAnimationForState(Bubble.BubbleState.FALLING, createBubbleFallAnimation())
                .addAnimationForState(Bubble.BubbleState.DYING, createBubbleDeathAnimation())
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
        Array<TextureAtlas.AtlasRegion> regions = assets.get(Assets.bubbleDeath).getRegions();
        return new Animation<TextureRegion>(1.0f / 15.0f, regions);
    }

    private void randomizeSpawnTime() {
        float startTime;
        float endTime;
        if (time >= 1.8f) {
            startTime = 0.2f;
            endTime = 2.2f;
        } else {
            startTime = 2.0f - time;
            endTime = 4.0f - time;
        }
        if (time / 10.0f >= endTime - 0.2f) {
            endTime = 0.4f;
        } else {
            endTime -= time / 10.0f;
        }
        spawnTime = MathUtils.random(startTime, endTime);
    }


    @Override
    public void act(float delta) {
        super.act(delta);
        time += delta / 40.0f;
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
}
