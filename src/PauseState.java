import java.awt.Image;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;


public class PauseState extends BasicGameState
{
	
	Image pauseMenu = null;
	private final String ST_CONTINUE = "CONTINUE";
	int stateID = -1;
	
	public PauseState(int stateID){
		this.stateID = stateID;
	}

	@Override
	public void init(GameContainer gc, StateBasedGame sbg)
		throws SlickException
	{
		
		
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g)
		throws SlickException
	{
		g.drawString(ST_CONTINUE, gc.getWidth() / 2, gc.getHeight() / 3);
		
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta)
		throws SlickException
	{
		Input input = new Input(delta);
		
		if(input.isKeyDown(Input.KEY_ESCAPE)){
			sbg.enterState(DisplayManager.GAMEPLAYSTATE);
		}
		
	}

	@Override
	public int getID()
	{
		// TODO Auto-generated method stub
		return stateID;
	}

}
