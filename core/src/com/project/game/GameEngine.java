package com.project.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.JsonReader;
import com.badlogic.gdx.utils.JsonValue;
import com.project.game.Entity.EntityManager;
import com.project.game.IO.IOManager;
import com.project.game.Screen.SceneManager;
import com.project.game.SimulationLifeCycle.SimulationCycleManager;

public class GameEngine extends Game {
	private static String gameConfigPath;
	public static JsonValue config;
	public static EntityManager entityManager;
	public static AIControlManager aiControlManager;
	public static PlayerControlManager playerControlManager;
	public static IOManager ioManager;
	public static CollisionManager collisionManager;
	public static SimulationCycleManager simulationCycleManager;
	public static SceneManager sceneManager;
	public void loadConfig(){
		//load game specific configs.
		JsonReader json = new JsonReader();
		this.config = json.parse(Gdx.files.internal(this.gameConfigPath));

	}
	@Override
	public void create() {
		// load global configs for game
		gameConfigPath = "config.json";
		loadConfig();

		// Init all managers
		entityManager = new EntityManager(this);
		aiControlManager = new AIControlManager(this);
		playerControlManager = new PlayerControlManager(this);
		ioManager = new IOManager(this);
		collisionManager = new CollisionManager(this);
		simulationCycleManager = new SimulationCycleManager(this);
		sceneManager = new SceneManager(this);
		Gdx.input.setInputProcessor(ioManager.getInputHandler());

		//set Main Screen as first Screen
		sceneManager.setMainScreen();


	};
	@Override
	public void render() {
		// render() function is main process loop
		// render scenes
		sceneManager.render();
		// Check Collision
		collisionManager.checkCollisions();
		if (!ioManager.getKeypressedlist().isEmpty()){
			playerControlManager.handleInput(ioManager.getKeypressedlist());
		}

	};
	@Override
	public void dispose() {
		if (screen != null) screen.hide();
		entityManager.clearAllEntities();
	};
	@Override
	public void pause(){
		simulationCycleManager.pauseGame();
	}

	@Override
	public void resume(){
		simulationCycleManager.pauseGame();
	}

}