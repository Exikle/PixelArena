package com.xidstudios.pixelarena.arena;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;

public class Arena extends ArenaBase {

	/**
	 * The map the player starts in
	 */
	private String START_MAP_NAME = "AreaOne";

	private final Vector2 STARTCOORD = new Vector2(50, 50);

	private OrthographicCamera camera = new OrthographicCamera();

	private SpriteBatch batch;

	private OrthogonalTiledMapRenderer tileMapRenderer;

	TiledMap map;

	public void render(float delta) {
		super.render(delta);
		cameraUpdater();
		// drawArena(batch);
		tileMapRenderer.render();

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
		camera.position.set(100, 50, 0);
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
