package com.xidstudios.pixelarena.arena;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
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
		camera.position.y = Gdx.graphics.getHeight() / 2;
		Gdx.input.setInputProcessor(new InputHandler(camera, player, map));
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
