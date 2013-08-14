package com.xidstudios.pixelarena.screens;

import aurelienribon.tweenengine.BaseTween;
import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenCallback;
import aurelienribon.tweenengine.TweenEquations;
import aurelienribon.tweenengine.TweenManager;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.xidstudios.pixelarena.PixelArenaMain;
import com.xidstudios.pixelarena.tweenaccesors.SpriteTween;

public class SplashScreen implements Screen {

	private Texture splashTexture;
	private Sprite splashSprite;
	private SpriteBatch batch;
	private PixelArenaMain game;
	private TweenManager manager;

	public SplashScreen(PixelArenaMain game) {
		this.game = game;
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		// Gdx.app.log(PixelArenaMain.LOG, "Rendering SplashScreen");

		manager.update(delta);
		batch.begin();

		splashSprite.draw(batch);

		batch.end();
	}

	@Override
	public void resize(int width, int height) {

	}

	@Override
	public void show() {
		batch = new SpriteBatch();
		splashTexture = new Texture("imgs/xidstudios_splash.png");
		splashSprite = new Sprite(splashTexture);

		splashSprite.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		splashSprite.setColor(1, 1, 1, 0);

		Tween.registerAccessor(Sprite.class,
				new com.xidstudios.pixelarena.tweenaccesors.SpriteTween());

		manager = new TweenManager();

		TweenCallback cb = new TweenCallback() {

			@Override
			public void onEvent(int type, BaseTween<?> source) {
				tweenCompleted();
			}
		};

		Tween.to(splashSprite, SpriteTween.ALPHA, 1.5f).target(1)
				.ease(TweenEquations.easeInQuad).repeatYoyo(1, 1.5f)
				.setCallback(cb).setCallbackTriggers(TweenCallback.COMPLETE)
				.start(manager);
	}

	protected void tweenCompleted() {
		game.setScreen(new StartScreen(game));
	}

	@Override
	public void hide() {

	}

	@Override
	public void pause() {

	}

	@Override
	public void resume() {

	}

	@Override
	public void dispose() {

	}

}
