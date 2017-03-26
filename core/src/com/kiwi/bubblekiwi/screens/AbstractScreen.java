package com.kiwi.bubblekiwi.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.kiwi.bubblekiwi.BubbleKiwiGame;

public abstract class AbstractScreen implements Screen {
    protected BubbleKiwiGame game;
    protected Stage stage;
    protected SpriteBatch spriteBatch;
    protected Color backgroundColor;

    private OrthographicCamera camera;

    public AbstractScreen(BubbleKiwiGame game) {
        this.game = game;
        createCamera();
        stage = new Stage(new StretchViewport(BubbleKiwiGame.WIDTH, BubbleKiwiGame.HEIGHT, camera));
        spriteBatch = new SpriteBatch();
        Gdx.input.setInputProcessor(stage);
        initialize();
    }

    private void createCamera() {
        camera = new OrthographicCamera();
        camera.setToOrtho(false, BubbleKiwiGame.WIDTH, BubbleKiwiGame.HEIGHT);
        camera.update();
    }

    protected abstract void initialize();

    @Override
    public void show() {
    }

    @Override
    public void render(float delta) {
        clearScreen();
        camera.update();
        spriteBatch.setProjectionMatrix(camera.combined);
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