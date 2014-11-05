package org.gdesign.game.ecs;

import java.util.ArrayList;
import java.util.HashMap;

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
	
	public boolean hasComponent(ArrayList<Class<? extends BaseComponent>> scope) {
		boolean hasComponent = (scope.size() != 0);
		for (Class<? extends BaseComponent> clazz : scope){
			hasComponent &= components.containsKey(clazz);
		}
		return hasComponent;
	}

	public int getComponentCount(){
		return components.size();
	}
	
	@Override
	public String toString() {
		return "[Type:"+ super.getClass().getSimpleName() + ", Id:"+this.id+", Components:"+components+"]";
	}


}
