package com.xidstudios.pixelarena.pathfinding;

public class TileInfo {

	public TileInfo parentTile;
	
	public int tileX;
	public int tileY;
	
	public int cost;
	public int heur;
	
	public TileInfo(int x, int y, TileInfo parent, int costToAdd, int heuristicScore){
		tileX = x;
		tileY = y;
		cost = costToAdd;
		parentTile = parent;
		heur = heuristicScore;
	}	
}
