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

	static Arena arena;

	static Node currentNode;

	static List<Node> openList = new ArrayList<Node>();

	static List<Node> closedList = new ArrayList<Node>();

	static int MIN_X;

	static int MIN_Y;

	static int MAX_X;

	static int MAX_Y;

	static int startPosX;

	static int startPosY;

	static int endPosX;

	static int endPosY;

	public static void calcPAth(Vector2 from, Vector2 to,
			Cell[][] mapCells, Arena a) {
		start = from;
		end = to;
		cells = mapCells;
		arena = a;
		// Gdx.app.log(PArena.LOG, "Lists Created");

		currentNode = new Node(null, start);
		openList.add(currentNode);
		// Gdx.app.log(PArena.LOG, "Added start to openList");
		// check squares around this and add

		for (int loop = 0; loop < 2; loop++) {
			int startPX = (int) currentNode.parentV.x / 32;
			// Gdx.app.log(PArena.LOG, "Start X " + startPX);
			int startPY = (int) currentNode.parentV.y / 32;
			// Gdx.app.log(PArena.LOG, "Start Y " + startPY);

			Gdx.app.log("", "" + currentNode.parentV);
			//
			MIN_X = startPX - 1;
			MIN_Y = startPY - 1;
			MAX_X = startPX + 1;
			MAX_Y = startPY + 1;
			Gdx.app.log(PArena.LOG, "MinX " + MIN_X + ", MinY "
					+ MIN_Y);
			Gdx.app.log(PArena.LOG, "MaxX " + MAX_X + ", MaxY "
					+ MAX_Y);

			startPosX = (startPX - 1 < MIN_X) ? startPX : startPX - 1;
			startPosY = (startPY - 1 < MIN_Y) ? startPY : startPY - 1;
			endPosX = (startPX + 1 > MAX_X) ? startPX : startPX + 1;
			endPosY = (startPY + 1 > MAX_Y) ? startPY : startPY + 1;

			// Check boundaries on start cell
			for (int colNum = startPosX; colNum <= endPosX; colNum++) {
				for (int rowNum = startPosY; rowNum <= endPosY; rowNum++) {
					// Gdx.app.log(PArena.LOG, colNum + "," + rowNum);
					// All the neighbors will be grid[rowNum][colNum]
					if ((rowNum == startPY) && (colNum == startPY)) {
					} else if (!cells[rowNum][colNum].getTile()
							.getProperties().containsKey("blocked")) {
						Node node = new Node(currentNode,
								new Vector2(rowNum, colNum));
						if (rowNum != startPX && colNum != startPY) {
							node.setMovementCost(14);
						} else
							node.setMovementCost(10);
						// System.out.print(node.getFValue() + "|");
						openList.add(node);
					} else {
						// System.out.print("BB|");
					}
				}
				// System.out.println("");

			}

			openList.remove(currentNode);
			closedList.add(currentNode);
			int f = openList.get(0).getFValue();
			int index = 0;
			for (Node temp : openList) {
				if (temp.getFValue() < f) {
					f = temp.getFValue();
					index = openList.lastIndexOf(temp);
					// Gdx.app.log("Node Vals", "HVal = " + temp.hVal);
					// Gdx.app.log("Node Vals", "GVal = " + temp.gVal);
					// Gdx.app.log("Node Vals", "FVal = " + f);
				}
			}
			currentNode = openList.get(index);
			arena.colorSquare(currentNode.getVectorPos());

			// openList.

		}
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
			int x = (int) Math.abs(parentV.x - (end.x / 32));
			int y = (int) Math.abs(parentV.y - (end.y / 32));
			// Gdx.app.log("Node", "x = " + x);
			// Gdx.app.log("Node", "y = " + y);
			if (parentNode != null) {
				hVal += parentNode.hVal;
			}
			hVal += (int) (x + y);
		}

		private void debugCalcHValue() {
			int x = (int) Math.abs(parentV.x - (end.x / 32));
			int y = (int) Math.abs(parentV.y - (end.y / 32));
			Gdx.app.log("Node", "x = " + x);
			Gdx.app.log("Node", "y = " + y);

			hVal = (int) (x + y);
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
