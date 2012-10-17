public class ControllerManagerTester {
	public static void main(String[] args){
		ControllerManager cm = new ControllerManager();
		cm.addControllers();
		try {
			while (true) {
				ControllerEvent ce = cm.getEvent(0);
				System.out.println("X: " + ce.getXAxisValue() + " Y: " + ce.getYAxisValue() + " Button: " + ce.getButton());
				Thread.sleep(500L);
			}
		} catch (InterruptedException iex) {}
	}	
}

