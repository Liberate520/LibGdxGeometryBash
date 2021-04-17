package ru.samsung.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

import ru.samsung.game.Main;

public class Box2DScreen extends BaseScreen{

    public Box2DScreen(Main game){
        super(game);
    }

    private World world;
    private Box2DDebugRenderer renderer;
    private OrthographicCamera camera;

    private Body playerBody, downBody;
    private Fixture playerFixture, downFixture;

    @Override
    public void show() {
        world = new World(new Vector2(0, -10), true);
        renderer = new Box2DDebugRenderer();
        camera = new OrthographicCamera(32, 18);

        playerBody = world.createBody(createPlayerBodyDef());
        downBody = world.createBody(createDownBodyDef());

        PolygonShape playerShape = new PolygonShape();
        playerShape.setAsBox(0.5f,0.5f);
        playerFixture = playerBody.createFixture(playerShape, 1);
        playerShape.dispose();

        PolygonShape downShape = new PolygonShape();
        downShape.setAsBox(500,1);
        downFixture = downBody.createFixture(downShape, 1);
        downShape.dispose();
    }

    private BodyDef createPlayerBodyDef() {
        BodyDef def = new BodyDef();
        def.position.set(0, 10);
        def.type = BodyDef.BodyType.DynamicBody;
        return def;
    }

    private BodyDef createDownBodyDef() {
        BodyDef def = new BodyDef();
        def.position.set(0, 0);
        return def;
    }

    @Override
    public void dispose() {
        world.destroyBody(playerBody);
        world.dispose();
        renderer.dispose();
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        world.step(delta, 6, 2);

        camera.update();
        renderer.render(world, camera.combined);
    }
}
