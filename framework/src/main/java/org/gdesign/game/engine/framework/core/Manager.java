package org.gdesign.game.engine.framework.core;

public abstract class Manager implements IEntityObserver {

	public abstract void added(Entity e);

	public abstract void changed(Entity e);

	public abstract void removed(Entity e);

	public abstract void enabled(Entity e);

	public abstract void disabled(Entity e);

}
