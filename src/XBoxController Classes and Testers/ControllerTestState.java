import net.java.games.input.Component.Identifier;

import org.lwjgl.LWJGLException;
import org.lwjgl.input.Controller;
import org.lwjgl.input.Controllers;
import org.lwjgl.input.Mouse;
import org.newdawn.slick.ControlledInputReciever;
import org.newdawn.slick.ControllerListener;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.InputListener;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.command.Control;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class ControllerTestState extends BasicGameState{

	Input input;
	private String s = "";
	private String a = "";
	private String l = "";
	private int y;
	private int x;
	
	Rectangle r1;
	int xpos,ypos;
	Rectangle r2;
	
	public Menu(int state){
	}
	
	@Override
	public void init(GameContainer container, StateBasedGame game)
			throws SlickException {
		input = container.getInput();		
		input.initControllers();
		xpos = ypos = 100;
		r1 = new Rectangle(xpos,ypos,50,100);
		r2 = new Rectangle(500,500,100,100);
		//input.addControllerListener(this);

	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g)
			throws SlickException {
		g.drawString("This is a test: Read/Watch Tutorials and Look at other games", 50,50);
		g.drawRect(50,100,60,120);
		g.draw(r1);
		g.draw(r2);
		Image image = new Image("res/one.png");
		g.drawImage(image, 200,200);
		g.drawString(s, 100,100);
		g.drawString("Controller Y Direction" + a, 100,200);
		g.drawString("Controller X Direction " + l , 100,300);
		g.drawString(""+input.getAxisValue(0, 0)+ input.getAxisValue(0, 1),100,400);
		g.drawString("X,Y = (" + x + ", " + y + ")",100,500);
        
        
        
		
	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta)
			throws SlickException {
		 if(input.isControllerLeft(Input.ANY_CONTROLLER) && input.getAxisValue(0, 0) > -0.75 && input.getAxisValue(0, 0) < 0.75) {
	          x--;
	          xpos--;
	         }
	       
	        if(input.isControllerRight(Input.ANY_CONTROLLER)  && input.getAxisValue(0, 0) > -0.75 && input.getAxisValue(0, 0) < 0.75) {
	          x++;
	          xpos++;
	         }
	        if(input.isControllerUp(Input.ANY_CONTROLLER)  && input.getAxisValue(0, 1) > -0.75 && input.getAxisValue(0, 1) < 0.75) {
	            y--;
	            ypos--;
	         }
	         
	        if(input.isControllerDown(Input.ANY_CONTROLLER) && input.getAxisValue(0, 1) > -0.75 && input.getAxisValue(0, 1) < 0.75) {
	            y++;
	            ypos++;
	         }
	        if(input.isButtonPressed(7, 0)){
	        	game.enterState(1);
	        }
	        r1.setLocation(xpos, ypos);

	}

	@Override
	public int getID() {
		return 0;
		
	}
	
	@Override
	public void controllerButtonPressed(int controller, int button){
		s  = "Controller = " + controller + "button: " + button; 
	}
	
	@Override
	public void controllerDownPressed(int controller){
		a = "Down";
	}
	@Override
	public void controllerDownReleased(int controller){
		a = "";
	}
	@Override
	public void controllerUpPressed(int controller){
		a = "Up";
	}
	@Override
	public void controllerUpReleased(int controller){
		a = "";
	}
	@Override
	public void controllerLeftPressed(int controller){
		l = "Left";
	}
	@Override
	public void controllerLeftReleased(int controller){
		l = "";
	}
	@Override
	public void controllerRightPressed(int controller){
		l = "Right";
	}
	@Override
	public void controllerRightReleased(int controller){
		l = "";
	}
}
