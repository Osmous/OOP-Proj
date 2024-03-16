package com.project.game.Entity;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class ProjectileEntity extends Entity{
    private Vector2 direction;
    private float rotation;

    protected ProjectileEntity(int entityID, Vector2 pos, String type, Texture tex, Rectangle rectangle, float speed, Vector2 mouseClick) {
        super(entityID, pos, type, tex, rectangle, speed);
        this.direction = new Vector2(this.pos.x - mouseClick.x,this.pos.y - mouseClick.y);
        this.direction.nor(); // Normalize the direction vector
//        this.rotation = (MathUtils.atan2(this.pos.y, this.pos.x) - MathUtils.atan2(mouseClick.y, mouseClick.x));
        this.rotation= 45;
        sprite.setRotation(this.rotation);
    }

    @Override
    protected void renderEntity(SpriteBatch batch) {
//        batch.draw(this.tex, this.pos.x, this.pos.y, this.rec.width, this.rec.height);
        sprite.setPosition(this.pos.x,this.pos.y);
        sprite.draw(batch);
    }
}
