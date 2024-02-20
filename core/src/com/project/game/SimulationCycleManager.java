package com.project.game;

import com.badlogic.gdx.Gdx;
import com.project.game.GameEngine;
import com.project.game.Screen.SceneManager;

//dk if correct or not hehe. just testing LOL 

public class SimulationCycleManager {
    private GameEngine gameEngine;

    private enum GameState {
        START,
        PAUSE,
        RESUME,
        END
    }

    private GameState currentState;

    public SimulationCycleManager(GameEngine gameEngine) {
        this.gameEngine = gameEngine;
        this.currentState = GameState.START;
    }

    public void startGame() {
        if (currentState == GameState.START) {
            performStartGame();
            currentState = GameState.RESUME;
            System.out.println("Game started.");
        } else {
            System.out.println("Cannot start game. Game is already running or ended.");
        }
    }

    public void pauseGame() {
        if (currentState == GameState.RESUME) {
            performPauseGame();
            currentState = GameState.PAUSE;
            System.out.println("Game paused.");
        } else {
            System.out.println("Cannot pause game. Game is not running.");
        }
    }

    public void resumeGame() {
        if (currentState == GameState.PAUSE) {
            performResumeGame();
            currentState = GameState.RESUME;
            System.out.println("Game resumed.");
        } else {
            System.out.println("Cannot resume game. Game is not paused.");
        }
    }

    public void endGame() {
        if (currentState != GameState.END) {
            performEndGame();
            currentState = GameState.END;
            System.out.println("Game ended.");
        } else {
            System.out.println("Cannot end game. Game is already ended.");
        }
    }

    private void performStartGame() {
        // Logic to start the game
    }

    private void performPauseGame() {
        // Logic to pause the game
    }

    private void performResumeGame() {
        // Logic to resume the game
    }

    private void performEndGame() {
        // Logic to end the game
    }
    	
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
