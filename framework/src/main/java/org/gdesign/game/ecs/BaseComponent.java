package org.gdesign.game.ecs;

public abstract class BaseComponent {
	
	private int parent_id;
	
	public void setParent(int id){
		this.parent_id = id;
	}
	
	public int getParent(){
		return this.parent_id;
	}
	
	@Override
	public String toString() {
		return "[ParentId:"+this.parent_id+" ,Type;"+this.getClass().getSimpleName()+"]";
	}
	
}
