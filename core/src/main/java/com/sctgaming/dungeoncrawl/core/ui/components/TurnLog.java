package com.sctgaming.dungeoncrawl.core.ui.components;

import java.util.LinkedList;
import java.util.ListIterator;

import com.badlogic.gdx.graphics.Color;
import com.sctgaming.dungeoncrawl.core.ui.Component;

public class TurnLog extends Component {
	private static LinkedList<TurnEntry> log = new LinkedList<TurnEntry>();
	
	public static void addEntry(String message) {
		addEntry(message, Color.WHITE);
	}
	
	public static void addEntry(String message, Color color) {
		log.offer(new TurnEntry(message, color));
		
		while (log.size() > 10) {
			log.poll();
		}
	}
	
	public static LinkedList<TurnEntry> getLog() {
		return log;
	}
	
	@Override
	public void update(float dt) {
		// TODO Auto-generated method stub
		super.update(dt);
	}
	
	@Override
	public void render(float dt) {
		getBatch().begin();
		
		ListIterator<TurnEntry> li = log.listIterator(log.size());
		
		int x = 0;
		
		while (li.hasPrevious()) {
			TurnEntry entry = li.previous();
			getFont().setColor(entry.getColor().r,entry.getColor().g,entry.getColor().b,1-((x*10)/100f));
			getFont().draw(getBatch(), entry.getMessage(), 10, getCamera().viewportHeight - 30 - x * 20);
			getFont().setColor(Color.WHITE);
			x += 1;
		}
		
		getBatch().end();
	}

}
