package com.sctgaming.dungeoncrawl.core;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.sctgaming.dungeoncrawl.core.entity.Entity;
import com.sctgaming.dungeoncrawl.core.entity.Player;
import com.sctgaming.dungeoncrawl.core.entity.monster.Goblin;
import com.sctgaming.dungeoncrawl.core.entity.monster.Wraith;
import com.sctgaming.dungeoncrawl.core.tiles.TileMap;
import com.sctgaming.dungeoncrawl.core.tiles.TileMapGenerator;
import com.sctgaming.dungeoncrawl.core.utils.Directions;

public class GameScreen implements Screen, InputProcessor {
	private TileMap map;
	private Player player;
	public static OrthographicCamera CAMERA;
	public static OrthographicCamera ICAMERA;
	public static SpriteBatch BATCH;
	public static SpriteBatch INTERFACE;
	public static BitmapFont font = new BitmapFont(true);
	public static List<Entity> entities = new ArrayList<Entity>();
	
	private int selectX = 0;
	private int selectY = 0;
	
	public GameScreen() {
		Gdx.input.setInputProcessor(this);
		BATCH = new SpriteBatch();
		INTERFACE = new SpriteBatch();
		ICAMERA = new OrthographicCamera();
		ICAMERA.setToOrtho(true);
		this.setMap(TileMapGenerator.generateDungeon());
		this.setPlayer(new Player(map,50,50));
		Wraith wraith = new Wraith(map,51,51);
		Goblin goblin = new Goblin(map,52,52);
		entities.add(wraith);
		entities.add(goblin);
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		
		BATCH.setProjectionMatrix(CAMERA.combined);
		INTERFACE.setProjectionMatrix(ICAMERA.combined);
		BATCH.begin();
		map.update(delta);
		player.update(delta);
		map.render(delta);
		player.render(delta);
		
		for (Entity entity : entities) {
			entity.update(delta);
			entity.render(delta);
		}
		BATCH.end();
		
		INTERFACE.begin();
		font.draw(INTERFACE, "Dungeon Crawler v0.1", 10, 10);
		INTERFACE.end();
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}
	
	private void setMap(TileMap map) {
		this.map = map;
	}
	
	public void setPlayer(Player player) {
		this.player = player;
	}

	@Override
	public boolean keyDown(int keycode) {
		switch (keycode) {
			case Keys.UP:
				player.setDirection(Directions.NORTH);
				player.setMoving(true);
				break;
			case Keys.RIGHT:
				player.setDirection(Directions.EAST);
				player.setMoving(true);
				break;
			case Keys.DOWN:
				player.setDirection(Directions.SOUTH);
				player.setMoving(true);
				break;
			case Keys.LEFT:
				player.setDirection(Directions.WEST);
				player.setMoving(true);
				break;
			case Keys.SPACE:
				player.action();
				break;
		}
		
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		if (player.isMoving()) {
			if (player.getDirection().equals(Directions.NORTH) && keycode == Keys.UP) {
				player.setMoving(false);
			}
			
			if (player.getDirection().equals(Directions.EAST) && keycode == Keys.RIGHT) {
				player.setMoving(false);
			}
			
			if (player.getDirection().equals(Directions.SOUTH) && keycode == Keys.DOWN) {
				player.setMoving(false);
			}
			
			if (player.getDirection().equals(Directions.WEST) && keycode == Keys.LEFT) {
				player.setMoving(false);
			}
		}
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		selectX = screenX;
		selectY = screenY;
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		int xDist = selectX - screenX;
		int yDist = selectY - screenY;
		float camX = CAMERA.viewportWidth / 2;
		float camY = CAMERA.viewportHeight / 2;

		float xMov = xDist * TileMap.ACTUALSCALE;
		float yMov = yDist * TileMap.ACTUALSCALE;

		if (xMov + CAMERA.position.x <= camX) {
			xMov = camX - CAMERA.position.x;
		} else if (xMov + CAMERA.position.x >= map.MAP_WIDTH - camX) {
			xMov = map.MAP_WIDTH - camX - CAMERA.position.x;
		}

		if (yMov + CAMERA.position.y <= camY) {
			yMov = camY - CAMERA.position.y;
		} else if (yMov + CAMERA.position.y >= map.MAP_HEIGHT - camY) {
			yMov = map.MAP_HEIGHT - camY - CAMERA.position.y;
		}
		
		CAMERA.translate(xMov, yMov, 0);
		selectX = screenX;
		selectY = screenY;
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		// TODO Auto-generated method stub
		return false;
	}

}
