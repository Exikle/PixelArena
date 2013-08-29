/**
 * 
 */
package com.xidstudios.pixelarena.screens;

import aurelienribon.tweenengine.TweenManager;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.xidstudios.pixelarena.GFile;
import com.xidstudios.pixelarena.GameFont;
import com.xidstudios.pixelarena.Graphic;
import com.xidstudios.pixelarena.PArena;

/**
 * @author Dixon D'Cunha
 */
public class MainMenu implements Screen, InputProcessor {

	private Sprite splashSprite;

	private SpriteBatch batch;

	private BitmapFont fWhite, fBlack;

	private Stage stage;

	private Skin skin;

	private Table table;

	private TextButton btnPlay, btnExit, btnStore;

	private Label heading;

	private int width = Gdx.graphics.getWidth();

	private int height = Gdx.graphics.getHeight();

	private final boolean DEBUG = true;

	private TweenManager manager;

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);

		manager.update(delta);

		stage.act(delta);

		batch.begin();

		splashSprite.draw(batch);

		batch.end();

		stage.draw();

		if (DEBUG) {
			Table.drawDebug(stage);
		}
	}

	@Override
	public void resize(int width, int height) {}

	@Override
	public void show() {
		Gdx.app.log(PArena.LOG, "Main Menu Rendered");

		batch = new SpriteBatch();

		initializeFont();
		initializeBG();

		skin = new Skin(new TextureAtlas("ui/button.pack"));
		stage = new Stage();
		Gdx.input.setInputProcessor(stage);
		table = new Table(skin);
		table.setBounds(0, 0, width, height);

		heading = new Label(PArena.TITLE, new LabelStyle(fWhite,
				Color.WHITE));
		table.add(heading);
		table.getCell(heading).spaceBottom(100);
		table.row();

		createTextButton();

		// anim

	}

	private void createTextButton() {
		TextButtonStyle txtBtnStyle = new TextButtonStyle();

		txtBtnStyle.up = skin.getDrawable("button.up");

		txtBtnStyle.down = skin.getDrawable("button.down");

		txtBtnStyle.disabled = skin.getDrawable("button.disabled");

		txtBtnStyle.pressedOffsetX = 1;
		txtBtnStyle.pressedOffsetY = -1;

		txtBtnStyle.font = fBlack;

		btnPlay = new TextButton("PLAY", txtBtnStyle);
		btnPlay.pad(15);

		btnStore = new TextButton("STORE", txtBtnStyle);
		btnStore.pad(15);
		btnStore.setDisabled(true);

		btnExit = new TextButton("EXIT", txtBtnStyle);
		btnExit.pad(15);

		addClickListeners();

		addToTable();

		table.debug();

		stage.addActor(table);
	}

	private void addToTable() {
		table.add(btnPlay);
		table.getCell(btnPlay).spaceBottom(20);
		table.row();
		table.add(btnStore);
		table.getCell(btnStore).spaceBottom(20);
		table.row();
		table.add(btnExit);
		table.getCell(btnExit).spaceBottom(20);
		table.row();
	}

	private void addClickListeners() {
		btnExit.addListener(new ClickListener() {

			@Override
			public void clicked(InputEvent event, float x, float y) {
				dispose();
				Gdx.app.log(PArena.LOG, "Exit Clicked");
				Gdx.app.exit();
			}

		});
		btnPlay.addListener(new ClickListener() {

			@Override
			public void clicked(InputEvent event, float x, float y) {
				Gdx.app.log(PArena.LOG, "Play Clicked");
				GFile.game.setScreen(new ArenaScreen());
			}

		});
	}

	private void initializeFont() {
		fWhite = GameFont.fWhite;
		fBlack = GameFont.fBlack;
	}

	private void initializeBG() {
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
	public void dispose() {
		stage.dispose();
		fBlack.dispose();
		fWhite.dispose();
		skin.dispose();
		batch.dispose();
	}

	@Override
	public boolean keyDown(int keycode) {
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
