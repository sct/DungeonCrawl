package com.sctgaming.dungeoncrawl.core.tiles.map;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import com.sctgaming.dungeoncrawl.core.tiles.Tile;

public class Room {
	private Room parent;
	private List<Tile> tiles = new ArrayList<Tile>();
	private List<Door> doors = new ArrayList<Door>();
	private List<Wall> walls = new ArrayList<Wall>();
	private Point firstPoint = new Point();
	private Point lastPoint = new Point();
	private int w;
	private int h;
	
	public Room(int w, int h, Room parent) {
		this.w = w;
		this.h = h;
		this.parent = parent;
	}
	
	public void setLocation(int fx, int fy, int lx, int ly) {
		this.firstPoint.setLocation(fx, fy);
		this.lastPoint.setLocation(lx, ly);
	}
	
	public void setTiles(List<Tile> tiles) {
		this.tiles = tiles;
	}
	
	public void addTile(Tile tile) {
		tiles.add(tile);
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
	
	public void addWall(Wall wall) {
		walls.add(wall);
	}
	
	public List<Wall> getWalls() {
		return walls;
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
	
	public Room getParent() {
		return parent;
	}
	
	public void setParent(Room parent) {
		this.parent = parent;
	}
}
