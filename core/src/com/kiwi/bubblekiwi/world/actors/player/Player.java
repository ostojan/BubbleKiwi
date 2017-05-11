package com.kiwi.bubblekiwi.world.actors.player;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.*;
import com.kiwi.bubblekiwi.BubbleKiwiGame;
import com.kiwi.bubblekiwi.controllers.Assets;
import com.kiwi.bubblekiwi.data.PlayerConfiguration;
import com.kiwi.bubblekiwi.world.entities.AnimatedActor;

import java.util.HashMap;
import java.util.Map;

import static com.kiwi.bubblekiwi.world.actors.player.PlayerStates.*;

public class Player extends AnimatedActor<PlayerStates> {
    private static final float WIDTH = 120;
    private static final float HEIGHT = 157;

    private boolean isInAir;
    private PlayerConfiguration configuration;

    public static Player createPlayer(World world, Assets assets) {
        Map<PlayerStates, Animation<TextureRegion>> animations = new HashMap<PlayerStates, Animation<TextureRegion>>();
        Animation<TextureRegion> stayAnimation = new Animation<TextureRegion>(1.0f, assets.get(Assets.kiwiMoving).getRegions().first());
        Animation<TextureRegion> moveAnimation = new Animation<TextureRegion>(1.0f / 15.0f, assets.get(Assets.kiwiMoving).getRegions());
        Animation<TextureRegion> jumpAnimation = new Animation<TextureRegion>(1.0f / 30.0f, assets.get(Assets.kiwiJumping).getRegions());
        animations.put(STAYING, stayAnimation);
        animations.put(MOVING_LEFT, moveAnimation);
        animations.put(MOVING_RIGHT, moveAnimation);
        animations.put(JUMPING, jumpAnimation);
        PlayerAnimationsStates playerStates = new PlayerAnimationsStates(STAYING, animations);
        return new Player(world, playerStates, new PlayerConfiguration(0.05f, 1.8f));
    }

    public Player(World world, PlayerAnimationsStates playerState, PlayerConfiguration configuration) {
        super(world, playerState);
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
            setActorState(MOVING_RIGHT);
        }
    }

    public void moveLeft() {
        if (!isInAir) {
            getActorBody().applyLinearImpulse(configuration.getMoveLeftImpulse(), getActorBody().getWorldCenter(), true);
            setActorState(MOVING_LEFT);
        }
    }

    public void jump() {
        if (!isInAir) {
            getActorBody().applyLinearImpulse(configuration.getJumpImpulse(), getActorBody().getWorldCenter(), true);
            setActorState(JUMPING);
            isInAir = true;
        }
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        updatePosition();
        if (!isMoving()) {
            setActorState(STAYING);
        }
    }

    public void setInAir(boolean inAir) {
        isInAir = inAir;
    }
}
