package com.kiwi.bubblekiwi.world.elements;

import com.badlogic.gdx.physics.box2d.*;

public class GameplayBoundary {
    private World world;
    private GameplayBoundaryType type;
    private Body body;

    public GameplayBoundary(World world, GameplayBoundaryType type) {
        this.world = world;
        this.type = type;
        initializeBody();
    }

    private void initializeBody() {
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.StaticBody;
        bodyDef.position.set(type.getPosition());
        body = world.createBody(bodyDef);
        body.setUserData(this);
        initializeBodyFixture();
    }

    private void initializeBodyFixture() {
        PolygonShape shape = (PolygonShape) createShape();

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.friction = type.getFriction();

        body.createFixture(fixtureDef);
        shape.dispose();
    }

    private Shape createShape() {
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(type.getHalfSizes().x, type.getHalfSizes().y);
        return shape;
    }

    public Body getBody() {
        return body;
    }
}
