import java.io.IOException;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class GameplayState extends BasicGameState {
	
	int stateID = -1;
	
	Character player = null;
	Character player2 = null;
	GameWorld gw = null;
	Image[] uiImages = new Image[4];
	int inLine;
	int uiIntervals;

	private ControllerManager controllerManager;
	
	
	public GameplayState(int stateID, GameWorld gw, ControllerManager controllerManager){
		this.stateID = stateID;
		this.gw = gw;
		this.controllerManager = controllerManager;
	}

	
	
	//Collision Testing
	
	
	@Override
	public void init(GameContainer gc, StateBasedGame sbg)
		throws SlickException
	{
		try
		{
			gw.init();
		}
		catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		gw.init(gc);
		uiImages[0] = new Image("assets/p1state.png");
		uiImages[1] = new Image("assets/p2state.png");
		uiImages[2] = new Image("assets/p3state.png");
		uiImages[3] = new Image("assets/p4state.png");
		inLine = gc.getHeight() - uiImages[0].getHeight() + 16;
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g)
		throws SlickException
	{
		gw.render(gc, g);
		
		int dist = gc.getWidth() / 8;
		uiIntervals = dist * 2;
		
		for(int i = 0; i < 4; i ++){
			uiImages[i].draw(dist - 84, inLine, (float).75);
			g.drawString((String)gw.listOfCharacters.get(i).displayHP(),
				dist + (uiImages[i].getWidth() * 3 / 4) - 84, inLine + 16);
			dist += uiIntervals;
		}
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException
	{
		
		Input input = new Input(delta);
		if(input.isKeyDown(Input.KEY_P)){
			sbg.enterState(DisplayManager.PAUSESTATE);
		}
		
		try
		{
			gw.update(gc, delta);
		}
		catch (InterruptedException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

	@Override
	public int getID()
	{
		// TODO Auto-generated method stub
		return stateID;
	}

}
