package com.kiwi.bubblekiwi.entities;

import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.kiwi.bubblekiwi.BubbleKiwiGame;
import com.kiwi.bubblekiwi.controllers.Assets;

public class Player extends Image {
    private static final float WIDTH = 120;
    private static final float HALF_WIDTH = WIDTH / 2.0f;
    private static final float HEIGHT = 157;
    private static final float HALF_HEIGHT = HEIGHT / 2.0f;

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
        if (!isMovingLeft) {
            isMovingRight = true;
        }
    }

    public void moveLeft() {
        if (!isMovingRight) {
            isMovingLeft = true;
        }
    }

    public void jump() {
        if (!isInAir) {
            body.applyForceToCenter(0.0f, 100.0f, true);
            isInAir = true;
        }
    }

    public void update(float delta) {
        setPosition(body.getPosition().x - HALF_WIDTH / BubbleKiwiGame.PPM, body.getPosition().y - HALF_HEIGHT / BubbleKiwiGame.PPM);
        body.setLinearVelocity(0.0f, body.getLinearVelocity().y);
        if (isMovingLeft) {
            body.setLinearVelocity(-2.5f, body.getLinearVelocity().y);
            isMovingLeft = false;
        }
        if (isMovingRight) {
            body.setLinearVelocity(2.5f, body.getLinearVelocity().y);
            isMovingRight = false;
        }
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
