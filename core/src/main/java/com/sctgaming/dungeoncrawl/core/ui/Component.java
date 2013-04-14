package com.sctgaming.dungeoncrawl.core.ui;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Component extends Container {
	private float x = 0;
	private float y = 0;
	private float width = 0;
	private float height = 0;
	
	public SpriteBatch getBatch() {
		return GameOverlay.batch;
	}
	
	public OrthographicCamera getCamera() {
		return GameOverlay.camera;
	}
	
	public BitmapFont getFont() {
		return GameOverlay.font;
	}
	
	public float getX() {
		return x;
	}
	
	public float getY() {
		return y;
	}
	
	public void setWidth(float width) {
		this.width = width;
	}
	
	public float getWidth() {
		return width;
	}
	
	public void setHeight(float height) {
		this.height = height;
	}
	
	public float getHeight() {
		return height;
	}
	
	@Override
	public Component setX(float x) {
		this.x = x;
		return this;
	}
	
	@Override
	public Component setY(float y) {
		this.y = y;
		return this;
	}
}
