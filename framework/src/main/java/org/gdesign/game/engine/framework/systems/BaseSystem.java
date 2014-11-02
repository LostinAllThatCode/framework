package org.gdesign.game.engine.framework.systems;

import java.util.ArrayList;

import org.gdesign.game.engine.framework.core.Entity;
import org.gdesign.game.engine.framework.core.World;
import org.gdesign.game.engine.framework.core.components.BaseComponent;


public abstract class BaseSystem {
	protected World world;
	protected ArrayList<Entity> entities;
	protected ArrayList<Class<? extends BaseComponent>> scope;
	
	public BaseSystem(){
		entities = new ArrayList<Entity>();
		scope = new ArrayList<Class<? extends BaseComponent>>();
	}
	
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
		processEntities(entities);
		end();
	}
	
	//TEMP
	public void addEntity(Entity e){
		entities.add(e);
	}
	
	public abstract void begin();
	public abstract void processEntities(ArrayList<Entity> entities);
	public abstract void end();
	
}
