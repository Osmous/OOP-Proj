package com.project.game.Entity;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public class PlayerEntity extends CharacterEntity{
    public PlayerEntity(int posX, int posY,String type, Texture tex, Rectangle rec) {
        super(posX, posY, type, tex, rec);
    }

    @Override
    public void renderEntity(SpriteBatch batch) {
        batch.begin();
        batch.draw(this.tex, this.posX,this.posY,this.rec.width,this.rec.height);
        batch.end();
    }

}
