package com.kiwi.bubblekiwi.entities;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.kiwi.bubblekiwi.BubbleKiwiGame;

public class GameplayBoundary {
    public enum GameplayBoundaryTypes {
        DOWN(new Vector2((BubbleKiwiGame.WIDTH / 2.0f) / BubbleKiwiGame.PPM, 20.0f / BubbleKiwiGame.PPM),
                new Vector2((BubbleKiwiGame.WIDTH / 2.0f) / BubbleKiwiGame.PPM, 0.0f),
                0.8f),
        LEFT(new Vector2(0.0f, (BubbleKiwiGame.HEIGHT / 2.0f) / BubbleKiwiGame.PPM),
                new Vector2(0.0f, (BubbleKiwiGame.HEIGHT / 2.0f) / BubbleKiwiGame.PPM),
                0.0f),
        RIGHT(new Vector2(BubbleKiwiGame.WIDTH / BubbleKiwiGame.PPM, (BubbleKiwiGame.HEIGHT / 2.0f) / BubbleKiwiGame.PPM),
                new Vector2(0.0f, (BubbleKiwiGame.HEIGHT / 2.0f) / BubbleKiwiGame.PPM),
                0.0f);

        private Vector2 position;
        private Vector2 halfSizes;
        private final float friction;

        GameplayBoundaryTypes(Vector2 position, Vector2 halfSizes, float friction) {
            this.position = position;
            this.halfSizes = halfSizes;
            this.friction = friction;
        }

        public Vector2 getPosition() {
            return position;
        }

        public Vector2 getHalfSizes() {
            return halfSizes;
        }

        public float getFriction() {
            return friction;
        }
    }

    private World world;
    private GameplayBoundaryTypes type;
    private Body body;

    public GameplayBoundary(World world, GameplayBoundaryTypes type) {
        this.world = world;
        this.type = type;
        initializeBody();
    }

    private void initializeBody() {
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.StaticBody;
        bodyDef.position.set(type.getPosition());
        body = world.createBody(bodyDef);

        initializeBodyFixture();
    }

    private void initializeBodyFixture() {
        PolygonShape shape = (PolygonShape) createShape();

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.friction = type.friction;

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
