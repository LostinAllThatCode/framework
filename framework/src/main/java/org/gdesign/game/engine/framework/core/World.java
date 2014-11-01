package org.gdesign.game.engine.framework.core;

public class World {

	public float m_gravity = 9.8f;
	
	private float delta=0f;

	public World(){
	}
	
	public World(float m_gravity){
		this.m_gravity = m_gravity;
	}
	
	public float getDelta() {
		return this.delta;
	}
	
	public void setDelta(float delta){
		this.delta = delta;
	}
}
