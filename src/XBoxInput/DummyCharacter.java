

import java.util.ArrayList;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;


public class DummyCharacter {
	int x,y;
	int controllerID;
	private ArrayList<String> strings;
	public DummyCharacter(int i) {
		controllerID = i;
		strings = new ArrayList<String>();
	}
	public void update(GameContainer container, int delta, ControllerEvent controllerEvent)
			throws SlickException {
		
		if(controllerEvent.getXAxisValue() < -0.75 && controllerEvent.getYAxisValue() > -0.75 && controllerEvent.getYAxisValue() < 0.75) {
			x--;
		}

		if(controllerEvent.getXAxisValue() > 0.75 && controllerEvent.getYAxisValue() > -0.75 && controllerEvent.getYAxisValue() < 0.75) {
			x++;

		}
		if(controllerEvent.getYAxisValue() < 0.75 && controllerEvent.getYAxisValue() < -0.75 && controllerEvent.getYAxisValue() < 0.75) {
			y--;

		}
		if(controllerEvent.getYAxisValue() > 0.75 && controllerEvent.getXAxisValue() > -0.75 && controllerEvent.getXAxisValue() < 0.75) {
			y++;

		}
		strings = new ArrayList<String>();
		for(Button button: Button.values()){
			if(controllerEvent.getButtonState(button) != false){
				strings.add("ButtonID: " + button + " is Pressed");
			}
		}

	}
	public void render(GameContainer container, Graphics g)
			throws SlickException {
		g.draw(new Rectangle(x,y,100,100));
		int z = 0;
		for(String str: strings){
			g.drawString(str, 100, 100*z);
			z++;
			
		}
	}
}
