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

	}

}
