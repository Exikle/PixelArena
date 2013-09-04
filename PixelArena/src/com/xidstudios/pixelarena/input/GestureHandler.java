package com.xidstudios.pixelarena.input;

import aurelienribon.tweenengine.TweenManager;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.input.GestureDetector.GestureListener;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Vector2;
import com.xidstudios.pixelarena.PArena;
import com.xidstudios.pixelarena.entity.Player;

public class GestureHandler implements GestureListener {

	private OrthographicCamera cam;

	private boolean dragged = false;

	private Player player;

	private TiledMap map;

	private Vector2 oPos;

	private TweenManager manager;

	public GestureHandler(OrthographicCamera camera, Player player,
			TiledMap map, TweenManager manager) {
		this.cam = camera;
		this.player = player;
		this.map = map;
		this.manager = manager;
	}

	@Override
	public boolean touchDown(float x, float y, int pointer, int button) {
		Gdx.app.log(PArena.LOG, "Touch Down");
		oPos = new Vector2(Gdx.input.getX(), Gdx.input.getY());
		oPos.set(x, y);
		return false;
	}

	@Override
	public boolean tap(float x, float y, int count, int button) {
		Gdx.app.log(PArena.LOG, "Tap");
		return false;
	}

	@Override
	public boolean longPress(float x, float y) {
		Gdx.app.log(PArena.LOG, "Long Press");
		return false;
	}

	@Override
	public boolean fling(float velocityX, float velocityY, int button) {
		Gdx.app.log(PArena.LOG, "Fling");
		// moveCamera(velocityX, velocityY);
		cam.translate(-velocityX, velocityY);
		return false;
	}

	@Override
	public boolean pan(float x, float y, float deltaX, float deltaY) {
		Gdx.app.log(PArena.LOG, "Pan");
		return false;
	}

	@Override
	public boolean zoom(float initialDistance, float distance) {
		Gdx.app.log(PArena.LOG, "Zoom");
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
