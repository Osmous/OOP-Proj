package com.project.game.Entity;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public class EnemyEntity extends CharacterEntity {
    protected EnemyEntity(int entityID, float posX, float posY, String type, Texture tex, Rectangle rec) {
        super(entityID, posX, posY, type, tex, rec);
    }

    @Override
    protected void renderEntity(SpriteBatch batch) {
        batch.draw(this.tex, this.posX, this.posY, this.rec.width, this.rec.height);
    }

}
