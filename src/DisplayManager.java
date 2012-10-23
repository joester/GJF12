

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;


public class DisplayManager extends StateBasedGame
{
	
	public static final int MAINMENUSTATE = 0;
	public static final int GAMEPLAYSTATE = 1;
	public static final int PAUSESTATE = 2;
	private ControllerManager controllerManager;
	
	GameWorld gw;
	
	//Builds the actual display for the game.

	
	public static void main(String args[])throws SlickException{
		AppGameContainer app = 
			new AppGameContainer(new DisplayManager("Chibi Fighter"));
		app.setDisplayMode(1280, 720, false);
		app.setTargetFrameRate(60);
		app.start();
	}
	
	public DisplayManager(String str) throws SlickException{
		super(str);
		try{
			controllerManager = new ControllerManager();
			controllerManager.createControllers();
			gw =  new GameWorld(controllerManager);
		}catch(Exception e){
			gw =  new GameWorld(null);
		}
	}
	
	
	
	
	
	@Override
	public void initStatesList(GameContainer gc) throws SlickException
	{
		this.addState(new MainMenu(DisplayManager.MAINMENUSTATE,controllerManager));
		this.addState(new GameplayState(DisplayManager.GAMEPLAYSTATE, gw,controllerManager));
		this.addState(new PauseState(DisplayManager.PAUSESTATE,controllerManager));
		
	}

	

	
}
