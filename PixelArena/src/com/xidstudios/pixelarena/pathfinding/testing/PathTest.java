package com.xidstudios.pixelarena.pathfinding.testing;

import com.xidstudios.pixelarena.pathfinding.AStarPathfinder;

public class PathTest {

	// Start here.
	public static void main(String[] args) {

		args = new String[] { "-a*1D" };
		
		switch (args[0]) {
			case "-a*1D": {
				AStarPathfinder.findPath(0, 0, 9, 9);
				break;
			}
			case "-help": {
				System.out.println("NO HELP FOR YOU");
				System.out
						.println("...change the args to the right tag");
			}

		}

	}

}
