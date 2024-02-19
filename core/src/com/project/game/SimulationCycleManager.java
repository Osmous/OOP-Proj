package com.project.game;

import com.project.game.Screen.SceneManager;

public class SimulationCycleManager {
    private boolean isGameRunning;
    private SceneManager sceneManager;
    	
    	public SimulationCycleManager(SceneManager sceneManager) {
    		this.sceneManager = sceneManager;
    		this.isGameRunning = false;
    	}
    	
    	public void loadLevel() {
    		String levelPath = sceneManager.getLevelScenePath();
    		// Load the level using the levelPath
    		// What is our implementation of level loading ah?
    	}
    	
    	public void startGame() {
    		this.isGameRunning = true;
    		loadLevel();
    	}
    	
 }