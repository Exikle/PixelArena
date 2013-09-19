package com.xidstudios.pixelarena.pathfinding.testing;

import com.xidstudios.pixelarena.pathfinding.AStarPathfinder;

public class PathTest {

	// Start here.
	public static void main(String[] args) {

		args = new String[] { "-a*" };

		if (args == null) {
			args = new String[] { "-help" };
		}

		// Start the A* Pathfinder
		switch (args[0]) {
			case "-a*": {
				//put start to finish
				AStarPathfinder.findPath(0, 0, 9, 9);
				break;
			}

		}

	}

}
