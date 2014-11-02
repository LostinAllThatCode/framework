package org.gdesign.game.engine.framework.core;

public interface IEntityObserver {
	
    void added(Entity e);
    
    void changed(Entity e);
    
    void removed(Entity e);
    
    void enabled(Entity e);
    
    void disabled(Entity e);

}
