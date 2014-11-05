package org.gdesign.game.engine.framework.core;

public abstract class Manager implements IEntityObserver {
	
	private World world;
	
	public void setWorld(World world){
		this.world = world;
	}
	
	public World getWorld(){
		return this.world;
	}

	public abstract void added(Entity e);

	public abstract void changed(Entity e);

	public abstract void removed(Entity e);

	public abstract void enabled(Entity e);

	public abstract void disabled(Entity e);

}
