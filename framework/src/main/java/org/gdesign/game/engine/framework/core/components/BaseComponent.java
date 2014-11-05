package org.gdesign.game.engine.framework.core.components;

public abstract class BaseComponent {
	
	private int parent_id;
	
	public void setParent(int id){
		this.parent_id = id;
	}
	
	public int getParent(){
		return this.parent_id;
	}
	
}
