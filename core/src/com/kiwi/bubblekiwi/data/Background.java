package com.kiwi.bubblekiwi.data;

import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.graphics.Texture;

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

    public AssetDescriptor<Texture> getAssetDescriptor() {
        return new AssetDescriptor<Texture>(String.format("backgrounds/%s", file), Texture.class);
    }
}
