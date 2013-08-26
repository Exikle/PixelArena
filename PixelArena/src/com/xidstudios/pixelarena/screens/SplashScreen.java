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
import com.xidstudios.pixelarena.MainPArena;
import com.xidstudios.pixelarena.tweenaccesors.SpriteTween;

public class SplashScreen implements Screen {

	private Texture splashTexture;

	private Sprite splashSprite;

	private SpriteBatch batch;

	private MainPArena game;

	private TweenManager manager;

	private int splashCount;

	public SplashScreen(MainPArena game) {
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

		Tween.registerAccessor(
				Sprite.class,
				new com.xidstudios.pixelarena.tweenaccesors.SpriteTween());

		manager = new TweenManager();

		splashSprite = new Sprite(new Texture(
				"imgs/xidstudios_splash.png"));
		setSpriteDefaults();
		tweenSprite();

	}

	private void tweenSprite() {
		Tween.to(splashSprite, SpriteTween.ALPHA, 1.5f).target(1)
				.ease(TweenEquations.easeInQuad).repeatYoyo(1, 1.5f)
				.setCallback(cb)
				.setCallbackTriggers(TweenCallback.COMPLETE)
				.start(manager);

	}

	TweenCallback cb = new TweenCallback() {

		@Override
		public void onEvent(int type, BaseTween<?> source) {
			switch (splashCount) {
				case 0:
					firstCompleted();
					break;
				case 1:
					secondCompleted();
					break;
			}
		}
	};

	private void firstCompleted() {
		// Gdx.app.log(MainPArena.LOG, "Switch to Start Screen");
		// game.setScreen(new StartScreen(game));
		splashCount++;
		startSecondTween();
	}

	private void startSecondTween() {
		splashSprite.setTexture(new Texture("imgs/Exikle.png"));
		tweenSprite();

	}

	private void secondCompleted() {
		// Gdx.app.log(MainPArena.LOG, "Switch to Start Screen");
		game.setScreen(new StartScreen(game));
	}

	private void setSpriteDefaults() {
		splashSprite.setSize(Gdx.graphics.getWidth(),
				Gdx.graphics.getHeight());
		splashSprite.setColor(1, 1, 1, 0);
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
