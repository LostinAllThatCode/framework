package framework;

import static org.junit.Assert.*;

import java.util.Random;

import org.gdesign.game.engine.framework.core.Box;
import org.gdesign.game.engine.framework.core.Entity;
import org.gdesign.game.engine.framework.core.EntityManager;
import org.gdesign.game.engine.framework.core.components.DisplayComponent;
import org.gdesign.game.engine.framework.core.components.PositionComponent;
import org.gdesign.game.engine.framework.core.components.RotationComponent;
import org.gdesign.game.engine.framework.systems.EntityRenderSystem;
import org.junit.Test;
import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;

public class EntityTests {
	
	Random rng = new Random();
	
	@Test
	public void EntityComponentsTest() {
		Entity allCompTestEntity = createEntity(false);
		assertNotNull(allCompTestEntity.getComponent(PositionComponent.class));
		assertNotNull(allCompTestEntity.getComponent(DisplayComponent.class));
		assertNotNull(allCompTestEntity.getComponent(RotationComponent.class));
		allCompTestEntity.removeComponent(allCompTestEntity.getComponent(PositionComponent.class));
	}
	
	@Test
	public void EntityManagerTest(){
		EntityManager man = new EntityManager();
		for (int i=1; i <= 10; i++){
			man.addEntity(createBox(false));
		}

		
		EntityRenderSystem entityRenderer = new EntityRenderSystem();

		try {
		    Display.setDisplayMode(new DisplayMode(800,600));
		    Display.create();
		} catch (LWJGLException e) {
		    e.printStackTrace();
		    System.exit(0);
		}
	 
		// init OpenGL
		GL11.glMatrixMode(GL11.GL_PROJECTION);
		GL11.glLoadIdentity();
		GL11.glOrtho(0, 800, 0, 600, 1, -1);
		GL11.glMatrixMode(GL11.GL_MODELVIEW);
	 
		while (!Display.isCloseRequested()) {
		    // Clear the screen and depth buffer
		    GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);	
		    entityRenderer.process(man.getEntities());
		    Display.update();
		}
	 
		Display.destroy();	
	}

	Entity createEntity(boolean randomMode){
		Entity e = new Entity();
		PositionComponent p = new PositionComponent();
		p.x = (float) rng.nextInt(800)+1; p.y = (float) rng.nextInt(600)+1;
		e.addComponent(p);
		RotationComponent r = new RotationComponent();
		r.rotation = (float) Math.random();
		e.addComponent(r);
		DisplayComponent d = new DisplayComponent();
		d.x = p.x; d.y = p.y; d.rotation = r.rotation;
		if (randomMode) {
			if (p.x < 0.5) e.addComponent(d);
		} else e.addComponent(d);
		return e;
	}
	
	Entity createBox(boolean randomMode){
		Box e = new Box();
		PositionComponent p = new PositionComponent();
		p.x = (float) rng.nextInt(800)+1; p.y = (float) rng.nextInt(600)+1;
		e.addComponent(p);
		RotationComponent r = new RotationComponent();
		r.rotation = (float) rng.nextInt(360)+1;
		e.addComponent(r);
		DisplayComponent d = new DisplayComponent();
		d.x = p.x; d.y = p.y; d.rotation = r.rotation;
		e.addComponent(d);
		return e;
	}
}
