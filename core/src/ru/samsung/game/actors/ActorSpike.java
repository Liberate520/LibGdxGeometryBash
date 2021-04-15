package ru.samsung.game.actors;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class ActorSpike extends Actor {

    Texture textureSpike;

    public ActorSpike(Texture textureSpike) {
        this.textureSpike = textureSpike;
    }

    @Override
    public void act(float delta) {
        float x = getX();
        setX(x - 250 * delta);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(textureSpike, getX(), getY(), 200, 200);
    }
}
