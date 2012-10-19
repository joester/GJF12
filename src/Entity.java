import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;

public abstract class Entity{
	protected Image image;
	protected Rectangle hitBox;
	protected int xCoord;
	protected int yCoord;
	protected String imagePath;

	public Entity(int x, int y, String imageLocation){
		xCoord = x;
		yCoord = y;
		imagePath = imageLocation;
		/**try {
			image = new Image(imagePath);
		} catch (SlickException e) {
			System.out.println("Image not found for " + this.getClass());
			e.printStackTrace();
		}**/
	}
	
	public void setHitBox(int x, int y){
		hitBox = new Rectangle(0,0,x,y);
	}
	
	public void setHitBox(Rectangle hitbox){
		this.hitBox = hitbox;
	}
	
	public Rectangle getHitBox(){
		return hitBox;
	}
	
	public void move(int x, int y){
		xCoord = x;
		yCoord = y;
	}
	
	public int getX(){
		return xCoord;
	}
	
	public int getY(){
		return yCoord;
	}
	
	public Image getImage(){
		return image;
	}
	public String getImagePath(){
		return imagePath;
	}
}