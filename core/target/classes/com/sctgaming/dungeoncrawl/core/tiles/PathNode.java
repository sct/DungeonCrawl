package com.sctgaming.dungeoncrawl.core.tiles;

public class PathNode {
	private PathNode parent;
	private int distance;
	private int cost;
	private Tile tile;
	private Tile dest;
	
	public PathNode(PathNode parent, Tile tile, Tile dest) {
		this.parent = parent;
		this.tile = tile;
		this.dest = dest;
		if (parent != null) {
			this.cost = parent.getCost() + tile.getCost();
		} else {
			this.cost = tile.getCost();
		}
		this.distance = Math.abs(dest.getX() - tile.getX()) + Math.abs(dest.getY() - tile.getY());
	}
	
	public int getDistance() {
		return distance;
	}
	
	public int getCost() {
		return cost;
	}
	
	public Tile getDest() {
		return dest;
	}
	
	public Tile getTile() {
		return tile;
	}
	
	public int getTotal() {
		return distance + cost;
	}
	
	public PathNode getParent() {
		return parent;
	}

}
