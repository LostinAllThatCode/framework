package org.gdesign.game.engine.framework.core;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

import org.gdesign.game.engine.framework.systems.BaseSystem;

public class World {

	public float m_gravity = 9.8f;
	
	private float delta=0f;
	private EntityManager entityManager;
	
	private HashMap<Integer,Entity> added,changed,removed,enable,disable;
	private ArrayList<BaseSystem> systems;
	private ArrayList<Manager> managers;
	
	public World(){
		this(9.8f);
	}
	
	public World(float gravity){
		this.m_gravity = gravity;
		this.added = new HashMap<Integer, Entity>();
		this.changed = new HashMap<Integer, Entity>();
		this.removed = new HashMap<Integer, Entity>();
		this.enable = new HashMap<Integer, Entity>();
		this.disable = new HashMap<Integer, Entity>();
		
		this.systems = new ArrayList<BaseSystem>();
		this.managers = new ArrayList<Manager>();
		this.managers.add(entityManager = new EntityManager());
	}
	
	public void addEntity(Entity e){
		this.added.put(e.id,e);
	}
	
	public void changedEntity(Entity e){
		this.changed.put(e.id,e);
	}
	
	public void removeEntity(Entity e){
		this.removed.put(e.id,e);
		System.out.println("Removing entity: " + e);
	}
	
	public void enableEntity(Entity e){
		this.disable.put(e.id,e);;
	}
	
	public void disableEntity(Entity e){
		this.enable.put(e.id,e);
	}	
	
	public EntityManager getEntityManager(){
		return entityManager;
	}
	
	public <T extends BaseSystem> T setSystem(T system){
		systems.add(system);
		system.setWorld(this);
		return system;
	}
	
    public <T extends BaseSystem> T getSystem(Class<T> type) {
		for (BaseSystem s : systems){
			if (s.getClass().equals(type)) return type.cast(s);
		}
		throw new NullPointerException("No BaseSystem["+type.getSimpleName()+"] system available.");
    }
    
    public void process(){
    	updateObservers();
    	for (BaseSystem system : systems) {
    		system.process();
    	}
    }
    
    public Collection<Entity> getEntities(){
    	return entityManager.getEntities();
    }
    
    private void updateObservers(){
    	for (IEntityObserver observer : systems) {
    		if (!added.isEmpty()) 	for (Entity entity : added.values()) observer.added(entity);
    		if (!removed.isEmpty()) for (Entity entity : removed.values()) observer.removed(entity);
    		if (!changed.isEmpty()) for (Entity entity : changed.values()) observer.changed(entity);
    	}
    	
    	for (IEntityObserver observer : managers) {
    		if (!added.isEmpty()) 	for (Entity entity : added.values()) observer.added(entity);
    		if (!removed.isEmpty()) for (Entity entity : removed.values()) observer.removed(entity);
    		if (!changed.isEmpty()) for (Entity entity : changed.values()) observer.changed(entity);
    	}
    	clean();
    }
    
    private void clean(){
    	added.clear();
    	removed.clear();
    	changed.clear();
    }
	
	public float getDelta() {
		return this.delta;
	}
	
	public void setDelta(float delta){
		this.delta = delta;
	}
}
