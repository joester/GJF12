import org.newdawn.slick.Animation;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.geom.Rectangle;

public abstract class Entity{
	boolean isCharacter = false;
	protected Image image;
	protected Rectangle hitBox;
	protected int xCoord;
	protected int yCoord;
	protected Animation animation;


	public Entity(int x, int y, String imageLocation){
		if(imageLocation != null){
			String newLocation = "";
			if(this instanceof Character){
				newLocation = "/assets/Art/Characters/" + imageLocation
						+ "/stand-spritesheet.png";
				isCharacter = true;
			}

			try {
				if(isCharacter){
					image = new Image(newLocation);
				}
				else{
					image = new Image(imageLocation);
				}
			} catch (SlickException e) {
				System.out.println("Image not found for " + this.getClass());
				e.printStackTrace();
			}
			}
		xCoord = x * MapEntity.BLOCKSIZE;
		yCoord = y * MapEntity.BLOCKSIZE;
		hitBox = new Rectangle(xCoord,yCoord,0,0);
	}

	public Entity(int xSpawnLocation, int ySpawnLocation) {
		xCoord = xSpawnLocation;
		yCoord = ySpawnLocation;
	}

	public void setHitBox(int x, int y, int w, int h){
		hitBox.setBounds(x,y, w, h);
	}

	public void setHitBox(int x, int y){
		hitBox.setX(x);
		hitBox.setY(y);
	}

	public void setHitBox(float x, float y){
		hitBox.setX(x);
		hitBox.setY(y);
	}

	public Rectangle getHitBox(){
		return hitBox;
	}

	public void setLocation(int x, int y){
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
		hitBox.setX(xCoord);
		hitBox.setY(yCoord);
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

	public void setHitBoxSize(int w, int h) {
		hitBox.setSize(w, h);
	}
}