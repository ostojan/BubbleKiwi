package com.kiwi.bubblekiwi.entities;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.kiwi.bubblekiwi.BubbleKiwiGame;
import com.kiwi.bubblekiwi.controllers.Assets;

public class Player extends WorldActor {
    private static final float WIDTH = 120;
    private static final float HEIGHT = 157;
    private static final float MOVE_IMPULSE_VALUE = 0.05f;
    private static final Vector2 MOVE_LEFT_IMPULSE = new Vector2(-MOVE_IMPULSE_VALUE, 0.0f);
    private static final Vector2 MOVE_RIGHT_IMPULSE = new Vector2(MOVE_IMPULSE_VALUE, 0.0f);
    private static final float JUMP_IMPULSE_VALUE = 1.8f;
    private static final Vector2 JUMP_IMPULSE = new Vector2(0.0f, JUMP_IMPULSE_VALUE);

    private boolean isInAir;

    public Player(World world, Assets assets) {
        super(world);
        setDrawable(assets.get(Assets.player));
    }

    @Override
    protected BodyDef createBodyDef() {
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set((BubbleKiwiGame.WIDTH / 2.0f) / BubbleKiwiGame.PPM, (HEIGHT / 2.0f + 20.0f) / BubbleKiwiGame.PPM);
        bodyDef.fixedRotation = true;
        return bodyDef;
    }

    @Override
    protected FixtureDef createFixtureDef() {
        PolygonShape shape = (PolygonShape) createShape();

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.density = 1.0f;

        return fixtureDef;
    }

    private Shape createShape() {
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(WIDTH / (2.0f * BubbleKiwiGame.PPM), HEIGHT / (2.0f * BubbleKiwiGame.PPM));
        return shape;
    }

    @Override
    protected void initialize() {
        setSize(WIDTH, HEIGHT);
        setOrigin(WIDTH / 2.0f, HEIGHT / 2.0f);
    }

    public void moveRight() {
        if (!isInAir) {
            getActorBody().applyLinearImpulse(MOVE_RIGHT_IMPULSE, getActorBody().getWorldCenter(), true);
        }
    }

    public void moveLeft() {
        if (!isInAir) {
            getActorBody().applyLinearImpulse(MOVE_LEFT_IMPULSE, getActorBody().getWorldCenter(), true);
        }
    }

    public void jump() {
        if (!isInAir) {
            getActorBody().applyLinearImpulse(JUMP_IMPULSE, getActorBody().getWorldCenter(), true);

            isInAir = true;
        }
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        updatePosition();
    }

    public boolean isInAir() {
        return isInAir;
    }

    public void setInAir(boolean inAir) {
        isInAir = inAir;
    }
}
