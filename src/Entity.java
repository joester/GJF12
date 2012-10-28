import org.lwjgl.Sys;
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
	protected float xCoord, yCoord;
	//For managing size and position of hitbox relative to the image
	//x + xposoffset = adjusts placement of hitbox relative to position
	//y + yposoffset = adjusts placement of hitbox relative to position
	//h - xoffset = adjusts size of hitbox for offset on both sides
	//h - yoffset = adjusts size of hitbox for offset on both sides
	protected float hitBoxXPosOffset, hitBoxYPosOffset;
	protected float hitBoxXOffset, hitBoxYOffset;
	protected Animation animation;
	protected GameWorld gW;

	/**
	 * Sets x,y position and creates a hitbox established at that position
	 * and loads an image.
	 * @param Sets x coordinate
	 * @param Sets y coordinate
	 * @param imageLocation Path to image
	 * @param gW 
	 */
	public Entity(float x, float y, String imageLocation, GameWorld gW){
		this(x, y, gW);
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
	 * @param gW 
	 */
	public Entity(float x, float y, GameWorld gW) {
		xCoord = x;
		yCoord = y;
		this.gW = gW;
		hitBox = new Rectangle(xCoord,yCoord,0,0);
	}

	//-----------------Methods for managing the hitbox------------------------------//
	/**
	 * 
	 * @param x The new x coordinate of the hitbox
	 * @param y The new y coordinate of hitbox
	 * @param w The new width of hitbox
	 * @param h The new height of hitbox
	 */
	public void setHitBoxBounds(float x, float y, float w, float h){
		hitBox.setBounds(xCoord + hitBoxXPosOffset, 
				yCoord + hitBoxYPosOffset, 
				image.getWidth() - hitBoxXOffset,
				image.getHeight() - hitBoxYOffset);
	}

	/**
	 * 
	 * @param x The new x coordinate of the hitbox
	 * @param y The new y coordinate of hitbox
	 */
	public void setHitBoxLocation(float x, float y){
		hitBox.setLocation(x + hitBoxXPosOffset, y + hitBoxYPosOffset);
	}


	/**
	 * 
	 * @param w The new width of hitbox
	 * @param h The new height of hitbox
	 */
	public void setHitBoxSize(float w, float h) {
		hitBox.setSize(w - hitBoxXOffset, h - hitBoxYOffset);
	}

	/**
	 * 
	 * @return Rectangle hitBox
	 */
	public Rectangle getHitBox(){
		return hitBox;
	}

	
	public void setHitBoxOffsets(float hitBoxXPosOffset, float hitBoxYPosOffset, float hitBoxXOffset, float hitBoxYOffset){
		this.hitBoxXPosOffset = hitBoxXPosOffset;
		this.hitBoxYPosOffset = hitBoxYPosOffset;
		this.hitBoxXOffset = hitBoxXOffset;
		this.hitBoxYOffset = hitBoxYOffset;
		setHitBoxBounds(xCoord + hitBoxXPosOffset, 
				yCoord + hitBoxYPosOffset, 
				image.getWidth() - hitBoxXOffset,
				image.getHeight() - hitBoxYOffset);
	}
	//------------------Methods for managing position of entity---------------------//
	public void setLocation(float x, float y){
		xCoord = x;
		yCoord = y;
		setHitBoxLocation(x, y);
	}

	public float getX(){
		return xCoord;
	}

	public float getY(){
		return yCoord;
	}
	//------------------------------------------------------------------------------//
	
	public abstract void render(GameContainer arg0, Graphics arg1) throws SlickException;

	public void update(GameContainer gc, int delta) throws SlickException, InterruptedException{
		setHitBoxLocation(xCoord,yCoord);
	}

	protected void renderEnt(Image img, int width, int height,int duration) throws SlickException{
		SpriteSheet sheet = new SpriteSheet(img, width, height);
		animation = new Animation(sheet, duration);
	}
	
	public Image getImage(){
		return image;
	}
	
	public GameWorld getGameWorld(){
		return gW;
	}
}