package com.project.game;


import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class GameEngine extends Game {
	private String gameConfigPath;

	public EntityManager entityManager;
	public AIControlManager aiControlManager;
	public PlayerControlManager playerControlManager;
	public IOManager ioManager;
	public CollisionManager collisionManager;
	public SimulationCycleManager simulationCycleManager;
	public SceneManager sceneManager;

	public SpriteBatch batch;
	public BitmapFont font;

	 public void loadConfig(){
		//load game specific configs. dk if have tho but this is here nonetheless

	 }

	public void create() {
		// Init all managers
		entityManager = new EntityManager();
		aiControlManager = new AIControlManager();
		playerControlManager = new PlayerControlManager();
		ioManager = new IOManager();
		collisionManager = new CollisionManager();
		simulationCycleManager = new SimulationCycleManager();
		sceneManager = new SceneManager(this);

		//set batch and font style
		batch = new SpriteBatch();
		font = new BitmapFont();

		//set Main Screen as first Screen
		sceneManager.setMainScreen();
	};
	public void render() {
		// render scene
		sceneManager.render();
	};
	public void dispose() {
		// TODO
	};
}
