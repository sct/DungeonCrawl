package com.sctgaming.dungeoncrawl.core;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.sctgaming.dungeoncrawl.core.tiles.TileMap;

public class GameScreen implements Screen {
	private TileMap map;
	public static OrthographicCamera CAMERA;
	public static SpriteBatch BATCH;
	
	public GameScreen() {
		BATCH = new SpriteBatch();
		this.setMap(new TileMap());
		
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		CAMERA.update();
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

}
