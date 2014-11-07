package org.gdesign.game.ecs.systems;

import org.gdesign.game.ecs.BaseSystem;

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
