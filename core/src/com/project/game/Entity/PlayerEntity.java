package com.project.game.Entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class PlayerEntity extends CharacterEntity {
    private float flashTimer = 0;
    private boolean isFlashing = false;
    private static final float FLASH_DURATION = 1.0f; // Total duration of flash
    private static final float FLASH_INTERVAL = 0.1f; // Interval between flashes
    public PlayerEntity(int entityID, Vector2 pos, String type, Texture tex, Rectangle rectangle, float speed, int health) {
        super(entityID, pos, type, tex, rectangle, speed, health);

    }
    @Override
    public void draw(Batch batch, float parentAlpha){
        super.draw(batch, parentAlpha);
    }
    @Override
    public void act(float deltaTime) {
        if (isFlashing) {
            flashTimer += deltaTime;

            // Calculate the alpha value based on the flash timer
            float alpha = ((int)(flashTimer / FLASH_INTERVAL) % 2 == 0) ? 0.0f : 1.0f;
            sprite.setAlpha(alpha);

            if (flashTimer >= FLASH_DURATION) {
                isFlashing = false; // Stop flashing after the duration ends
                sprite.setAlpha(1.0f); // Ensure the sprite is fully visible
                flashTimer = 0;
            }
        }
    }

    protected void startFlashing() {
        isFlashing = true;
        flashTimer = 0;
    }


}