package com.kiwi.bubblekiwi.controllers;

public class LevelController {
    public enum LevelState {
        PLAY,
        GAME_OVER
    }

    private int lives;
    private int points;
    private LevelState state;

    public LevelController() {
        this.lives = 3;
        this.points = 0;
        state = LevelState.PLAY;
    }

    public void addPoints(int points) {
        if (state == LevelState.PLAY) {
            this.points += points;
        }
    }

    public void addLives(int lives) {
        if (state == LevelState.PLAY) {
            this.lives += lives;
        }
    }

    public void subtractPoints(int points) {
        if (state == LevelState.PLAY) {
            this.points -= points;
        }
    }

    public void subtractLives(int lives) {
        if (state == LevelState.PLAY) {
            this.lives -= lives;
        }
    }

    public int getLives() {
        return lives;
    }

    public int getPoints() {
        return points;
    }

    public LevelState getState() {
        return state;
    }

    public void setState(LevelState state) {
        this.state = state;
    }
}
