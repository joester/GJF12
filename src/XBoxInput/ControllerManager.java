import org.lwjgl.LWJGLException;
import org.lwjgl.input.Controller;
import org.lwjgl.input.Controllers;



public class ControllerManager{
	public ControllerManager(){
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
		ce.setXAxis(c.getXAxisValue());
		ce.setYAxis(c.getYAxisValue());
		for(Button button: Button.values()){
			ce.addButtonState(button,c.isButtonPressed(button.buttonID));
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