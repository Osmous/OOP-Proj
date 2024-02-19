package com.project.game.Entity;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

import java.nio.channels.FileLock;

public abstract class Entity {
    protected int entityID;
    protected float posX;
    protected float posY;
    protected String type;
    protected Texture tex;
    protected Rectangle rec;
    protected float speed;


    protected Entity(int entityID, float posX, float posY, String type, Texture tex, Rectangle rectangle, float speed) {
        this.entityID = entityID;
        this.posX = posX;
        this.posY = posY;
        this.type = type;
        this.tex = tex;
        this.rec = rectangle;
        this.speed = speed;
    }
    protected void dispose(){
        this.tex.dispose();
    }

    protected float getPosX() {
        return posX;
    }

    protected void setPosX(float posX) {
        this.posX = posX;
    }

    protected float getPosY() {
        return posY;
    }

    protected void setPosY(float posY) {
        this.posY = posY;
    }

    protected Texture getTex() {
        return tex;
    }

    protected void setTex(Texture tex) {
        this.tex = tex;
    }

    protected String getType() {
        return type;
    }

    protected void setType(String type) {
        this.type = type;
    }

    protected int getEntityID() {
        return entityID;
    }

    protected void setEntityID(int entityID) {
        this.entityID = entityID;
    }

    protected Rectangle getRec() {
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

    public void setSpeed(float speed) {
        this.speed = speed;
    }
}