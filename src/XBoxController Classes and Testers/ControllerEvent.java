public class ControllerEvent {
	private Button button;
	private float xAxisValue;
	private float yAxisValue;
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

	
}
