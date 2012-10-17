import org.lwjgl.input.Controller;
import org.lwjgl.input.Controllers;




public class ControllerTest{
	public static void main(String[] args){
		
		Controller controller = null;
		Controllers.create();
		for(int i = 0; i < Controllers.getControllerCount(); i++){
			if(Controllers.getController(i).getName() == "Controller (XBOX 360 For Windows)")
				controller = Controllers.getController(i);
		}
		System.out.println(controller);
		
		if(controller != null){
			for(Controller c : controller.getComponents()){
				System.out.println(c);
			}
		}
		else{
			System.out.println("Controller is null");
		}
		
		//ControllerListener cl = new XBOXListener(controller);
		while(true){
			for(Component c : controller.getComponents()){
				if(c.getIdentifier() instanceof Component.Identifier.Button){
					controller.g
				}
			}
		}
	}
	
	public class XBoxListener implements ControllerListener{
		Controller controller;
		public void controllerAdded(ControllerEvent arg0) {
			controller = arg0.getController();
			this.
		}
		

		@Override
		public void controllerRemoved(ControllerEvent arg0) {
			controller = null;
		}		
	}
	
}