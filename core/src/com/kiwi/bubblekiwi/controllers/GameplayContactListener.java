package com.kiwi.bubblekiwi.controllers;

import com.badlogic.gdx.physics.box2d.*;
import com.kiwi.bubblekiwi.entities.Bubble;
import com.kiwi.bubblekiwi.entities.GameplayBoundary;
import com.kiwi.bubblekiwi.entities.Player;

public class GameplayContactListener implements ContactListener {
    private Player player;
    private Body playerBody;
    private Body groundBody;
    private BubblesController bubblesController;

    public GameplayContactListener(Player player, BubblesController bubblesController, GameplayBoundary groundBoundary) {
        this.player = player;
        playerBody = player.getBody();
        this.bubblesController = bubblesController;
        groundBody = groundBoundary.getBody();
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
                bubble.remove();
            }
        } else if (playerTouchedBubble(firstContactBody, secondContactBody)) {
            Bubble bubble = findBubbleThatTouchedPlayer(firstContactBody, secondContactBody);
            if (bubble != null && player.isInAir()) {
                bubble.remove();
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
        return ((first == playerBody) && (second == groundBody)) || ((first == groundBody) && (second == playerBody));
    }

    private boolean bubbleTouchedGround(Body first, Body second) {
        return ((first == groundBody) && (second != playerBody)) || ((first != playerBody) && (second == groundBody));
    }

    private boolean playerTouchedBubble(Body first, Body second) {
        return ((first == playerBody) && (second != groundBody)) || ((first != groundBody) && (second == playerBody));
    }

    private Bubble findBubbleThatTouchedGround(Body first, Body second) {
        Body possibleBubbleBody = findPossibleBubbleBodyWithKnownBody(first, second, groundBody);
        return findBubbleWithBody(possibleBubbleBody);
    }

    private Bubble findBubbleThatTouchedPlayer(Body first, Body second) {
        Body possibleBubbleBody = findPossibleBubbleBodyWithKnownBody(first, second, playerBody);
        return findBubbleWithBody(possibleBubbleBody);
    }

    private Body findPossibleBubbleBodyWithKnownBody(Body first, Body second, Body knownBody) {
        if (first == knownBody) {
            return second;
        }
        if (second == knownBody) {
            return first;
        }
        return null;
    }

    private Bubble findBubbleWithBody(Body possibleBubbleBody) {
        if (possibleBubbleBody == null) {
            return null;
        }
        for (Bubble bubble : bubblesController.getBubbles()) {
            if (possibleBubbleBody == bubble.getBody()) {
                return bubble;
            }
        }
        return null;
    }
}
