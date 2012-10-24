import java.io.IOException;

import org.lwjgl.Sys;
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
	Character player2 = null;
	GameWorld gW;
	Image[] uiImages = new Image[4];
	int inLine;
	int uiIntervals;

	private ControllerManager controllerManager;
	
	
	public GameplayState(int stateID, ControllerManager controllerManager){
		this.stateID = stateID;
		this.controllerManager = controllerManager;
		gW = new GameWorld(controllerManager);
	}

	public GameWorld getGameWorld(){
		return gW;
	}
	
	@Override
	public void init(GameContainer gc, StateBasedGame sbg)
		throws SlickException
	{
		uiImages[0] = new Image("assets/Art/p1state.png");
		uiImages[1] = new Image("assets/Art/p2state.png");
		uiImages[2] = new Image("assets/Art/p3state.png");
		uiImages[3] = new Image("assets/Art/p4state.png");
		inLine = gc.getHeight() - uiImages[0].getHeight() + 16;
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g)
		throws SlickException
	{
		gW.render(gc, g);
		
		int dist = gc.getWidth() / 8;
		uiIntervals = dist * 2;
		
		for(int i = 0; i < gW.numberOfPlayers; i ++){
			uiImages[i].draw(dist - 84, inLine, (float).75);
			Character c = gW.listOfPlayers.get(i);
			c.currentAnimation.getCurrentFrame().draw(dist - 84, inLine, (float).75);
			if(c.hasItem){
				c.item.image.draw(dist - 45, inLine + 4, (float) .9);
			}
			g.drawString((String)c.displayHP(),
				dist + (uiImages[i].getWidth() * 3 / 4) - 84, inLine + 16);
			g.drawString(c.wins + "/" +gW.winsNeeded,
					dist - (uiImages[i].getWidth() * 3 / 4), inLine + 16);
			dist += uiIntervals;
		}
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException
	{
		
		Input input = new Input(delta);
		if(input.isKeyDown(Input.KEY_P)){
			sbg.enterState(DisplayManager.PAUSESTATE);
		}
		
		try
		{
			gW.update(gc, delta);
		}
		catch (InterruptedException e)
		{
			Sys.alert("Something went wrong!", e.getMessage());
		}
		if(gW.checkIsRoundOver()){
			WinnerDisplayState wds = (WinnerDisplayState) sbg.getState(DisplayManager.WINNERDISPLAYSTATE);
			if(!gW.listOfCharacters.isEmpty()){
				Character c = gW.listOfCharacters.get(0);
				c.wins++;
				wds.setPlayerList(gW.listOfPlayers);
				wds.setWinner(c.playerID);
			}
			try {
				gW.setNextRound();
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				Sys.alert("Something went wrong!", e.getMessage());
			}
			wds.background = gW.background.copy();
			sbg.enterState(DisplayManager.WINNERDISPLAYSTATE);
		}
	}




	@Override
	public int getID()
	{
		// TODO Auto-generated method stub
		return stateID;
	}

}
