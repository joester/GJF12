import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;


public class Projectile extends Entity
{
	protected float xSpawnLocation;
	protected float ySpawnLocation;
	protected float xVelocity;
	protected float yVelocity;
	protected int damage;
	protected float maxRange;
	protected GameWorld gW;
	protected Character owner;

	public Projectile (float xSpawnLocation, float ySpawnLocation, Image image, float xVelocity, float yVelocity, int damage,
			float maxRange, Rectangle hitBox, Character owner, GameWorld gW)
	{
		super(xSpawnLocation, ySpawnLocation);
		this.gW = gW;
		this.xSpawnLocation = xSpawnLocation;
		this.ySpawnLocation = ySpawnLocation;
		this.xVelocity = xVelocity;
		this.yVelocity = yVelocity;
		this.hitBox = hitBox;		
		this.maxRange = maxRange;
		this.setDamage(damage);
		this.setOwner(owner);
		this.image = image;
	}

	public Projectile(float xSpawnLocation, float ySpawnLocation, Image image,
			float projectileXSpeed, float projectileYSpeed, int damage, float projectileRange,
			float hitBoxXPosOffset, float hitBoxYPosOffset, float hitBoxXOffset, float hitBoxYOffset, Character c, GameWorld gW) {
		super(xSpawnLocation, ySpawnLocation,null);
		this.gW = gW;
		this.xSpawnLocation = xSpawnLocation;
		this.ySpawnLocation = ySpawnLocation;
		xVelocity = projectileXSpeed;
		yVelocity = projectileYSpeed;	
		maxRange = projectileRange;
		this.setDamage(damage);
		setOwner(c);
		this.image = image;
		setHitBoxOffsets(hitBoxXPosOffset, hitBoxYPosOffset, hitBoxXOffset, hitBoxYOffset);
	}

	@Override
	public void update(GameContainer gc, int delta) throws SlickException, InterruptedException
	{
		//update projectile's location
		xCoord += xVelocity;
		yCoord += yVelocity;

		if (getDistanceTravelled() >= maxRange)
		{
			gW.removeProjectile(this);
		}

		//keeps rectangle in line with sprite
		super.update(gc, delta);
	}

	//used to determine if the projectile has reached its maximum range
	protected double getDistanceTravelled()
	{
		return Math.sqrt(Math.pow((xCoord - xSpawnLocation), 2) +
				Math.pow((yCoord - ySpawnLocation), 2));
	}

	@Override
	public void render(GameContainer gc, Graphics g)
	{
		g.draw(hitBox);
		if(image != null){
			image.draw(xCoord, yCoord, 1);	
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




