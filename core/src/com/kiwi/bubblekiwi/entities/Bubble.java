package com.kiwi.bubblekiwi.entities;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.utils.Disposable;
import com.kiwi.bubblekiwi.BubbleKiwiGame;

import java.util.HashMap;

public class Bubble extends AnimatedWorldActor<Bubble.BubbleState, HashMap<Bubble.BubbleState, Animation<TextureRegion>>> implements Disposable {
    public enum BubbleState {
        FALLING,
        DYING
    }
    private BubblesController bubblesController;
    private float radius;
    private float startX;

    private Bubble(World world,
                  HashMap<BubbleState, Animation<TextureRegion>> animations,
                  BubblesController bubblesController) {
        super(world, BubbleState.FALLING, animations);
        this.bubblesController = bubblesController;

    }

    @Override
    protected void initializeBody() {
        randomizeStartXAndRadius();
        super.initializeBody();
        getActorBody().setGravityScale(0.005f);
    }

    private void randomizeStartXAndRadius() {
        radius = MathUtils.random(25.0f, 50.0f);
        startX = MathUtils.random(radius, BubbleKiwiGame.WIDTH - radius);
    }

    @Override
    protected BodyDef createBodyDef() {
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(
                startX / BubbleKiwiGame.PPM,
                (BubbleKiwiGame.HEIGHT + 100.0f + radius) / BubbleKiwiGame.PPM
        );
        return bodyDef;
    }

    @Override
    protected FixtureDef createFixtureDef() {
        CircleShape shape = (CircleShape) createShape();
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.density = 0.0001f;
        return fixtureDef;
    }

    private Shape createShape() {
        CircleShape shape = new CircleShape();
        shape.setRadius(radius / BubbleKiwiGame.PPM);
        return shape;
    }


    @Override
    protected void initialize() {
        setSize((radius * 2.0f), (radius * 2.0f));
        setOrigin(radius, radius);
    }


    @Override
    protected void initializeAnimations(HashMap<BubbleState, Animation<TextureRegion>> animations) {
        this.animations = animations;
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        switch (getActorState()) {
            case FALLING:
                updatePosition();
                break;
            case DYING:
                getActorBody().setActive(false);
                if (currentAnimation.isAnimationFinished(getStateTime())) {
                    remove();
                }
                break;
        }
    }

    public void destroy() {
        setActorState(BubbleState.DYING);
    }

    @Override
    public boolean remove() {
        bubblesController.removeBubble(this);
        return super.remove();
    }

    public static class Builder {
        private World world;
        private HashMap<BubbleState, Animation<TextureRegion>> animations;
        private BubblesController bubblesController;

        public Builder() {
            animations = new HashMap<BubbleState, Animation<TextureRegion>>();
        }

        public Builder world(World world) {
            this.world = world;
            return this;
        }

        public Builder addAnimationForState(BubbleState state, Animation<TextureRegion> animation) {
            this.animations.put(state, animation);
            return this;
        }

        public Builder bubblesController(BubblesController controller) {
            bubblesController = controller;
            return this;
        }

        public Bubble build() {
            return new Bubble(world, animations, bubblesController);
        }

    }
}
