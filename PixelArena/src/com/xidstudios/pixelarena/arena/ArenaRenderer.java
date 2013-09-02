package com.xidstudios.pixelarena.arena;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class ArenaRenderer extends ArenaBase {

	/**
	 * The map the player starts in
	 */
	private String START_MAP_NAME = "AreaOne";

	private final Vector2 STARTCOORD = new Vector2(50, 50);

	private OrthographicCamera camera = new OrthographicCamera();

	private SpriteBatch batch;

	public void render(float delta) {
		super.render(delta);
		cameraUpdater();
		drawArena(batch);

	}

	@Override
	public void show() {
		importMap(START_MAP_NAME, STARTCOORD);

		batch = new SpriteBatch();
		camera = new OrthographicCamera();
	}

	private void cameraUpdater() {
		// camera.zoom = 1.1f;
		camera.update();

		batch.setProjectionMatrix(camera.combined);
		renderer.setView(camera);
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
