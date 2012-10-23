import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;


public class Projectile extends Entity
{
	int xSpawnLocation;
	int ySpawnLocation;
	int xCurrentLocation;
	int yCurrentLocation;
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
		this.xCurrentLocation = xSpawnLocation;
		this.ySpawnLocation = ySpawnLocation;
		this.yCurrentLocation = xSpawnLocation;
		this.xVelocity = xVelocity;
		this.yVelocity = yVelocity;
		this.hitBox = hitBox;		
		this.maxRange = maxRange;
	}
	
	public Rectangle getRectangle()
	{
		return hitBox;
	}
	
	@Override
	public void update(GameContainer gc, int delta) throws SlickException, InterruptedException
	{
		super.update(gc, delta);
		//update projectile's location
		xCurrentLocation += xVelocity;
		yCurrentLocation += yVelocity;
		if (getDistanceTravelled() >= maxRange)
		{
			gW.getListOfProjectiles().remove(this);
		}
		
		//keeps rectangle in line with sprite
		getRectangle().setBounds(xCurrentLocation, yCurrentLocation, getRectangle().getWidth(), getRectangle().getHeight());
	}
	
	
	
	//used to determine if the projectile has reached its maximum range
	public double getDistanceTravelled()
	{
		return Math.sqrt(Math.pow((xCurrentLocation - xSpawnLocation), 2) +
						 Math.pow((yCurrentLocation - ySpawnLocation), 2));
	}

	@Override
	public void render(GameContainer gc, Graphics g)
	{
		image.draw(xCoord, yCoord);
		
	}

	@Override
	public void init(GameContainer gc)
	{
		// TODO Auto-generated method stub
		
	}
	
		
}
	
	


