import java.io.IOException;
import java.util.ArrayList;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class CharacterSelectState extends BasicGameState
{
	private ControllerManager controllerManager;
	private int stateID = -1;
	private Sound BGM = null;
	private Image background;
	protected ArrayList<Thumbnail> chars = new ArrayList<Thumbnail>();
	private World world = null;
	ArrayList<Pointer> pointers = new ArrayList<Pointer>();
	ArrayList<CharacterDisplay> displays = new ArrayList<CharacterDisplay>();
	
	
	public CharacterSelectState(int stateID, ControllerManager controllerManager, World world) {
		this.stateID  = stateID;
		this.controllerManager = controllerManager;		
		this.world = world;		
	}
	
	@Override
	public void init(GameContainer gc, StateBasedGame sbg)
		throws SlickException
	{
		background = new Image("assets/Art/example.png");
		chars.add(new Thumbnail("assets/Art/p1state.png", gc.getWidth() / 3, gc.getHeight()/2));
		chars.add(new Thumbnail("assets/Art/p2state.png", 2 * gc.getWidth() / 3, gc.getHeight()/2));
		pointers.add(new Pointer(0, 0, chars.get(0), new Image("assets/Art/one.jpg")));
		pointers.add(new Pointer(0, 1, chars.get(1), new Image("assets/Art/two.png")));
		if(world.getNumberOfPlayers() == 2){
			displays.add(new CharacterDisplay(0, 500));
			displays.add(new CharacterDisplay(1150, 500));
		}
		if(world.getNumberOfPlayers() == 3){
			displays.add(new CharacterDisplay(0, 500));
			displays.add(new CharacterDisplay(500, 500));
			displays.add(new CharacterDisplay(1000, 600));
		}
		if(world.getNumberOfPlayers() == 4){
			displays.add(new CharacterDisplay(0, 0));
			displays.add(new CharacterDisplay(1000, 0));
			displays.add(new CharacterDisplay(0, 500));
			displays.add(new CharacterDisplay(1000, 500));
		}
		
	}

	private class Thumbnail extends Image{
		int xPos, yPos;
		float scale;
		boolean isSelected;
		ArrayList<Image> currentScheme = new ArrayList<Image>();
		ArrayList<ColorNail> colors = new ArrayList<ColorNail>();
		ArrayList<Pointer> setPointers = new ArrayList<Pointer>();
		ArrayList<Pointer> selectPointers = new ArrayList<Pointer>();
		
		public Thumbnail(String loc, int xPos, int yPos) throws SlickException{
			super(loc);
			this.xPos = xPos;
			this.yPos = yPos;
			scale = 1;
			colors.add(new ColorNail(xPos - 70, yPos, "assets/Art/p1state.png"));
			colors.add(new ColorNail(xPos - 70, yPos + 35, "assets/Art/p2state.png"));
			colors.add(new ColorNail(xPos - 70, yPos + 70, "assets/Art/p3state.png"));
			colors.add(new ColorNail(xPos - 70, yPos + 105, "assets/Art/p4state.png"));
			currentScheme.add(new Image("assets/Art/p1state.png"));
			currentScheme.add(new Image("assets/Art/p2state.png"));
			currentScheme.add(new Image("assets/Art/p3state.png"));
			currentScheme.add(new Image("assets/Art/p4state.png"));
		}
		public void setScale(){
			if(this.scale == 1){
				scale = 1.5f;
			}
			else{
				scale = 1;
			}
		}
		
		public void addPointer(Pointer p){
			setPointers.add(p);
		}
		
		public void removePointer(Pointer p){
			setPointers.remove(p);
		}
		
		public void setSelect(Pointer p){
			if(isSelected && selectPointers.contains(p)){			
				selectPointers.remove(p);
				if(selectPointers.size() == 0){
					setScale();
					isSelected = false;
				}					
			}
			else{
				isSelected = true;
				if(selectPointers.size() == 0){
					setScale();
				}
				selectPointers.add(p);					
			}
			
		}

		public class ColorNail extends Image{
			int xPos, yPos;
			float scale;
			boolean isFocused, isSelected;
			ArrayList<Pointer> currentPointers = new ArrayList<Pointer>();
			Pointer selectedPointer = null;
			
			public ColorNail(int xPos, int yPos, String loc) throws SlickException{
				super(loc);
				this.xPos = xPos;
				this.yPos = yPos;
				scale = .5f;
			}			
			public void setSelect(Pointer p){
				if(isSelected && selectedPointer.equals(p)){
					this.isSelected = false;
					selectedPointer = null;
				}
				else{
					this.isSelected = true;	
					selectedPointer = p;
				}
			}
			public void addPointer(Pointer p){
				currentPointers.add(p);
			}
			public void removePointer(Pointer p){
				currentPointers.remove(p);
			}
		}
	}
	
	
	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g)
		throws SlickException
	{
		background.draw(0, 0);
		g.setColor(Color.black);
		/*
		//g.drawString("So here's the story. You don't like the other person. You want to beat the crap", 200, 10);
		//g.drawString("out of them. Why? I don't know, they stole your sweet roll or something.", 200, 30);
		g.drawString("For player one, use A and D to choose between the many character options we have.", 200, 50);
		g.drawString("When you've found your soul mate, press Q to confirm.", 200, 70);
		g.drawString("Now, you get to choose from our many color options, which pop out to the side, by using W and S.", 200, 90);
		g.drawString("That's ingenious, you say? Brilliant, even? You're damn right it is.", 200, 110);
		g.drawString("Once you've found something that matches your shoes, press Q again to confirm your choice.", 200, 130);
		g.drawString("But wait! What if you decide you want your character to be just the right (and only) shade of red?", 200, 150);
		g.drawString("Then press Q. E-Z P-Z. Reselect your color, and if you want a different character, press E.", 200, 170);
		g.drawString("For player two, I and K correspond to W and S, J and L correspond to A and D, Q and E correspond to U and O.", 200, 190);
		g.drawString("Now if you'll excuse me, I need to go wonder where I lost control of my life. Have fun.", 200, 210);
		*/
		
		g.drawString("A and D to navigate between chararacters, Q to select character", 200, 10);
		g.drawString("W and S to navigate between colors, Q again to select the color.", 200, 30);
		g.drawString("Q again to deselect the color if you've picked one, E to deselect your character", 200, 50);
		g.drawString("For Player 2, J and L act as A and D for Player 1.", 200, 70);
		g.drawString("U acts as Q for Player One, and O acts as E for Player One", 200, 90);
		
		for(Thumbnail nail : chars){
			nail.draw(nail.xPos, nail.yPos, nail.scale);
			if(nail.isSelected){
				for(Thumbnail.ColorNail colorNail : nail.colors){
					if(colorNail.isSelected){
						colorNail.isFocused = false;
						colorNail.draw(colorNail.xPos, colorNail.yPos, colorNail.scale + .25f);
					}
					if(colorNail.isFocused){
						colorNail.draw(colorNail.xPos, colorNail.yPos, colorNail.scale + .15f);
					}
					else{
						if(!colorNail.isSelected)
							colorNail.draw(colorNail.xPos, colorNail.yPos, colorNail.scale);
					}
					
				}
			}
			if(nail.setPointers.size() > 1){
				int vertBufferSpace = 10;
				for(int i = 0; i < nail.setPointers.size(); i ++){
					nail.setPointers.get(i).currentPos.draw(nail.xPos + nail.getWidth(), nail.yPos + vertBufferSpace * i, .5f);
				}
			}
			else if(nail.setPointers.size() == 1){
				nail.setPointers.get(0).currentPos.draw(nail.xPos + nail.getWidth(), nail.yPos, .5f);
			}	
		}
		for(CharacterDisplay cDisplay : displays){
			if(!(cDisplay.charDisplayBox == null)){
				cDisplay.draw();
				
			}
		}
	}

	private class Pointer{
		int charPos;
		int colorPos;
		boolean isSelected, colorChosen;
		String dir;
		Image currentPos;
		Thumbnail imageSelected, imageFocused;
		Thumbnail.ColorNail colorSelected, colorFocused;
		
		public Pointer(int charPos, int colorPos, Thumbnail image, Image currentPos){
			this.charPos = 0;
			this.colorPos = colorPos;
			imageSelected = image;
			imageFocused = image;
			this.currentPos = currentPos;
		}
		public void setCharPos(){
			imageFocused.removePointer(this);
			this.charPos --;
			if(this.charPos < 0){
				charPos = 1;
			}
			imageFocused = chars.get(charPos);
			imageFocused.addPointer(this);
		}
		public void setColorPos(boolean isUp){
			if(colorFocused.currentPointers.size() == 1){
				colorFocused.isFocused = false;	
			}			
			colorFocused.removePointer(this);
			if(isUp){
				if(colorPos - 1 < 0){
					colorPos = 3;
				}
				else{
					colorPos --;
				}
			}
			else{
				if(colorPos + 1 > 3){
					colorPos = 0;
				}
				else{
					colorPos ++;
				}
			}			
			colorFocused = imageSelected.colors.get(colorPos);
			colorFocused.addPointer(this);
			if(colorFocused.currentPointers.size() > 1){
				setColorPos(isUp);
			}
			colorFocused.isFocused = true;
			
		}		
		public void setSelect(){
			if(isSelected){
				isSelected = false;
				imageSelected.setSelect(this);
			}
			else{
				isSelected = true;
				imageSelected = chars.get(this.charPos);
				colorFocused = imageSelected.colors.get(0);
				imageSelected.setSelect(this);
			}			
		}	
		public void setSelectColor(){
			if(colorChosen){
				colorChosen = false;
				colorSelected.setSelect(this);
			}
			else{
				colorChosen = true;
				this.setColorPos(false);
				colorSelected = imageSelected.colors.get(colorPos);
				colorSelected.setSelect(this);
			}
		}
		public boolean checkComplete(){
			if(dir == null){
				return false;
			}
			return true;
		}
	}
	
	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta)
		throws SlickException
	{
		Input input = new Input(delta);
		
		if((input.isKeyDown(Input.KEY_A) || input.isKeyDown(Input.KEY_D)) && !pointers.get(0).isSelected){
			pointers.get(0).setCharPos();
		}
		if((input.isKeyDown(Input.KEY_J) || input.isKeyDown(Input.KEY_L)) && !pointers.get(1).isSelected){
			pointers.get(1).setCharPos();
		}
		if(input.isKeyDown(Input.KEY_W) && pointers.get(0).isSelected && !pointers.get(0).colorChosen){
			if(!pointers.get(0).colorChosen)
				pointers.get(0).setColorPos(true);
			displays.get(0).setCharToDisplay(pointers.get(0).colorPos);
		}
		if(input.isKeyDown(Input.KEY_S) && pointers.get(0).isSelected && !pointers.get(0).colorChosen){
			if(!pointers.get(0).colorChosen)
				pointers.get(0).setColorPos(false);	
			displays.get(0).setCharToDisplay(pointers.get(0).colorPos);
		}
		if(input.isKeyDown(Input.KEY_I) && pointers.get(1).isSelected && !pointers.get(1).colorChosen){
			if(!pointers.get(1).colorChosen)
				pointers.get(1).setColorPos(true);
			displays.get(1).setCharToDisplay(pointers.get(1).colorPos);
		}
		if(input.isKeyDown(Input.KEY_K) && pointers.get(1).isSelected && !pointers.get(1).colorChosen){
			if(!pointers.get(1).colorChosen)
				pointers.get(1).setColorPos(false);	
			displays.get(1).setCharToDisplay(pointers.get(1).colorPos);
		}
		if(input.isKeyDown(Input.KEY_Q)){
			if(pointers.get(0).isSelected){
				if(pointers.get(0).colorSelected == null){
					pointers.get(0).colorSelected = pointers.get(0).colorFocused;
					pointers.get(0).colorChosen = true;
					selectionToChar(pointers.get(0));
				}
				else{
					pointers.get(0).colorSelected = null;
					pointers.get(0).colorChosen = false;
					pointers.get(0).dir = null;
				}				
			}
			else{
				pointers.get(0).setSelect();
				displays.get(0).setCharDisplay(pointers.get(0).charPos);
			}			
		}
		if(input.isKeyDown(Input.KEY_U)){
			if(pointers.get(1).isSelected){
				if(pointers.get(1).colorSelected == null){
					pointers.get(1).colorSelected = pointers.get(1).colorFocused;
					pointers.get(1).colorChosen = true;
					selectionToChar(pointers.get(1));
				}
				else{
					pointers.get(1).colorSelected = null;
					pointers.get(1).colorChosen = false;
					pointers.get(1).dir = null;
				}				
			}
			else{
				pointers.get(1).setSelect();
				displays.get(1).setCharDisplay(pointers.get(1).charPos);
			}	
		}
		if(input.isKeyDown(Input.KEY_E) && pointers.get(0).isSelected){
			pointers.get(0).setSelect();
			if(pointers.get(0).colorChosen){
				pointers.get(0).colorSelected.isSelected = false;
				pointers.get(0).colorSelected.currentPointers.remove(pointers.get(0));
				pointers.get(0).colorChosen = false;
			}
			displays.get(0).setCharDisplay(2);
			displays.get(0).charToDisplay = null;
		}
		if(input.isKeyDown(Input.KEY_O) && pointers.get(1).isSelected){
			pointers.get(1).setSelect();
			if(pointers.get(1).colorChosen){
				pointers.get(1).colorSelected.isSelected = false;
				pointers.get(1).colorSelected.currentPointers.remove(pointers.get(1));
				pointers.get(1).colorChosen = false;
			}
			displays.get(1).setCharDisplay(2);
			displays.get(1).charToDisplay = null;
		}
		boolean allReady = true;
		for(Pointer p : pointers){
			if(!p.checkComplete()){
				allReady = false;
				break;
			}
		}
		if(allReady){
			ArrayList<String> dirs = new ArrayList<String>();
			for(Pointer p : pointers){
				dirs.add(p.dir);
			}
			try {
				world.init();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			world.setDirectories(dirs);
			world.loadChars();
			world.getBGM().loop();
			sbg.enterState(DisplayManager.GAMEPLAYSTATE);
		}
		
		try
		{
			Thread.sleep(100);
		}
		catch (InterruptedException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void selectionToChar(Pointer p){
		if(p.imageSelected.equals(chars.get(0))){
			if(p.colorSelected.equals(p.imageSelected.colors.get(0))){
				p.dir = "player1";
			}
			if(p.colorSelected.equals(p.imageSelected.colors.get(1))){
				p.dir = "player2";
			}
			if(p.colorSelected.equals(p.imageSelected.colors.get(2))){
				p.dir = "player3";
			}
			if(p.colorSelected.equals(p.imageSelected.colors.get(3))){
				p.dir = "player4";
			}
		}
		else{
			if(p.colorSelected.equals(p.imageSelected.colors.get(0))){
				p.dir = "sableRed";
			}
			if(p.colorSelected.equals(p.imageSelected.colors.get(1))){
				p.dir = "sableBlue";
			}
			if(p.colorSelected.equals(p.imageSelected.colors.get(2))){
				p.dir = "sableGreen";
			}
			if(p.colorSelected.equals(p.imageSelected.colors.get(3))){
				p.dir = "sableYellow";
			}
		}
	}

	@Override
	public int getID()
	{
		// TODO Auto-generated method stub
		return stateID;
	}
}
/*
Player 1

A, D : Select between characters
Q : Confirm character choice

W, S: Select between colors
Q : Confirm color choice
Q again : Deselect color choice

E : Revert all choices.

Each time you press Q, your choice is locked in, except in colors, where
each second time you press deselects the color chosen

Player 2

J, L : Select between characters
U : Confirm character choice

I, K : Select between colors
U : See player one
U again : Again, see player one

O : Same as player one's E

If a player is hovering over a character color, the other player will not 
be able to hover over that player color, nor select it.
*/
