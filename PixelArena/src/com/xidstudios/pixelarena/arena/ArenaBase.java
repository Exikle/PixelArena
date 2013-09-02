package com.xidstudios.pixelarena.arena;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;

public abstract class ArenaBase implements Screen {

	protected String arenaName;

	protected TiledMap map;

	protected int[] bgLayers;

	protected int[] fgLayers;

	protected OrthogonalTiledMapRenderer renderer;

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
	}

	protected void importMap(String m, Vector2 pos) {
		map = new TmxMapLoader().load("maps/" + m + ".tmx");
		arenaName = m;
		int index = 0;
		int layerNum = map.getLayers().getCount() - 2;

		for (int x = 0; x < layerNum; x++) {
			if (map.getLayers().get(x).getName()
					.equalsIgnoreCase("playerLayer")) {
				index = x;
			}
		}
		bgLayers = new int[index];
		for (int x = 0; x < index; x++) {
			bgLayers[x] = x;
		}

		fgLayers = new int[layerNum - (index + 1)];
		int topLayerStart = index + 1;

		for (int x = 0; x < fgLayers.length; x++) {
			fgLayers[x] = x + topLayerStart;
		}
		renderer = new OrthogonalTiledMapRenderer(map);

		// createCollisions();
		// createInteractions();
		// player.setPosition(pos.x, pos.y);
		// camera.position.set(player.getX(), player.getY(), 0);
	}

	protected void drawArena(SpriteBatch batch) {
		renderer.render(bgLayers);

		batch.begin();

		// batch.draw(player, player.getX(), player.getY());

		batch.end();

		renderer.render(fgLayers);
	}

}
