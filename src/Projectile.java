import org.newdawn.slick.geom.Rectangle;


public class Projectile
{
	int xSpawnLocation;
	int ySpawnLocation;
	int xCurrentLocation;
	int yCurrentLocation;
	int xVelocity;
	int yVelocity;
	int damage;
	int maxRange;
	Rectangle hitBox;
	GameWorld gW = new GameWorld();
	
	public Projectile (int xSpawnLocation, int ySpawnLocation, int xVelocity, int yVelocity,
						int maxRange, Rectangle hitBox)
	{
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
	
	public void update()
	{
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
	
		
}
	
	


