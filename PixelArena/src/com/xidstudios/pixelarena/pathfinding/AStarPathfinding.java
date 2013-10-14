package com.xidstudios.pixelarena.pathfinding;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;
import com.badlogic.gdx.math.Vector2;

public class AStarPathfinding {
	private static Cell[][] cells;
	private static String[][] map;

	public static void findPath(TiledMap m, Vector2 origin, Vector2 destination) {
		TiledMap tileMap = m;
		TiledMapTileLayer layer = (TiledMapTileLayer) tileMap.getLayers()
				.get(0);
		int col = layer.getWidth();
		int row = layer.getHeight();

		cells = new Cell[col][row];
		map = new String[col][row];
		for (int y = 0; y < row; y++) {
			// System.out.print("|");
			for (int x = 0; x < col; x++) {
				cells[x][y] = new Cell();
				cells[x][y] = layer.getCell(x, y);
				map[x][y] = cells[x][y].getTile().getProperties()
						.containsKey("blocked") ? "X" : "-";
				// System.out.print(map[x][y]);
			}
			// System.out.println("|");
		}
	}

}
