package com.xidstudios.pixelarena.pathfinding;

import java.util.Comparator;

public class ComparatorByScore implements Comparator<TileInfo> {

	@Override
	// Yeah, this. This is... a replacement comparator for the PriorityQueue, of which I know little of.
	// Maybe this works, maybe this doesn't. I don't really know yet. But it's here nonetheless.
	public int compare(TileInfo tile1, TileInfo tile2) {
		int tileOneTotal = tile1.heur + tile1.cost;
		int tileTwoTotal = tile2.heur + tile2.cost;
		if (tileOneTotal > tileTwoTotal) {

			return tileTwoTotal;
		} else {

			return tileOneTotal;
		}

	}

}
