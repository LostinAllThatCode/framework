package org.gdesign.games.ecs.systems;

import java.util.Collection;

import org.gdesign.games.ecs.BaseSystem;
import org.gdesign.games.ecs.Entity;

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
