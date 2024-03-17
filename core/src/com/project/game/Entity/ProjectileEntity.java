package com.project.game.Entity;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class ProjectileEntity extends Entity{
    private Vector2 direction;

    protected ProjectileEntity(int entityID, Vector2 pos, String type, Texture tex, Rectangle rectangle, float speed, Vector2 mouseClick) {
        super(entityID, pos, type, tex, rectangle, speed);
        this.direction = new Vector2( mouseClick.x-this.pos.x,mouseClick.y - this.pos.y);
        this.direction.nor(); // Normalize the direction vector
        this.rotation = this.direction.angleDeg();
        sprite.setRotation(this.rotation);
    }

    @Override
    protected void renderEntity(SpriteBatch batch) {
//        batch.draw(this.tex, this.pos.x, this.pos.y, this.rec.width, this.rec.height);
        sprite.setPosition(this.pos.x,this.pos.y);
        sprite.draw(batch);
    }
    public Vector2 getDirection() {
        return direction;
    }

}
