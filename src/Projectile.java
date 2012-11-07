import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;


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

	public Projectile(float xSpawnLocation, float ySpawnLocation, Item item, World world){
		super(xSpawnLocation, ySpawnLocation, item.projectileImageLocation, world);
		this.xSpawnLocation = xSpawnLocation;
		this.ySpawnLocation = ySpawnLocation;
		xVelocity = item.projectileXVelocity;
		yVelocity = item.projectileYVelocity;	
		maxRange = item.projectileRange;
		this.setDamage(item.damage);
		setOwner(item.owner);
		this.image = item.projectileImage;
		hitbox.setSize(image.getWidth(),image.getHeight());
		getHitbox().setOffsets(item.projectileOffsets);
	}

	public Projectile(float xSpawnLocation, float ySpawnLocation, World world){
		super(xSpawnLocation, ySpawnLocation, world);
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
			world.removeProjectile(this);
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
	public void render(GameContainer gc, Graphics g) throws SlickException
	{
		super.render(gc, g);
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
		for (Character c : world.getCharacters())
		{
			if(owner != c){
				if (getHitbox().intersects(c.getHitbox()))
				{			
					c.modifyHealth(damage);
					world.removeProjectile(this);
					world.punchHit.get((int) (3 * Math.random())).play();
				}
			}
		}
		for(Block b: world.getBlocks()){
			if(getHitbox().intersects(b.getHitbox())){
				if(b.blockType == BlockType.Impassable){		
					world.removeProjectile(this);
				}
				else if(b.blockType == BlockType.Crate){
					world.spawnItem(b);
					world.playRandomSound(world.punchHit);
					world.removeProjectile(this);
				}
			}
		}
	}		

}




