package com.sctgaming.dungeoncrawl.core.ui.components;

import com.badlogic.gdx.graphics.Color;

public class TurnEntry {
	private String message;
	private Color color;
	
	public TurnEntry(String message) {
		this(message,Color.WHITE);
	}
	
	public TurnEntry(String message, Color color) {
		this.message = message;
		this.color = color;
	}
	
	public String getMessage() {
		return message;
	}
	
	public Color getColor() {
		return color;
	}
}
