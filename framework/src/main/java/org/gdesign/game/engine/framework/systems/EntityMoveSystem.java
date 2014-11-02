package org.gdesign.game.engine.framework.systems;

import java.util.ArrayList;

import org.gdesign.game.engine.framework.core.Entity;

public class EntityMoveSystem extends BaseSystem{

	@Override
	public void begin() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void processEntities(ArrayList<Entity> entities) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void end() {
		// TODO Auto-generated method stub
		
	}
	/*
	private PositionComponent position;
	private VelocityComponent velocity;
	
	public EntityMoveSystem(){
	}
	
	public void process(Entity e) {
		position = e.getComponent(PositionComponent.class);
		velocity = e.getComponent(VelocityComponent.class);
		
		float dX=position.x,dY=position.y;
		
		float acceleration = -world.m_gravity / 5000f;
		float oldVel = velocity.velY;
		velocity.velY = velocity.velY + acceleration * world.getDelta();
		dY += (oldVel + velocity.velY) * 0.5f * world.getDelta();

		if (dY < 0) {
			velocity.velY = 0;
			System.out.println(velocity.velY);
		} else {
			position.y = dY;
		}
		position.x = dX;
	}
	*/
}
