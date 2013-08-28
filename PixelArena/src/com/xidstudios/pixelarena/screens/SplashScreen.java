package com.xidstudios.pixelarena.screens;

import aurelienribon.tweenengine.BaseTween;
import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenCallback;
import aurelienribon.tweenengine.TweenEquations;
import aurelienribon.tweenengine.TweenManager;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.xidstudios.pixelarena.GFile;
import com.xidstudios.pixelarena.Graphic;
import com.xidstudios.pixelarena.PArena;
import com.xidstudios.pixelarena.tweenaccesors.SpriteTween;

public class SplashScreen implements Screen {

	private SpriteBatch batch;

	private TweenManager manager;

	private int splashCount = 0;

	private Sprite[] splash;

	private final float SPLASH_SPEED = 1.5f;

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		// Gdx.app.log(PixelArenaMain.LOG, "Rendering SplashScreen");

		manager.update(delta);
		batch.begin();
		try {
			splash[splashCount].draw(batch);
		} catch (Exception Exception) {
			tweenCompleted();
		}

		batch.end();
	}

	@Override
	public void show() {
		batch = new SpriteBatch();

		Tween.registerAccessor(
				Sprite.class,
				new com.xidstudios.pixelarena.tweenaccesors.SpriteTween());

		manager = new TweenManager();

		splash = new Sprite[3];

		splash[0] = new Sprite(Graphic.XID_LOGO.getTexture());
		splash[1] = new Sprite(Graphic.EXIKLE_LOGO.getTexture());
		splash[2] = new Sprite(Graphic.STARTBG.getTexture());

		setSpriteDefaults();
		tweenSprite();

	}

	private void tweenSprite() {
		Gdx.app.log(PArena.LOG, "SplashScreen " + splashCount
				+ " Rendered");
		Tween.to(splash[splashCount], SpriteTween.ALPHA, SPLASH_SPEED)
				.target(1).ease(TweenEquations.easeInQuad)
				.repeatYoyo(1, 1.5f).setCallback(cb).start(manager);

	}

	private void stopTween() {
		Gdx.app.log(PArena.LOG, "SplashScreen " + splashCount
				+ " Rendered");
		Tween.to(splash[splashCount], SpriteTween.ALPHA, SPLASH_SPEED)
				.target(1).ease(TweenEquations.easeInQuad)
				.setCallback(cb).start(manager);
	}

	TweenCallback cb = new TweenCallback() {

		@Override
		public void onEvent(int type, BaseTween<?> source) {
			splashCount++;
			try {
				if (splashCount < splash.length - 1) {
					tweenSprite();
				} else {
					stopTween();
				}
			} catch (Exception Exception) {
				tweenCompleted();
			}
		}
	};

	private void tweenCompleted() {
		GFile.game.setScreen(new StartScreen());
	}

	private void setSpriteDefaults() {
		for (int x = 0; x < splash.length; x++) {
			splash[x].setSize(Gdx.graphics.getWidth(),
					Gdx.graphics.getHeight());
			splash[x].setColor(1, 1, 1, 0);
		}
	}

	@Override
	public void resize(int width, int height) {

	}

	@Override
	public void hide() {

	}

	@Override
	public void pause() {
		splashCount = 0;
	}

	@Override
	public void resume() {
		tweenSprite();
	}

	@Override
	public void dispose() {
		batch.dispose();
	}

}
