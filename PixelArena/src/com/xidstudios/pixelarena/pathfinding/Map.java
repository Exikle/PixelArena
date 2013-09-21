package com.xidstudios.pixelarena.pathfinding;

public class Map {

	public static Map map;

	public boolean[][] passable;

	public int[][] passCost;

	String[] mapRows = new String[10];

	String[] cells;

	public Map() {
		map = new Map();
		passable = new boolean[10][10];
		passCost = new int[passable.length][passable[0].length];

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

		findPassableTiles();
	}

	private void findPassableTiles() {
		for (int x = 0; x < passable.length; x++) {
			for (int y = 0; y < passable[x].length; y++) {

				passCost[x][y] = 1;

				//if (condition)? (choice one) :which means or (choice two)
				passable[y][x] = mapRows[y].charAt(x) == 'X' ? false
						: true;

			}

		}
	}

	public char[][] getMap() {
		char[][] mapArray = new char[passable.length][passable[0].length];
		for (int y = 0; y < mapArray.length; y++) {
			for (int x = 0; x < mapArray[0].length; x++) {

				mapArray[y][x] = passable[y][x] ? ' ' : 'B';// blocked tiles

			}
		}

		return mapArray;
	}

	public static void printMap(char[][] mapArray) {
		for (int i = 0; i < mapArray[0].length + 2; i++) {
			System.out.print("_");
		}
		System.out.println();
		for (char[] line : mapArray) {
			System.out.print("|");
			System.out.print(new String(line));
			System.out.print("|");
			System.out.println();
		}
		for (int i = 0; i < mapArray[0].length + 2; i++) {
			System.out.print("-");
		}
		System.out.println();
	}

}
