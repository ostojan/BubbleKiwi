package com.kiwi.bubblekiwi.controllers;

import com.badlogic.gdx.physics.box2d.*;
import com.kiwi.bubblekiwi.world.actors.bubble.Bubble;
import com.kiwi.bubblekiwi.world.elements.GameplayBoundary;
import com.kiwi.bubblekiwi.world.actors.Player;

public class GameplayContactListener implements ContactListener {
    private Player player;
    private GameplayBoundary ground;
    private LevelController levelController;

    public GameplayContactListener(Player player, GameplayBoundary groundBoundary, LevelController levelController) {
        this.player = player;
        this.ground = groundBoundary;
        this.levelController = levelController;
    }

    @Override
    public void beginContact(Contact contact) {
        Body firstContactBody = contact.getFixtureA().getBody();
        Body secondContactBody = contact.getFixtureB().getBody();
        if (playerTouchedGround(firstContactBody, secondContactBody)) {
            player.setInAir(false);
        } else if (bubbleTouchedGround(firstContactBody, secondContactBody)) {
            Bubble bubble = findBubbleThatTouchedGround(firstContactBody, secondContactBody);
            if (bubble != null) {
                bubble.destroy();
                levelController.subtractLives(1);
            }
        } else if (playerTouchedBubble(firstContactBody, secondContactBody)) {
            Bubble bubble = findBubbleThatTouchedPlayer(firstContactBody, secondContactBody);
            if (bubble != null) {
                bubble.destroy();
                levelController.addPoints(1);
            }
        }
    }

    @Override
    public void endContact(Contact contact) {
    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {
    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {
    }

    private boolean playerTouchedGround(Body first, Body second) {
        return ((first.getUserData() == player) && (second.getUserData() == ground)) ||
                ((first.getUserData() == ground) && (second.getUserData() == player));
    }

    private boolean bubbleTouchedGround(Body first, Body second) {
        return ((first.getUserData() == ground) && (second.getUserData() != player)) ||
                ((first.getUserData() != player) && (second.getUserData() == ground));
    }

    private boolean playerTouchedBubble(Body first, Body second) {
        return ((first.getUserData() == player) && (second.getUserData() != ground)) ||
                ((first.getUserData() != ground) && (second.getUserData() == player));
    }

    private Bubble findBubbleThatTouchedGround(Body first, Body second) {
        return findBubbleThatTouchedObject(first, second, ground);
    }

    private Bubble findBubbleThatTouchedPlayer(Body first, Body second) {
        return findBubbleThatTouchedObject(first, second, player);
    }

    private Bubble findBubbleThatTouchedObject(Body first, Body second, Object userDataObject) {
        try {
            if (first.getUserData() == userDataObject) {
                return (Bubble) second.getUserData();
            }
            if (second.getUserData() == userDataObject) {
                return (Bubble) first.getUserData();
            }
        } catch (ClassCastException e) {
            return null;
        }
        return null;
    }
}
