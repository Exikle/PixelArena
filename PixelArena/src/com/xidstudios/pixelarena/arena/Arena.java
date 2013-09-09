package com.xidstudios.pixelarena.arena;

import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenManager;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTile;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.utils.Array;
import com.xidstudios.pixelarena.GameFont;
import com.xidstudios.pixelarena.PArena;
import com.xidstudios.pixelarena.entity.Player;
import com.xidstudios.pixelarena.input.GestureHandler;
import com.xidstudios.pixelarena.input.InputHandler;
import com.xidstudios.pixelarena.tweenaccessors.SpriteTween;

public class Arena extends ArenaBase {

	private OrthographicCamera camera = new OrthographicCamera();

	private SpriteBatch batch;

	private OrthogonalTiledMapRenderer tileMapRenderer;

	private TiledMap map;

	private Player player;

	private final boolean DEBUG = true;

	private TweenManager manager;

	ShapeRenderer render = new ShapeRenderer();

	ShapeRenderer clicked = new ShapeRenderer();

	Array<TiledMapTile> tiles = new Array<TiledMapTile>();

	float tileWidth;

	float tileHeight;

	public Cell[][] cell;

	int col;

	private int row;

	float mapWidth;

	public float mapHeight;

	public int touchX = 0;

	public int touchY = 0;

	public int oX = 0;

	public int oY = 0;

	private BitmapFont font = GameFont.fWhite;

	public void render(float delta) {
		super.render(delta);
		manager.update(delta);
		cameraUpdater();
		// drawArena(batch);

		tileMapRenderer.render();// need to render layers

		batch.begin();
		batch.draw(player, player.getX(), player.getY());

		font.setScale(.4f);

		for (int y = 0; y < row; y++) {
			for (int x = 0; x < col; x++) {
				if (!cell[x][y].getTile().getProperties()
						.containsKey("blocked")) {
					font.draw(batch, x + "," + y, x * tileWidth,
							tileWidth + (y * tileWidth));
				}
			}
		}
		batch.end();

		if (DEBUG) {
			// the point i clicked, rectangles, etc

			render.begin(ShapeType.Line);
			for (int y = 0; y < row; y++) {
				for (int x = 0; x < col; x++) {
					if (!cell[x][y].getTile().getProperties()
							.containsKey("blocked")) {
						render.setColor(Color.GRAY);
						render.rect(x * tileWidth, y * tileHeight,
								tileWidth, tileHeight);
					}
				}
			}
			render.setColor(Color.ORANGE);
			render.rect(0, 0, mapWidth, mapHeight);
			render.end();

			render.begin(ShapeType.Filled);

			render.setColor(Color.RED);
			render.rect((touchX / 32) * tileWidth, (touchY / 32)
					* tileHeight, tileWidth, tileHeight);

			render.setColor(Color.YELLOW);
			// find amount of steps needed, xy coord of box
			// for (int x = 0; x < 1; x++) {
			// render.rect((40 / 32) * tileWidth, (50 / 32)
			// * tileHeight, tileWidth, tileHeight);
			// }

			render.setColor(Color.BLUE);
			render.rect((oX / 32) * tileWidth,
					(oY / 32) * tileHeight, tileWidth, tileHeight);
			render.end();
		}

	}

	@Override
	public void show() {
		// importMap(START_MAP_NAME, STARTCOORD);

		Tween.registerAccessor(Sprite.class, new SpriteTween(this));
		manager = new TweenManager();

		// Load the tmx file into map
		map = new TmxMapLoader().load("maps/AreaTest.tmx");

		player = new Player(camera, new TextureRegion(new Texture(
				"imgs/MalePlayer.png")));
		player.setPosition(0, 0);

		// Create the renderer
		tileMapRenderer = new OrthogonalTiledMapRenderer(map);

		batch = new SpriteBatch();
		camera = new OrthographicCamera();
		camera.viewportHeight = Gdx.graphics.getHeight();
		camera.viewportWidth = Gdx.graphics.getWidth();
		camera.position.set(Gdx.graphics.getWidth() / 2,
				Gdx.graphics.getHeight() / 2, 0);

		InputMultiplexer iM = new InputMultiplexer(new InputHandler(
				camera, map, manager, player),
				new GestureDetector(new GestureHandler(this, camera,
						player, manager, map)));

		Gdx.input.setInputProcessor(iM);

		createTiles();
		oX = (int) player.getX();
		oY = (int) player.getY();
		touchX = (int) player.getX();
		touchY = (int) player.getY();
	}

	private void createTiles() {
		TiledMapTileLayer layer = (TiledMapTileLayer) map.getLayers()
				.get(0);

		tileHeight = layer.getTileHeight();
		Gdx.app.log(PArena.LOG, "" + tileHeight);
		tileWidth = layer.getTileWidth();
		Gdx.app.log(PArena.LOG, "" + tileWidth);

		col = layer.getWidth();
		row = layer.getHeight();

		mapWidth = col * tileWidth;
		mapHeight = row * tileHeight;

		Gdx.app.log(PArena.LOG, "THeight - " + tileHeight
				+ ",TWidth - " + tileWidth);
		Gdx.app.log(PArena.LOG, "Col - " + col + ",Row - " + row);

		cell = new Cell[col][row];
		for (int y = 0; y < row; y++) {
			for (int x = 0; x < col; x++) {
				cell[x][y] = new Cell();
				cell[x][y] = layer.getCell(x, y);
			}
		}

	}

	private void cameraUpdater() {
		camera.update();

		batch.setProjectionMatrix(camera.combined);
		render.setProjectionMatrix(camera.combined);
		tileMapRenderer.setView(camera);
	}

	@Override
	public void resize(int width, int height) {}

	@Override
	public void hide() {}

	@Override
	public void pause() {}

	@Override
	public void resume() {}

	@Override
	public void dispose() {}

}
