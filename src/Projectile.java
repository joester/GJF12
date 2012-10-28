import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;
import org.newdawn.slick.geom.Rectangle;


public class Projectile extends Entity
{
	protected float xSpawnLocation;
	protected float ySpawnLocation;
	protected float xVelocity;
	protected float yVelocity;
	protected int damage;
	protected float maxRange;
	protected Character owner;
	protected boolean isFlippedHorizontally;
	protected boolean isFlippedVertically;

	public Projectile(float xSpawnLocation, float ySpawnLocation, Item item, GameWorld gW){
		super(xSpawnLocation, ySpawnLocation, item.projectileImageLocation, gW);
		this.xSpawnLocation = xSpawnLocation;
		this.ySpawnLocation = ySpawnLocation;
		xVelocity = item.projectileXVelocity;
		yVelocity = item.projectileYVelocity;	
		maxRange = item.projectileRange;
		this.setDamage(item.damage);
		setOwner(item.owner);
		this.image = item.projectileImage;
		setHitBoxOffsets(item.pXPosOffset, item.pYPosOffset, item.pXOffset, item.pYOffset);
	}

	public Projectile(float xSpawnLocation, float ySpawnLocation, GameWorld gW){
		super(xSpawnLocation, ySpawnLocation, gW);
	}

	public void flipImage(boolean horizontal, boolean vertical){
		isFlippedHorizontally = horizontal;
		isFlippedVertically = vertical;
		image = image.getFlippedCopy(horizontal, vertical);
	}

	public void setXVelocity(float projectileXVelocity){
		xVelocity = projectileXVelocity;
	}

	public void setYVelocity(float vel){
		yVelocity = vel;
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
					gW.punchHit.get((int) (3 * Math.random())).play();
				}
			}
		}
		for(Block b: gW.getListOfBlocks()){
			if(getHitBox().intersects(b.getHitBox())){
				if(b.blockType == BlockType.Impassable){		
					gW.removeProjectile(this);
				}
				else if(b.blockType == BlockType.Crate){
					gW.spawnItem(b);
					gW.playRandomSound(gW.punchHit);
					gW.removeProjectile(this);
				}
			}
		}
	}		

}




