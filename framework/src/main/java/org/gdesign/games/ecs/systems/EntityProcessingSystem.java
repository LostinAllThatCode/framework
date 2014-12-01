package org.gdesign.games.ecs.systems;

import java.util.Collection;

import org.gdesign.games.ecs.BaseSystem;
import org.gdesign.games.ecs.Entity;

public abstract class EntityProcessingSystem extends BaseSystem {

	protected abstract void process(Entity entity);
	
	@Override
	public boolean checkProcessing() {
		return true;
	}

	@Override
	public void processEntities(Collection<Entity> collection) {
		for (Entity entity : collection) process(entity);
	}

}
