package com.project.game;

// Handle player movement from IO Manager

public class PlayerControlManager {
    private Player player; // Declared at the class level
    
    // Constructor 
    public PlayerControlManager(Player player) {
        this.player = player;
    }
    
    // Method to move player left
    public void movePlayerLeft() {
        // Implement the logic to move the player left
        player.moveLeft();
    }
    
    // Method to move player right
    public void movePlayerRight() {
        // Implement the logic to move the player right
        player.moveRight();
    }
}
