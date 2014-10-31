package org.gdesign.game.engine.framework.systems;

import java.util.ArrayList;

import org.gdesign.game.engine.framework.core.Entity;
import org.gdesign.game.engine.framework.core.components.DisplayComponent;
import org.gdesign.game.engine.framework.core.components.PositionComponent;
import org.gdesign.game.engine.framework.core.components.RotationComponent;

import static org.lwjgl.opengl.GL11.*;

public class EntityRenderSystem {
	
	public void process(ArrayList<Entity> components) {	
		for (Entity e : components) {
			if (e instanceof IRenderable) {
				DisplayComponent display = (DisplayComponent) e.getComponent(DisplayComponent.class);
				PositionComponent position = (PositionComponent) e.getComponent(PositionComponent.class);
				RotationComponent rotation = (RotationComponent) e.getComponent(RotationComponent.class);
				
				if (display == null || position == null || rotation == null) break;
			
				display.x = position.x;
				display.y = position.y;
				display.rotation = rotation.rotation;
				
				//OPENGL RENDERING CALL
				glPushMatrix();
				glTranslatef(display.x, display.y, 0);
				glRotatef(display.rotation, 0, 0, 1);
				glColor3f(1, 1, 1);
				glBegin(GL_QUADS);
					glVertex2f(0, 0);
					glVertex2f(20,0);
					glVertex2f(20,-20);
					glVertex2f(0,-20);
				glEnd();
				glPopMatrix();
			}
		}

	}

}
