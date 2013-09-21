package com.xidstudios.pixelarena.pathfinding;

import java.util.Comparator;

public class ScoreComparator implements Comparator<Node> {

	@Override
	public int compare(Node node1, Node node2) {

		return Integer.compare(node1.heuristic + node1.cost,
				node2.heuristic + node2.cost);

	}

}
