import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;


public class Projectile extends Entity
{
	int xSpawnLocation;
	int ySpawnLocation;
	int xVelocity;
	int yVelocity;
	int damage;
	int maxRange;
	GameWorld gW;
	
	public Projectile (int xSpawnLocation, int ySpawnLocation, int xVelocity, int yVelocity,
						int maxRange, Rectangle hitBox)
	{
		super(xSpawnLocation, ySpawnLocation, "assets/Art/Transformations/wind.png");
		this.xSpawnLocation = xSpawnLocation;
		this.xCoord = xSpawnLocation;
		this.ySpawnLocation = ySpawnLocation;
		this.yCoord = xSpawnLocation;
		this.xVelocity = xVelocity;
		this.yVelocity = yVelocity;
		this.hitBox = hitBox;		
		this.maxRange = maxRange;
	}
	
	@Override
	public void update(GameContainer gc, int delta) throws SlickException, InterruptedException
	{
		super.update(gc, delta);
		//update projectile's location
		xCoord += xVelocity;
		yCoord += yVelocity;
		if (getDistanceTravelled() >= maxRange)
		{
			gW.getListOfProjectiles().remove(this);
		}
		
		//keeps rectangle in line with sprite
		setHitBox(xCoord, yCoord);
	}
	
	
	
	//used to determine if the projectile has reached its maximum range
	public double getDistanceTravelled()
	{
		return Math.sqrt(Math.pow((xCoord - xSpawnLocation), 2) +
						 Math.pow((yCoord - ySpawnLocation), 2));
	}

	@Override
	public void render(GameContainer gc, Graphics g)
	{
		g.draw(hitBox);
		image.draw(xCoord, yCoord);
		
	}

	@Override
	public void init(GameContainer gc)
	{
		// TODO Auto-generated method stub
		
	}
	
		
}
	
	


