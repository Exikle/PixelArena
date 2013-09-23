package com.xidstudios.pixelarena.pathfinding;

import java.util.ArrayList;
import java.util.PriorityQueue;

import com.xidstudios.pixelarena.pathfinding.testing.Map;

public class AStarPathfinder {

	static TileInfo[][] superList;

	static PriorityQueue<TileInfo> openList;

	static ArrayList<TileInfo> closedList;

	static int destX;

	static int destY;

	// Yeah! Lets find paths and stuff! Totally!
	// Alright. So this is the true "start" of the pathfinder.
	// This method takes the start and the end points, but it also uses the map defined in the main class.
	// You might want to check that map out, or maybe not.
	public static void findPath(int x1, int y1, int x2, int y2) {

		boolean pathFound = false;

		destX = x2;
		destY = y2;

		// Superlist? What's that? I don't really know why I put this here.
		// But essentially it is a list of many many tiles. All the tiles, in fact.
		superList = new TileInfo[Map.map.passable.length][Map.map.passable[0].length];

		// OPEN LIST. This is the créme de la créme, the finisher to the act, the Shakespeare of poetry.
		// This is the list of tiles that will be calculated, as in, the pathfinder will look into these tiles eventually
		// I don't even know if the Queue should be initiated with such a massive size. Seems... excessive.
		openList = new PriorityQueue<TileInfo>(superList.length
				* superList[0].length, new ComparatorByScore());

		// The list of tiles that the program will never go into again. Once again, I have no clue why this is here.
		closedList = new ArrayList<TileInfo>();

		// Whee initializing the initial tile that we start on to get this party started
		superList[x1][y1] = new TileInfo(x1, y1, null, 0,
				getHeuristicScore(x1, y1, x2, y2));
		// Aaand adding it to the open list so it will actually calculate.
		openList.add(superList[x1][y1]);

		// KEEP CALCULATING UNTIL THERE IS NOTHING ELSE LEFT IN THE OPEN LIST
		while (openList.size() > 0) {

			// Takes the tile from the TileInfo PriorityQueue. Once it's polled, the Queue no longer has it. It is in our hands now.
			TileInfo currentTile = openList.poll();

			// Calculating all the nice tiles around the open tile we found
			calcSurroundingTile(currentTile, -1, -1);
			calcSurroundingTile(currentTile, 0, -1);
			calcSurroundingTile(currentTile, +1, -1);
			calcSurroundingTile(currentTile, +1, 0);
			calcSurroundingTile(currentTile, +1, +1);
			calcSurroundingTile(currentTile, 0, +1);
			calcSurroundingTile(currentTile, -1, +1);
			calcSurroundingTile(currentTile, -1, 0);

			// And if it so happens that the open tile we selected is the ending tile, fun stuff happens.
			// This is mostly happytime pathdrawing code
			if (currentTile.tileX == x2 && currentTile.tileY == y2) {
				System.out.println("Path totally found brah");
				char[][] thePath = Map.map.getMap();
				while (currentTile != null) {
					thePath[currentTile.tileX][currentTile.tileY] = ',';
					currentTile = currentTile.parentTile;
				}
				Map.printMap(thePath);
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
	public static void calcSurroundingTile(TileInfo tile, int cX,
			int cY) {

		// Whee integers
		int newX = tile.tileX + cX;
		int newY = tile.tileY + cY;

		// Whee out of bounds checking
		if (newX < 0 || newY < 0 || newX >= Map.map.passable.length
				|| newY >= Map.map.passable[0].length) {
			return;
		}

		// Now we take that surrounding tile from the SUPER LIST.
		TileInfo surroundingTile = superList[tile.tileX + cX][tile.tileY
				+ cY];
		// And calculate how much it costs to move to the surrounding tile from the original tile (assuming we are taking the path that goes to the TileInfo tile)
		int toTileCost = (cX != 0 && cY != 0 ? 14 : 10)
				* Map.map.passCost[newX][newY];

		// Now if we pulled a null from the superlist, we MAKE IT NOT NULL ANYMORE.
		// How? BY MAKING A NEW TILEINFO THING. YEAH. TOTALLY.
		if (surroundingTile == null) {
			// We are happy to include all tiles of all types in the superlist.
			surroundingTile = new TileInfo(newX, newY, tile,
					tile.cost + toTileCost, getHeuristicScore(newX,
							newY, destX, destY));
			superList[newX][newY] = surroundingTile;
			// But if its one of those dirty, unpassable tiles... then we don't want it in the open list.
			if (Map.map.passable[newX][newY]) {
				// PURE AND PASSABLE TILES ONLY.
				openList.add(surroundingTile);
			}
		} else if (surroundingTile.cost > tile.cost + toTileCost) {
			// If it so happens that the cost we calculated to this tile was cheaper than the cost calculated to that tile from before,
			// then by all means, the quickest way to this tile is by the tile we just came from.
			surroundingTile.parentTile = tile;
			// Resort here?
			// Nah.
		}

	}

	/*
	 * A method for finding the heuristic score of a tile
	 * where x/y1 defines the tile
	 * and x/y2 defines the destination (what you're trying to get the heuristic for).
	 */
	// This is just Manhattan distance. xDistance + yDistance. Nothing complicated
	public static int getHeuristicScore(int x1, int y1, int x2, int y2) {
		int xDif = x2 > x1 ? x2 - x1 : x1 - x2; // because if-statements are faster than calling Math.abs (probably)
		int yDif = y2 > y1 ? y2 - y1 : y1 - y2;

		return (xDif + yDif) * 1; // Messing around with the heuristic is one of the key differences between A* and Djikstras. (Djistra? Djksrtsata? DK-DJ-Istria?)
		// Because Djkistra's is more like a flood fill, and A* is more like a smart flood fill.
		// When A* is "flood filling", it fills the flood toward where the heuristic tells it to go.
		// And here, the heuristic is manhattan distance. The pathfinder (if written correctly), goes to tiles that are closer to the exit (which is a guesstimation,
		// you don't know the true distance to the exit, you can only hypothesize)

	}
}
