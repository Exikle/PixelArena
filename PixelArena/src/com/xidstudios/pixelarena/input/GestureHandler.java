package com.xidstudios.pixelarena.input;

import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenEquation;
import aurelienribon.tweenengine.TweenEquations;
import aurelienribon.tweenengine.TweenManager;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.input.GestureDetector.GestureListener;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.xidstudios.pixelarena.PArena;
import com.xidstudios.pixelarena.entity.Player;
import com.xidstudios.pixelarena.tweenaccessors.SpriteTween;

public class GestureHandler implements GestureListener {

	private OrthographicCamera camera;

	private Player player;

	private Vector2 oPos;

	private TweenManager manager;

	public GestureHandler(OrthographicCamera camera, Player player,
			TweenManager manager) {
		this.camera = camera;
		this.player = player;
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
		checkWhatClicked(x, y);
		return false;
	}

	private void checkWhatClicked(float x, float y) {
		// if(entity){
		// show stats
		// }else
		movePlayer(x, y);
	}

	private void movePlayer(float x, float y) {
		Vector3 newPos = new Vector3();
		camera.unproject(newPos.set(x, y, 0));
		// player.setPosition(newPos.x, newPos.y);

		Tween.to(player, SpriteTween.POS_XY, 1.6f)
				.ease(TweenEquations.easeNone)
				.target(newPos.x, newPos.y).start(manager);
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
		// camera.translate(-velocityX, velocityY);
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
