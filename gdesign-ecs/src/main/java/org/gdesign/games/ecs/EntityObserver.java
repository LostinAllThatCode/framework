package org.gdesign.games.ecs;

public interface EntityObserver {
	
    void added(Entity e);
    
    void changed(Entity e);
    
    void removed(Entity e);
    
    void enabled(Entity e);
    
    void disabled(Entity e);

}
