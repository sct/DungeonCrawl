package com.sctgaming.dungeoncrawl.core;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.sctgaming.dungeoncrawl.core.tiles.TileMap;
import com.sctgaming.dungeoncrawl.core.tiles.TileMapGenerator;

public class GameScreen implements Screen, InputProcessor {
	private TileMap map;
	public static OrthographicCamera CAMERA;
	public static SpriteBatch BATCH;
	
	private int selectX = 0;
	private int selectY = 0;
	
	public GameScreen() {
		Gdx.input.setInputProcessor(this);
		BATCH = new SpriteBatch();
		this.setMap(TileMapGenerator.generateDungeon());
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		CAMERA.update();
		BATCH.setProjectionMatrix(CAMERA.combined);
		map.update(delta);
		map.render(delta);
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

	@Override
	public boolean keyDown(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		// TODO Auto-generated method stub
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
