/**
 * 
 */
package com.xidstudios.pixelarena.input;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.xidstudios.pixelarena.PArena;
import com.xidstudios.pixelarena.entity.Player;

/**
 * @author Dixon D'Cunha
 */
public class InputHandler implements InputProcessor {

	private OrthographicCamera camera;

	private Vector2 oPos;

	private boolean dragged = false;

	private Player player;

	/**
	 * 
	 */
	public InputHandler(OrthographicCamera camera, Player player) {
		this.camera = camera;
		this.player = player;
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

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer,
			int button) {
		Gdx.app.log(PArena.LOG, "Press Down");
		oPos = new Vector2(Gdx.input.getX(), Gdx.input.getY());
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer,
			int button) {
		Gdx.app.log(PArena.LOG, "Let Go");

		if (!dragged) {
			// move player
		} else {
			// move camera
			Vector2 nPos = new Vector2(Gdx.input.getX(),
					Gdx.input.getY());
			if (nPos.x > oPos.x) {
				camera.position.x = (nPos.x);
			} else {
				camera.position.x = (nPos.x);
			}
			Gdx.app.log(PArena.LOG, camera.position.x + "");
			if (camera.position.x < 0) {
				camera.position.x = 0;
			}
			Gdx.app.log(PArena.LOG, camera.position.x + "NEW");
		}
		dragged = false;
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		Gdx.app.log(PArena.LOG, "Dragged");
		dragged = true;
		// Gdx.app.log(PArena.LOG, "" + (camera.position.x + screenX));
		// screenX));
		// Gdx.app.log(PArena.LOG, "" + screenX);

		// Vector2 nPos = new Vector2(Gdx.input.getX(), Gdx.input.getY());

		// if (nPos.y > oPos.y) {
		// camera.position.y -= 10;
		// } else {
		// camera.position.y += 10;
		// }
		// if () {
		// camera.position.x += 10;
		// } else {
		// camera.position.x -= 10;
		// }

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

}
