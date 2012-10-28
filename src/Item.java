import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;


public class Item extends Entity {
	protected double startUpTime, reloadTime;
	protected int damage;
	protected float xVelocity;
	protected float yVelocity;
	protected String name;
	protected boolean dropChance;
	
	//fields pertaining to instantiating projectiles
	protected float projectileRange;
	protected float projectileXSpeed;
	protected float projectileYSpeed;
	protected String projectileImageLocation;
	protected Image projectileImage;
	protected Rectangle projectileHitBox;
	protected float pXPosOffset, pYPosOffset;
	protected float pXOffset, pYOffset;
	private final int floatRange = 8;
	private float ySpawnLocation;

	
	public Item (float x, float y, String fileLocation)
	{	 
		super(x, y, fileLocation);
		//Sets the hit box
		setHitBoxSize(45, 45);	
		ySpawnLocation = yCoord;
		yVelocity = .25f;
	}

	@Override
	public void render(GameContainer gc, Graphics g) throws SlickException
	{
		//g.draw(hitBox);
		image.draw(xCoord, yCoord);
	}

	@Override
	public void update(GameContainer gc, int delta) throws SlickException,
	InterruptedException
	{
		yCoord += yVelocity;
		if(Math.abs(yCoord - ySpawnLocation) > floatRange)
				yVelocity *= -1;
		
		super.update(gc, delta);
	}



	public void use(GameWorld gW,Character c)
	{		
		if(c.isFacingRight){
			float x = c.xCoord+c.getHitBox().getWidth()/2;
			float y = c.yCoord;
			gW.getListOfProjectiles().add(new Projectile(x, y,
					projectileImage, 
					projectileXSpeed, projectileYSpeed, 
					damage, projectileRange,
					pXPosOffset,pYPosOffset,pXOffset,pYOffset,
					c,gW));
		}
		else{
			float x = c.xCoord - projectileImage.getWidth()/2;
			float y = c.yCoord;
			gW.getListOfProjectiles().add(new Projectile(x, y,
					projectileImage.getFlippedCopy(true, false),
					-projectileXSpeed, projectileYSpeed, 
					damage, projectileRange,
					pXPosOffset,pYPosOffset,pXOffset,pYOffset,
					c,gW));
		}
	}

	public void setYSpawnLocation(float y) {
		ySpawnLocation = y;
	}
}
