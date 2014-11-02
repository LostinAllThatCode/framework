package org.gdesign.game.engine.framework.systems;

import java.util.ArrayList;

import org.gdesign.game.engine.framework.core.Entity;
import org.gdesign.game.engine.framework.core.IEntityObserver;
import org.gdesign.game.engine.framework.core.World;
import org.gdesign.game.engine.framework.core.components.BaseComponent;


public abstract class BaseSystem implements IEntityObserver{
	protected World world;
	protected ArrayList<Entity> entities,disabled;
	protected ArrayList<Class<? extends BaseComponent>> scope;
	
	public BaseSystem(){
		entities = new ArrayList<Entity>();
		disabled = new ArrayList<Entity>();
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
	
	public abstract void begin();
	public abstract void processEntities(ArrayList<Entity> entities);
	public abstract void end();
	
	public void added(Entity e) {
		if (e.getComponentList(scope) != null) entities.add(e);
	}
	
	public void changed(Entity e) {
		if (e.getComponentList(scope) != null) {
			entities.remove(e);
		}
	}
	
	public void removed(Entity e) {
		entities.remove(e);
	}
	
	public void disabled(Entity e) {
		entities.remove(e);
	}
	
	public void enabled(Entity e) {
		entities.add(e);
	}
}
