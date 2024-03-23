package com.project.game.Screen.HUD;

import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.Align;

public class HealthBar extends Group {
    private final Texture heartTexture;
    private int maxHealth;

    public HealthBar(int maxHealth) {
        this.maxHealth = maxHealth;
        heartTexture = new Texture("heart.png"); // Load the heart texture

        for (int i = 0; i < maxHealth; i++) {
            Image heart = new Image(heartTexture);
            heart.setPosition(i * (heart.getWidth() + 5), 0); // Position hearts with a small gap
            this.addActor(heart); // Add heart to the group
        }
    }

    public void setHealth(int health) {
        if (health > maxHealth) health = maxHealth;
        if (health < 0) health = 0;

        for (int i = 0; i < this.getChildren().size; i++) {
            this.getChildren().get(i).setVisible(i < health); // Show or hide hearts based on health
        }
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        // Here you can add logic that needs to run each frame, if necessary
    }

    public void dispose() {
        heartTexture.dispose(); // Don't forget to dispose of the texture
    }
}

