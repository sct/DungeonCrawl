package com.sctgaming.dungeoncrawl.core.ui;

import java.util.ArrayList;
import java.util.List;

import com.sctgaming.dungeoncrawl.core.Tickable;

public class Container implements Tickable {
	private Container parent = null;
	private List<Component> components = new ArrayList<Component>();
	
	public void setParent(Container container) {
		this.parent = container;
	}
	
	public Container getParent() {
		return parent;
	}
	
	public void addComponent(Component component) {
		components.add(component);
	}
	
	public List<Component> getComponents() {
		return components;
	}
	
	public Container setX(float x) {
		for (Component component : components) {
			component.setX(component.getX() + x);
		}
		return this;
	}
	
	public Container setY(float y) {
		for (Component component : components) {
			component.setY(component.getY() + y);
		}
		return this;
	}

	@Override
	public void update(float dt) {
		for (Component component : components) {
			component.update(dt);
		}
	}

	@Override
	public void render(float dt) {
		for (Component component : components) {
			component.render(dt);
		}
	}

	@Override
	public void turn() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

}
