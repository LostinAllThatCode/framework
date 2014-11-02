package org.gdesign.game.engine.framework.core;

import java.util.ArrayList;

import org.gdesign.game.engine.framework.core.components.BaseComponent;

public class Entity {
	private static int INDEX;
	
	private ArrayList<BaseComponent> components = new ArrayList<BaseComponent>();
	
	public int id;
	
	public Entity(){
		id = ++INDEX;
	}
	
	public Entity addComponent(BaseComponent c){
		components.add(c);
		return this;
	}
	
	public Entity removeComponent(BaseComponent c){
		components.remove(c);
		return this;
	}

	public Entity removeComponent(Class<? extends BaseComponent> type){
		components.remove(this.getComponent(type));
		return this;
	}
	
	public <T extends BaseComponent> T getComponent(Class<T> type) throws NullPointerException{
		for (BaseComponent c : components){
			if (c.getClass().equals(type)) return type.cast(c);
		}
		throw new NullPointerException("BaseComponent["+type.getSimpleName()+"] is not component of entity with id: "+this.id+"");
	}

	public ArrayList<BaseComponent> getComponentList(ArrayList<Class<? extends BaseComponent>> req){
		ArrayList<BaseComponent> list = new ArrayList<BaseComponent>();
		for (Class<? extends BaseComponent> cl : req){
			BaseComponent c = this.getComponent(cl);
			if (c == null) return null;
			list.add(c);
		}
		return list;
	}
	
	public boolean hasComponent(ArrayList<Class<? extends BaseComponent>> list){
		for (Class<? extends BaseComponent> clazz : list){
			if (getComponent(clazz) == null) return false;
		}
		return true;	
	}

	public int getComponentCount(){
		return components.size();
	}
	
	@Override
	public String toString() {
		return super.getClass() + "[ID: "+this.id+",COMPONENTS:"+components.size()+"]";
	}
}
