package com.xidstudios.pixelarena;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;
import com.badlogic.gdx.math.Vector2;
import com.xidstudios.pixelarena.arena.Arena;

/*
 * 1. check through closed list and make sure node being checked isnt on list
 * 2. check through openlist
 */

public class PathFinding {

	static Vector2 start;

	static Vector2 end;

	static Cell[][] cells;

	static Node currentNode;

	static Arena arena;

	public static void calcPAth(Vector2 from, Vector2 to,
			Cell[][] mapCells, Arena a) {
		start = from;
		end = to;
		cells = mapCells;
		arena = a;

		List<Node> openList = new ArrayList<Node>();
		List<Node> closedList = new ArrayList<Node>();
		Gdx.app.log(PArena.LOG, "Lists Created");

		currentNode = new Node(null, start);
		openList.add(currentNode);
		Gdx.app.log(PArena.LOG, "Added start to openList");
		// check squares around this and add

		int startPX = (int) currentNode.parentV.x / 32;
		Gdx.app.log(PArena.LOG, "Start X" + startPX);
		int startPY = (int) currentNode.parentV.y / 32;
		Gdx.app.log(PArena.LOG, "Start Y" + startPY);

		Gdx.app.log("", "");
		//
		int MIN_X = startPX - 1;
		int MIN_Y = startPY - 1;
		int MAX_X = startPX + 1;
		int MAX_Y = startPY + 1;

		int startPosX = (startPX - 1 < MIN_X) ? startPX : startPX - 1;
		int startPosY = (startPY - 1 < MIN_Y) ? startPY : startPY - 1;
		int endPosX = (startPX + 1 > MAX_X) ? startPX : startPX + 1;
		int endPosY = (startPY + 1 > MAX_Y) ? startPY : startPY + 1;

		// Check boundaries on start cell
		for (int rowNum = startPosX; rowNum <= endPosX; rowNum++) {
			for (int colNum = startPosY; colNum <= endPosY; colNum++) {
				// All the neighbors will be grid[rowNum][colNum]

				if (!cells[rowNum][colNum].getTile().getProperties()
						.containsKey("blocked")) {
					Node node = new Node(currentNode, new Vector2(
							rowNum, colNum));
					if (rowNum != startPX && colNum != startPY) {
						node.setMovementCost(14);
					} else
						node.setMovementCost(10);
					openList.add(node);

					System.out.print(node.getFValue() + "|");
				} else
					System.out.print("B");

			}
			System.out.println("");

		}

		openList.remove(currentNode);
		closedList.add(currentNode);
		int n = openList.get(0).getFValue();
		int index = 0;
		for (Node temp : openList) {
			if (temp.getFValue() < n) {
				n = temp.getFValue();
				index = openList.lastIndexOf(temp);
				Gdx.app.log("n", "n = " + n);
			}
		}
		currentNode = openList.get(index);
		arena.colorSquare(currentNode.getVectorPos());

		// need to calc move cost;

		//

		Gdx.app.log("", "");
		openList.clear();
		closedList.clear();

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
			calcHValue();
		}

		public void setMovementCost(int c) {
			this.gVal = c;
			calcFVal();
		}

		private void calcFVal() {
			fVal = gVal + hVal;
			// Gdx.app.log("Node", "HVal = " + hVal);
			// Gdx.app.log("Node", "GVal = " + gVal);
			// Gdx.app.log("Node", "FVal = " + fVal);
		}

		private void calcHValue() {
			int x = (int) (parentV.x - end.x);
			if (x < 0)
				x *= -1;
			int y = (int) (parentV.y - end.y);
			if (y < 0)
				y *= -1;

			hVal = (int) (x + y) / 32;
			// Gdx.app.log(PArena.LOG, "Heuristic Value" + hVal);
		}

		private void setParent(Node node) {
			this.parentNode = node;
		}

		public int getFValue() {
			return fVal;
		}

		public Vector2 getVectorPos() {
			return parentV;
		}
	}
}
