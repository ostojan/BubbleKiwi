package com.kiwi.bubblekiwi.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.kiwi.bubblekiwi.BubbleKiwiGame;
import com.kiwi.bubblekiwi.controllers.Assets;

public abstract class AbstractScreen implements Screen {
    protected BubbleKiwiGame game;
    protected Stage stage;
    protected Stage worldStage;
    protected Assets assets;

    private OrthographicCamera camera;
    private OrthographicCamera worldCamera;

    public AbstractScreen(BubbleKiwiGame game, Assets assets) {
        this.game = game;
        this.assets = assets;
        createCameras();
        createStages();
        Gdx.input.setInputProcessor(stage);
        Gdx.gl.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);
    }

    private void createCameras() {
        worldCamera = new OrthographicCamera();
        worldCamera.setToOrtho(false, BubbleKiwiGame.WIDTH / BubbleKiwiGame.PPM, BubbleKiwiGame.HEIGHT / BubbleKiwiGame.PPM);
        worldCamera.update();
        camera = new OrthographicCamera();
        camera.setToOrtho(false, BubbleKiwiGame.WIDTH, BubbleKiwiGame.HEIGHT);
        camera.update();
    }

    private void createStages() {
        worldStage = new Stage(new StretchViewport(BubbleKiwiGame.WIDTH / BubbleKiwiGame.PPM, BubbleKiwiGame.HEIGHT / BubbleKiwiGame.PPM, worldCamera));
        stage = new Stage(new StretchViewport(BubbleKiwiGame.WIDTH, BubbleKiwiGame.HEIGHT, camera));
    }

    @Override
    public void show() {
    }

    @Override
    public void render(float delta) {
        clearScreen();
        camera.update();
        worldCamera.update();
    }

    private void clearScreen() {
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
        worldStage.dispose();
        stage.dispose();
    }

    protected void goToScreen(AbstractScreen screen) {
        dispose();
        game.setScreen(screen);
    }
}
