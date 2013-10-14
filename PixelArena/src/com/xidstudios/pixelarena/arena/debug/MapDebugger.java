package com.xidstudios.pixelarena.arena.debug;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;

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
		int col = layer.getWidth();
		int row = layer.getHeight();

		cells = new Cell[col][row];
		String[][] map = new String[col][row];
		for (int y = 0; y < row; y++) {
			System.out.print("|");
			for (int x = 0; x < col; x++) {
				cells[x][y] = new Cell();
				cells[x][y] = layer.getCell(x, y);
				map[x][y] = cells[x][y].getTile().getProperties()
						.containsKey("blocked") ? "X" : "-";
				System.out.print(map[x][y]);
			}
			System.out.println("|");
		}
	}
}
