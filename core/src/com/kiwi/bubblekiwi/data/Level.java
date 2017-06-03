package com.kiwi.bubblekiwi.data;

import java.util.List;

public class Level {
    private int id;
    private List<Background> backgrounds;
    private int time;

    public Level() {
    }

    public Level(int id, List<Background> backgrounds, int time) {
        this.id = id;
        this.backgrounds = backgrounds;
        this.time = time;
    }

    public int getId() {
        return id;
    }

    public List<Background> getBackgrounds() {
        return backgrounds;
    }

    public int getTime() {
        return time;
    }
}
