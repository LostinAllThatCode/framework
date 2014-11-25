package org.gdesign.game.ecs.manager;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

import org.gdesign.game.ecs.BaseManager;
import org.gdesign.game.ecs.Entity;

public class EntityManager extends BaseManager{
	
	private int active,created,removed;
	
	private HashMap<Integer,Entity> entities;
		
	public EntityManager() {
		entities = new HashMap<Integer, Entity>();
	}
	
	public Entity createEntityInstance() {
		Entity e = new Entity(getWorld());
		entities.put(e.getId(),e);
		return e;
	}
		
	public Entity getEntity(int id){
		return entities.get(id);
	}
	
	public Collection<Entity> getAllEntities(){
		return entities.values();
	}
	
	public Collection<Entity> getAllEntitiesWithComponents(Class<?>... components){
		Collection<Entity> targets = new ArrayList<Entity>();
		for (Entity e : entities.values()){
			if (e.hasComponent(components)) targets.add(e);
		}
		return targets;
	}
	
	public int getActiveCount(){
		return active;
	}
	
	public int getCreatedCount(){
		return created;
	}
	
	public int getRemovedCount(){
		return removed;
	}
	
	@Override
	public void added(Entity e) {
		active++;
		created++;
		entities.put(e.getId(),e);
	}
	
	@Override
	public void removed(Entity e) {
		active--;
		removed++;
		entities.remove(e.getId());	
	}

	@Override
	public void changed(Entity e) {
		if (e.isDisabled()) active--; else active++;
	}

	@Override
	public void enabled(Entity e) {
	}

	@Override
	public void disabled(Entity e) {
	}


	
}
