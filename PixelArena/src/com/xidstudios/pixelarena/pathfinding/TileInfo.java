package com.xidstudios.pixelarena.pathfinding;

public class TileInfo {

	public TileInfo parentTile;
	
	public int tileX;
	public int tileY;
	
	public int cost;
	public int heur;
	
	public TileInfo(float x, float y, TileInfo parent, int costToAdd, int heuristicScore){
		tileX = (int) x;
		tileY = (int) y;
		cost = costToAdd;
		parentTile = parent;
		heur = heuristicScore;
	}
	
	
	
	
}
