package com.xidstudios.pixelarena;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;
import com.badlogic.gdx.math.Vector2;
import com.xidstudios.pixelarena.arena.Arena;
import com.xidstudios.pixelarena.entity.Player;


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

public class Path {
	
	private static Node cNode;
	private static List<Node> oList = new ArrayList<Node>();
	private static List<Node> cList = new ArrayList<Node>();

	private static Vector2 origin;
	private static Vector2 end;	
	private static Cell[][] cells;
	private static Arena arena;
	private static Player player;
	
	public static void calculatePath(Vector2 from, Vector2 to,
			Cell[][] mapCells, Arena a, Player p){
		initializeVariables(from, to, mapCells, a, p);
		
		cNode = new Node(null, origin, end);
		//step 1
		oList.add(cNode);
		//step 2
		for(int x=0;x<1;x++){
			
		}
		
	}
	
	public static void calc(){
		
	}
	
	
	private static void initializeVariables(Vector2 from, Vector2 to,
			Cell[][] mapCells, Arena a, Player p) {
		origin = from;
		end = to;
		cells = mapCells;
		arena = a;
		player =p;}
}
