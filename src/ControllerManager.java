import java.util.HashMap;

import org.lwjgl.LWJGLException;
import org.lwjgl.input.Controller;
import org.lwjgl.input.Controllers;



public class ControllerManager{
	private boolean[][] buttonWasPressed;
	private final float DEFAULT_DEAD_ZONE = .75f;

	public ControllerManager(){
		try {
			Controllers.create();
			for(int i = 0; i < Controllers.getControllerCount(); i++){
				Controller c = Controllers.getController(i);
				c.setXAxisDeadZone(DEFAULT_DEAD_ZONE);
				c.setYAxisDeadZone(DEFAULT_DEAD_ZONE);
			}
			//for each button for each controller
			buttonWasPressed = new boolean[getControllerCount()][Controllers.getController(0).getButtonCount()];
			//c = controller index
			for(int c = 0; c < buttonWasPressed.length; c++){ 
				//b = buttonID index
				for(int b = 0; b < buttonWasPressed[0].length; b++){ 
					buttonWasPressed[c][b] = false;
				}
			}
		} catch (LWJGLException e) {
			e.printStackTrace();
		}

	}	

	public void createControllers(){
		Controllers.destroy();
		try {
			Controllers.create();
			for(int i = 0; i < Controllers.getControllerCount(); i++){
				Controller c = Controllers.getController(i);
				c.setXAxisDeadZone(.75f);
				c.setYAxisDeadZone(.75f);
			}
		} catch (LWJGLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void setDeadZones(float xDeadZone, float yDeadZone){
		for(int i = 0; i < Controllers.getControllerCount(); i++){
			Controller c = Controllers.getController(i);
			c.setXAxisDeadZone(xDeadZone);
			c.setYAxisDeadZone(yDeadZone);
		}
	}

	public void pollControllers(){
		for(int i = 0; i < Controllers.getControllerCount(); i++){
			Controller c = Controllers.getController(i);
			c.poll();
		}
	}

	public ControllerEvent getInput(int controllerIndex){
		Controller c = Controllers.getController(controllerIndex);
		ControllerEvent ce = new ControllerEvent();
		if(ce.getXAxisValue() < -0.75 && ce.getYAxisValue() > -0.75 && ce.getYAxisValue() < 0.75) {
			ce.setDirection(Direction.LEFT);
		}

		if(ce.getXAxisValue() > 0.75 && ce.getYAxisValue() > -0.75 && ce.getYAxisValue() < 0.75) {
			ce.setDirection(Direction.RIGHT);

		}
		if(ce.getYAxisValue() < -0.75 && ce.getXAxisValue() > -0.75 && ce.getXAxisValue() < 0.75) {
			ce.setDirection(Direction.UP);

		}
		if(ce.getYAxisValue() > 0.75 && ce.getXAxisValue() > -0.75 && ce.getXAxisValue() < 0.75) {
			ce.setDirection(Direction.DOWN);

		}
		for(Button button: Button.values()){
			if(c.isButtonPressed(button.buttonID)){
				if(!buttonWasPressed[controllerIndex][button.buttonID]){
					ce.addButtonState(button,true);
					buttonWasPressed[controllerIndex][button.buttonID] = true;
				}
			}
			else{
				buttonWasPressed[controllerIndex][button.buttonID] = false;
			}
		}
		return ce;
	}

	public Controller getController(int index){
		return Controllers.getController(index);
	}

	public int getControllerCount(){
		return Controllers.getControllerCount();
	}




}