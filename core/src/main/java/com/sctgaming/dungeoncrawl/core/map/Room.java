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
	private int w;
	private int h;
	
	public Room(int w, int h) {
		this.w = w;
		this.h = h;
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
	
	public void addDoor(Door door) {
		doors.add(door);
	}
	
	public List<Door> getDoors() {
		return this.doors;
	}
	
	public int getWidth() {
		return w;
	}
	
	public int getHeight() {
		return h;
	}
	
	public Point getMin() {
		return firstPoint;
	}
	
	public Point getMax() {
		return lastPoint;
	}
}
