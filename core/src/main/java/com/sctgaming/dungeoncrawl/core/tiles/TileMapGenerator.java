package com.sctgaming.dungeoncrawl.core.tiles;

import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import com.badlogic.gdx.graphics.Color;
import com.sctgaming.dungeoncrawl.core.tiles.map.Corridor;
import com.sctgaming.dungeoncrawl.core.tiles.map.Door;
import com.sctgaming.dungeoncrawl.core.tiles.map.Floor;
import com.sctgaming.dungeoncrawl.core.tiles.map.Room;
import com.sctgaming.dungeoncrawl.core.tiles.map.Wall;

/**
 * Generates a random dungeon based on a provided seed
 * 
 * @author sct
 *
 */
public class TileMapGenerator {
	public static final boolean DEBUG = true;
	public static final int MAX_ROOMS = 10;
	public static final int MAX_ROOM_WIDTH = 10;
	public static final int MAX_ROOM_HEIGHT = 10;
	public static final int MIN_ROOM_WIDTH = 3;
	public static final int MIN_ROOM_HEIGHT = 3;
	public static int DUNGEON_WIDTH = 100;
	public static int DUNGEON_HEIGHT = 100;
	public static TileMap currentMap;
	public static Room lastRoom = null;
	public static Random rand = new Random();
	private static List<Point> unscannedTiles = new ArrayList<Point>();
	private static List<Point> scannedTiles = new ArrayList<Point>();
	
	public static TileMap generateDungeon() {
		
		currentMap = new TileMap(DUNGEON_WIDTH, DUNGEON_HEIGHT);
		
		/*
		 * We know the width and height of the dungeon, so we can place the first room
		 * near the center.
		 */
		addRoom(DUNGEON_WIDTH/2, DUNGEON_HEIGHT/2);
		
		
		createOtherRooms();
		
		createCorridors();
		addCorridorWalls();
		
		return currentMap;
	}
	
	private static void createCorridors() {
		for (Room room : currentMap.getRooms()) {
			for (Door door : room.getDoors()) {
				if (!door.isConnected() && room.getParent() != null) {
					Door closestDoor = null;
					int closestDistance = 0;
					for (Door destDoor : room.getParent().getDoors()) {
						int distance = Math.abs(destDoor.getX() - door.getX()) + Math.abs(destDoor.getY() - door.getY());
						if ((closestDoor == null || distance < closestDistance) && !door.equals(destDoor) && !door.getRoom().equals(destDoor.getRoom())) {
							closestDoor = destDoor;
							closestDistance = distance;
						}
					}
					logAction("Pathing","Closest door found! Distance: " + closestDistance);
					createCorridor(door, closestDoor);
					door.setConnected(true);
					closestDoor.setConnected(true);
				}
			}
		}
		
	}
	
	private static boolean createCorridor(Tile start, Tile dest) {
		HashMap<Tile,PathNode> open = new HashMap<Tile,PathNode>();
		HashSet<Tile> closed = new HashSet<Tile>();
		PathNode curNode = null;
		
		open.put(start, new PathNode(null, start, dest));
		
		logAction("Pathing","Trying to carve tunnel from door @ " + start.getX() + "," + start.getY() + " to " + dest.getX() + "," + dest.getY());
		while (!open.containsKey(dest)) {
			curNode = null;
			
			if (open.isEmpty()) {
				logAction("Pathing","No path found");
				return false;
			}
			
			for (PathNode node : open.values()) {
				if (curNode == null || node.getTotal() < curNode.getTotal()) {
					curNode = node;
				}
			}
			
			closed.add(curNode.getTile());
			open.remove(curNode.getTile());
			
			for (Tile tile : curNode.getTile().getAdjacent()) {
				if (tile != null && !closed.contains(tile) && tile.canTunnel()) {
					if (!open.containsKey(tile) || (curNode.getCost() + tile.getCost()) < open.get(tile).getCost()) {
						PathNode newNode = new PathNode(curNode, tile, dest);
						open.put(tile, newNode);
					}
				}
			}
		}
		
		logAction("Pathing", "Path found, setting tiles...");
		while (curNode.getParent() != null) {
			if (curNode.getTile().canTunnel() && !curNode.getTile().isDoor()) {
				Corridor corridor = new Corridor(currentMap,curNode.getTile().getX(), curNode.getTile().getY());
				currentMap.addTile(corridor);
			}
			curNode = curNode.getParent();
		}
		
		logAction("Pathing", "Path created");
		return true;
	}
	
