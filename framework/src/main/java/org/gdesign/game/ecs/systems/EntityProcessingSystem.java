package org.gdesign.game.ecs.systems;

import java.util.Collection;

import org.gdesign.game.ecs.BaseSystem;
import org.gdesign.game.ecs.Entity;

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
