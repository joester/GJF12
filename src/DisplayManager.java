
import java.awt.Rectangle;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;




public class DisplayManager extends StateBasedGame
{
	
	public static final int MAINMENUSTATE = 0;
	public static final int GAMEPLAYSTATE = 1;
	public static final int PAUSESTATE = 2;
	
	
	public DisplayManager() throws SlickException{
		super("A string");
		
	}
	
	public static void main(String args[]) throws SlickException{
		AppGameContainer app = 
			new AppGameContainer(new DisplayManager());
		app.setDisplayMode(640, 480, false);
		app.setTargetFrameRate(60);
		app.start();
	}

	

	@Override
	public void initStatesList(GameContainer gc) throws SlickException
	{
		this.addState(new MainMenu(MAINMENUSTATE));
		this.addState(new GameplayState(GAMEPLAYSTATE));
		this.addState(new PauseState(PAUSESTATE));
		
	}
}
