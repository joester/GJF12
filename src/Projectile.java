import org.newdawn.slick.Animation;
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
	protected Animation leftAnimation;
	protected Animation rightAnimation;
	protected Animation currentAnimation;//

	public Projectile(float xSpawnLocation, float ySpawnLocation, Item item, World world){
		super(xSpawnLocation, ySpawnLocation, item.projectileImageLocation, world);
		this.xSpawnLocation = xSpawnLocation;
		this.ySpawnLocation = ySpawnLocation;
		xVelocity = item.projectileXVelocity;
		yVelocity = item.projectileYVelocity;	
		maxRange = item.projectileRange;
		this.setDamage(item.damage);
		setOwner(item.owner);
		leftAnimation = item.projectileLeftAnimation;
		rightAnimation = item.projectileRightAnimation;
		currentAnimation = rightAnimation;
		hitbox.setSize(item.projectileWidth, item.projectileHeight);
		getHitbox().setOffsets(item.projectileOffsets);
	}

	public Projectile(float xSpawnLocation, float ySpawnLocation, World world){
		super(xSpawnLocation, ySpawnLocation, world);
	}

	public void flip(){
		if(isFlippedHorizontally){
			currentAnimation = rightAnimation;
			isFlippedHorizontally = false;
		}
		else{
			currentAnimation = leftAnimation;
			isFlippedHorizontally = true;
		}
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
			world.remove(this);
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
		currentAnimation.draw(xCoord,yCoord);
		if(currentAnimation.isStopped()){
			currentAnimation.restart();
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
					world.remove(this);
					world.punchHit.get((int) (3 * Math.random())).play();
				}
			}
		}
		for(Block b: world.getBlocks()){
			if(getHitbox().intersects(b.getHitbox())){
				if(b.blockType == BlockType.Impassable){		
					world.remove(this);
				}
				else if(b.blockType == BlockType.Crate){
					b.currentCrateHealth -= damage;
					world.remove(this);
					world.playRandomSound(world.punchHit);
					if (b.currentCrateHealth <= 0)
					{
						world.spawnItem(b);						
						world.remove(this);
					}
				}
			}
		}
	}		

}




