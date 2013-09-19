package com.xidstudios.pixelarena.pathfinding.testing;

import java.util.PriorityQueue;

public class Map {

	public static Map map = new Map();

	public boolean[][] passable;

	public int[][] passCost;

	public Map() {
		passable = new boolean[10][10];
		passCost = new int[passable.length][passable[0].length];

		String[] mapRows = new String[11];
		mapRows[0] = "..........";
		mapRows[1] = ".XXX.XXX..";
		mapRows[2] = ".X.X.X.XXX";
		mapRows[3] = "......X.X.";
		mapRows[4] = ".XXXXXxX.X";
		mapRows[5] = "........XX";
		mapRows[6] = "XX....XX..";
		mapRows[7] = "....XXX...";
		mapRows[8] = ".XXXX.....";
		mapRows[9] = "......X...";
		mapRows[10] = "......X...";

		for (int x = 0; x < passable.length; x++) {
			for (int y = 0; y < passable[x].length; y++) {

				passCost[x][y] = 1;

				passable[y][x] = mapRows[y].charAt(x) == 'X' ? false
						: true;

			}

		}
	}

	public char[][] getMap() {
		char[][] mapArray = new char[passable.length][passable[0].length];
		for (int y = 0; y < mapArray.length; y++) {
			for (int x = 0; x < mapArray[0].length; x++) {

				mapArray[y][x] = passable[y][x] ? ' ' : 'X';

			}
		}

		return mapArray;
	}

	public static void printMap(char[][] mapArray) {
		for (int i = 0; i < mapArray[0].length + 2; i++) {
			System.out.print("X");
		}
		System.out.println();
		for (char[] line : mapArray) {
			System.out.print("X");
			System.out.print(new String(line));
			System.out.print("X");
			System.out.println();
		}
		for (int i = 0; i < mapArray[0].length + 2; i++) {
			System.out.print("X");
		}
		System.out.println();
	}

}
