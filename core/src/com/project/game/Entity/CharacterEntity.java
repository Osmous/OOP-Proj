package com.project.game.Entity;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public abstract class CharacterEntity extends Entity {
    // to do add health and what not
    protected int health;
    public CharacterEntity(int entityID, Vector2 pos, String type, Texture tex, Rectangle rectangle, float speed, int health) {
        super(entityID, pos, type, tex, rectangle, speed);
        this.health = health;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }
}
