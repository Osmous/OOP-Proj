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

        //caluate direction of projectile travel and calculate the angle which the projectile sprite needs to be rotated
        //in order to make it look like the projectile is traveling in the direction its facing
        this.direction = new Vector2( mouseClick.x-this.pos.x-this.sprite.getWidth()/2,mouseClick.y - this.pos.y-this.sprite.getHeight()/2);
        this.direction.nor(); // Normalize the direction vector
        this.rotation = this.direction.angleDeg();
        sprite.setRotation(this.rotation);
    }
    public Vector2 getDirection() {
        return direction;
    }

}
