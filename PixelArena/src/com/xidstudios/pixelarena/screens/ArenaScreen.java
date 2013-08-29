/**
 * 
 */
package com.xidstudios.pixelarena.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * @author Dixon D'Cunha
 */
public class ArenaScreen implements Screen {

	private SpriteBatch batch;

	/**
	 * 
	 */
	public ArenaScreen() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		
		batch.begin();
		
		batch.end();
	}

	@Override
	public void resize(int width, int height) {}

	@Override
	public void show() {
		batch = new SpriteBatch();
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
