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

	public void findPath(Vector2 startVector,
			Vector2 destinationVector) {
		this.sV = startVector;
		this.dV = destinationVector;

		superList = new Node[MapChecker.m.passable.length][MapChecker.m.passable[0].length];
		openList = new PriorityQueue<Node>(superList.length
				* superList[0].length, new ScoreComparator());

		closedList = new ArrayList<Node>();

		superList[(int) startVector.x][(int) startVector.y] = new Node(
				startVector, null, 0, getHeuristicScore(startVector,
						destinationVector));

	}

	private int getHeuristicScore(Vector2 startV, Vector2 destV) {

		int xDif = (int) (destV.x > startV.x ? destV.x - startV.x
				: startV.x - destV.x);
		int yDif = (int)  (destV.y > startV.y ? destV.y - startV.y
				: startV.y - destV.y);

		return (xDif + yDif) * 1;
	}

	public static int getHeuristicScore(float x, float y, float x2,
			float y2) {
		int xDif = (int) (x2 > x ? x2 - x : x - x2); // because if-statements are faster than calling Math.abs (probably)
		int yDif = (int) (y2 > y ? y2 - y : y - y2);

		return (xDif + yDif) * 1; // Messing around with the heuristic is one of the key differences between A* and Djikstras. (Djistra? Djksrtsata? DK-DJ-Istria?)
		// Because Djkistra's is more like a flood fill, and A* is more like a smart flood fill.
		// When A* is "flood filling", it fills the flood toward where the heuristic tells it to go.
		// And here, the heuristic is manhattan distance. The pathfinder (if written correctly), goes to tiles that are closer to the exit (which is a guesstimation,
		// you don't know the true distance to the exit, you can only hypothesize)

	}
}
