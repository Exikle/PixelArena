package com.xidstudios.pixelarena.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.xidstudios.pixelarena.PixelArenaMain;

public class StartScreen implements Screen {

	private Texture splashTexture;

	private Sprite splashSprite;

	private SpriteBatch batch;

	private PixelArenaMain game;

	public StartScreen(PixelArenaMain game) {
		this.game = game;
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		// Gdx.app.log(PixelArenaMain.LOG, "Rendering SplashScreen");

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
		splashTexture = new Texture("imgs/Exikle.png");
		splashSprite = new Sprite(splashTexture);

		splashSprite.setSize(Gdx.graphics.getWidth(),
				Gdx.graphics.getHeight());
		splashSprite.setColor(1, 1, 1, 0);
	}

	@Override
	public void hide() {}

	@Override
	public void pause() {}

	@Override
	public void resume() {}

	@Override
	public void dispose() {}

}
