package com.kiwi.bubblekiwi.world.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Disposable;
import com.kiwi.bubblekiwi.BubbleKiwiGame;

public abstract class Actor extends Image implements Disposable {
    private Body actorBody;
    protected World world;

    public Actor(World world) {
        super();
        this.world = world;
        initializeBody();
        initialize();
    }

    protected void initializeBody() {
        actorBody = world.createBody(createBodyDef());
        actorBody.createFixture(createFixtureDef());
        actorBody.setUserData(this);
    }

    protected abstract BodyDef createBodyDef();

    protected abstract FixtureDef createFixtureDef();

    protected abstract void initialize();

    public Body getActorBody() {
        return actorBody;
    }

    public void updatePosition() {
        Vector2 actorBodyPosition = actorBody.getPosition();
        setPosition(actorBodyPosition.x - getOriginX(), actorBodyPosition.y - getOriginY());
    }

    public void setDrawable(Texture texture) {
        setDrawable(new TextureRegion(texture));
    }

    public void setDrawable(TextureRegion textureRegion) {
        TextureRegionDrawable drawable = (TextureRegionDrawable) getDrawable();
        if (drawable == null) {
            setDrawable(new TextureRegionDrawable(textureRegion));
        } else {
            drawable.setRegion(textureRegion);
        }
    }

    public boolean isMoving() {
        Vector2 velocity = actorBody.getLinearVelocity();
        return Math.abs(velocity.x) != 0.0f || Math.abs(velocity.y) != 0.0f;
    }

    @Override
    public void setOrigin(float originX, float originY) {
        super.setOrigin(originX / BubbleKiwiGame.PPM, originY / BubbleKiwiGame.PPM);
    }

    @Override
    public void setSize(float width, float height) {
        super.setSize(width / BubbleKiwiGame.PPM, height / BubbleKiwiGame.PPM);
    }

    @Override
    public void dispose() {
        world.destroyBody(actorBody);
    }
}
