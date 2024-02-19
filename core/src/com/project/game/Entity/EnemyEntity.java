package com.project.game.Entity;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public class EnemyEntity extends CharacterEntity {
    public EnemyEntity(int entityID, float posX, float posY, String type, Texture tex, Rectangle rectangle, float speed) {
        super(entityID, posX, posY, type, tex, rectangle, speed);
    }

    @Override
    protected void renderEntity(SpriteBatch batch) {
        batch.draw(this.tex, this.posX, this.posY, this.rec.width, this.rec.height);
    }

}