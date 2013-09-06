/**
 * 
 */
package com.xidstudios.pixelarena.input;

import aurelienribon.tweenengine.TweenManager;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Vector2;
import com.xidstudios.pixelarena.PArena;
import com.xidstudios.pixelarena.entity.Player;

/**
 * @author Dixon D'Cunha
 */
public class InputHandler implements InputProcessor {

	private OrthographicCamera cam;

	private TiledMap map;

	private Vector2 oPos;

	private Player player;

	public InputHandler(OrthographicCamera camera, TiledMap map,
			TweenManager manager, Player player) {
		this.cam = camera;
		this.map = map;
		this.player = player;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer,
			int button) {
		oPos = new Vector2(Gdx.input.getX(), Gdx.input.getY());
		oPos.set(screenX, screenY);
		return false;
	}

	public boolean touchDragged(int x, int y, int pointer) {
		// if (!player.cameraLocked)
		moveCamera(x, y);
		return false;
	}

	private void moveCamera(int touchX, int touchY) {
		Vector2 nPos = getNewCameraPosition(touchX, touchY);

		// if (!cameraOutOfLimit(nPos))
		{
			cam.translate(nPos.sub(cam.position.x, cam.position.y));
		}
		oPos.set(touchX, touchY);
		// Gdx.app.log(PArena.LOG, oPos.x + "," + oPos.y);
	}

	private Vector2 getNewCameraPosition(int x, int y) {
		Vector2 nPos = oPos;
		nPos.sub(x, y);
		nPos.y = -nPos.y;
		nPos.add(cam.position.x, cam.position.y);
		return nPos;
	}

	@SuppressWarnings("unused")
	private boolean cameraOutOfLimit(Vector2 position) {
		final int WINDOW_WIDTH = Gdx.graphics.getWidth();
		final int WINDOW_HEIGHT = Gdx.graphics.getHeight();
		final TiledMapTileLayer l = (TiledMapTileLayer) this.map
				.getLayers().get(0);
		int x_left_limit = WINDOW_WIDTH / 2;
		int x_right_limit = l.getWidth() - WINDOW_WIDTH / 2;
		int y_bottom_limit = WINDOW_HEIGHT / 2;
		int y_top_limit = l.getHeight() - WINDOW_HEIGHT / 2;

		if (position.x < x_left_limit || position.x > x_right_limit) {
			Gdx.app.log(PArena.LOG, "Out of Limit");
			return true;
		} else if (position.y < y_bottom_limit
				|| position.y > y_top_limit) {
			Gdx.app.log(PArena.LOG, "Out of Limit");
			return true;
		} else
			return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer,
			int button) {
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		return false;
	}

	@Override
	public boolean keyDown(int keycode) {
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		return false;
	}

}
