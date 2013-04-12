package com.sctgaming.dungeoncrawl.core.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.sctgaming.dungeoncrawl.core.GameScreen;
import com.sctgaming.dungeoncrawl.core.Tickable;
import com.sctgaming.dungeoncrawl.core.utils.Textures;

public class GameOverlay implements Tickable {
	
	public static OrthographicCamera camera = new OrthographicCamera();;
	public static SpriteBatch batch = new SpriteBatch();
	
	public static OrthographicCamera fontCamera = new OrthographicCamera();
	public static SpriteBatch fontBatch = new SpriteBatch();
	public static BitmapFont font = new BitmapFont(Gdx.files.internal("visitor.fnt"), true);
	
	public GameOverlay() {
		camera.setToOrtho(true, 640, 360);
		fontCamera.setToOrtho(true);
		batch.setProjectionMatrix(camera.combined);
		fontBatch.setProjectionMatrix(fontCamera.combined);
	}

	@Override
	public void update(float dt) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(float dt) {
		batch.begin();
		TextureRegion sbg = new TextureRegion(Textures.INTERFACE);
		sbg.setRegion(0,0,215,360); 
		
		batch.draw(sbg,camera.viewportWidth-sbg.getRegionWidth(),0);
		batch.end();
		
		float sidePosX = fontCamera.viewportWidth - 430 + 20;
		fontBatch.begin();
		font.draw(fontBatch, "Dungeon Crawler v0.1", sidePosX, 20);
		font.draw(fontBatch, "Turn: " + GameScreen.getTurn(), sidePosX, 40);
		fontBatch.end();
		
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

}
