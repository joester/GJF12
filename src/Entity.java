import org.newdawn.slick.Animation;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.geom.Rectangle;

public abstract class Entity{
	protected Image image;
	protected Rectangle hitBox;
	protected int xCoord;
	protected int yCoord;
	protected Animation animation;

	
	public Entity(int x, int y, String imageLocation){
		xCoord = x;
		yCoord = y;
		hitBox = new Rectangle(x,y,0,0);
		try {
			image = new Image(imageLocation);
		} catch (SlickException e) {
			System.out.println("Image not found for " + this.getClass());
			e.printStackTrace();
		}
	}
	
	public void setHitBox(int x, int y, int w, int h){
		hitBox = new Rectangle(x,y, w, h);
	}
	
	public void setHitBox(int x, int y){
		hitBox.setX(x);
		hitBox.setY(y);
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
	public abstract void render(GameContainer arg0, Graphics arg1) throws SlickException;
	
	public abstract void init(GameContainer arg0) throws SlickException;
	
	public void update(GameContainer arg0, int arg1) throws SlickException, InterruptedException
	{
		getHitBox().setX(xCoord);
		getHitBox().setY(yCoord);
	}
	
	public void renderEnt(String str, int width, int height) throws SlickException{
		Image img = new Image(str);
		SpriteSheet sheet = new SpriteSheet(img, width, height);
		animation = new Animation(sheet, 300);
		animation.addFrame(new Image(str), 300);
	}
	public void renderEnt(Image img, int width, int height) throws SlickException{
		SpriteSheet sheet = new SpriteSheet(img, width, height);
		animation = new Animation(sheet, 500);
	}
	public void addFrame(String str, int width, int height) throws SlickException{
		Image img = new Image(str);
		animation.addFrame(img, 300);
	}
}