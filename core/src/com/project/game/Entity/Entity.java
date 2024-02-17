package com.project.game.Entity;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

public abstract class Entity implements iDrawEntity {
    protected int posX;
    protected int posY;
    protected String type;
    protected Texture tex;
    protected Rectangle rec;

    public Entity(int posX, int posY, String type, Texture tex, Rectangle rectangle) {
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

    public Rectangle getRectangle() {
        return rec;
    }

    public void setRectangle(Rectangle rectangle) {
        this.rec = rectangle;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
