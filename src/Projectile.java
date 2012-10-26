import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;


public class Projectile extends Entity
{
	protected int currentXLocation;
	protected int currentYLocation;
	protected int xSpawnLocation;
	protected int ySpawnLocation;
	protected int xVelocity;
	protected int yVelocity;
	protected int damage;
	protected int maxRange;
	protected GameWorld gW;
	protected Character owner;
	protected int hitBoxXPosOffSet;
	protected int hitBoxYPosOffSet;
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
		this.setDamage(damage);
		this.setOwner(owner);
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
		this.setDamage(damage);
		setOwner(c);
		this.image = image;
		this.hitBoxXPosOffSet = hitBoxXPosOffSet;
		this.hitBoxYPosOffSet = hitBoxYPosOffSet;
		hitBox = new Rectangle(xSpawnLocation + hitBoxXPosOffSet, 
				ySpawnLocation + hitBoxYPosOffSet, 
				image.getWidth() - hitBoxXOffSet,
				image.getHeight() - hitBoxYOffSet);
	}

	@Override
	public void update(GameContainer gc, int delta) throws SlickException, InterruptedException
	{
		//update projectile's location
		currentXLocation += xVelocity;
		currentYLocation += yVelocity;

		if (getDistanceTravelled() >= maxRange)
		{
			gW.removeProjectile(this);
		}

		//keeps rectangle in line with sprite
		setHitBox(currentXLocation + hitBoxXPosOffSet, currentYLocation + hitBoxYPosOffSet);
	}

	//used to determine if the projectile has reached its maximum range
	protected double getDistanceTravelled()
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

	public Character getOwner() {
		return owner;
	}

	public void setOwner(Character owner) {
		this.owner = owner;
	}

	public int getDamage() {
		return damage;
	}

	public void setDamage(int damage) {
		this.damage = damage;
	}

	public void checkCollisions() {
		for (Character c : gW.getListOfCharacters())
		{
			if(owner != c){
				if (getHitBox().intersects(c.getHitBox()))
				{			
					c.modifyHealth(damage);
					gW.removeProjectile(this);
				}
			}
		}

		for(Block b: gW.getListOfBlocks()){
			if(b.blockType == BlockType.Impassable){
				if (getHitBox().intersects(b.getHitBox()))
				{			
					gW.removeProjectile(this);
				}

			}
		}
	}		

}




