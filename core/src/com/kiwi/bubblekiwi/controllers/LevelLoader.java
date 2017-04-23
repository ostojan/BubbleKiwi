package com.kiwi.bubblekiwi.controllers;

import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetLoaderParameters;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.AsynchronousAssetLoader;
import com.badlogic.gdx.assets.loaders.FileHandleResolver;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Json;
import com.kiwi.bubblekiwi.data.Level;

public class LevelLoader extends AsynchronousAssetLoader<Level, LevelLoader.LevelParameters> {
    private Level level;

    public LevelLoader(FileHandleResolver resolver) {
        super(resolver);
    }

    @Override
    public void loadAsync(AssetManager manager, String fileName, FileHandle file, LevelParameters parameter) {
        String jsonString = new String(file.readBytes());
        Json json = new Json();
        level = json.fromJson(Level.class, jsonString);
    }

    @Override
    public Level loadSync(AssetManager manager, String fileName, FileHandle file, LevelParameters parameter) {
        return level;
    }

    @Override
    public Array<AssetDescriptor> getDependencies(String fileName, FileHandle file, LevelParameters parameter) {
        return null;
    }

    public static class LevelParameters extends AssetLoaderParameters<Level> {
    }
}
