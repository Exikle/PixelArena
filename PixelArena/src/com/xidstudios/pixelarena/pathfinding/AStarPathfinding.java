package com.xidstudios.pixelarena.pathfinding;

import java.util.ArrayList;
import java.util.PriorityQueue;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;
import com.badlogic.gdx.math.Vector2;

public class AStarPathfinding {
	private static Cell[][] cells;

	private static boolean[][] passable;

	public static int[][] passCost;
	static TileInfo[][] superList;

	static PriorityQueue<TileInfo> openList;

	static ArrayList<TileInfo> closedList;

	static int destX;

	static int destY;

	static int col;
	static int row;

	public static void findPath(TiledMap m, Vector2 origin, Vector2 destination) {
		System.out.println((int) origin.y / 32 + "," + (int) origin.y / 32);

		TiledMap tileMap = m;
		TiledMapTileLayer layer = (TiledMapTileLayer) tileMap.getLayers()
				.get(0);
		int col = layer.getWidth();
		int row = layer.getHeight();

		cells = new Cell[col][row];
		passable = new boolean[col][row];
		passCost = new int[col][row];
		superList = new TileInfo[col][row];
		for (int y = 0; y < row; y++) {
			for (int x = 0; x < col; x++) {
				cells[x][y] = new Cell();
				cells[x][y] = layer.getCell(x, y);
				passable[x][y] = cells[x][y].getTile().getProperties()
						.containsKey("blocked") ? false : true;
				passCost[x][y] = 1;
			}
		}
		findPath(origin.x, origin.y, destination.x, destination.y);
	}

	private static void findPath(float x1, float y1, float x2, float y2) {

		boolean pathFound = false;

		int originX = (int) x1 / 32;
		int originY = (int) y1 / 32;

		destX = (int) x2 / 32;
		destY = (int) y2 / 32;

		openList = new PriorityQueue<TileInfo>(superList.length
				* superList[0].length, new ComparatorByScore());

		closedList = new ArrayList<TileInfo>();
		
		superList[originX][originY] = new TileInfo(originX, originY, null, 0,
				getHeuristicScore(originX, originY, destX, destY));
		
		openList.add(superList[originX][originY]);
		
		while (openList.size() > 0) {
			
			TileInfo currentTile = openList.poll();

			calcSurroundingTile(currentTile, -1, -1);
			calcSurroundingTile(currentTile, 0, -1);
			calcSurroundingTile(currentTile, +1, -1);
			calcSurroundingTile(currentTile, +1, 0);
			calcSurroundingTile(currentTile, +1, +1);
			calcSurroundingTile(currentTile, 0, +1);
			calcSurroundingTile(currentTile, -1, +1);
			calcSurroundingTile(currentTile, -1, 0);
			
			if (currentTile.tileX == destX && currentTile.tileY == destY) {
				System.out.println("The Path has been Found");
				char[][] thePath = getMap();
				while (currentTile != null) {
					thePath[currentTile.tileX][currentTile.tileY] = '+';
					currentTile = currentTile.parentTile;
				}
				printMap(thePath);
				pathFound = true;
				break;
			}

		}

		// Sadtime message
		if (!pathFound) {
			System.out.println("The end");
		}

	}

	// Calculates the tile that is cX, cY away from the TileInfo tile.
	// Handles it, essentially
	public static void calcSurroundingTile(TileInfo tile, int cX, int cY) {

		// Whee integers
		int newX = tile.tileX + cX;
		int newY = tile.tileY + cY;

		// Whee out of bounds checking
		if (newX < 0 || newY < 0 || newX >= passable.length
				|| newY >= passable[0].length) {
			return;
		}

		// Now we take that surrounding tile from the SUPER LIST.
		TileInfo surroundingTile = superList[newX][newY];
		// And calculate how much it costs to move to the surrounding tile from
		// the original tile (assuming we are taking the path that goes to the
		// TileInfo tile)
		int toTileCost = (cX != 0 && cY != 0 ? 14 : 10) * passCost[newX][newY];

		// Now if we pulled a null from the superlist, we MAKE IT NOT NULL
		// ANYMORE.
		// How? BY MAKING A NEW TILEINFO THING. YEAH. TOTALLY.
		if (surroundingTile == null) {
			// We are happy to include all tiles of all types in the superlist.
			surroundingTile = new TileInfo(newX, newY, tile, tile.cost
					+ toTileCost, getHeuristicScore(newX, newY, destX, destY));
			superList[newX][newY] = surroundingTile;
			// But if its one of those dirty, unpassable tiles... then we don't
			// want it in the open list.
			if (passable[newX][newY]) {
				// PURE AND PASSABLE TILES ONLY.
				openList.add(surroundingTile);
			}
		} else if (surroundingTile.cost > tile.cost + toTileCost) {
			surroundingTile.parentTile = tile;
		}

	}

	/*
	 * A method for finding the heuristic score of a tile where x/y1 defines the
	 * tile and x/y2 defines the destination (what you're trying to get the
	 * heuristic for).
	 */
	public static int getHeuristicScore(int x1, int y1, int x2, int y2) {
		int xDif = x2 > x1 ? x2 - x1 : x1 - x2;
		int yDif = y2 > y1 ? y2 - y1 : y1 - y2;

		return (xDif + yDif) * 1;
	}

	public static char[][] getMap() {
		char[][] mapArray = new char[passable.length][passable[0].length];
		for (int y = 0; y < mapArray.length; y++) {
			for (int x = 0; x < mapArray[0].length; x++) {
				mapArray[y][x] = passable[y][x] ? ' ' : 'X';
			}
		}

		return mapArray;
	}

	public static void printMap(char[][] mapArray) {
		System.out.print("|");
		for (int i = 0; i < mapArray[0].length; i++) {
			System.out.print("-");
		}

		System.out.println("|");
		for (char[] line : mapArray) {
			System.out.print("|");
			System.out.print(new String(line));
			System.out.print("|");
			System.out.println();
		}

		System.out.print("|");
		for (int i = 0; i < mapArray[0].length; i++) {
			System.out.print("-");
		}
		System.out.println("|");
	}
}
