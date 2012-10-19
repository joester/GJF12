import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class GameplayState extends BasicGameState {
	
	int stateID = -1;
	
	Character player = null;
	
	public GameplayState(int stateID){
		this.stateID = stateID;
	}

	
	//Collision Testing
	
	@Override
	public void init(GameContainer gc, StateBasedGame sbg)
		throws SlickException
	{
		player = new Character("Bob", 5, 100);
		player.renderChar("/res/Untitled.png", 128, 128);
		
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g)
		throws SlickException
	{
		float x = player.x;
		if(player.hasDX){
			player.getAnimation().draw(player.x, player.y, 64, 64);
		}
		else{
			player.getAnimation().getImage(0).draw(player.x, player.y, 64, 64);
		}
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta)
		throws SlickException
	{
		
		Input input = new Input(delta);
		if(input.isKeyDown(Input.KEY_P)){
			sbg.enterState(DisplayManager.PAUSESTATE);
		}
		if(input.isKeyDown(Input.KEY_D)){
			player.setX(1, delta);
			player.setMove(true);
		}
		if(input.isKeyDown(Input.KEY_A)){
			player.setX(-1, delta);
			player.setMove(true);
		}
		else{
			player.setMove(false);
		}
		
	}

	@Override
	public int getID()
	{
		// TODO Auto-generated method stub
		return stateID;
	}
}
