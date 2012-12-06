import org.lwjgl.Sys;
import org.newdawn.slick.Animation;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.geom.Rectangle;

public class Entity{
	protected Image image;
	protected Hitbox hitbox;

	protected float xCoord, yCoord;
	//For managing size and position of hitbox relative to the image
	//x + xposoffset = adjusts placement of hitbox relative to position
	//y + yposoffset = adjusts placement of hitbox relative to position
	//h - xoffset = adjusts size of hitbox for offset on both sides
	//h - yoffset = adjusts size of hitbox for offset on both sides
	protected Animation animation;
	protected World world;

	/**
	 * Sets x,y position and creates a hitbox established at that position
	 * and loads an image.
	 * @param Sets x coordinate
	 * @param Sets y coordinate
	 * @param imageLocation Path to image
	 * @param world 
	 */
	public Entity(float x, float y, String imageLocation, World world){
		this(x, y, world);
		if(imageLocation != null){
			try{
				if(this instanceof Character){
					image = new Image("assets/Art/Characters/" + imageLocation
							+ "/stand-spritesheet.png");
				}
				else{
					image = new Image(imageLocation);
				}
			}
			catch (SlickException e) {
				System.out.println("Image not found for " + this.getClass());
				Sys.alert("Something went wrong!", e.getMessage());
			}
		}
	}

	/**
	 * Sets x,y position and creates a hitbox established at that position
	 * @param x	Sets x coordinate
	 * @param y	Sets y coordinate
	 * @param world 
	 */
	
	public Entity(float x, float y, World world) {
		xCoord = x;
		yCoord = y;
		this.world = world;
		hitbox = new Hitbox(xCoord,yCoord,0,0);
	}

	//-----------------Methods for managing the hitbox------------------------------//
	/**
	 * 
	 * @param x The new x coordinate of the hitbox
	 * @param y The new y coordinate of hitbox
	 * @param w The new width of hitbox
	 * @param h The new height of hitbox
	 */
	public void setHitboxBounds(float x, float y, float w, float h){
		hitbox.setBounds(x, y, w, h);
	}

	/**
	 * 
	 * @param x The new x coordinate of the hitbox
	 * @param y The new y coordinate of hitbox
	 */
	public void setHitboxLocation(float x, float y){
		hitbox.setLocation(x, y);
	}


	/**
	 * 
	 * @param w The new width of hitbox
	 * @param h The new height of hitbox
	 */
	public void setHitboxSize(float w, float h) {
		hitbox.setSize(w,h);
	}

	/**
	 * 
	 * @return Rectangle hitbox
	 */
	public Hitbox getHitbox(){
		return hitbox;
	}

	
	public void setHitboxOffsets(Rectangle offsets){
		hitbox.setOffsets(offsets);
	}
	//------------------Methods for managing position of entity---------------------//
	public void setLocation(float x, float y){
		xCoord = x;
		yCoord = y;
		setHitboxLocation(x, y);
	}

	public float getX(){
		return xCoord;
	}

	public float getY(){
		return yCoord;
	}
	//------------------------------------------------------------------------------//
	
	public void render(GameContainer gc, Graphics g) throws SlickException{
		//g.setColor(Color.white);
		//g.draw(hitbox);
	}

	public void update(GameContainer gc, int delta) throws SlickException, InterruptedException{
		setHitboxLocation(xCoord,yCoord);
	}

	protected void renderEnt(Image img, int width, int height,int duration) throws SlickException{
		SpriteSheet sheet = new SpriteSheet(img, width, height);
		animation = new Animation(sheet, duration);
	}
	
	public Image getImage(){
		return image;
	}
	
	public World getWorld(){
		return world;
	}
}