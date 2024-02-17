package com.project.game.Entity;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public abstract class Entity {
    protected int entityID;
    protected int posX;
    protected int posY;
    protected String type;
    protected Texture tex;
    protected Rectangle rec;

    public Entity(int entityID, int posX, int posY, String type, Texture tex, Rectangle rectangle) {
        this.entityID = entityID;
        this.posX = posX;
        this.posY = posY;
        this.type = type;
        this.tex = tex;
        this.rec = rectangle;
    }
    public void dispose(){
        this.tex.dispose();
    }

    public int getPosX() {
        return posX;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public int getPosY() {
        return posY;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }

    public Texture getTex() {
        return tex;
    }

    public void setTex(Texture tex) {
        this.tex = tex;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getEntityID() {
        return entityID;
    }

    public void setEntityID(int entityID) {
        this.entityID = entityID;
    }

    public Rectangle getRec() {
        return rec;
    }

    public void setRec(Rectangle rec) {
        this.rec = rec;
    }

    public void renderEntity(SpriteBatch batch) {
    }
}
