package com.xidstudios.pixelarena.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.xidstudios.pixelarena.GameFont;
import com.xidstudios.pixelarena.Graphic;
import com.xidstudios.pixelarena.PArena;

public class StartScreen implements Screen, InputProcessor {

	private Sprite splashSprite;

	private SpriteBatch batch;

	private BitmapFont font;

	private final float mid = Gdx.graphics.getWidth() / 2;

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		// Gdx.app.log(PixelArenaMain.LOG, "Rendering SplashScreen");
		batch.begin();

		splashSprite.draw(batch);
		String string = "";
		switch (Gdx.app.getType()) {
			case Android:
				string = "Tap to Start";
				break;
			case Desktop:
				string = "Press to Start";
				break;
			default:
				break;
		}
		font.setScale(2.3f);
		font.draw(batch, string, mid - font.getBounds(string).width
				/ 2, 150);
		batch.end();
	}

	@Override
	public void resize(int width, int height) {}

	@Override
	public void show() {
		Gdx.app.log(PArena.LOG, "StartScreen Rendered");
		batch = new SpriteBatch();
		font = GameFont.fWhite;

		Gdx.input.setInputProcessor(this);
		splashSprite = new Sprite(Graphic.STARTBG.getTexture());
		splashSprite.setSize(Gdx.graphics.getWidth(),
				Gdx.graphics.getHeight());
	}

	@Override
	public void hide() {}

	@Override
	public void pause() {}

	@Override
	public void resume() {}

	@Override
	public void dispose() {}

	@Override
	public boolean keyDown(int keycode) {
		((Game) Gdx.app.getApplicationListener())
				.setScreen(new MainMenu());
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
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer,
			int button) {
		((Game) Gdx.app.getApplicationListener())
				.setScreen(new MainMenu());
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
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
