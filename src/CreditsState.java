import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;


public class CreditsState extends BasicGameState{
	private ControllerManager controllerManager;
	private int stateID;
	private Sound BGM;
	private Image background;
	private World world;
	
	public CreditsState(int stateID, ControllerManager controllerManager, World world) {
		this.controllerManager = controllerManager;
		this.stateID  = stateID;
		this.world = world;
	}
	@Override
	public void init(GameContainer arg0, StateBasedGame arg1)
			throws SlickException {
		BGM = new Sound("assets/SFX/music/OurDestiny.wav");
		background = new Image("assets/Art/credits.png");
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g)
			throws SlickException {
		background.draw();
		g.setColor(Color.white);
		g.drawString("Press Space to Return to Main Menu",gc.getWidth()/2 - 150, gc.getHeight() - 100);
		//g.drawString("Credits Music 'Our Destiny' by Archie Monji",gc.getWidth()/2 - 200, gc.getHeight() - 50);
		
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta)
			throws SlickException {
		Input input = new Input(delta);
		if(!BGM.playing())
			BGM.play();
		
		if(input.isKeyDown(Input.KEY_SPACE)){
			BGM.stop();
			sbg.enterState(DisplayManager.TITLESTATE);
		}
		
	}

	@Override
	public int getID() {
		// TODO Auto-generated method stub
		return stateID;
	}

}
