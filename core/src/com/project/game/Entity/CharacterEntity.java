package com.project.game.Entity;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

public abstract class CharacterEntity extends Entity {
    // to do add health and what not
    public CharacterEntity(int entityID, int posX, int posY, String type, Texture tex, Rectangle rec) {
        super(entityID, posX, posY, type, tex, rec);
    }


}
