

import java.io.IOException;

import org.lwjgl.Sys;
import org.newdawn.slick.Animation;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class TitleState extends BasicGameState
{
	
	Image images[] = new Image[2];
	Animation anime = new Animation();
	ControllerManager controllerManager;
	int stateID = -1;
	Image background = null;
	World world;
	private String message;
	
	
	public TitleState(int stateID, ControllerManager controllerManager, World world){
		this.stateID = stateID;
		this.controllerManager = controllerManager;
		this.world = world;
		message = "";
		try {
			background = new Image("assets/Art/title_screen.png");
		} catch (SlickException e) {
			Sys.alert("Could not open", "Missing Image");
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
    	//g.setColor(Color.black);
    	//g.drawString("Press Enter to Start", gc.getWidth()/2 - 100, gc.getHeight()/2);
    	//g.setColor(Color.white);
    	//g.drawString("Press 2 - 4 to Set Number of Players", 50, 550);
    	//g.drawString(message, 50,600);
    	
    }
 
    public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
    	Input input = new Input(delta);
    	/**if(input.isKeyDown(Input.KEY_2)){
    		world.setNumberOfPlayers(2);
    		message = "Number of Players Set to 2"; 
    	}
    	if(input.isKeyDown(Input.KEY_3)){
        	world.setNumberOfPlayers(3);
        	message = "Number of Players Set to 3"; 
    	}
        if(input.isKeyDown(Input.KEY_4)){
           	world.setNumberOfPlayers(4);
           	message = "Number of Players Set to 4"; 

    	}**/
    	if(input.isKeyDown(Input.KEY_ENTER)){
    		sbg.enterState(DisplayManager.HOWTOSTATE);
    	}
    	/**for(int i = 0; i < controllerManager.getControllerCount(); i++){
    		if(controllerManager.getController(i).isButtonPressed(Button.A.buttonID)){
    			sbg.enterState(DisplayManager.GAMEPLAYSTATE);
    		}
    	}**/
    	
    }
}
