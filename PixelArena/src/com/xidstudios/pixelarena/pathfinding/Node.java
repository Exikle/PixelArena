package com.xidstudios.pixelarena.pathfinding;

import com.badlogic.gdx.math.Vector2;

public class Node {

	public Node parentNode;

	public Vector2 tileVector;

	public int cost;

	public int heuristic;

	public Node(Vector2 tileVector, Node parentNode, int costToAdd,
			int heuristicScore) {
		this.tileVector = tileVector;
		this.parentNode = parentNode;
		this.cost = costToAdd;
		this.heuristic = heuristicScore;
	}

}