package com.kiwi.bubblekiwi.world.entities;

import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

public abstract class AnimatedActor<State extends Enum<State>> extends Actor {
    protected ActorAnimationsStates<State> actorState;
    private float stateTime;

    public AnimatedActor(World world, ActorAnimationsStates initialState) {
        super(world);
        actorState = initialState;
        setDrawable(new TextureRegionDrawable(actorState.getFrame(stateTime)));
    }

    public void setActorState(State state) {
        actorState.setState(state);
        if (actorState.isTimeResetNeeded()) {
            stateTime = 0.0f;
        }
    }

    public State getActorState() {
        return actorState.getState();
    }

    public boolean isAnimationFinished() {
        return actorState.isAnimationFinished(stateTime);
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        stateTime += delta;
        setDrawable(actorState.getFrame(stateTime));
    }
}
