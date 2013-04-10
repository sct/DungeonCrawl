package com.sctgaming.dungeoncrawl.core.tiles;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector3;
import com.sctgaming.dungeoncrawl.core.GameScreen;
import com.sctgaming.dungeoncrawl.core.Tickable;
import com.sctgaming.dungeoncrawl.core.tiles.map.Door;
import com.sctgaming.dungeoncrawl.core.tiles.map.Room;
import com.sctgaming.dungeoncrawl.core.tiles.map.Void;

public class TileMap implements Tickable {
	public static final float UNITSCALE = 1 / 16f;
	public static final float ACTUALSCALE = 1 / 32f;
	private static final float TILES_WIDTH = 20;
	private static final float TILES_HEIGHT = 14.375f;
	public int MAP_WIDTH;
	public int MAP_HEIGHT;
	private OrthographicCamera camera;
	private List<List<Tile>> tiles = new ArrayList<List<Tile>>();
	private List<Room> rooms = new ArrayList<Room>();
	private List<Door> doors = new ArrayList<Door>();
	
	public TileMap(int w, int h) {
		MAP_WIDTH = w;
		MAP_HEIGHT = h;
		populateColumns(w,h);
		
		camera = new OrthographicCamera();
		camera.setToOrtho(true, TILES_WIDTH, TILES_HEIGHT);
		
		/*
		 * Move the camera to the center of the map
		 */
		Vector3 pos = new Vector3();
		pos.set(MAP_WIDTH/2-camera.viewportWidth/2,MAP_HEIGHT/2-camera.viewportHeight/2,0);
		camera.translate(pos.x,pos.y);
		
		camera.update();
		
		GameScreen.CAMERA = camera;
		GameScreen.BATCH.setProjectionMatrix(camera.combined);
	}
	
	public List<List<Tile>> getTiles() {
		return tiles;
	}
	
	public Tile getTile(int x, int y) {
		if (x < MAP_WIDTH && y < MAP_HEIGHT && x >= 0 && y >= 0) {
			return tiles.get(x).get(y);
		}
		return null;
	}
	
	public void addTile(Tile tile) {
		tiles.get(tile.getX()).set(tile.getY(), tile);
	}

	public List<Door> getDoors() {
		return doors;
	}
	
	public void addDoor(Door door) {
		doors.add(door);
	}
	
	public List<Room> getRooms() {
		return rooms;
	}
	
	public void addRoom(Room room) {
		rooms.add(room);
	}
	
	/**
	 * Populates the multi-dimensional List with nulls to prevent
	 * out of bounds exception errors when checking for tiles
	 * 
	 * @param columns Number of columns to pre-populate
	 * @param rows Number of rows to pre-populate
	 */
	private void populateColumns(int columns, int rows) {
		for (int x=0; x<columns; x++) {
			tiles.add(x, new ArrayList<Tile>(rows));
			for (int y=0; y < rows; y++) {
				tiles.get(x).add(y, new Void(this, x, y));
			}
		}
	}

	@Override
	public void update(float dt) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(float dt) {
		for (List<Tile> column : this.getTiles()) {
			for (Tile tile : column) {
				if (tile != null)
				{
					tile.render(dt);
				}
			}
		}
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

}
