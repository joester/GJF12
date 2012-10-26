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
	private GameWorld gW;
	public WinnerDisplayState(int stateID,
			ControllerManager controllerManager, GameWorld gW) {
		this.controllerManager = controllerManager;
		this.stateID  = stateID;
		this.gW = gW;
	}

	public void setWinner(int winnerID){
		this.winner = winnerID;
		winningPlayer = players.get(winner);
		gW = winningPlayer.gW;
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
		if(gW.getMap() instanceof IceMap)
			g.setColor(Color.black);
		else
			g.setColor(Color.white);
		if(getBackground() != null)
			getBackground().draw();

		if(winningPlayer != null){
			if(winningPlayer.wins >= gW.winsNeeded){
				g.drawString("Player " + (winner + 1) + " Won!", gc.getWidth()/2 - 75, gc.getHeight()/2 - 100);
				g.drawString("Press Enter to Continue", gc.getWidth()/2 - 125, gc.getHeight()/2 + 100);
			}
			else{
				g.drawString("Player " + (winner + 1)  + " wins!", gc.getWidth()/2 - 75, gc.getHeight()/2 - 100);
				g.drawString("Press Enter to Start Next Round", gc.getWidth()/2 - 125, gc.getHeight()/2 + 100);
			}
		}
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta)
			throws SlickException {
		Input input = new Input(delta);

		if(input.isKeyDown(Input.KEY_ENTER)){
			if(winningPlayer.wins >= winningPlayer.gW.winsNeeded){
				winningPlayer.gW.getBGM().stop();
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
