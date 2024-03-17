package com.project.game.Entity;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;


public abstract class Entity extends Actor {
    protected int entityID;
    protected Vector2 pos;
    protected String type;
    protected Texture tex;
    protected Rectangle rec;
    protected float speed;
    protected boolean[] blockedMovement;
    protected Sprite sprite;
    protected long nextHitTime;
    protected float rotation;

    protected Entity(int entityID, Vector2 pos, String type, Texture tex, Rectangle rectangle, float speed) {
        this.entityID = entityID;
        this.pos = pos;
        this.type = type;
        this.tex = tex;
        this.rec = rectangle;
        this.speed = speed;
        this.rotation=0;
        // check variable for if for any reason cannot move in the any of the 4 directions
        // left, top , right , bottom
        this.blockedMovement = new boolean[4];
        this.nextHitTime = 0;
        this.sprite = new Sprite(this.tex);
        sprite.setPosition(this.pos.x,this.pos.y);
    }
    @Override
    public void draw(Batch batch, float parentAlpha) {
//        batch.draw(this.tex, this.pos.x, this.pos.y, this.rec.width, this.rec.height);
        sprite.setPosition(this.pos.x,this.pos.y);
        sprite.draw(batch);
    }
    @Override
    public void act(float delta) {
        super.act(delta);
        // Here you can add logic that needs to run each frame, if necessary
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

    public boolean[] getBlockedMovement() {
        return blockedMovement;
    }

    protected void setBlockedMovement(int index, boolean s) {
        this.blockedMovement[index] = s;
    }

    public long getNextHitTime() {
        return nextHitTime;
    }
    public void setNextHitTime(long nextHitTime) {
        this.nextHitTime = nextHitTime;
    }

    @Override
    public float getRotation() {
        return rotation;
    }

    @Override
    public void setRotation(float rotation) {
        this.rotation = rotation;
        sprite.setRotation(this.rotation);
        super.setRotation(rotation);
    }
}
