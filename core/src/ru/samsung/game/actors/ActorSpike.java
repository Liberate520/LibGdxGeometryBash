package ru.samsung.game.actors;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class ActorSpike extends Actor {

    TextureRegion textureSpike;
    float spikeWidth = 200;
    float spikeHeight = 200;

    public ActorSpike(TextureRegion textureSpike) {
        this.textureSpike = textureSpike;
        setSize(spikeWidth, spikeHeight);
    }

    @Override
    public void act(float delta) {
        float x = getX();
        setX(x - 350 * delta);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(textureSpike, getX(), getY(), spikeWidth, spikeHeight);
    }
}
