package com.kiwi.bubblekiwi.entities;

import com.badlogic.gdx.physics.box2d.*;
import com.kiwi.bubblekiwi.BubbleKiwiGame;
import com.kiwi.bubblekiwi.controllers.Assets;
import com.kiwi.bubblekiwi.data.PlayerConfiguration;

public class Player extends WorldActor {
    private static final float WIDTH = 120;
    private static final float HEIGHT = 157;

    private boolean isInAir;
    private PlayerConfiguration configuration;

    public Player(World world, Assets assets, PlayerConfiguration configuration) {
        super(world);
        setDrawable(assets.get(Assets.player));
        this.configuration = configuration;
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
            getActorBody().applyLinearImpulse(configuration.getMoveRightImpulse(), getActorBody().getWorldCenter(), true);
        }
    }

    public void moveLeft() {
        if (!isInAir) {
            getActorBody().applyLinearImpulse(configuration.getMoveLeftImpulse(), getActorBody().getWorldCenter(), true);
        }
    }

    public void jump() {
        if (!isInAir) {
            getActorBody().applyLinearImpulse(configuration.getJumpImpulse(), getActorBody().getWorldCenter(), true);
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
