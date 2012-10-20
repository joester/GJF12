package gamejam.input;
import java.util.ArrayList;
import java.util.List;

public class ControllerEvent {
	private List<Boolean> buttonStates;
	private Button button;
	private float xAxisValue;
	private float yAxisValue;
	
	public ControllerEvent(Button button, float xAxisValue, float yAxisValue){
		this.button = button;
		this.xAxisValue = xAxisValue;
		this.yAxisValue = yAxisValue;
	}
	
	public ControllerEvent(List<Boolean> buttonStates, float xAxisValue, float yAxisValue){
		this.buttonStates = buttonStates;
		this.xAxisValue = xAxisValue;
		this.yAxisValue = yAxisValue;
	}
	
	public ControllerEvent() {
		buttonStates = new ArrayList<Boolean>();
	}

	public void setButton(Button button) {
		this.button = button;
	}
	public void setXAxis(float xAxisValue) {
		this.xAxisValue = xAxisValue;
	}
	public void setYAxis(float yAxisValue) {
		this.yAxisValue = yAxisValue;		
	}
	public float getXAxisValue() {
		return xAxisValue;
	}
	public float getYAxisValue() {
		return yAxisValue;
	}
	
	public Button getButton() {
		return button;
	}
	
	public List<Boolean> getButtonStates(){
		return buttonStates;
	}

	public void addButtonState(Button button, boolean buttonPressed) {
		while(buttonStates.size() < button.buttonID){
			buttonStates.add(null);
		}
		buttonStates.add(button.buttonID, buttonPressed);
	}
	
	public boolean getButtonState(Button button){
		return buttonStates.get(button.buttonID);
	}
}
