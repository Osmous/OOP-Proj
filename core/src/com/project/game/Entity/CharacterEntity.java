package com.project.game.Entity;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

public abstract class CharacterEntity extends Entity {
    // to do add health and what not
    private float speed;
    public CharacterEntity(int entityID, float posX, float posY, String type, Texture tex, Rectangle rectangle, float speed) {
        super(entityID, posX, posY, type, tex, rectangle);
        this.speed = speed;
    }

    public float getSpeed() {
        return speed;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }
}
