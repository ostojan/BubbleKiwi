package com.kiwi.bubblekiwi.entities;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

import java.util.Map;

public abstract class AnimatedWorldActor<ActorState extends Enum<ActorState>, AnimationsInitializer> extends WorldActor {
    protected Map<ActorState, Animation<TextureRegion>> animations;
    protected Animation<TextureRegion> currentAnimation;
    private ActorState actorState;
    private float stateTime;
    private boolean loopAnimation;

    public AnimatedWorldActor(World world, ActorState initialState, AnimationsInitializer initializer) {
        super(world);
        initializeAnimations(initializer);
        setActorState(initialState);
        setDrawable(new TextureRegionDrawable(currentAnimation.getKeyFrame(stateTime, loopAnimation)));
    }

    protected abstract void initializeAnimations(AnimationsInitializer initializer);

    public ActorState getActorState() {
        return actorState;
    }

    public void setActorState(ActorState actorState) {
        this.actorState = actorState;
        stateTime = 0.0f;
        currentAnimation = animations.get(this.actorState);
    }

    public boolean isLoopAnimation() {
        return loopAnimation;
    }

    public void setLoopAnimation(boolean loopAnimation) {
        this.loopAnimation = loopAnimation;
    }

    public float getStateTime() {
        return stateTime;
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        stateTime += delta;
        TextureRegion keyFrame = currentAnimation.getKeyFrame(stateTime, loopAnimation);
        setDrawable(keyFrame);
    }
}
