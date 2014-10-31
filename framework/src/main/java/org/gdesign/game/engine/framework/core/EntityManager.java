package org.gdesign.game.engine.framework.core;

import java.util.ArrayList;

public class EntityManager {
	
	private ArrayList<Entity> entities;
		
	public EntityManager() {
		entities = new ArrayList<Entity>();
	}
	
	public void addEntity(Entity e){
		entities.add(e);
	}
	
	public void removeEntity(Entity e){
		entities.remove(e);
		System.out.println(e.id + " removed");
	}
	
	public int getEntityCount(){
		return entities.size();
	}
	
	public ArrayList<Entity> getEntities(){
		return entities;
	}
	
}
