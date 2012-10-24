

import java.io.IOException;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class HowToState extends BasicGameState
{
	
	Image images[] = new Image[2];
	Animation anime = new Animation();
	ControllerManager controllerManager;
	int stateID = -1;
	Image background = null;
	GameWorld gW;
	private String message;
	
	
	public HowToState(int stateID, ControllerManager controllerManager){
		this.stateID = stateID;
		this.controllerManager = controllerManager;
		message = "";
		gW = new GameWorld(controllerManager);
		try {
			background = new Image("assets/Art/howto.png");
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public int getID(){
		return stateID;
	}
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		 
    }
 
    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
    	if(background != null)
    		background.draw();  
    	g.setColor(Color.white);
    	g.drawString("Press Space to Start", 50,50);
    	
    }
 
    public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
    	Input input = new Input(delta);
    	GameplayState gps = (GameplayState) sbg.getState(GameRunner.GAMEPLAYSTATE);
    	gW = gps.getGameWorld();
    	if(input.isKeyDown(Input.KEY_SPACE)){
    		try {
				gW.init();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    		sbg.enterState(GameRunner.GAMEPLAYSTATE);
    	}
    	/**for(int i = 0; i < controllerManager.getControllerCount(); i++){
    		if(controllerManager.getController(i).isButtonPressed(Button.A.buttonID)){
    			sbg.enterState(DisplayManager.GAMEPLAYSTATE);
    		}
    	}**/
    	
    }
}
