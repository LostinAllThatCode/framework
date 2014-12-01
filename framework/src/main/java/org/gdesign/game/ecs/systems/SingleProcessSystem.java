package org.gdesign.game.ecs.systems;

import java.util.Collection;

import org.gdesign.game.ecs.BaseSystem;
import org.gdesign.game.ecs.Entity;

public abstract class SingleProcessSystem extends BaseSystem {
	
	
	
	public abstract void processSystem();
	
	@Override
	public boolean checkProcessing() {
		return true;
	}

	@Override
	public void processEntities(Collection<Entity> collection) {
		processSystem();
	}


}
