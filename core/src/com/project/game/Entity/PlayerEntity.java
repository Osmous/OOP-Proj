package com.project.game.Entity;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class PlayerEntity extends CharacterEntity {
    public PlayerEntity(int entityID, Vector2 pos, String type, Texture tex, Rectangle rectangle, float speed) {
        super(entityID, pos, type, tex, rectangle, speed);
    }

    @Override
    protected void renderEntity(SpriteBatch batch) {
        batch.draw(this.tex, this.pos.x, this.pos.y, this.rec.width, this.rec.height);
    }
}