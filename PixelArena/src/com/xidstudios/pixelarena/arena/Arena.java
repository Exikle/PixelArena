package com.xidstudios.pixelarena.arena;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.xidstudios.pixelarena.entity.Player;
import com.xidstudios.pixelarena.input.InputHandler;

public class Arena extends ArenaBase {

	private OrthographicCamera camera = new OrthographicCamera();

	private SpriteBatch batch;

	private OrthogonalTiledMapRenderer tileMapRenderer;

	private TiledMap map;

	private Player player;

	private final boolean DEBUG = false;

	public void render(float delta) {
		super.render(delta);
		cameraUpdater();
		// drawArena(batch);

		tileMapRenderer.render();// need to render layers

		batch.begin();
		// layes under player
		batch.draw(player, player.getX(), player.getY());
		// layers over player
		batch.end();

		if (DEBUG) {
			// the point i clicked, rectangles, etc
		}

	}

	@Override
	public void show() {
		// importMap(START_MAP_NAME, STARTCOORD);

		// Load the tmx file into map
		map = new TmxMapLoader().load("maps/AreaOne.tmx");

		// Create the renderer
		tileMapRenderer = new OrthogonalTiledMapRenderer(map);

		batch = new SpriteBatch();
		camera = new OrthographicCamera();
		camera.viewportHeight = Gdx.graphics.getHeight();
		camera.viewportWidth = Gdx.graphics.getWidth();
		camera.position.set(900, 50, 0);
		camera.position.y = Gdx.graphics.getHeight() / 2;
		Gdx.input.setInputProcessor(new InputHandler(camera, player,
				map));

		player = new Player(camera, new TextureRegion(new Texture(
				"imgs/MalePlayer.png")));
	}

	private void cameraUpdater() {
		// camera.zoom = 1.1f;
		camera.update();

		batch.setProjectionMatrix(camera.combined);
		tileMapRenderer.setView(camera);
		// sRen.setProjectionMatrix(camera.combined);
	}

	@Override
	public void resize(int width, int height) {}

	@Override
	public void hide() {}

	@Override
	public void pause() {}

	@Override
	public void resume() {}

	@Override
	public void dispose() {}

}
