package org.gdesign.game.engine.framework.core.components;

public class VelocityComponent extends BaseComponent {
	public float velX, velY;
	
	public float velX_old,velY_old;
	
	public VelocityComponent (float velocityX, float velocityY){
		this.velX = velocityX;
		this.velY = velocityY;
		this.velY_old = 0;
	}
}
