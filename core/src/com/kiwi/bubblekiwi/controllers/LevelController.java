package com.kiwi.bubblekiwi.controllers;

public class LevelController {
    private int lives;
    private int points;

    public LevelController() {
        this.lives = 3;
        this.points = 0;
    }

    public void addPoints(int points) {
        this.points += points;
    }

    public void addLives(int lives) {
        this.lives += lives;
    }

    public void subtractPoints(int points) {
        this.points -= points;
    }

    public void subtractLives(int lives) {
        this.lives -= lives;
    }

    public int getLives() {
        return lives;
    }

    public int getPoints() {
        return points;
    }
}
