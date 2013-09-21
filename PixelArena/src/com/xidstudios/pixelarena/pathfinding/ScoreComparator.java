package com.xidstudios.pixelarena.pathfinding;

import java.util.Comparator;

public class ScoreComparator implements Comparator<Node> {

	@Override
	public int compare(Node tile1, Node tile2) {

		return Integer.compare(tile1.heuristic + tile1.cost, tile2.heuristic
				+ tile2.cost);

	}

}
