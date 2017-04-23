package com.kiwi.bubblekiwi.controllers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.FileHandleResolver;
import com.badlogic.gdx.assets.loaders.TextureAtlasLoader;
import com.badlogic.gdx.assets.loaders.TextureLoader;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.assets.loaders.resolvers.ResolutionFileResolver;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGeneratorLoader;
import com.badlogic.gdx.graphics.g2d.freetype.FreetypeFontLoader;
import com.badlogic.gdx.utils.Disposable;
import com.kiwi.bubblekiwi.data.Level;

import java.util.ArrayList;
import java.util.List;

public class Assets implements Disposable {
    private AssetManager manager;

    public static final AssetDescriptor<Texture> farClouds =
            new AssetDescriptor<Texture>("backgrounds/far_clouds.png", Texture.class);
    public static final AssetDescriptor<Texture> middleClouds =
            new AssetDescriptor<Texture>("backgrounds/middle_clouds.png", Texture.class);
    public static final AssetDescriptor<Texture> mountains =
            new AssetDescriptor<Texture>("backgrounds/mountains.png", Texture.class);
    public static final AssetDescriptor<Texture> nearClouds =
            new AssetDescriptor<Texture>("backgrounds/near_clouds.png", Texture.class);
    public static final AssetDescriptor<Texture> fog =
            new AssetDescriptor<Texture>("backgrounds/fog.png", Texture.class);
    public static final AssetDescriptor<Texture> ground =
            new AssetDescriptor<Texture>("backgrounds/ground.png", Texture.class);

    public static final AssetDescriptor<Texture> player =
            new AssetDescriptor<Texture>("sprites/kiwi.png", Texture.class);
    public static final AssetDescriptor<TextureAtlas> bubbles =
            new AssetDescriptor<TextureAtlas>("sprites/bubbles.atlas", TextureAtlas.class);
    public static final AssetDescriptor<TextureAtlas> bubbleDeath =
            new AssetDescriptor<TextureAtlas>("sprites/bubble_death.atlas", TextureAtlas.class);

    public static AssetDescriptor<Texture> arrow =
            new AssetDescriptor<Texture>("ui/arrow.png", Texture.class);

    public static AssetDescriptor<BitmapFont> arialSmall;
    public static AssetDescriptor<BitmapFont> arialMedium;
    public static AssetDescriptor<BitmapFont> arialBig;

    static {
        FreetypeFontLoader.FreeTypeFontLoaderParameter smallFontParams = new FreetypeFontLoader.FreeTypeFontLoaderParameter();
        smallFontParams.fontFileName = "fonts/arial.ttf";
        smallFontParams.fontParameters.size = 25;
        ;
        arialSmall = new AssetDescriptor<BitmapFont>("arialSmall.ttf", BitmapFont.class, smallFontParams);
        FreetypeFontLoader.FreeTypeFontLoaderParameter mediumFontParams = new FreetypeFontLoader.FreeTypeFontLoaderParameter();
        mediumFontParams.fontFileName = "fonts/arial.ttf";
        mediumFontParams.fontParameters.size = 50;
        arialMedium = new AssetDescriptor<BitmapFont>("arialMedium.ttf", BitmapFont.class, mediumFontParams);
        FreetypeFontLoader.FreeTypeFontLoaderParameter bigFontParams = new FreetypeFontLoader.FreeTypeFontLoaderParameter();
        bigFontParams.fontFileName = "fonts/arial.ttf";
        bigFontParams.fontParameters.size = 75;
        arialBig = new AssetDescriptor<BitmapFont>("arialBig.ttf", BitmapFont.class, bigFontParams);
    }

    public Assets() {
        manager = new AssetManager();
        FileHandleResolver resolver = createResolutionFileResolver();
        manager.setLoader(Texture.class, new TextureLoader(resolver));
        manager.setLoader(TextureAtlas.class, new TextureAtlasLoader(resolver));
        manager.setLoader(FreeTypeFontGenerator.class, new FreeTypeFontGeneratorLoader(resolver));
        manager.setLoader(BitmapFont.class, ".ttf", new FreetypeFontLoader(resolver));
        manager.setLoader(Level.class, new LevelLoader(resolver));
    }

    private FileHandleResolver createResolutionFileResolver() {
        ResolutionFileResolver.Resolution sd = new ResolutionFileResolver.Resolution(854, 480, "sd");
        ResolutionFileResolver.Resolution hd = new ResolutionFileResolver.Resolution(1280, 720, "hd");
        ResolutionFileResolver.Resolution fhd = new ResolutionFileResolver.Resolution(1920, 1080, "fhd");
        ResolutionFileResolver.Resolution qhd = new ResolutionFileResolver.Resolution(2560, 1440, "qhd");
        return new ResolutionFileResolver(new InternalFileHandleResolver(), sd, hd, fhd, qhd);
    }

    public void loadMenuScreen() {
        manager.load(arialSmall);
        manager.load(arialMedium);
        manager.load(arialBig);
        loadLevels();
        manager.finishLoading();
    }

    public void loadGameplayScreen() {
        manager.load(farClouds);
        manager.load(middleClouds);
        manager.load(mountains);
        manager.load(nearClouds);
        manager.load(fog);
        manager.load(ground);
        manager.load(player);
        manager.load(bubbles);
        manager.load(bubbleDeath);
        manager.load(arrow);
        manager.finishLoading();
    }

    public <T> T get(AssetDescriptor<T> descriptor) {
        manager.load(descriptor);
        return manager.get(descriptor);
    }

    public void loadLevels() {
        FileHandle levelsDir = Gdx.files.internal("levels");
        for (FileHandle level : levelsDir.list()) {
            manager.load(level.path(), Level.class);
        }
    }

    public List<Level> getLevels() {
        List<Level> levels = new ArrayList<Level>();
        FileHandle levelsDir = Gdx.files.internal("levels");
        for (FileHandle level : levelsDir.list()) {
            levels.add(manager.get(level.path(), Level.class));
        }
        return levels;
    }

    @Override
    public void dispose() {
        manager.dispose();
    }
}
