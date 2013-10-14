package com.xidstudios.pixelarena.pathfinding.testing;

import java.util.ArrayList;
import java.util.PriorityQueue;

import com.xidstudios.pixelarena.pathfinding.ComparatorByScore;
import com.xidstudios.pixelarena.pathfinding.TileInfo;

public class TwoDPathTest {

	String[][] twoDMap = {
			{ ".", ".", ".", ".", ".", ".", ".", ".", ".", "." },// ROW0
			{ ".", "X", "X", "X", "X", "X", ".", ".", ".", "." },// ROW1
			{ ".", "X", "X", "X", ".", ".", ".", ".", ".", "." },// ROW2
			{ ".", "X", "X", ".", ".", ".", ".", ".", ".", "." },// ROW3
			{ ".", "X", ".", ".", ".", ".", ".", ".", ".", "." },// ROW4
			{ ".", "X", ".", ".", ".", ".", ".", ".", ".", "." },// ROW5
			{ ".", "X", ".", ".", ".", ".", ".", ".", ".", "." },// RO6
			{ ".", "x", ".", ".", ".", ".", ".", ".", ".", "." },// RO7
			{ ".", "X", ".", ".", ".", ".", ".", ".", ".", "." },// ROW8
			{ ".", ".", ".", ".", ".", ".", ".", ".", ".", "." },// ROW9
			{ ".", ".", ".", "X", ".", ".", ".", ".", "X", "." },// ROW10
			{ ".", ".", ".", ".", ".", ".", ".", ".", ".", "." } };// ROW11

	TileInfo[][] superList;

	PriorityQueue<TileInfo> openList;

	ArrayList<TileInfo> closedList;

	int destX;

	int destY;

	public boolean[][] passable;

	public int[][] passCost;

	public TwoDPathTest() {
		createMap();
		findPath(0, 0, 11, 7);
	}

	private void createMap() {
		passable = new boolean[twoDMap.length][twoDMap[0].length];
		passCost = new int[passable.length][passable[0].length];
		System.out.println("X" + twoDMap.length);
		System.out.println("Y" + twoDMap[0].length);
		System.out.println("" + twoDMap[10][8].charAt(0));
		for (int x = 0; x < passable.length; x++) {
			for (int y = 0; y < passable[x].length; y++) {
				passCost[x][y] = 1;
				// System.out.print(twoDMap[x][y]);
				passable[x][y] = twoDMap[x][y] == "X" ? false : true;
			}
			// System.out.println();
		} 

	}

	public void findPath(int x1, int y1, int x2, int y2) {

		boolean pathFound = false;

		destX = x2;
		destY = y2;
		
		superList = new TileInfo[passable.length][passable[0].length];
		
		openList = new PriorityQueue<TileInfo>(superList.length
				* superList[0].length, new ComparatorByScore());

		closedList = new ArrayList<TileInfo>();

		superList[x1][y1] = new TileInfo(x1, y1, null, 0, getHeuristicScore(x1,
				y1, x2, y2));

		openList.add(superList[x1][y1]);

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

			if (currentTile.tileX == x2 && currentTile.tileY == y2) {
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
		
		if (!pathFound) {
			System.out.println("The end");
		}

	}

	public char[][] getMap() {
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
	
	public void calcSurroundingTile(TileInfo tile, int cX, int cY) {

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
			// If it so happens that the cost we calculated to this tile was
			// cheaper than the cost calculated to that tile from before,
			// then by all means, the quickest way to this tile is by the tile
			// we just came from.
			surroundingTile.parentTile = tile;
			// Resort here?
			// Nah.
		}

	}

	public static int getHeuristicScore(int x1, int y1, int x2, int y2) {
		int xDif = x2 > x1 ? x2 - x1 : x1 - x2; // because if-statements are
												// faster than calling Math.abs
												// (probably)// yea probably
		int yDif = y2 > y1 ? y2 - y1 : y1 - y2;

		return (xDif + yDif) * 1; // Messing around with the heuristic is one of
									// the key differences between A* and
									// Djikstras. (Djistra? Djksrtsata?
									// DK-DJ-Istria?)
		// Because Djkistra's is more like a flood fill, and A* is more like a
		// smart flood fill.
		// When A* is "flood filling", it fills the flood toward where the
		// heuristic tells it to go.
		// And here, the heuristic is manhattan distance. The pathfinder (if
		// written correctly), goes to tiles that are closer to the exit (which
		// is a guesstimation,
		// you don't know the true distance to the exit, you can only
		// hypothesize)

	}

	// Start here.
	public static void main(String[] args) {
		new TwoDPathTest();
	}

}
