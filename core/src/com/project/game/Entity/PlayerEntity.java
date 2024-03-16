package com.project.game.Entity;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class PlayerEntity extends CharacterEntity {

    protected int health;

    public PlayerEntity(int entityID, Vector2 pos, String type, Texture tex, Rectangle rectangle, float speed, int health) {
        super(entityID, pos, type, tex, rectangle, speed);
        this.health = health;
    }

    @Override
    protected void renderEntity(SpriteBatch batch) {
//        batch.draw(this.tex, this.pos.x, this.pos.y, this.rec.width, this.rec.height);
        sprite.setPosition(this.pos.x,this.pos.y);
        sprite.draw(batch);
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }
}