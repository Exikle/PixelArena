package com.xidstudios.pixelarena.screens;

import aurelienribon.tweenengine.TweenManager;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.xidstudios.pixelarena.PixelArenaMain;

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
//		Gdx.app.log(PixelArenaMain.LOG, "Rendering SplashScreen");

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
		splashSprite = new Sprite(new Texture("imgs/xidstudios_splash.png"));

		splashSprite.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
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
