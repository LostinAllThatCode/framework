package org.gdesign.game.engine.framework.core;

import java.util.HashMap;

import org.gdesign.game.engine.framework.core.components.Component;

public class Entity {
	private static int EntityCounter;
	
	private HashMap<Class<?>, Component> components = new HashMap<Class<?>, Component>();

	public int id;
	
	public Entity(){
		id = ++Entity.EntityCounter;
	}
	
	public void addComponent(Component c){
		components.put(c.getClass(), c);
	}
	
	public void removeComponent(Component c){
		components.remove(c);
	}
	
	public Component getComponent(Class<?> clazz){
		return components.get(clazz);
	}
	
	public boolean hasComponents(Class<?>... clazz){
		for (int i=0; i<=clazz.length-1;i++){
			if (components.get(clazz) == null) return false;
		}
		return true;		
	}
	
	public int getComponentCount(){
		return components.size();
	}
}
