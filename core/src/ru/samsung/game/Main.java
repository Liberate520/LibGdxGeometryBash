package ru.samsung.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import ru.samsung.game.screens.GameScreen;

public class Main extends Game {

	private AssetManager manager;

	public AssetManager getManager() {
		return manager;
	}

	@Override
	public void create () {

//		MyProcessor p = new MyProcessor();
//		Gdx.input.setInputProcessor(p);

		manager = new AssetManager();
		manager.load("player.png", Texture.class);
		manager.load("spike.png", Texture.class);
		manager.load("background.jpg", Texture.class);
		manager.load("ground.png", Texture.class);
		manager.load("overground.png", Texture.class);
		manager.finishLoading();

		setScreen(new GameScreen(this));
	}

}
