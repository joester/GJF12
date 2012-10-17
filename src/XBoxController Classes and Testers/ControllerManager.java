import org.lwjgl.LWJGLException;
import org.lwjgl.input.*;

public class ControllerManager{
	public boolean wasPressed[][];
	public ControllerManager(){

	}

	public void addControllers(){
		Controllers.destroy();
		try {
			Controllers.create();
			wasPressed = new boolean[Controllers.getControllerCount()][17];
		} catch (LWJGLException e) {
			e.printStackTrace();
		}
	}
	
	public void setDeadZone(Controller c, float x, float y){
		if(c == null){
			return;
		}
		c.setXAxisDeadZone(x);
		c.setYAxisDeadZone(y);
	}
	
	public ControllerEvent getEvent(int index){
		Controller c = Controllers.getController(index);
		if(c == null){
			return null;
		}
		c.poll();
		ControllerEvent event = new ControllerEvent();
		for(Button button: Button.values()){
			if(c.isButtonPressed(button.buttonID) && !wasPressed[index][button.buttonID]){
				event.setButton(button);
				wasPressed[index][button.buttonID] = true;
			}
			if(!c.isButtonPressed(button.buttonID) && wasPressed[index][button.buttonID]){
				wasPressed[index][button.buttonID] = false;
			}
		}
		event.setXAxis(c.getXAxisValue());
		event.setYAxis(c.getYAxisValue());
		return event;
	}	
}