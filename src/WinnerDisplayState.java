import java.util.List;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;


public class WinnerDisplayState extends BasicGameState{
	private ControllerManager controllerManager;
	private int stateID = -1;
	private int winner;
	private List<Character> players;
	private Image background;
	private Character winningPlayer;
	private World world;
	public WinnerDisplayState(int stateID,
			ControllerManager controllerManager, World world) {
		this.controllerManager = controllerManager;
		this.stateID  = stateID;
		this.world = world;
	}

	public void setWinner(int winnerID){
		this.winner = winnerID;
		winningPlayer = players.get(winner);
		world = winningPlayer.world;
	}
	public void setPlayerList(List<Character> players){
		this.players = players;
	}

	@Override
	public void init(GameContainer arg0, StateBasedGame arg1)
			throws SlickException {


	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g)
			throws SlickException {		
		if(world.getMap() instanceof IceMap)
			g.setColor(Color.black);
		else
			g.setColor(Color.white);
		if(getBackground() != null)
			getBackground().draw();

		if(winningPlayer != null){
			if(winningPlayer.wins >= world.winsNeeded){
				Image victoryMessage = new Image("assets/Art/p" + (winner + 1) + "wins.png");
				victoryMessage.draw(gc.getWidth()/2 - victoryMessage.getWidth()/2, gc.getHeight()/2 - 100);
				g.drawString("Press Enter to Continue", gc.getWidth()/2 - 125, gc.getHeight()/2 + 100);
			}
			else{
				Image victoryMessage = new Image("assets/Art/p" + (winner + 1) + "wins.png");
				victoryMessage.draw(gc.getWidth()/2 - victoryMessage.getWidth()/2, gc.getHeight()/2 - 100);
				g.drawString("Press Enter to Start Next Round", gc.getWidth()/2 - 125, gc.getHeight()/2 + 100);
			}
		}
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta)
			throws SlickException {
		Input input = new Input(delta);

		if(input.isKeyDown(Input.KEY_ENTER)){
			if(winningPlayer.wins >= winningPlayer.world.winsNeeded){
				winningPlayer.world.getBGM().stop();
				sbg.enterState(DisplayManager.CREDITS);
			}
			else{
				sbg.enterState(DisplayManager.GAMEPLAYSTATE);
			}

		}
	}

	@Override
	public int getID() {
		// TODO Auto-generated method stub
		return stateID;
	}

	public Image getBackground() {
		return background;
	}

	public void setBackground(Image background) {
		this.background = background;
	}

}
