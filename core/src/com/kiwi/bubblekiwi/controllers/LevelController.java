package com.kiwi.bubblekiwi.controllers;

public class LevelController {
    public enum LevelState {
        PLAY,
        GAME_OVER
    }

    private int lives;
    private int points;
    private LevelState state;
    private float stateTime;

    public LevelController() {
        this.lives = 3;
        this.points = 0;
        this.state = LevelState.PLAY;
        this.stateTime = 0.0f;
    }

    public void addTime(float time) {
        stateTime += time;
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
            if (points > this.points) {
                this.points = 0;
            } else {
                this.points -= points;
            }
        }
    }

    public void subtractLives(int lives) {
        if (state == LevelState.PLAY) {
            if (lives > this.lives) {
                this.lives = 0;
            } else {
                this.lives -= lives;
            }
        }
    }

    public int getLives() {
        return lives;
    }

    public int getPoints() {
        return points;
    }

    public float getStateTime() {
        return stateTime;
    }

    public LevelState getState() {
        return state;
    }

    public void setState(LevelState state) {
        this.stateTime = 0.0f;
        this.state = state;
    }
}
