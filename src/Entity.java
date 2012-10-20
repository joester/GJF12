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
	protected Animation a;

	public Entity(int x, int y, String imageLocation){
		xCoord = x;
		yCoord = y;
		try {
			image = new Image(imageLocation);
		} catch (SlickException e) {
			System.out.println("Image not found for " + this.getClass());
			e.printStackTrace();
		}
	}
	
	public void setHitBox(int x, int y){
		hitBox = new Rectangle(image.getWidth(), image.getHeight() ,x,y);
	}
	
	public void setHitBox(Rectangle hitbox){
		this.hitBox = hitBox;
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
	public void render(GameContainer arg0, Graphics arg1) throws SlickException
	{
		// TODO Auto-generated method stub
	}
	public void init(GameContainer arg0) throws SlickException
	{
		// TODO Auto-generated method stub
	}
	public void update(GameContainer arg0, int arg1) throws SlickException, InterruptedException
	{
		// TODO Auto-generated method stub	
	}
	public void renderEnt(String str, int width, int height) throws SlickException{
		Image img = new Image(str);
		SpriteSheet sheet = new SpriteSheet(img, width, height);
		a = new Animation(sheet, 300);
		a.addFrame(new Image(str), 300);
	}
	public void addFrame(String str, int width, int height) throws SlickException{
		Image img = new Image(str);
		a.addFrame(img, 300);
	}
}