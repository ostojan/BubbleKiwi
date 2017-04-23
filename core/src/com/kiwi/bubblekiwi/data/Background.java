package com.kiwi.bubblekiwi.data;

public class Background {
    private String file;
    private float speed;

    public Background() {
    }

    public Background(String file, float speed) {
        this.file = file;
        this.speed = speed;
    }

    public String getFile() {
        return file;
    }

    public float getSpeed() {
        return speed;
    }
}
