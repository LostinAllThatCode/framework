package org.gdesign.game.engine.framework.core;

import java.util.ArrayList;

import org.gdesign.game.engine.framework.systems.BaseSystem;

public class World {

	public float m_gravity = 9.8f;
	
	
	private float delta=0f;
	private EntityManager entityManager;
	
	private ArrayList<Entity> entities;
	private ArrayList<BaseSystem> systems;
	
	public World(){
		entityManager = new EntityManager();
		entities = new ArrayList<Entity>();
		systems = new ArrayList<BaseSystem>();
	}
	
	public World(float m_gravity){
		super();
		this.m_gravity = m_gravity;
	}
	
	public void addEntity(Entity e){
		this.entities.add(e);
	}
	
	public void removeEntity(Entity e){
		this.entities.remove(e);
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
    		system.process();
    	}
    }
	
	public float getDelta() {
		return this.delta;
	}
	
	public void setDelta(float delta){
		this.delta = delta;
	}
}
