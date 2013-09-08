package com.xidstudios.pixelarena;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;
import com.badlogic.gdx.math.Vector2;

public class PathFinding {

	static Vector2 start;

	static Vector2 end;

	static Cell[][] cells;

	static Node currentNode;

	public static void calcPAth(Vector2 from, Vector2 to,
			Cell[][] mapCells) {
		start = from;
		end = to;
		cells = mapCells;

		List<Node> openList = new ArrayList<Node>();
		List<Node> closedList = new ArrayList<Node>();

		currentNode = new Node(null, start);
		// check if the parent is in a list
		// if (openList.contains(new Vector2())) {
		//
		// } else if (openList.contains(new Vector2())) {
		//
		// } else {
		//
		// }

	}

	private static void createNode() {}

	public static class Node {

		int hVal;

		int gVal;

		int fVal;

		Node parentNode;

		Vector2 parentV;

		private Node(Node node, Vector2 p) {
			this.parentNode = node;
			this.parentV = p;
			calcVal();
		}

		private void calcVal() {
			hValCalc();
		}

		private void hValCalc() {
			int x = (int) (parentV.x - end.x);
			if (x < 0)
				x *= -1;
			int y = (int) (parentV.y - end.y);
			if (y < 0)
				y *= -1;

			hVal = (int) ((parentV.x) + (parentV.y));
		}
	}
}
