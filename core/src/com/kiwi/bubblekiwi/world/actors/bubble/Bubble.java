package com.kiwi.bubblekiwi.world.actors.bubble;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.utils.Disposable;
import com.kiwi.bubblekiwi.BubbleKiwiGame;
import com.kiwi.bubblekiwi.world.entities.AnimatedActor;

public class Bubble extends AnimatedActor<BubbleStates> implements Disposable {
    private BubblesController bubblesController;
    private float radius;
    private float startX;

    Bubble(World world,
           BubbleAnimationsStates bubbleState,
           BubblesController bubblesController) {
        super(world, bubbleState);
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
        fixtureDef.isSensor = true;
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
    public void act(float delta) {
        super.act(delta);
        switch (getActorState()) {
            case FALLING:
                updatePosition();
                break;
            case DYING:
                getActorBody().setActive(false);
                if (isAnimationFinished()) {
                    remove();
                }
                break;
        }
    }

    public void destroy() {
        setActorState(BubbleStates.DYING);
    }

    @Override
    public boolean remove() {
        bubblesController.removeBubble(this);
        return super.remove();
    }
}
