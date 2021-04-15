package ru.samsung.game.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;

import ru.samsung.game.Main;
import ru.samsung.game.actors.ActorPlayer;
import ru.samsung.game.actors.ActorSpike;
import sun.rmi.runtime.Log;

public class MainGameScreen extends BaseScreen {

    private Stage stage;
    private ActorPlayer player;
    private ActorSpike spike;
    private Texture texturePlayer;
    private Texture textureSpike;

    public MainGameScreen(Main game) {
        super(game);
        texturePlayer = new Texture("player.png");
        textureSpike = new Texture("spike.png");
    }

    @Override
    public void show() {
        stage = new Stage();

        player = new ActorPlayer(texturePlayer);
        spike = new ActorSpike(textureSpike);

        stage.addActor(player);
        stage.addActor(spike);

        player.setPosition(300 - player.getWidth(), 300 - player.getHeight());
        spike.setPosition(1500 - player.getWidth(), 300 - player.getHeight());
    }

    @Override
    public void hide() {
        stage.dispose();
        texturePlayer.dispose();
        textureSpike.dispose();
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.act();
        stage.draw();
    }
}
