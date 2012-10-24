import java.util.ArrayList;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
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
