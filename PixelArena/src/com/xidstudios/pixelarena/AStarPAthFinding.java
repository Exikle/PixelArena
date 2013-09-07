package com.xidstudios.pixelarena;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;
import com.badlogic.gdx.math.Vector2;

public class AStarPAthFinding {

	public static void calcPAth(Vector2 from, Vector2 to,
			Cell[][] mapCells) {
		Vector2 start = from;
		Vector2 end = to;
		Cell[][] cells = mapCells;

		List<Vector2> openList = new ArrayList<Vector2>();
		List<Vector2> closedList = new ArrayList<Vector2>();

		closedList.add(start);// add start to closed list,
								// dont need to check;

		// check if the parent is in a list
		// if (openList.contains(new Vector2())) {
		//
		// } else if (openList.contains(new Vector2())) {
		//
		// } else {
		//
		// }

	}
}
