

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
	ControllerManager controllerManager;
	int stateID = -1;
	Image background = null;
	
	
	
	public MainMenu(int stateID, ControllerManager controllerManager){
		this.stateID = stateID;
		this.controllerManager = controllerManager;
	}
	
	public int getID(){
		return stateID;
	}
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		 background = new Image("/assets/background.jpg");
    }
 
    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
    	background.draw();
    	
    }
 
    public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
    	Input input = new Input(delta);
    	
    	if(input.isKeyDown(Input.KEY_ENTER)){
    		sbg.enterState(DisplayManager.GAMEPLAYSTATE);
    	}
    	/**for(int i = 0; i < controllerManager.getControllerCount(); i++){
    		if(controllerManager.getController(i).isButtonPressed(Button.A.buttonID)){
    			sbg.enterState(DisplayManager.GAMEPLAYSTATE);
    		}
    	}**/
    	
    }
}
