package com.kiwi.bubblekiwi.data;

import java.util.List;

public class Level {
    private int id;
    private List<Background> backgrounds;

    public Level() {
    }

    public Level(int id, List<Background> backgrounds) {
        this.id = id;
        this.backgrounds = backgrounds;
    }

    public int getId() {
        return id;
    }

    public List<Background> getBackgrounds() {
        return backgrounds;
    }
}
