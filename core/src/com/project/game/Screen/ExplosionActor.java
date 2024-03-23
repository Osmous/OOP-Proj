package com.project.game.Screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Array;

public class ExplosionActor extends Actor {
    private Animation<TextureRegion> explosionAnimation;
    private float stateTime = 0;
    private boolean animationFinished = false;
    private Texture texSheet;
    private static final int FRAME_COLS = 12, FRAME_ROWS = 1;

    public ExplosionActor(String texturePath) {
        super();
        texSheet = new Texture(Gdx.files.internal(texturePath));

        // Use the split utility method to create a 2D array of TextureRegions. This is
        // possible because this sprite sheet contains frames of equal size and they are
        // all aligned.
        TextureRegion[][] tmp = TextureRegion.split(texSheet,
                texSheet.getWidth() / FRAME_COLS,
                texSheet.getHeight() / FRAME_ROWS);
        this.setWidth((float) texSheet.getWidth() / FRAME_COLS);
        this.setHeight((float) texSheet.getHeight() / FRAME_ROWS);
        this.setOrigin(getWidth()/2,getHeight()/2);

        // Place the regions into a 1D array in the correct order, starting from the top
        // left, going across first. The Animation constructor requires a 1D array.
        // Currently set to single row with 12 columns
        TextureRegion[] explosionFrames = new TextureRegion[FRAME_COLS * FRAME_ROWS];
        int index = 0;
        for (int i = 0; i < FRAME_ROWS; i++) {
            for (int j = 0; j < FRAME_COLS; j++) {
                explosionFrames[index++] = tmp[i][j];
            }
        }
        // change float value to desired frame duration
        explosionAnimation = new Animation<TextureRegion>(0.15f, explosionFrames);

    }

    @Override
    public void act(float delta) {
        super.act(delta);
        stateTime += delta;
        if (!animationFinished && explosionAnimation.isAnimationFinished(stateTime)) {
            animationFinished = true;
            texSheet.dispose();
            remove(); // Remove the actor when the animation is finished
        }
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        if (!animationFinished) {
            TextureRegion currentFrame = explosionAnimation.getKeyFrame(stateTime);
            batch.draw(currentFrame, getX(), getY(), getOriginX(), getOriginY(), getWidth(), getHeight(), getScaleX(), getScaleY(), getRotation());
        }
    }
}

