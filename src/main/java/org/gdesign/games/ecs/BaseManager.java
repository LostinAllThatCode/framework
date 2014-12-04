package org.gdesign.games.ecs;


public abstract class BaseManager implements EntityObserver {
	
	protected World world;
	
	
	protected void setWorld(World world){
		this.world = world;
		this.initialize();
	}
	
	public World getWorld(){
		return this.world;
	}
	
	protected void initialize() {};

	public abstract void added(Entity e);

	public abstract void changed(Entity e);

	public abstract void removed(Entity e);

	public abstract void enabled(Entity e);

	public abstract void disabled(Entity e);

}
