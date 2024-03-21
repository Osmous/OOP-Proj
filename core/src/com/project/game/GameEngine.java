package com.project.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.JsonReader;
import com.badlogic.gdx.utils.JsonValue;
import com.project.game.Entity.EntityManager;
import com.project.game.Screen.SceneManager;
import com.project.game.SimulationLifeCycle.SimulationCycleManager;

public class GameEngine extends Game {
	private String gameConfigPath;
	public JsonValue config;

	public EntityManager entityManager;
	public AIControlManager aiControlManager;
	public PlayerControlManager playerControlManager;
	public IOManager ioManager;
	public CollisionManager collisionManager;
	public SimulationCycleManager simulationCycleManager;
	public SceneManager sceneManager;

	public void loadConfig(){
		//load game specific configs.
		JsonReader json = new JsonReader();
		this.config = json.parse(Gdx.files.internal(this.gameConfigPath));

	}
	@Override
	public void create() {
		// Init all managers
		entityManager = new EntityManager(this);
		aiControlManager = new AIControlManager(this);
		playerControlManager = new PlayerControlManager(this);
		ioManager = new IOManager(this);
		collisionManager = new CollisionManager(this);
		simulationCycleManager = new SimulationCycleManager(this);
		sceneManager = new SceneManager(this);
		Gdx.input.setInputProcessor(ioManager);
		gameConfigPath = "config.json";
		loadConfig();

		//set Main Screen as first Screen
		sceneManager.setMainScreen();
	};
	@Override
	public void render() {
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

}