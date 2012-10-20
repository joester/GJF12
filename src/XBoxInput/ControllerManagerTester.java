import java.util.ArrayList;

import net.java.games.input.Component.Identifier;

import org.lwjgl.LWJGLException;
import org.lwjgl.input.Controller;
import org.lwjgl.input.Controllers;
import org.lwjgl.input.Mouse;
import org.newdawn.slick.ControlledInputReciever;
import org.newdawn.slick.ControllerListener;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.InputListener;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.command.Control;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class ControllerManagerTester extends BasicGameState{
	ControllerManager cm;
	ArrayList<DummyCharacter> dummies;
	public ControllerManagerTester(int state){
	}
	
	@Override
	public void init(GameContainer container, StateBasedGame game)
			throws SlickException {
		cm = new ControllerManager();
		dummies = new ArrayList<DummyCharacter>();
		//container.getInput().initControllers();
		for(int i = 0; i < cm.getControllerCount(); i++){
			dummies.add(new DummyCharacter(i));
		}
		//dummies.add(new DummyCharacter(1));
		
	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g)
			throws SlickException {
		for(DummyCharacter c: dummies){
			c.render(container,g);
		}
		
	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta)
			throws SlickException {
		cm.pollControllers();
		for(int i = 0; i < cm.getControllerCount(); i++){	
			dummies.get(i).update(container, delta, cm.getInput(i));}
		//for(DummyCharacter c: dummies){			
		//	c.update(container, delta);
		//}
	}

	@Override
	public int getID() {
		return 0;
		
	}
}
