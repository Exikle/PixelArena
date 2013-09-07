package com.xidstudios.pixelarena;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;
import com.badlogic.gdx.math.Vector2;

public class AStarPAthFinding {

	public static void calcPAth(int fromX, int fromY, int toX,
			int toY, Cell[][] mapCells) {
		int startX = fromX;
		int startY = fromY;
		int endX = toX;
		int endY = toY;
		Cell[][] cells = mapCells;

		List<Vector2> openList = new ArrayList<Vector2>();
		List<Vector2> closedList = new ArrayList<Vector2>();

		closedList.add(new Vector2(startX, startY));// add start to closed list,
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
