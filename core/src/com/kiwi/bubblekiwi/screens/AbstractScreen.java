package com.kiwi.bubblekiwi.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.kiwi.bubblekiwi.BubbleKiwiGame;
import com.kiwi.bubblekiwi.controllers.Assets;

public abstract class AbstractScreen implements Screen {
    protected BubbleKiwiGame game;
    protected Stage stage;
    protected Color backgroundColor;
    protected Assets assets;

    private OrthographicCamera camera;

    public AbstractScreen(BubbleKiwiGame game, Assets assets) {
        this(game, assets, false);
    }

    public AbstractScreen(BubbleKiwiGame game, Assets assets, boolean realWorldCamera) {
        this.game = game;
        this.assets = assets;
        createCamera(realWorldCamera);
        createStage(realWorldCamera);
        Gdx.input.setInputProcessor(stage);
        initialize();
    }

    private void createCamera(boolean realWorldCamera) {
        camera = new OrthographicCamera();
        if (realWorldCamera) {
            camera.setToOrtho(false, BubbleKiwiGame.WIDTH / BubbleKiwiGame.PPM, BubbleKiwiGame.HEIGHT / BubbleKiwiGame.PPM);
        } else {
            camera.setToOrtho(false, BubbleKiwiGame.WIDTH, BubbleKiwiGame.HEIGHT);
        }
        camera.update();
    }

    private void createStage(boolean realWorldCamera) {
        if (realWorldCamera) {
            stage = new Stage(new StretchViewport(BubbleKiwiGame.WIDTH / BubbleKiwiGame.PPM, BubbleKiwiGame.HEIGHT / BubbleKiwiGame.PPM, camera));
        } else {
            stage = new Stage(new StretchViewport(BubbleKiwiGame.WIDTH, BubbleKiwiGame.HEIGHT, camera));
        }
    }

    protected abstract void initialize();

    @Override
    public void show() {
    }

    @Override
    public void render(float delta) {
        clearScreen();
        camera.update();
    }

    private void clearScreen() {
        Gdx.gl.glClearColor(backgroundColor.r, backgroundColor.g,  backgroundColor.b, backgroundColor.a);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    }

    @Override
    public void resize(int width, int height) {
    }

    @Override
    public void pause() {
        game.setPaused(true);
    }

    @Override
    public void resume() {
        game.setPaused(false);
    }

    @Override
    public void hide() {
    }

    @Override
    public void dispose() {
        game.dispose();
    }
}
