import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;


public class InputButton
{
	int xPos, yPos;
	private Image img;
	GameContainer gc;
	public InputButton(int x, int y, Image i, GameContainer gc){
		xPos = x;
		yPos = y;
		img = i;
		this.gc = gc;
	}
	public boolean withinBounds(Input input){
		if(input.getMouseX() < xPos + img.getWidth() &&
			input.getMouseX() > xPos){
			
			return true;
		}
		return false;
	}
	public int getWidth(){
		return img.getWidth();
	}
	public int getHeight(){
		return img.getHeight();
	}
	public Image getImage(){
		return img;
	}
	public void setXpos(){
		xPos = (gc.getWidth() / 2) - img.getWidth() / 2;
	}
}
