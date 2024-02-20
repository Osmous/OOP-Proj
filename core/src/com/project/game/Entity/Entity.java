package com.project.game.Entity;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public abstract class Entity {
    protected int entityID;
    protected Vector2 pos;
    protected String type;
    protected Texture tex;
    protected Rectangle rec;
    protected float speed;

    protected Entity(int entityID, Vector2 pos, String type, Texture tex, Rectangle rectangle, float speed) {
        this.entityID = entityID;
        this.pos = pos;
        this.type = type;
        this.tex = tex;
        this.rec = rectangle;
        this.speed = speed;
    }
    protected void dispose(){
        this.tex.dispose();
    }
        protected void setPosX(float posX) {
        this.pos.x = posX;
    }

    protected void setPosY(float posY) {
        this.pos.y = posY;
    }
    protected Texture getTex() {
        return tex;
    }

    protected void setTex(Texture tex) {
        this.tex = tex;
    }

    public String getType() {
        return type;
    }

    protected void setType(String type) {
        this.type = type;
    }

    public int getEntityID() {
        return entityID;
    }

    protected void setEntityID(int entityID) {
        this.entityID = entityID;
    }

    public Rectangle getRec() {
        return rec;
    }

    protected void setRec(Rectangle rec) {
        this.rec = rec;
    }

    protected void renderEntity(SpriteBatch batch) {
    }
    public float getSpeed() {
        return speed;
    }

    protected void setSpeed(float speed) {
        this.speed = speed;
    }

    public Vector2 getPos() {
        return pos;
    }

    protected void setPos(Vector2 pos) {
        this.pos = pos;
    }
}
