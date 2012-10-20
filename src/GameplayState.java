

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class GameplayState extends BasicGameState {
	
	int stateID = -1;
	
	Character player = null;
	Character player2 = null;
	GameWorld gw = null;
	
	public GameplayState(int stateID){
		this.stateID = stateID;
	}

	
	public void setGameWorld(GameWorld gw){
		this.gw = gw;
	}
	
	//Collision Testing
	
	
	@Override
	public void init(GameContainer gc, StateBasedGame sbg)
		throws SlickException
	{
		player = new Character(30, 30, "/assets/jump-spritesheet.png", "name", 4, 4, 1);
		player2 = new Character(60, 60, "/assets/stand-spritesheet.png", "name", 4, 3, 1);
		player.init(gc);
		player.setControls(Input.KEY_A, Input.KEY_W, Input.KEY_D, Input.KEY_S);
		player2.init(gc);
		player2.setControls(Input.KEY_LEFT, Input.KEY_UP, Input.KEY_RIGHT, Input.KEY_DOWN);
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g)
		throws SlickException
	{
		player.render(gc, g);
		player2.render(gc, g);
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta)
		throws SlickException
	{
		
		Input input = new Input(delta);
		if(input.isKeyDown(Input.KEY_P)){
			sbg.enterState(DisplayManager.PAUSESTATE);
		}
		
		gw.update(gc, delta);
		
		
		
		
	}
	

	@Override
	public int getID()
	{
		// TODO Auto-generated method stub
		return stateID;
	}

}
