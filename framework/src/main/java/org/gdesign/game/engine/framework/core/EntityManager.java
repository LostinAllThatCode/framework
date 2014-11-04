package org.gdesign.game.engine.framework.core;

import java.util.Collection;
import java.util.HashMap;

public class EntityManager extends Manager{
	
	private int created,removed;
	
	private HashMap<Integer,Entity> entities;
		
	public EntityManager() {
		entities = new HashMap<Integer, Entity>();
	}
		
	public Collection<Entity> getEntities(){
		return entities.values();
	}
	
	public int getActiveCount(){
		return entities.size();
	}
	
	public int getCreatedCount(){
		return created;
	}
	
	public int getRemovedCount(){
		return removed;
	}
	
	
	@Override
	public void added(Entity e) {
		created++;
		entities.put(e.id,e);
	}
	@Override
	public void removed(Entity e) {
		removed++;
		entities.remove(e.id);	
	}

	@Override
	public void changed(Entity e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void enabled(Entity e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void disabled(Entity e) {
		// TODO Auto-generated method stub
		
	}
	
}
