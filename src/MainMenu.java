import org.newdawn.slick.Animation;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class MainMenu extends BasicGameState
{
	
	Image images[] = new Image[2];
	Animation anime = new Animation();
	
	int stateID = -1;
	Image background = null;
	InputButton startGameOption = null;
	InputButton exitOption = null;
	
	float startGameScale = 1;
	float exitScale = 1;
	
	public MainMenu(int stateID){
		this.stateID = stateID;
	}
	
	public int getID(){
		return stateID;
	}
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		 background = new Image("/assets/background.jpg");
		 //Image spst = new Image("/res/submitbuttonScreenshot.png");
		 //SpriteSheet sheet = new SpriteSheet(spst, 105, 67);
		 //anime = new Animation(sheet, 1);
		 //startGameOption = new InputButton(0, 0, anime.getImage(0), gc);
		 //exitOption = new InputButton(0, 0, anime.getImage(1), gc);
		 //startGameOption.setXpos();
		 //exitOption.setXpos();
    }
 
    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
    	background.draw(0, 0, 1);
    	/*
    	startGameOption.getImage().draw(startGameOption.xPos, 
    		gc.getHeight() / 3, 1);
    	exitOption.getImage().draw(exitOption.xPos,
    		2 * gc.getHeight()/ 3, 1);
    		*/
    	
    }
 
    public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
    	Input input = new Input(delta);
    	
    	if(input.isKeyDown(Input.KEY_ENTER)){
    		sbg.enterState(DisplayManager.GAMEPLAYSTATE);
    	}
    	/*
    	if(startGameOption.withinBounds(input)){
    		if(input.isMouseButtonDown(Input.MOUSE_LEFT_BUTTON)){
    			sbg.enterState(DisplayManager.GAMEPLAYSTATE);
    		}
    	}
    	if(exitOption.withinBounds(input)){
    		if(input.isMouseButtonDown(Input.MOUSE_LEFT_BUTTON)){
    			System.exit(0);
    		}
    	}
    	*/
    }
}
