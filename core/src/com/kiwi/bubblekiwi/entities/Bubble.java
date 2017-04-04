package com.kiwi.bubblekiwi.entities;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Disposable;
import com.kiwi.bubblekiwi.BubbleKiwiGame;

public class Bubble extends Image implements Disposable {
    private BubblesController bubblesController;
    private World world;
    private Body body;
    private TextureRegion textureRegion;
    private float radius;
    private float startX;

    private void initialize() {
        setDrawable(new TextureRegionDrawable(textureRegion));
        setSize((radius * 2.0f) / BubbleKiwiGame.PPM, (radius * 2.0f) / BubbleKiwiGame.PPM);
        initializeBody();

    }

    private void initializeBody() {
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(startX / BubbleKiwiGame.PPM, (BubbleKiwiGame.HEIGHT + radius) / BubbleKiwiGame.PPM);
        body = world.createBody(bodyDef);
        body.setGravityScale(0.01f);
        body.setUserData(this);

        initializeBodyFixture();
    }

    private void initializeBodyFixture() {
        CircleShape shape = (CircleShape) createShape();

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.density = 0.0001f;

        body.createFixture(fixtureDef);
        shape.dispose();
    }

    private Shape createShape() {
        CircleShape shape = new CircleShape();
        shape.setRadius(radius / BubbleKiwiGame.PPM);
        return shape;
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        setPosition(body.getPosition().x - radius / BubbleKiwiGame.PPM, body.getPosition().y - radius / BubbleKiwiGame.PPM);
    }

    @Override
    public boolean remove() {
        bubblesController.removeBubble(this);
        return super.remove();
    }

    @Override
    public void dispose() {
        world.destroyBody(body);
    }

    public Body getBody() {
        return body;
    }

    public static class Builder {

        private Bubble bubble;

        public Builder() {
            bubble = new Bubble();
        }

        public Builder textureRegion(TextureRegion region) {
            bubble.textureRegion = region;
            return this;
        }

        public Builder radius(float radius) {
            bubble.radius = radius;
            return this;
        }

        public Builder startX(float startX) {
            bubble.startX = startX;
            return this;
        }

        public Builder world(World world) {
            bubble.world = world;
            return this;
        }

        public Builder bubblesController(BubblesController controller) {
            bubble.bubblesController = controller;
            return this;
        }

        public Bubble build() {
            bubble.initialize();
            return bubble;
        }

    }
}
