package com.xidstudios.pixelarena.input;

import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenEquations;
import aurelienribon.tweenengine.TweenManager;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.input.GestureDetector.GestureListener;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.xidstudios.pixelarena.PArena;
import com.xidstudios.pixelarena.entity.Player;
import com.xidstudios.pixelarena.tweenaccessors.SpriteTween;

public class GestureHandler implements GestureListener {

	private OrthographicCamera camera;

	private Player player;

	private Vector3 oPos;

	private TweenManager manager;

	private TiledMap map;

	public GestureHandler(OrthographicCamera camera, Player player,
			TweenManager manager, TiledMap map) {
		this.camera = camera;
		this.player = player;
		this.manager = manager;
		this.map = map;
	}

	@Override
	public boolean touchDown(float x, float y, int pointer, int button) {
		oPos = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
		camera.unproject(oPos.set(x, y, 0));
		oPos.set(x, y, 0);
		return false;
	}

	@Override
	public boolean tap(float x, float y, int count, int button) {
		checkWhatTapped(x, y);
		return false;
	}

	private void checkWhatTapped(float x, float y) {
		// if(entity){
		// show stats
		// }else
		movePlayer(x, y);
	}

	private void movePlayer(float x, float y) {
		Vector3 nPos = new Vector3();

		camera.unproject(nPos.set(x, y, 0));

		player.move = true;
		if (player.move) {
			Tween.to(player, SpriteTween.POS_XY, 1.6f)
					.ease(TweenEquations.easeNone)
					.target(nPos.x, nPos.y).start(manager);
			if(player.cameraLocked){
				//move camera with player
			}
		}
	}

	@Override
	public boolean longPress(float x, float y) {
		Gdx.app.log(PArena.LOG, "Long Press");
		player.cameraLocked = !player.cameraLocked;
		return false;
	}

	@Override
	public boolean fling(float velocityX, float velocityY, int button) {
		// moveCamera(velocityX, velocityY);
		// camera.translate(-velocityX, velocityY);
		return false;
	}

	@Override
	public boolean pan(float x, float y, float deltaX, float deltaY) {
		return false;
	}

	@Override
	public boolean zoom(float initialDistance, float distance) {
		if (initialDistance > distance)
			camera.zoom += .01;
		else if (initialDistance < distance)
			camera.zoom -= .01;
		return false;
	}

	@Override
	public boolean pinch(Vector2 initialPointer1,
			Vector2 initialPointer2, Vector2 pointer1,
			Vector2 pointer2) {
		Gdx.app.log(PArena.LOG, "Pinch");
		return false;
	}

}
