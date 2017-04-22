package com.kiwi.bubblekiwi.ui;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.kiwi.bubblekiwi.BubbleKiwiGame;
import com.kiwi.bubblekiwi.controllers.Assets;

public class MoveButton extends ImageButton {
    private static final float WIDTH = 120.0f;
    private static final float HEIGHT = 120.0f;

    public enum MoveButtonTypes {
        LEFT(180.0f, 20.0f, 20.0f),
        RIGHT(0.0f, 160.0f, 20.0f),
        JUMP(90.0f, BubbleKiwiGame.WIDTH - 140.0f, 20.0f);

        private float imageRotation;
        private float x;
        private float y;

        MoveButtonTypes(float imageRotation, float x, float y) {
            this.imageRotation = imageRotation;
            this.x = x;
            this.y = y;
        }

        public float getImageRotation() {
            return imageRotation;
        }

        public float getX() {
            return x;
        }

        public float getY() {
            return y;
        }
    }

    public MoveButton(MoveButtonTypes type, Assets assets) {
        super(createImageButtonStyle(assets));
        initialize(type);
    }

    private void initialize(MoveButtonTypes type) {
        setSize(WIDTH, HEIGHT);
        setOrigin((WIDTH / 2.0f), (HEIGHT / 2.0f));
        setPosition(type.getX(), type.getY());
        setTransform(true);
        setRotation(type.getImageRotation());
    }

    private static ImageButtonStyle createImageButtonStyle(Assets assets) {
        TextureRegionDrawable drawable = new TextureRegionDrawable(new TextureRegion(assets.get(Assets.arrow)));
        return new ImageButtonStyle(drawable,
                drawable,
                null,
                null,
                null,
                null);
    }
}
