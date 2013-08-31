/**
 * 
 */
package com.xidstudios.pixelarena.screens;

import aurelienribon.tweenengine.Timeline;
import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenManager;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.xidstudios.pixelarena.GameFont;
import com.xidstudios.pixelarena.Graphic;
import com.xidstudios.pixelarena.PArena;
import com.xidstudios.pixelarena.tweenaccesors.ActorAccessor;

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

	private final boolean DEBUG = false;

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
		stage = new Stage();
		Gdx.input.setInputProcessor(stage);

		initializeFont();
		initializeBG();
		loadSkin();
		createButtons();
		createTable();
		createTweens();
	}

	private void loadSkin() {
		skin = new Skin(Gdx.files.internal("ui/menuSkin.json"),
				new TextureAtlas("ui/button.pack"));
	}

	private void createTweens() {
		manager = new TweenManager();
		Tween.registerAccessor(Actor.class, new ActorAccessor());

		Timeline.createParallel()
				.beginParallel()
				.push(Tween.from(table, ActorAccessor.ALPHA, 0.5f)
						.target(0)).end().start(manager);
	}

	private void createButtons() {
		btnStore = new TextButton("STORE", skin);
		btnStore.pad(15);
		btnStore.setDisabled(true);

		btnExit = new TextButton("EXIT", skin);
		btnExit.pad(15);
		btnExit.addListener(new ClickListener() {

			@Override
			public void clicked(InputEvent event, float x, float y) {
				dispose();
				Gdx.app.log(PArena.LOG, "Exit Clicked");
				Gdx.app.exit();
			}

		});

		btnPlay = new TextButton("PLAY", skin);
		btnPlay.pad(15);
		btnPlay.addListener(new ClickListener() {

			@Override
			public void clicked(InputEvent event, float x, float y) {
				Gdx.app.log(PArena.LOG, "Play Clicked");
//				GFile.game.setScreen(new ArenaSelect());
			}

		});
	}

	private void createTable() {
		table = new Table(skin);
		table.setBounds(0, 0, width, height);

		heading = new Label(PArena.TITLE, skin);
		heading.scale(2f);

		table.add(heading);
		table.getCell(heading).spaceBottom(100);
		table.row();

		table.add(btnPlay);
		table.getCell(btnPlay).spaceBottom(20);
		table.row();
		table.add(btnStore);
		table.getCell(btnStore).spaceBottom(20);
		table.row();
		table.add(btnExit);
		table.getCell(btnExit).spaceBottom(20);
		table.row();

		table.debug();

		stage.addActor(table);
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
