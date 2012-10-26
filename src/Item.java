import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;


public class Item extends Entity {
	protected double startUpTime, reloadTime;
	protected int damage;
	protected int xVelocity;
	protected int yVelocity;
	protected String name;
	protected boolean dropChance;
	
	//fields pertaining to instantiating projectiles
	protected int projectileRange;
	protected int projectileXSpeed;
	protected int projectileYSpeed = 0;
	protected String projectileImageLocation;
	protected Image projectileImage;
	protected Rectangle projectileHitBox;
	
	//For managing size and position of hitbox relative to the image
	//x + xposoffset = adjusts placement of hitbox relative to position
	//y + yposoffset = adjusts placement of hitbox relative to position
	//h - xoffset = adjusts size of hitbox for offset on both sides
	//h - yoffset = adjusts size of hitbox for offset on both sides
	protected int hitBoxXPosOffSet;
	protected int hitBoxYPosOffSet;
	protected int hitBoxXOffSet;
	protected int hitBoxYOffSet;
	private final int floatRange = 8;
	private float spawnYPosition;

	
	public Item (int x, int y, String fileLocation, int xVel, int yVel)
	{	 
		super(x, y, fileLocation);
		//Sets the hit box
		super.setHitBox(x, y, 45, 45);	
		spawnYPosition = yCoord;
		this.xVelocity = xVel;
		this.yVelocity = 1;
	}

	@Override
	public void render(GameContainer gc, Graphics g) throws SlickException
	{
		//g.draw(hitBox);
		image.draw(xCoord, yCoord);
	}

	@Override
	public void init(GameContainer gc) throws SlickException{
	}

	@Override
	public void update(GameContainer gc, int delta) throws SlickException,
	InterruptedException
	{
		yCoord += yVelocity;
		if(Math.abs(yCoord - spawnYPosition) > floatRange)
				yVelocity *= -1;
		
		super.update(gc, delta);
	}



	public void use(GameWorld gW,Character c)
	{		
		if(c.isFacingRight){
			int x = (int)(c.xCoord+c.getHitBox().getWidth()/2);
			int y = (int) c.yCoord;
			gW.getListOfProjectiles().add(new BasicProjectile(x, y,
					projectileImage, 
					projectileXSpeed, projectileYSpeed, 
					damage, projectileRange,
					hitBoxXPosOffSet,hitBoxYPosOffSet,hitBoxXOffSet,hitBoxYOffSet,
					c,gW));
		}
		else{
			int x = (int) (c.xCoord - projectileImage.getWidth()/2);
			int y = (int) c.yCoord;
			gW.getListOfProjectiles().add(new BasicProjectile(x, y,
					projectileImage.getFlippedCopy(true, false),
					-projectileXSpeed, projectileYSpeed, 
					damage, projectileRange,
					hitBoxXPosOffSet,hitBoxYPosOffSet,hitBoxXOffSet,hitBoxYOffSet,
					c,gW));
		}
	}

	public void setYSpawn(int y) {
		spawnYPosition = y;
		
	}
}
