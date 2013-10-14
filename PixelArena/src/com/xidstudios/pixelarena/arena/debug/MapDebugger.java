package com.xidstudios.pixelarena.arena.debug;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;
import com.xidstudios.pixelarena.pathfinding.testing.Map;

public class MapDebugger {
	private TiledMap tileMap;
	private Cell[][] cells;

	public MapDebugger(TiledMap map) {
		this.tileMap = map;
		print2DMap();
	}

	private void print2DMap() {
		TiledMapTileLayer layer = (TiledMapTileLayer) tileMap.getLayers()
				.get(0);

		float tileHeight = layer.getTileHeight();
		// Gdx.app.log(PArena.LOG, "" + tileHeight);
		float tileWidth = layer.getTileWidth();
		// Gdx.app.log(PArena.LOG, "" + tileWidth);

		int col = layer.getWidth();
		int row = layer.getHeight();

		float mapWidth = col * tileWidth;
		float mapHeight = row * tileHeight;

		// Gdx.app.log(PArena.LOG, "THeight - " + tileHeight
		// + ",TWidth - " + tileWidth);
		// Gdx.app.log(PArena.LOG, "Col - " + col + ",Row - " + row);

		cells = new Cell[(int) col][row];
		for (int y = 0; y < row; y++) {
			for (int x = 0; x < col; x++) {
				cell[x][y] = new Cell();
				cell[x][y] = layer.getCell(x, y);
			}
		}
	}

}