	private static void addCorridorWalls() {
		for (List<Tile> column : currentMap.getTiles()) {
			for (Tile tile : column) {
				if (tile.isCorridor()) {
					for (Tile adjacent : tile.getAdjacent()) {
						if (adjacent != null && adjacent.isVoid()) {
							Wall wall = new Wall(currentMap, adjacent.getX(), adjacent.getY());
							currentMap.addTile(wall);
						}
					}
				}
			}
		}
		
		List<Tile> newWalls = new ArrayList<Tile>();
		for (List<Tile> column : currentMap.getTiles()) {
			for (Tile tile : column) {
				if (tile.isVoid()) {
					int walls = 0;
					for (Tile adjacent : tile.getAdjacent()) {
						if (adjacent != null && adjacent.isWall()) {
							walls += 1;
						}
					}
					
					if (walls > 1) {
						Wall wall = new Wall(currentMap, tile.getX(), tile.getY());
						newWalls.add(wall);
					}
					
				}
			}
		}
		
		for (Tile tile : newWalls) {
			currentMap.addTile(tile);
		}
	}
	
	private static void createOtherRooms() {
		prepopulatePoints();
		
		Point randPoint;
		while (!unscannedTiles.isEmpty()) {
			randPoint = unscannedTiles.get(rand.nextInt(unscannedTiles.size()));
			
			Tile tile = currentMap.getTile(randPoint.x, randPoint.y);
			if (tile.isVoid()) {
				if (!addRoom(randPoint.x, randPoint.y)) {
					logAction("Room Creation", "Failed to fit room @ X: " + randPoint.x + " Y: " + randPoint.y);
				}
			}
			unscannedTiles.remove(randPoint);
			scannedTiles.add(randPoint);
		}
	}
	
	private static void prepopulatePoints() {
		for (int x = 0; x < DUNGEON_WIDTH; x++) {
			for (int y = 0; y < DUNGEON_HEIGHT; y++) {
				unscannedTiles.add(new Point(x,y));
			}
		}
	}
	
