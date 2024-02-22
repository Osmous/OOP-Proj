package com.project.game;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Gdx;
import com.project.game.Entity.Entity;

import java.util.ArrayList;
import java.util.List;

public class IOManager extends InputAdapter {
    private GameEngine gameEngine; // Reference to the game engine
    private List<String> keypressedlist; // List to store the pressed keys
    private boolean isButtonPressed; // Flag to track if a button is pressed

    public IOManager(GameEngine gameEngine) {
        this.gameEngine = gameEngine; // Initialize the game engine
        this.keypressedlist = new ArrayList<String>(); // Initialize the key pressed list
        this.isButtonPressed = false; // Initialize the button pressed flag
    }

    @Override
    public boolean keyDown(int keycode) {
//        if (keycode == Input.Keys.LEFT) {
//            //gameEngine.playerControlManager.movePlayerLeft();
//        } else if (keycode == Input.Keys.RIGHT) {
//            //gameEngine.playerControlManager.movePlayerRight();
//        }
        if (this.gameEngine.simulationCycleManager.getCurrentState().equals("RUNNING")){
            keypressedlist.add(Integer.toString(keycode));
            System.out.println(keycode);
        }
        return true;
    }

    @Override
    public boolean keyUp(int keycode){
        if (this.gameEngine.simulationCycleManager.getCurrentState().equals("RUNNING")){
            keypressedlist.remove(Integer.toString(keycode)); // Remove the released key from the list
        }
        return true;
    }

    public List<String> getKeypressedlist() {
        return keypressedlist; // Return the key pressed list
    }

    public void resetKeypressedList() {
        keypressedlist.clear(); // Clear the key pressed list
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        if (!isButtonPressed) {
            isButtonPressed = true; // Set the button pressed flag to true
            // Handle the click event here
        }
        return true;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        if (isButtonPressed) {
            isButtonPressed = false; // Set the button pressed flag to false
        }
        return true;
    }
}