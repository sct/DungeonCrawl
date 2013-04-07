package com.sctgaming.dungeoncrawl.core.map;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import com.sctgaming.dungeoncrawl.core.tiles.Tile;

public class Room {
	private List<Tile> tiles = new ArrayList<Tile>();
	private List<Door> doors = new ArrayList<Door>();
	private Point firstPoint = new Point();
	private Point lastPoint = new Point();
	
	public Room() {
		
	}
	
	public void setLocation(int fx, int fy, int lx, int ly) {
		this.firstPoint.setLocation(fx, fy);
		this.lastPoint.setLocation(lx, ly);
	}
	
	public void setTiles(List<Tile> tiles) {
		this.tiles = tiles;
	}
	
	public List<Tile> getTiles() {
		return this.tiles;
	}
	
	public void setDoors(List<Door> doors) {
		this.doors = doors;
	}
	
	public List<Door> getDoors() {
		return this.doors;
	}
}
