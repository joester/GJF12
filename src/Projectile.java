import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;


public class Projectile extends Entity
{
	int xCoord;
	int yCoord;
	int xSpawnLocation;
	int ySpawnLocation;
	int xVelocity;
	int yVelocity;
	int damage;
	int maxRange;
	GameWorld gW;
	Character owner;
	
	public Projectile (int xSpawnLocation, int ySpawnLocation, Image pi, int xVelocity, int yVelocity,
						int maxRange, Rectangle hitBox, Character owner, GameWorld gW)
	{
		
		super(xSpawnLocation, ySpawnLocation, null);
		this.gW = gW;
		this.xSpawnLocation = xSpawnLocation;
		this.ySpawnLocation = ySpawnLocation;
		xCoord = xSpawnLocation;
		yCoord = ySpawnLocation;
		this.xVelocity = xVelocity;
		this.yVelocity = yVelocity;
		this.hitBox = hitBox;		
		this.maxRange = maxRange;
		this.owner = owner;
		gW.listOfProjectiles.add(this);
		this.image = pi;
	}
	
	@Override
	public void update(GameContainer gc, int delta) throws SlickException, InterruptedException
	{
		super.update(gc, delta);
		//update projectile's location
		xCoord += xVelocity;
		yCoord += yVelocity;
		if (getDistanceTravelled() >= 1000)
		{
			gW.projectilesToBeRemoved.add(this);
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
		System.out.println("projectile is rendering" + hitBox.getX() + "" +hitBox.getY() + " " + image);
		g.draw(hitBox);
		if(image != null){
			image.draw(xCoord, yCoord, 1);	
		}
		
	}

	@Override
	public void init(GameContainer gc)
	{
		// TODO Auto-generated method stub
		
	}
	
		
}
	
	


