package com.sctgaming.dungeoncrawl.core;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.sctgaming.dungeoncrawl.core.entity.Entity;
import com.sctgaming.dungeoncrawl.core.entity.LivingEntity;
import com.sctgaming.dungeoncrawl.core.entity.Player;
import com.sctgaming.dungeoncrawl.core.entity.type.PlayerType;
import com.sctgaming.dungeoncrawl.core.entity.type.Types;
import com.sctgaming.dungeoncrawl.core.tiles.TileMap;
import com.sctgaming.dungeoncrawl.core.tiles.TileMapGenerator;
import com.sctgaming.dungeoncrawl.core.tiles.TileMapSpawner;
import com.sctgaming.dungeoncrawl.core.ui.GameOverlay;
import com.sctgaming.dungeoncrawl.core.utils.Directions;

import java.util.Random;

public class GameScreen implements Screen, InputProcessor {
	public static TileMap map;
	public static Player player;
	public static OrthographicCamera CAMERA;
	public static SpriteBatch BATCH;
	public static ShapeRenderer SHAPE;
	public static GameOverlay overlay;
	
	private static int turn = 0;
	
	public GameScreen() {
		Gdx.input.setInputProcessor(this);
		BATCH = new SpriteBatch();
		SHAPE = new ShapeRenderer();

        /* Temporary Random seed generator */
        Random rand = new Random();

		setMap(TileMapGenerator.generateDungeon(rand.nextLong()));
		setPlayer(new Player(new PlayerType(),map,50,50));
		Types.initializeMonsters();
		Types.initializeItems();
		Types.initializeWeapons();
		TileMapSpawner.spawnMonsters(map);

        overlay = new GameOverlay();
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		
		BATCH.setProjectionMatrix(CAMERA.combined);
		SHAPE.setProjectionMatrix(CAMERA.combined);
		BATCH.begin();
		map.update(delta);
		player.update(delta);
		map.render(delta);
		player.render(delta);
		BATCH.end();
		
		/* Render all health bars */
		player.renderHealth();
		for (Entity entity : map.getEntities()) {
			if (entity instanceof LivingEntity) {
				((LivingEntity) entity).renderHealth();
			}
		}
		
		overlay.render(delta);
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
	
	private static void setMap(TileMap newMap) {
		map = newMap;
	}
	
	public static void setPlayer(Player newPlayer) {
		player = newPlayer;
	}
	
	public static void incrementTurn() {
		player.turn();
		map.turn();
		turn += 1;
	}
	
	public static int getTurn() {
		return turn;
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
            case Keys.X:
                player.pickUp();
                break;
            case Keys.I:
                GameOverlay.inventoryHolder.setVisible();
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
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
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
