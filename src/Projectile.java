import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;


public class Projectile extends Entity
{
	int currentXLocation;
	int currentYLocation;
	int xSpawnLocation;
	int ySpawnLocation;
	int xVelocity;
	int yVelocity;
	int damage;
	int maxRange;
	GameWorld gW;
	Character owner;
	
	int hitBoxXOffSet;
	int hitBoxYOffSet;
	public Projectile (int xSpawnLocation, int ySpawnLocation, Image image, int xVelocity, int yVelocity, int damage,
						int maxRange, Rectangle hitBox, Character owner, GameWorld gW)
	{
		super(xSpawnLocation, ySpawnLocation);
		this.gW = gW;
		this.xSpawnLocation = xSpawnLocation;
		this.ySpawnLocation = ySpawnLocation;
		currentXLocation = xSpawnLocation;
		currentYLocation = ySpawnLocation;
		this.xVelocity = xVelocity;
		this.yVelocity = yVelocity;
		this.hitBox = hitBox;		
		this.maxRange = maxRange;
		this.damage = damage;
		this.owner = owner;
		this.image = image;
	}

	public Projectile(int xSpawnLocation, int ySpawnLocation, Image image,
			int projectileXSpeed, int projectileYSpeed, int damage, int projectileRange,
			int hitBoxXPosOffSet, int hitBoxYPosOffSet, int hitBoxXOffSet, int hitBoxYOffSet, Character c, GameWorld gW) {
		super(xSpawnLocation, ySpawnLocation,null);
		this.gW = gW;
		this.xSpawnLocation = xSpawnLocation;
		this.ySpawnLocation = ySpawnLocation;
		currentXLocation = xSpawnLocation;
		currentYLocation = ySpawnLocation;
		xVelocity = projectileXSpeed;
		yVelocity = projectileYSpeed;	
		maxRange = projectileRange;
		this.damage = damage;
		owner = c;
		this.image = image;
		this.hitBoxXOffSet = hitBoxXPosOffSet;
		this.hitBoxYOffSet = hitBoxYPosOffSet;
		hitBox = new Rectangle(xSpawnLocation + hitBoxXPosOffSet, 
				ySpawnLocation + hitBoxYPosOffSet, 
				image.getWidth() - hitBoxXOffSet,
				image.getHeight() - hitBoxYOffSet);
	}

	@Override
	public void update(GameContainer gc, int delta) throws SlickException, InterruptedException
	{
		super.update(gc, delta);
		//update projectile's location
		currentXLocation += xVelocity;
		currentYLocation += yVelocity;

		if (getDistanceTravelled() >= maxRange)
		{
			gW.projectilesToBeRemoved.add(this);
		}
		
		//keeps rectangle in line with sprite
		setHitBox(currentXLocation + hitBoxXOffSet, currentYLocation + hitBoxYOffSet);
	}
	
	
	
	//used to determine if the projectile has reached its maximum range
	public double getDistanceTravelled()
	{
		return Math.sqrt(Math.pow((currentXLocation - xSpawnLocation), 2) +
						 Math.pow((currentYLocation - ySpawnLocation), 2));
	}

	@Override
	public void render(GameContainer gc, Graphics g)
	{
		if(image != null){
			image.draw(currentXLocation, currentYLocation, 1);	
		}
		
	}

	@Override
	public void init(GameContainer gc)
	{
		// TODO Auto-generated method stub
		
	}
	
		
}
	
	


