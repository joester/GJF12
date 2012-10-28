import org.lwjgl.LWJGLException;
import org.lwjgl.input.Controller;
import org.lwjgl.input.Controllers;



public class ControllerManager{
	private boolean[][] buttonWasPressed;
	private final float DEFAULT_DEAD_ZONE = .75f;

	public ControllerManager(){
	}	

	public void createControllers(){
		Controllers.destroy();
		try {
			Controllers.create();
			for(int i = 0; i < Controllers.getControllerCount(); i++){
				Controller c = Controllers.getController(i);
				c.setXAxisDeadZone(DEFAULT_DEAD_ZONE);
				c.setYAxisDeadZone(DEFAULT_DEAD_ZONE);
			}
			buttonWasPressed = new boolean[getControllerCount()][Controllers.getController(0).getButtonCount()];
			//c = controller index
			for(int c = 0; c < buttonWasPressed.length; c++){ 
				//b = buttonID index
				for(int b = 0; b < buttonWasPressed[0].length; b++){ 
					buttonWasPressed[c][b] = false;
				}
			}
		} catch (LWJGLException e) {
			// TODO Auto-generated catch block
			//Sys.alert("Something went wrong!", e.getMessage());
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
		if(c.getXAxisValue() < -0.75 && c.getYAxisValue() > -0.75 && c.getYAxisValue() < 0.75) {
			ce.setDirection(Direction.LEFT);
		}
		else if(c.getXAxisValue() > 0.75 && c.getYAxisValue() > -0.75 && c.getYAxisValue() < 0.75) {
			ce.setDirection(Direction.RIGHT);
		}
		else if(c.getYAxisValue() < -0.75 && c.getXAxisValue() > -0.75 && c.getXAxisValue() < 0.75) {
			ce.setDirection(Direction.UP);
		}
		else if(c.getYAxisValue() > 0.75 && c.getXAxisValue() > -0.75 && c.getXAxisValue() < 0.75) {
			ce.setDirection(Direction.DOWN);
		}
		for(Button button: Button.values()){
			if(c.isButtonPressed(button.buttonID)){
				if(!buttonWasPressed[controllerIndex][button.buttonID]){
					ce.addButtonState(button, true);
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