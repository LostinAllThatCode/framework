package org.gdesign.game.engine.framework.core;

import java.util.HashMap;

import org.gdesign.game.engine.framework.core.components.BaseComponent;

public class Entity {
	private static int INDEX;
	
	private HashMap<Class<? extends BaseComponent>, BaseComponent> components;
	
	private World world;

	public int id;
	
	public Entity(World world){
		this.id = ++INDEX;
		this.world = world;
		this.components = new HashMap<Class<? extends BaseComponent>, BaseComponent>();
	}
	
	public Entity addToWorld(){
		world.addEntity(this);
		return this;
	}
	
	public Entity addComponent(BaseComponent c){
		components.put(c.getClass(), c);
		c.setParent(this.id);
		return this;
	}
	
	public Entity removeComponent(BaseComponent c){
		return removeComponent(c.getClass());
	}

	public Entity removeComponent(Class<? extends BaseComponent> type){
		components.remove(type);
		world.changedEntity(this);
		return this;
	}
	
	public <T extends BaseComponent> T getComponent(Class<T> type){
		return type.cast(components.get(type));
	}
	
	public boolean hasComponent(Class<?>... clazzes){
		boolean hasComponent = (clazzes.length != 0);
		for (int i=0; i<clazzes.length; i++){
			hasComponent &= components.containsKey(clazzes[i]);
		}
		return hasComponent;
	}

	public int getComponentCount(){
		return components.size();
	}
	
	@Override
	public String toString() {
		return "[Type:"+ super.getClass().getSimpleName() + ", Id:"+this.id+", Components:"+components.size()+"]";
	}
}
