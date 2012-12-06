import java.util.ArrayList;
import org.newdawn.slick.Image;
import org.newdawn.slick.Animation;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

public class CharacterDisplay
{
	Image charDisplayBox;
	Animation charToDisplay;
	ArrayList<Image> displayBoxes = new ArrayList<Image>();
	int xPos, yPos;
	
	public CharacterDisplay(int xPos, int yPos){
		this.xPos = xPos;
		this.yPos = yPos;
		try{
			displayBoxes.add(new Image("assets/Art/borderNiko.png"));
			displayBoxes.add(new Image("assets/Art/borderSable.png"));
			displayBoxes.add(null);
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void setCharDisplay(int i){
		charDisplayBox = displayBoxes.get(i);
		setCharToDisplay(0);
	}
	
	public void setCharToDisplay(int color){
		try{
			if(charDisplayBox.equals(displayBoxes.get(0))){
				if(color == 0){
					Image img = new Image("assets/Art/Characters/player1/stand-spritesheet.png");
					SpriteSheet spr = new SpriteSheet(img, img.getWidth()/3, img.getHeight());
					charToDisplay = new Animation(spr, 150);
				}
				if(color == 1){
					Image img = new Image("assets/Art/Characters/player2/stand-spritesheet.png");
					SpriteSheet spr = new SpriteSheet(img, img.getWidth()/3, img.getHeight());
					charToDisplay = new Animation(spr, 150);
				}
				if(color == 2){
					Image img = new Image("assets/Art/Characters/player3/stand-spritesheet.png");
					SpriteSheet spr = new SpriteSheet(img, img.getWidth()/3, img.getHeight());
					charToDisplay = new Animation(spr, 150);
				}
				if(color == 3){
					Image img = new Image("assets/Art/Characters/player4/stand-spritesheet.png");
					SpriteSheet spr = new SpriteSheet(img, img.getWidth()/3, img.getHeight());
					charToDisplay = new Animation(spr, 150);
				}
			}
			if(charDisplayBox.equals(displayBoxes.get(1))){
				if(color == 0){
					Image img = new Image("assets/Art/Characters/sableRed/stand-spritesheet.png");
					SpriteSheet spr = new SpriteSheet(img, img.getWidth()/3, img.getHeight());
					charToDisplay = new Animation(spr, 150);
				}
				if(color == 1){
					Image img = new Image("assets/Art/Characters/sableBlue/stand-spritesheet.png");
					SpriteSheet spr = new SpriteSheet(img, img.getWidth()/3, img.getHeight());
					charToDisplay = new Animation(spr, 150);
				}
				if(color == 2){
					Image img = new Image("assets/Art/Characters/sableGreen/stand-spritesheet.png");
					SpriteSheet spr = new SpriteSheet(img, img.getWidth()/3, img.getHeight());
					charToDisplay = new Animation(spr, 150);
				}
				if(color == 3){
					Image img = new Image("assets/Art/Characters/sableYellow/stand-spritesheet.png");
					SpriteSheet spr = new SpriteSheet(img, img.getWidth()/3, img.getHeight());
					charToDisplay = new Animation(spr, 150);
				}
			}
				
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
	}
	
	public void draw(){
		charDisplayBox.draw(xPos, yPos, 0.07f);
		if(charDisplayBox.equals(displayBoxes.get(0))){
			charToDisplay.draw(xPos + 30, yPos + 90);
		}
		else{
			charToDisplay.draw(xPos + 25, yPos + 50);
		}
	}
}
