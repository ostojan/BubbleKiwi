package com.kiwi.bubblekiwi.controllers;

import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.FileHandleResolver;
import com.badlogic.gdx.assets.loaders.TextureAtlasLoader;
import com.badlogic.gdx.assets.loaders.TextureLoader;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.assets.loaders.resolvers.ResolutionFileResolver;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.utils.Disposable;

public class Assets implements Disposable {
    private AssetManager manager;

    public static final AssetDescriptor<Texture> farClouds =
            new AssetDescriptor<Texture>("backgrounds/far_clouds.png", Texture.class);
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

    public static AssetDescriptor<Texture> arrow =
            new AssetDescriptor<Texture>("ui/arrow.png", Texture.class);

    public Assets() {
        manager = new AssetManager();
        FileHandleResolver resolver = createResolutionFileResolver();
        manager.setLoader(Texture.class, new TextureLoader(resolver));
        manager.setLoader(TextureAtlas.class, new TextureAtlasLoader(resolver));
    }

    private FileHandleResolver createResolutionFileResolver() {
        ResolutionFileResolver.Resolution sd = new ResolutionFileResolver.Resolution(854, 480, "sd");
        ResolutionFileResolver.Resolution hd = new ResolutionFileResolver.Resolution(1280, 720, "hd");
        ResolutionFileResolver.Resolution fhd = new ResolutionFileResolver.Resolution(1920, 1080, "fhd");
        ResolutionFileResolver.Resolution qhd = new ResolutionFileResolver.Resolution(2560, 1440, "qhd");
        return new ResolutionFileResolver(new InternalFileHandleResolver(), sd, hd, fhd, qhd);
    }

    public void loadGameplayScreen() {
        manager.load(farClouds);
        manager.load(mountains);
        manager.load(nearClouds);
        manager.load(fog);
        manager.load(ground);
        manager.load(player);
        manager.load(bubbles);
        manager.load(arrow);
        manager.finishLoading();
    }

    public <T> T get(AssetDescriptor<T> descriptor) {
        manager.load(descriptor);
        return manager.get(descriptor);
    }

    @Override
    public void dispose() {
        manager.dispose();
    }
}
