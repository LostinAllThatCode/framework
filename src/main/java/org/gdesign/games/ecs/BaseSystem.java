package org.gdesign.games.ecs;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;


public abstract class BaseSystem implements EntityObserver{
	protected World world;
	protected HashMap<Integer,Entity> entities;
	protected ArrayList<Class<? extends BaseComponent>> scope;
	
	public BaseSystem(){
		entities = new HashMap<Integer, Entity>();
		scope = new ArrayList<Class<? extends BaseComponent>>();
	}
	
	@SafeVarargs
	public void setScope(Class<? extends BaseComponent>... type){
		for (int i=0;i<type.length;i++){
			scope.add(type[i]);
		}
	}
	
	public void setWorld(World w){
		this.world = w;
		this.initialize();
	}
	
	public void process(){
		if (checkProcessing()){
			begin();
			processEntities(entities.values());
			end();
		}
	}
	
	public abstract boolean checkProcessing();
	
	protected void initialize() {};
	
	protected void begin(){};
	public abstract void processEntities(Collection<Entity> collection);
	protected void end(){};
	
	public void added(Entity e) {
		if (e.hasComponent(scope) && e.isEnabled()) entities.put(e.getId(),e);
	}
	
	public void changed(Entity e) {
		if (e.isDisabled()) disabled(e); else enabled(e);
		if (!e.hasComponent(scope) || e.isDisabled()) entities.remove(e.getId()); else entities.put(e.getId(),e);
	}
	
	public void removed(Entity e) {
		entities.remove(e.getId());
	}
	
	public void disabled(Entity e) {}
	
	public void enabled(Entity e) {}
	
	public int getEntityCount(){
		return entities.size();
	}
}
