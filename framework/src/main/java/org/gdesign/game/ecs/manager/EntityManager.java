package org.gdesign.game.ecs.manager;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

import org.gdesign.game.ecs.BaseManager;
import org.gdesign.game.ecs.Entity;

public class EntityManager extends BaseManager{
	
	private int created,removed;
	
	private HashMap<Integer,Entity> entities;
		
	public EntityManager() {
		entities = new HashMap<Integer, Entity>();
	}
	
	public Entity createEntityInstance() {
		Entity e = new Entity(getWorld());
		entities.put(e.id,e);
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
	}

	@Override
	public void enabled(Entity e) {
	}

	@Override
	public void disabled(Entity e) {
	}


	
}
