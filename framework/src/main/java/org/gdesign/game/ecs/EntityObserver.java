package org.gdesign.game.ecs;

public interface EntityObserver {
	
    void added(Entity e);
    
    void changed(Entity e);
    
    void removed(Entity e);
    
    void enabled(Entity e);
    
    void disabled(Entity e);

}
