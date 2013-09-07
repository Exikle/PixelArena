package com.xidstudios.pixelarena;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;

public class AStarPAthFinding {

	public static void calcPAth(int fromX, int fromY, int toX,
			int toY, Cell[][] mapCells) {
		int startX = fromX;
		int startY = fromY;
		int endX = toX;
		int endY = toY;
		Cell[][] cells = mapCells;

		List<Integer> openList = new ArrayList<Integer>();
		List<Integer> closedList = new ArrayList<Integer>();
		
		
	}
}
