package com.kiwi.bubblekiwi.entities;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.kiwi.bubblekiwi.BubbleKiwiGame;
import com.kiwi.bubblekiwi.controllers.Assets;

public class Player extends Image {
    private static final float WIDTH = 120;
    private static final float HALF_WIDTH = WIDTH / 2.0f;
    private static final float HEIGHT = 157;
    private static final float HALF_HEIGHT = HEIGHT / 2.0f;
    private static final float MOVE_IMPULSE_VALUE = 0.05f;
    private static final Vector2 MOVE_LEFT_IMPULSE = new Vector2(-MOVE_IMPULSE_VALUE, 0.0f);
    private static final Vector2 MOVE_RIGHT_IMPULSE = new Vector2(MOVE_IMPULSE_VALUE, 0.0f);
    private static final float JUMP_IMPULSE_VALUE = 1.8f;
    private static final Vector2 JUMP_IMPULSE = new Vector2(0.0f, JUMP_IMPULSE_VALUE);

    private World world;
    private Assets assets;
    private Body body;
    private boolean isInAir;
    private boolean isMovingLeft;
    private boolean isMovingRight;

    public Player(World world, Assets assets) {
        super(assets.get(Assets.player));
        this.world = world;
        this.assets = assets;
        setSize(WIDTH / BubbleKiwiGame.PPM, HEIGHT / BubbleKiwiGame.PPM);
        setOrigin(HALF_WIDTH / BubbleKiwiGame.PPM, HALF_HEIGHT / BubbleKiwiGame.PPM);
        initializeBody();
    }

    private void initializeBody() {
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set((BubbleKiwiGame.WIDTH / 2.0f) / BubbleKiwiGame.PPM, (HALF_HEIGHT + 25.0f) / BubbleKiwiGame.PPM);
        bodyDef.fixedRotation = true;
        body = world.createBody(bodyDef);
        body.setUserData(this);

        initializeBodyFixture();
    }

    private void initializeBodyFixture() {
        PolygonShape shape = (PolygonShape) createShape();

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.density = 1.0f;

        body.createFixture(fixtureDef);
        shape.dispose();
    }

    private Shape createShape() {
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(HALF_WIDTH / BubbleKiwiGame.PPM, HALF_HEIGHT / BubbleKiwiGame.PPM);
        return shape;
    }

    public void moveRight() {
        if (!isInAir) {
            body.applyLinearImpulse(MOVE_RIGHT_IMPULSE, body.getWorldCenter(), true);
        }
    }

    public void moveLeft() {
        if (!isInAir) {
            body.applyLinearImpulse(MOVE_LEFT_IMPULSE, body.getWorldCenter(), true);
        }
    }

    public void jump() {
        if (!isInAir) {
            body.applyLinearImpulse(JUMP_IMPULSE, body.getWorldCenter(), true);

            isInAir = true;
        }
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        setPosition(body.getPosition().x - HALF_WIDTH / BubbleKiwiGame.PPM, body.getPosition().y - HALF_HEIGHT / BubbleKiwiGame.PPM);
    }

    public Body getBody() {
        return body;
    }

    public boolean isInAir() {
        return isInAir;
    }

    public void setInAir(boolean inAir) {
        isInAir = inAir;
    }
}
