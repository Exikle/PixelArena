package com.xidstudios.pixelarena;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;
import com.badlogic.gdx.math.Vector2;
import com.xidstudios.pixelarena.arena.Arena;

/*
 * calc surrounding tiles f val
 * Check through closed list and make sure node being checked isnt on list
 * Check through openlist, if node in list is better than current one(new g< current g), if it is, replace parent with new one
 * Check surrounding tiles for lowest f val
 * Set lowest as current node
 * remove current node from open and add to closed list
 * repeat.
 * 
 * 1. Add starting node to openlist
 * 2. Loop{
 *  a) Look for the lowest F cost square on the open list. We refer to this as the current square.
 *  b) Switch it to the closed list.
 *  c) For each of the 8 squares adjacent to this current square …
 *     - If it is not walkable or if it is on the closed list, ignore it. Otherwise do the following.           
 *     - If it isn’t on the open list, add it to the open list. Make the current square the parent of this square. Record the F, G, and H costs of the square. 
 *     - If it is on the open list already, check to see if this path to that square is better, using G cost as the measure. A lower G cost means that this is a better path. If so, change the parent of the square to the current square, and recalculate the G and F scores of the square. If you are keeping your open list sorted by F score, you may need to resort the list to account for the change.
 *  d) Stop when you:
 *     - Add the target square to the closed list, in which case the path has been found (see note below), or
 *     - Fail to find the target square, and the open list is empty. In this case, there is no path.   
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

	private final static int TIMES_TO_LOOP = 1;// debug variable

	public static void calcPAth(Vector2 from, Vector2 to,
			Cell[][] mapCells, Arena a) {
		start = new Vector2(from.x, from.y);
		end = to;
		cells = mapCells;
		arena = a;

		currentNode = new Node(null, start);
		openList.clear();

		for (int loop = 0; loop < TIMES_TO_LOOP; loop++) {
			if (!closedList.contains(currentNode)) {
				openList.add(currentNode);
				int startPX = (int) currentNode.parentVector.x;
				int startPY = (int) currentNode.parentVector.y;
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

				for (int colNum = startPosX; colNum <= endPosX; colNum++) {
					for (int rowNum = startPosY; rowNum <= endPosY; rowNum++) {
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
						index = openList.lastIndexOf(temp);
					}
				}
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
