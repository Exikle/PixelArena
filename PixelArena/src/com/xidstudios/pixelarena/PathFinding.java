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

	static int identity;

	public static void calcPAth(Vector2 from, Vector2 to,
			Cell[][] mapCells, Arena a) {
		start = new Vector2(from.x, from.y);
		end = to;
		cells = mapCells;
		arena = a;
		// Gdx.app.log(PArena.LOG, "Lists Created");

		currentNode = new Node(null, start);
		identity = 0;
		currentNode.setId(identity);
		// Gdx.app.log(PArena.LOG, "Added start to openList");
		// check squares around this and add
		openList.clear();

		for (int loop = 0; loop < 1; loop++) {
			if ((!openList.contains(currentNode))
					&& (!closedList.contains(currentNode))) {
				openList.add(currentNode);
				int startPX = (int) currentNode.parentVector.x;
				// Gdx.app.log(PArena.LOG, "Start X " + startPX);
				int startPY = (int) currentNode.parentVector.y;
				// Gdx.app.log(PArena.LOG, "Start Y " + startPY);

				// Gdx.app.log("", "" + currentNode.parentV);

				//
				Gdx.app.log(PArena.LOG, "Pos "
						+ currentNode.parentVector);
				MIN_X = startPX - 1;
				MIN_Y = startPY - 1;
				MAX_X = startPX + 1;
				MAX_Y = startPY + 1;
				Gdx.app.log(PArena.LOG, "MinX " + MIN_X + ", MinY "
						+ MIN_Y);
				Gdx.app.log(PArena.LOG, "MaxX " + MAX_X + ", MaxY "
						+ MAX_Y);

				startPosX = (startPX - 1 < MIN_X) ? startPX
						: startPX - 1;
				startPosY = (startPY - 1 < MIN_Y) ? startPY
						: startPY - 1;
				endPosX = (startPX + 1 > MAX_X) ? startPX
						: startPX + 1;
				endPosY = (startPY + 1 > MAX_Y) ? startPY
						: startPY + 1;

				// Check boundaries on start cell
				for (int colNum = startPosX; colNum <= endPosX; colNum++) {
					for (int rowNum = startPosY; rowNum <= endPosY; rowNum++) {
						// Gdx.app.log(PArena.LOG, colNum + "," + rowNum);
						// All the neighbors will be grid[rowNum][colNum]
						if ((rowNum == startPY)
								&& (colNum == startPY)) {
							System.out.print("SS|");
						} else if (!cells[rowNum][colNum].getTile()
								.getProperties()
								.containsKey("blocked")) {
							Node node = new Node(currentNode,
									new Vector2(colNum * 32,
											rowNum * 32));
							if (rowNum != startPX
									&& colNum != startPY) {
								node.setMoveCost(14);
							} else
								node.setMoveCost(10);
							System.out.print(node.getTotalValue()
									+ "|");
							openList.add(node);
						} else {
							System.out.print("BB|");
						}
					}
					System.out.println("");
				}

				openList.remove(currentNode);
				closedList.add(currentNode);
				int f = openList.get(0).getTotalValue();
				int index = 0;
				for (Node temp : openList) {
					if (temp.getTotalValue() < f) {
						f = temp.getTotalValue();
						// System.out.println("Lowest FVal"
						// + temp.calcFVal());
						index = openList.lastIndexOf(temp);
						// Gdx.app.log("Node Vals", "HVal = " + temp.hVal);
						// Gdx.app.log("Node Vals", "GVal = " + temp.gVal);
						// Gdx.app.log("Node Vals", "FVal = " + temp.fVal);
					}
				}
				identity++;
				currentNode.setId(identity);
				Gdx.app.log("PathFinding",
						"" + currentNode.getTotalValue());
				// System.out.println("PathFinding: " + currentNode.gVal);
				System.out.println("PathFinding: "
						+ currentNode.parentVector);
				currentNode = openList.get(index);
				arena.colorSquare(currentNode.parentVector);
			}
		}
	}
}