	private static boolean addRoom(int startx, int starty) {
		
		int w = rand.nextInt(MAX_ROOM_WIDTH - MIN_ROOM_WIDTH + 1) + MIN_ROOM_WIDTH;
		int h = rand.nextInt(MAX_ROOM_HEIGHT - MIN_ROOM_HEIGHT + 1) + MIN_ROOM_HEIGHT;
		
		/*
		 * First we check to make sure this room does not intersect with any
		 * other in-use tiles. If it does, return false and skip room creation.
		 */
		
		Room room = new Room(w,h, lastRoom);
		room.setLocation(startx, starty, startx + w, starty + h);
		
		for (Room cRoom : currentMap.getRooms()) {
			if (checkRoomCollision(room, cRoom))
				return false;
		}
		
		/*
		 * Now we create the floor tiles of the room and add the tiles to the main TileMap
		 */
		Floor floor;
		for (int x = startx; x < startx + w; x++) {
			for (int y = starty; y < starty + h; y++) {
				floor = new Floor(currentMap,x,y);
				logAction("Creating Floor", "Floor created @ X: " + x + " Y: " + y);
				currentMap.addTile(floor);
				room.addTile(floor);
			}
		}
		
		// First we figure out how many doors we want. 1-3 depending on size
		int doorChance = rand.nextInt(100);
		int doorCount = 0;
		
		if (w == 3 && h == 3) {
			doorCount = 1;
		} else if (w < 6 && h < 6) {
			if (doorChance < 70) {
				doorCount = 1;
			} else if (doorChance < 90) {
				doorCount = 2;
			} else {
				doorCount = 3;
			}
		} else {
			if (doorChance < 40) {
				doorCount = 1;
			} else if (doorChance < 80) {
				doorCount = 2;
			} else {
				doorCount = 3;
			}
		}
		
		logAction("Door Chance","Door chance calculated result is " + doorCount);
		
		Set<Integer> doorSides = new HashSet<Integer>();
		doorSides.add(0);
		doorSides.add(1);
		doorSides.add(2);
		doorSides.add(3);
		
		boolean doorsComplete = false;
		int doorsAdded = 0;
		while (doorsComplete == false) {
			if (doorSides.isEmpty() || doorsAdded == doorCount) {
				doorsComplete = true;
				logAction("Doors Created","Door creation process completed");
			}
			
			int sideCheck = rand.nextInt(4);
			int doorSpot;
			switch (sideCheck) {
				case 0:
					if (doorSides.contains(sideCheck)) {
						doorSpot = startx + rand.nextInt(w);
						logAction("Door Attempt","Side: Top Offset: " + doorSpot);
						if (starty - 1 != 0) {
							Door door = new Door(currentMap, doorSpot, starty - 1, room);
							currentMap.addTile(door);
							currentMap.addDoor(door);
							room.addDoor(door);
						
							// Door added, now we close this side.
							doorsAdded += 1;
						}
						doorSides.remove(sideCheck);
					}
					break;
				case 1:
					if (doorSides.contains(sideCheck)) {
						doorSpot = starty + rand.nextInt(h);
						logAction("Door Attempt","Side: Right Offset: " + doorSpot);
						
						if (startx + w != DUNGEON_WIDTH) {
							Door door = new Door(currentMap, startx + w, doorSpot, room);
							currentMap.addTile(door);
							currentMap.addDoor(door);
							room.addDoor(door);
							
							doorsAdded += 1;
						}
						doorSides.remove(sideCheck);
					}
					break;
				case 2:
					if (doorSides.contains(sideCheck)) {
						doorSpot = startx + rand.nextInt(w);
						logAction("Door Attempt","Side: Bottom Offset: " + doorSpot);
						
						if (starty + h == DUNGEON_HEIGHT) {
							Door door = new Door(currentMap, doorSpot, starty + h, room);
							currentMap.addTile(door);
							currentMap.addDoor(door);
							room.addDoor(door);
							
							doorsAdded += 1;
						}
						doorSides.remove(sideCheck);
					}
					break;
				case 3:
					if (doorSides.contains(sideCheck)) {
						doorSpot = starty + rand.nextInt(h);
						logAction("Door Attempt","Side: Left Offset: " + doorSpot);
						if (startx - 1 != 0) {
							Door door = new Door(currentMap, startx - 1, doorSpot, room);
							currentMap.addTile(door);
							currentMap.addDoor(door);
							room.addDoor(door);
							
							doorsAdded += 1;
						}
						doorSides.remove(sideCheck);
					}
					break;
			}
		}
		
		/*
		 * Now the walls are created for the room. Walls will skip any occupied blocks.
		 */
		for (int x = startx - 1; x < ((startx - 1) + w + 2); x++) {
			for (int y = starty - 1; y < ((starty - 1) + h + 2); y++) {
				if (currentMap.getTile(x, y).isVoid()) {
					Wall  wall = new Wall(currentMap,x,y);
					logAction("Creating Wall", "Wall created @ X: " + x + " Y: " + y);
					currentMap.addTile(wall);
					room.addWall(wall);
				}
					
			}
		}
		
		/*
		 * If it gets this far, the room creation was a success. Now we create a new Room
		 * object and pass over the tile data and entrance information, as well as point
		 * data.
		 */
		
		currentMap.addRoom(room);
		
		Color color = new Color(rand.nextFloat(),rand.nextFloat(),rand.nextFloat(),1);
		
		for (Tile tile : room.getTiles()) {
			if (tile instanceof Floor) {
				((Floor) tile).setColor(color);
			}
		}
		
		lastRoom = room;
		logAction("Room Created", "Room sucessfully created. Size of " + w + "x" + h);
		
		return true;
	}
	
	private static boolean checkRoomCollision(Room room1, Room room2) {
		if (room1.getMax().x + 2 >= DUNGEON_WIDTH || room1.getMin().x - 1 < 0) return true;
		if (room1.getMax().y + 2 >= DUNGEON_HEIGHT || room1.getMin().y - 1 < 0) return true;
		if (room1.getMax().x + 2 < room2.getMin().x) return false;
		if (room1.getMin().x - 2 > room2.getMax().x) return false;
		if (room1.getMax().y + 2 < room2.getMin().y) return false;
		if (room1.getMin().y - 2 > room2.getMax().y) return false;
		
		return true;
	}
	
	private static void logAction(String action, String message) {
		if (DEBUG) {
			System.out.println("[" + action + "] " + message);
		}
	}
}
