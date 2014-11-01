package org.gdesign.game.engine.framework.systems;

import org.gdesign.game.engine.framework.core.World;


public abstract class BaseSystem {
	World world;
	
	public void setWorld(World w){
		this.world = w;
	}
}
