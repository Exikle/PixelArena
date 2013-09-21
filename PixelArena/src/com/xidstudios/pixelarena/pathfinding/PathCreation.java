package com.xidstudios.pixelarena.pathfinding;

import java.util.ArrayList;
import java.util.PriorityQueue;

import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;
import com.badlogic.gdx.math.Vector2;
import com.xidstudios.pixelarena.arena.Arena;

public class PathCreation {

	static Node[][] superList;

	static PriorityQueue<Node> openList;

	static ArrayList<Node> closedList;

	static Vector2 sV;

	static Vector2 dV;

	static Cell[][] cells;

	static Arena arena;

	private boolean pathFound;

	public void findPath(Vector2 startVector,
			Vector2 destinationVector) {
		pathFound = false;

		this.sV = startVector;
		this.dV = destinationVector;

		superList = new Node[MapChecker.m.passable.length][MapChecker.m.passable[0].length];
		openList = new PriorityQueue<Node>(superList.length
				* superList[0].length, new ScoreComparator());

		closedList = new ArrayList<Node>();

		superList[(int) startVector.x][(int) startVector.y] = new Node(
				startVector, null, 0, getHeuristicScore(startVector,
						destinationVector));

		openList.add(superList[(int) startVector.x][(int) startVector.y]);

		while (openList.size() > 0) {

			// Takes the tile from the TileInfo PriorityQueue. Once it's polled, the Queue no longer has it. It is in our hands now.
			Node currentTile = openList.poll();

			// Calculating all the nice tiles around the open tile we found
			calcSurroundingTile(currentTile, -1, -1);
			calcSurroundingTile(currentTile, 0, -1);
			calcSurroundingTile(currentTile, +1, -1);
			calcSurroundingTile(currentTile, +1, 0);
			calcSurroundingTile(currentTile, +1, +1);
			calcSurroundingTile(currentTile, 0, +1);
			calcSurroundingTile(currentTile, -1, +1);
			calcSurroundingTile(currentTile, -1, 0);

			if (currentTile.tileVector.x == destinationVector.x
					&& currentTile.tileVector.y == destinationVector.y) {
				System.out.println("Path Found and Created");
				while (currentTile != null) {
					// thePath[(int) currentTile.tileVector.x][(int) currentTile.tileVector.y] = '#';
					arena.colorSquare(new Vector2(
							(int) currentTile.tileVector.x,
							(int) currentTile.tileVector.y));
					currentTile = currentTile.parentNode;
				}
				// Map.printMap(thePath);
				pathFound = true;
				break;
			}

		}
		if (!pathFound) {
			System.out.println("Path Not Found");
		}

	}

	private void calcSurroundingTile(Node node, int i, int j) {}

	private int getHeuristicScore(Vector2 startV, Vector2 destV) {

		int xDif = (int) (destV.x > startV.x ? destV.x - startV.x
				: startV.x - destV.x);
		int yDif = (int) (destV.y > startV.y ? destV.y - startV.y
				: startV.y - destV.y);

		return (xDif + yDif) * 1;
	}
}
