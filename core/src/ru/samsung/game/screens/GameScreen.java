package ru.samsung.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;

import java.util.ArrayList;
import java.util.List;

import ru.samsung.game.Main;
import ru.samsung.game.actors.SpikeActor;
import ru.samsung.game.actors.GroundActor;
import ru.samsung.game.actors.PlayerActor;

public class GameScreen extends BaseScreen{

    private Stage stage;
    private World world;

    private PlayerActor player;

    private List<GroundActor> groundList = new ArrayList<>();
    private List<SpikeActor> spikeList = new ArrayList<>();

    public GameScreen(Main game) {
        super(game);
        stage = new Stage(new FitViewport(640, 360));
        world = new World(new Vector2(0, -10), true);

        world.setContactListener(new ContactListener() {

            private boolean isCollide(Contact contact, String userA, String userB){
                return (contact.getFixtureA().getUserData().equals(userA) && contact.getFixtureB().getUserData().equals(userB) ||
                        contact.getFixtureA().getUserData().equals(userB) && contact.getFixtureB().getUserData().equals(userA));
            }

            @Override
            public void beginContact(Contact contact) {
                if (isCollide(contact, "player", "ground")){
                    player.setCanJump(true);
                    if (Gdx.input.isTouched()){
                        player.setAutoJump(true);
                    }
                }
                if (isCollide(contact, "player", "spike")){
                    player.setAlive(false);
                }
            }

            @Override
            public void endContact(Contact contact) {

            }

            @Override
            public void preSolve(Contact contact, Manifold oldManifold) {

            }

            @Override
            public void postSolve(Contact contact, ContactImpulse impulse) {

            }
        });
    }

    @Override
    public void show() {
        Texture playerTexture = game.getManager().get("player.png");
        Texture spikeTexture = game.getManager().get("spike.png");
        Texture backgroundTexture = game.getManager().get("background.jpg");
        Texture groundTexture = game.getManager().get("ground.png");
        Texture overgroundTexture = game.getManager().get("overground.png");
        player = new PlayerActor(world, playerTexture, new Vector2(1.5f, 1.5f));

        groundList.add(new GroundActor(world, groundTexture, overgroundTexture, 0, 1, 1000, 1));
        groundList.add(new GroundActor(world, groundTexture, overgroundTexture, 12, 2, 10, 1));

        spikeList.add(new SpikeActor(world, spikeTexture, 6, 1));

        stage.addActor(player);
        for (GroundActor actor: groundList){
            stage.addActor(actor);
        }
        for (SpikeActor actor: spikeList){
            stage.addActor(actor);
        }
    }

    @Override
    public void hide() {
        player.detach();
        player.remove();
        for (GroundActor actor: groundList){
            actor.detach();
            actor.remove();
        }
        for (SpikeActor actor: spikeList){
            actor.detach();
            actor.remove();
        }
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.act();
        world.step(delta, 6, 2);
        stage.draw();
    }

    @Override
    public void dispose() {
        stage.dispose();
        world.dispose();
    }
}
