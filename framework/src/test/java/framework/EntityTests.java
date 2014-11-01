package framework;

import static org.junit.Assert.*;

import java.util.Random;

import org.gdesign.game.engine.framework.core.Box;
import org.gdesign.game.engine.framework.core.Entity;
import org.gdesign.game.engine.framework.core.EntityManager;
import org.gdesign.game.engine.framework.core.World;
import org.gdesign.game.engine.framework.core.components.DisplayComponent;
import org.gdesign.game.engine.framework.core.components.PositionComponent;
import org.gdesign.game.engine.framework.core.components.VelocityComponent;
import org.gdesign.game.engine.framework.systems.EntityMoveSystem;
import org.gdesign.game.engine.framework.systems.EntityRenderSystem;
import org.junit.Test;
import org.lwjgl.LWJGLException;
import org.lwjgl.Sys;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;

public class EntityTests {
	long lastFrame;
	Random rng = new Random();
	EntityManager man = new EntityManager();
	
	@Test
	public void EntityComponentsTest() {
		Entity allCompTestEntity = createEntity(false);
		System.out.println(allCompTestEntity.toString());
		assertNotNull(allCompTestEntity.getComponent(PositionComponent.class));
		assertNotNull(allCompTestEntity.getComponent(DisplayComponent.class));
		allCompTestEntity.removeComponent(allCompTestEntity.getComponent(PositionComponent.class));
	}
	
	@Test
	public void SingleEntityTest(){
		man.addEntity(createBox(false));
		World w = new World();
		w.setDelta(getDelta());
		
		EntityRenderSystem entityRenderer = new EntityRenderSystem();
		EntityMoveSystem entityMover = new EntityMoveSystem();
		entityMover.setWorld(w);
		
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
		    w.setDelta(getDelta());
		    
		    for (Entity e : man.getEntities()) entityMover.process(e);
		    for (Entity e : man.getEntities()) entityRenderer.process(e);
		    
		    Display.update();
		    Display.sync(60);
		}
	 
		Display.destroy();	
	}
	
	@Test
	public void EntityManagerTest(){
		for (int i=1; i <= 20; i++){
			Box b = createBox(false);
			man.addEntity(b);
		}
	
		World w = new World();
		w.setDelta(getDelta());
		EntityRenderSystem entityRenderer = new EntityRenderSystem();
		EntityMoveSystem entityMover = new EntityMoveSystem();
		entityMover.setWorld(w);
		
		
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
		    w.setDelta(getDelta());
		    
		    for (Entity e : man.getEntities()) entityMover.process(e);
		    for (Entity e : man.getEntities()) entityRenderer.process(e);
		    
		    Display.update();
		}
	 
		Display.destroy();	
	}

	Entity createEntity(boolean randomMode){
		Entity e = new Entity();
		PositionComponent p = new PositionComponent((float) rng.nextInt(750)+11,(float) rng.nextInt(550)+11);
		e.addComponent(p);
		DisplayComponent d = new DisplayComponent(p.x,p.y,0);
		e.addComponent(d);
		return e;
	}
	
	Box createBox(boolean randomMode){
		Box e = new Box();
		PositionComponent p = new PositionComponent((float) rng.nextInt(750)+11,(float) rng.nextInt(550)+11);
		e.addComponent(p);
		e.addComponent(new VelocityComponent(0,0));
		e.addComponent(new DisplayComponent(p.x,p.y,0));
		return e;
	}
	
	public int getDelta() {
	    long time =  (Sys.getTime() * 1000) / Sys.getTimerResolution();
	    int delta = (int) (time - lastFrame);
	    lastFrame = time;	 
	    return delta;
	}
	

}
