package com.xidstudios.pixelarena;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Gdx;
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
		Gdx.app.log(PArena.LOG, "Lists Created");

		currentNode = new Node(null, start);
		openList.add(currentNode);
		Gdx.app.log(PArena.LOG, "Added start to openList");
		// check squares around this and add

		int startPX = (int) currentNode.parentV.x / 32;
		int startPY = (int) currentNode.parentV.y / 32;

		// middle right

		if (!cells[startPX + 1][startPY].getTile().getProperties()
				.containsKey("blocked")) {
			openList.add(new Node(currentNode, new Vector2(
					startPX + 1, startPY)));
		}
		// top right
		else if (!cells[startPX + 1][startPY + 1].getTile()
				.getProperties().containsKey("blocked")) {

		}
		// top middle
		else if (!cells[startPX][startPY + 1].getTile()
				.getProperties().containsKey("blocked")) {

		}
		// top left
		else if (!cells[startPX - 1][startPY + 1].getTile()
				.getProperties().containsKey("blocked")) {

		}
		// check if the parent is in a list
		// if (openList.contains(new Vector2())) {
		//
		// } else if (openList.contains(new Vector2())) {
		//
		// } else {
		//
		// }

	}

	public static class Node {

		int hVal;

		int gVal;

		int fVal;

		Node parentNode;

		Vector2 parentV;

		private Node(Node node, Vector2 p) {
			setParent(node);
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

			hVal = (int) (x + y) / 32;
			Gdx.app.log("Pathfinding", hVal + "");
		}

		private void setParent(Node node) {
			this.parentNode = node;
		}

		private int calcTotal() {
			int f = gVal + hVal;
			return f;
		}
	}
}
