package org.gdesign.game.engine.framework.systems;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

import org.gdesign.game.engine.framework.core.Entity;
import org.gdesign.game.engine.framework.core.IEntityObserver;
import org.gdesign.game.engine.framework.core.World;
import org.gdesign.game.engine.framework.core.components.BaseComponent;


public abstract class BaseSystem implements IEntityObserver{
	protected World world;
	protected HashMap<Integer,Entity> entities;
	protected ArrayList<Class<? extends BaseComponent>> scope;
	
	public BaseSystem(){
		entities = new HashMap<Integer, Entity>();
		scope = new ArrayList<Class<? extends BaseComponent>>();
	}
	
	@SuppressWarnings("unchecked")
	public void setScope(Class<? extends BaseComponent>... type){
		for (int i=0;i<type.length;i++){
			scope.add(type[i]);
		}
	}
	
	public void setWorld(World w){
		this.world = w;
	}
	
	public void process(){
		begin();
		processEntities(entities.values());
		end();
	}
	
	public abstract void begin();
	public abstract void processEntities(Collection<Entity> collection);
	public abstract void end();
	
	public void added(Entity e) {
		if (e.hasComponent((Class<?>[]) scope.toArray())) entities.put(e.id,e);
	}
	
	public void changed(Entity e) {
		if (e.hasComponent((Class<?>[]) scope.toArray())) entities.remove(e.id);
	}
	
	public void removed(Entity e) {
		entities.remove(e.id);
	}
	
	public void disabled(Entity e) {
		entities.remove(e.id);
	}
	
	public void enabled(Entity e) {
		if (e.hasComponent((Class<?>[]) scope.toArray())) entities.put(e.id,e);
	}
}
