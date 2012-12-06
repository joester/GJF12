

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;


public class DisplayManager extends StateBasedGame
{
	
	public static final int TITLESTATE = 0;
	public static final int HOWTOSTATE = 1;
	public static final int CHARACTERSELECTSTATE = 2;
	public static final int GAMEPLAYSTATE = 3;
	public static final int PAUSESTATE = 4;
	public static final int WINNERDISPLAYSTATE = 5;
	public static final int CREDITS = 6;
	
	
	private ControllerManager controllerManager;
	private World world;
	
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
		}catch(Exception e){
		}
	}
	
	
	
	
	
	@Override
	public void initStatesList(GameContainer gc) throws SlickException
	{
		world = new World(controllerManager);
		this.addState(new TitleState(DisplayManager.TITLESTATE, controllerManager, world));
		this.addState(new HowToState(DisplayManager.HOWTOSTATE, controllerManager, world));
		this.addState(new CharacterSelectState(DisplayManager.CHARACTERSELECTSTATE, controllerManager, world));
		this.addState(new GameplayState(DisplayManager.GAMEPLAYSTATE, controllerManager, world));
		this.addState(new PauseState(DisplayManager.PAUSESTATE, controllerManager, world));
		this.addState(new WinnerDisplayState(DisplayManager.WINNERDISPLAYSTATE, controllerManager, world));
		this.addState(new CreditsState(DisplayManager.CREDITS, controllerManager, world));
		
	}
	
	

	

	
}
