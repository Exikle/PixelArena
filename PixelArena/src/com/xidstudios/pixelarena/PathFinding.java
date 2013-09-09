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
		Gdx.app.log(PArena.LOG, "Start X" + startPX);
		int startPY = (int) currentNode.parentV.y / 32;
		Gdx.app.log(PArena.LOG, "Start Y" + startPY);

		Gdx.app.log("", "");

		// middle right
		if (!cells[startPX + 1][startPY].getTile().getProperties()
				.containsKey("blocked")) {
			openList.add(new Node(currentNode, new Vector2(
					startPX + 1, startPY)));
			Gdx.app.log(PArena.LOG, "MidRight " + (startPX + 1) + ","
					+ (startPY));
		}
		// top right
		if (!cells[startPX + 1][startPY + 1].getTile()
				.getProperties().containsKey("blocked")) {
			openList.add(new Node(currentNode, new Vector2(
					startPX + 1, startPY + 1)));
			Gdx.app.log(PArena.LOG, "TopRight " + (startPX + 1) + ","
					+ (startPY + 1));
		}
		// top middle
		if (!cells[startPX][startPY + 1].getTile().getProperties()
				.containsKey("blocked")) {
			openList.add(new Node(currentNode, new Vector2(startPX,
					startPY + 1)));
			Gdx.app.log(PArena.LOG, "TopMiddle " + (startPX) + ","
					+ (startPY + 1));
		}
		// top left
		if (!cells[startPX - 1][startPY + 1].getTile()
				.getProperties().containsKey("blocked")) {
			openList.add(new Node(currentNode, new Vector2(
					startPX - 1, startPY + 1)));
			Gdx.app.log(PArena.LOG, "TopLeft " + (startPX - 1) + ","
					+ (startPY + 1));
		}
		// middle left
		if (!cells[startPX - 1][startPY].getTile().getProperties()
				.containsKey("blocked")) {
			openList.add(new Node(currentNode, new Vector2(
					startPX - 1, startPY)));
			Gdx.app.log(PArena.LOG, "MidLeft " + (startPX - 1) + ","
					+ (startPY));
		}
		// bottom left
		if (!cells[startPX - 1][startPY - 1].getTile()
				.getProperties().containsKey("blocked")) {
			openList.add(new Node(currentNode, new Vector2(
					startPX - 1, startPY - 1)));
			Gdx.app.log(PArena.LOG, "BotLeft " + (startPX - 1) + ","
					+ (startPY - 1));
		}
		// bottom middle
		if (!cells[startPX][startPY - 1].getTile().getProperties()
				.containsKey("blocked")) {
			openList.add(new Node(currentNode, new Vector2(startPX,
					startPY - 1)));
			Gdx.app.log(PArena.LOG, "BotMid " + (startPX) + ","
					+ (startPY - 1));
		}
		// bottom right
		if (!cells[startPX + 1][startPY - 1].getTile()
				.getProperties().containsKey("blocked")) {
			openList.add(new Node(currentNode, new Vector2(
					startPX + 1, startPY - 1)));
			Gdx.app.log(PArena.LOG, "BotRight " + (startPX - 1) + ","
					+ (startPY + 1));
		}
		openList.remove(currentNode);
		closedList.add(currentNode);

		// need to calc move cost;
			
		//

		Gdx.app.log("", "");

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
			// Gdx.app.log(PArena.LOG, "Heuristic Value" + hVal);
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
