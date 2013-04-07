package com.sctgaming.dungeoncrawl.core.tiles;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.sctgaming.dungeoncrawl.core.GameScreen;
import com.sctgaming.dungeoncrawl.core.Tickable;
import com.sctgaming.dungeoncrawl.core.utils.Textures;

public class TileMap implements Tickable {
	private static final float UNITSCALE = 1 / 16f;
	private static final float TILES_WIDTH = 20;
	private static final float TILES_HEIGHT = 14.375f;
	private OrthographicCamera camera;
	private List<List<Tile>> tiles = new ArrayList<List<Tile>>();
	
	public TileMap() {
		tiles = TileMapGenerator.generateDungeon(this);
		
		camera = new OrthographicCamera();
		camera.setToOrtho(true, TILES_WIDTH, TILES_HEIGHT);
		
		GameScreen.CAMERA = camera;
		GameScreen.BATCH.setProjectionMatrix(camera.combined);
	}

	@Override
	public void update(float dt) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(float dt) {
		GameScreen.BATCH.begin();
		
		for (List<Tile> column : tiles) {
			for (Tile tile : column) {
				if (tile != null)
				{
					GameScreen.BATCH.draw(tile.getTexture(), tile.getX(), tile.getY(), 1, 1);
				}
			}
		}
		
		GameScreen.BATCH.end();
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

}
