import java.util.List;

import org.lwjgl.Sys;
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
	public WinnerDisplayState(int stateID,
			ControllerManager controllerManager) {
		this.controllerManager = controllerManager;
		this.stateID  = stateID;
	}

	public void setWinner(int winnerID){
		this.winner = winnerID;
	}
	public void setPlayerList(List<Character> players){
		this.players = players;
	}

	@Override
	public void init(GameContainer arg0, StateBasedGame arg1)
			throws SlickException {
		// TODO Auto-generated method stub

	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g)
			throws SlickException {
		Character winningPlayer = players.get(winner);
		GameWorld gW = winningPlayer.gW;
		Image background = winningPlayer.gW.background;
		if(gW.map instanceof IceMap)
			g.setColor(Color.black);
		else
			g.setColor(Color.white);
		background.draw();
		
		
		if(winningPlayer.wins >= gW.winsNeeded){
			g.drawString("Player " + (winner + 1) + " Won!", gc.getWidth()/2 - 75, gc.getHeight()/2 - 100);
			g.drawString("Press Enter to Continue", gc.getWidth()/2 - 125, gc.getHeight()/2 + 100);
		}
		else{
			g.drawString("Player " + (winner + 1)  + " wins!", gc.getWidth()/2 - 75, gc.getHeight()/2 - 100);
			g.drawString("Press Enter to Start Next Round", gc.getWidth()/2 - 125, gc.getHeight()/2 + 100);
		}
		



	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta)
			throws SlickException {
		Input input = new Input(delta);
		Character winningPlayer = players.get(winner);
		if(input.isKeyDown(Input.KEY_ENTER)){
			if(winningPlayer.wins >= winningPlayer.gW.winsNeeded){
				winningPlayer.gW.BGM.stop();
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

}
