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

		cells = new Cell[col][row];
		String[][] map = new String[col][row];
		for (int y = 0; y < row; y++) {
			for (int x = 0; x < col; x++) {
				cells[x][y] = new Cell();
				cells[x][y] = layer.getCell(x, y);
				map[x][y] = cells[x][y].getTile().getProperties()
						.containsKey("blocked") ? "X" : "-";
				System.out.print(map[x][y]);
			}
			System.out.println("");
		}
	}
}
