package org.gdesign.game.engine.framework.systems;

import org.gdesign.game.engine.framework.core.Entity;
import org.gdesign.game.engine.framework.core.components.BaseComponent;
import org.gdesign.game.engine.framework.core.components.DisplayComponent;
import org.gdesign.game.engine.framework.core.components.PositionComponent;
import org.gdesign.game.engine.framework.core.components.VelocityComponent;
import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;

import static org.lwjgl.opengl.GL11.*;

public class EntityRenderSystem extends BaseSystem{
	
	DisplayComponent display;
	PositionComponent position;
	
	public EntityRenderSystem(){
		
	}
	
	public void process(Entity e) {	
		try {
			display = e.getComponent(DisplayComponent.class);
			position = e.getComponent(PositionComponent.class);
			
			display.x = position.x;
			display.y = position.y;
			display.rotation = 0;
			
			if (Display.isCreated()) {
				glPushMatrix();				
					glTranslatef(display.x, display.y, 0);
					glRotatef(display.rotation, 0, 0, 1);
					glColor3f(1, 1, 1);			
					glBegin(GL_QUADS);
						glVertex2f(0, 0);
						glVertex2f(20,0);
						glVertex2f(20,20);
						glVertex2f(0,20);
					glEnd();
				glPopMatrix();
			} else throw new LWJGLException("Display is not created yet. Cant use opengl rendering methods.");
		} catch (Exception ex) {
			ex.printStackTrace();
		}

	
	}

}
