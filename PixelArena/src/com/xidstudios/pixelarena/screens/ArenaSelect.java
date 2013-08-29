/**
 * 
 */
package com.xidstudios.pixelarena.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.List;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

/**
 * @author Dixon D'Cunha
 */
public class ArenaSelect implements Screen {

	private SpriteBatch batch;

	private Stage stage;

	private Table table;

	private TextureAtlas atlas;

	private Skin skin;

	private List list;

	private ScrollPane scrlPane;

	private TextButton playBtn, backBtn;

	private final boolean DEBUG = true;

	/**
	 * 
	 */
	public ArenaSelect() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);

		batch.begin();

		batch.end();

		if (DEBUG) {
			Table.drawDebug(stage);
		}
	}

	@Override
	public void resize(int width, int height) {}

	@Override
	public void show() {
		batch = new SpriteBatch();
		stage = new Stage();
		Gdx.input.setInputProcessor(stage);

		atlas = new TextureAtlas("ui/atlas.pack");
		skin = new Skin(Gdx.files.internal("ui.menuSkin.json"), atlas);

		table = new Table(skin);
		table.debug();

		list = new List(new String[] { "Arena One", "Arena Two",
				"Arena Three", "Arena Four" }, skin);

		scrlPane = new ScrollPane(list, skin);

		playBtn = new TextButton("PLAY", skin);
		playBtn.pad(15f);
		backBtn = new TextButton("BACK", skin);
		backBtn.pad(7f);

		table.add("SELECT LEVEL");

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
