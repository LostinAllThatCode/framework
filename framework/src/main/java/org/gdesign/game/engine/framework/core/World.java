package org.gdesign.game.engine.framework.core;

import java.util.ArrayList;

import org.gdesign.game.engine.framework.systems.BaseSystem;

public class World {

	public float m_gravity = 9.8f;
	
	private float delta=0f;
	private EntityManager entityManager;
	
	private ArrayList<Entity> added,changed,removed,enable,disable;
	private ArrayList<BaseSystem> systems;
	
	public World(){
		this(9.8f);
	}
	
	public World(float gravity){
		this.m_gravity = gravity;
		this.entityManager = new EntityManager();
		this.added = new ArrayList<Entity>();
		this.changed = new ArrayList<Entity>();
		this.removed = new ArrayList<Entity>();
		this.enable = new ArrayList<Entity>();
		this.disable = new ArrayList<Entity>();
		this.systems = new ArrayList<BaseSystem>();
	}
	
	public void addEntity(Entity e){
		this.added.add(e);
	}
	
	public void changedEntity(Entity e){
		this.changed.add(e);
	}
	
	public void removeEntity(Entity e){
		this.removed.add(e);
	}
	
	public void enableEntity(Entity e){
		this.disable.add(e);
	}
	
	public void disableEntity(Entity e){
		this.enable.add(e);
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
    	for (BaseSystem system : systems) {
    		for (Entity entity : added) system.added(entity);
    		for (Entity entity : removed) system.removed(entity);
    		//for (Entity entity : deleted) system.addedEntity(entity);
    	}
    	
    	clean();
    	
    	for (BaseSystem system : systems) {
    		system.process();
    	}
    }
    
    public void clean(){
    	added.clear();
    	removed.clear();
    }
	
	public float getDelta() {
		return this.delta;
	}
	
	public void setDelta(float delta){
		this.delta = delta;
	}
}
