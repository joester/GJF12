

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.GameState;
import org.newdawn.slick.state.StateBasedGame;


public class DisplayManager extends StateBasedGame
{
	
	public static final int MAINMENUSTATE = 0;
	public static final int HOWTOSTATE = 1;
	public static final int GAMEPLAYSTATE = 2;
	public static final int PAUSESTATE = 3;
	public static final int WINNERDISPLAYSTATE = 4;
	public static final int CREDITS = 5;
	
	private ControllerManager controllerManager;
	
	//Builds the actual display for the game.

	
	public static void main(String args[])throws SlickException{
		AppGameContainer app = 
			new AppGameContainer(new DisplayManager("Chibi Fighters"));
		app.setDisplayMode(1280, 720, false);
		app.setTargetFrameRate(60);
		app.start();
	}
	
	public DisplayManager(String str) throws SlickException{
		super(str);

		try{
			controllerManager = new ControllerManager();
			controllerManager.createControllers();
			
		}catch(Exception e){
		}
	}
	
	
	
	
	
	@Override
	public void initStatesList(GameContainer gc) throws SlickException
	{
		this.addState(new MainMenu(DisplayManager.MAINMENUSTATE,controllerManager));
		this.addState(new HowToState(DisplayManager.HOWTOSTATE,controllerManager));
		this.addState(new GameplayState(DisplayManager.GAMEPLAYSTATE,controllerManager));
		this.addState(new PauseState(DisplayManager.PAUSESTATE,controllerManager));
		this.addState(new WinnerDisplayState(DisplayManager.WINNERDISPLAYSTATE,controllerManager));
		this.addState(new CreditsState(DisplayManager.CREDITS,controllerManager));
	}
	
	

	

	
}
