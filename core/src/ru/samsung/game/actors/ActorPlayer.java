package ru.samsung.game.actors;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class ActorPlayer extends Actor {

    private Texture playerImg;
    float playerWidth = 200;
    float playerHeight = 200;

    public ActorPlayer(Texture playerImg) {
        this.playerImg = playerImg;
        setSize(playerWidth, playerHeight);
    }

    @Override
    public void act(float delta) {

    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(playerImg, getX(), getY(), playerWidth, playerHeight);
    }
}
