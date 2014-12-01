package org.gdesign.games.ecs.systems;

import org.gdesign.games.ecs.BaseSystem;

public abstract class IntervalEntitySystem extends BaseSystem {
	
	private float acc,interval;
	
	public IntervalEntitySystem(float interval) {
		this.interval = interval;
	}
	
	@Override
	public boolean checkProcessing() {
        acc += world.getDelta();
        if(acc >= interval) {
                acc -= interval;
                return true;
        }
        return false;
	}

}
