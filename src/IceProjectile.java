import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;


public class IceProjectile extends Projectile {
	private int delayDuration;
	private int lingerDuration;
	private int cycles;

	public IceProjectile(float xSpawnLocation, float ySpawnLocation, Item item, World world) {
		super(xSpawnLocation, ySpawnLocation, item, world);
		delayDuration = 250;
		lingerDuration = 125;
		cycles = 3;
	}

	@Override
	public void checkCollisions(){
		for (Character c : world.getCharacters())
		{
			if(owner != c){
				if (getHitbox().intersects(c.getHitbox()))
				{			
					c.modifyHealth(getDamage());
					world.removeProjectile(this);
					world.punchHit.get((int) (3 * Math.random())).play();
				}
			}
		}
		for (Projectile p : world.getProjectiles())
		{
			if(p != this && p.getOwner() != owner){
				if (getHitbox().intersects(p.getHitbox()))
				{			
					world.removeProjectile(p);
				}
			}
		}
		for(Block b: world.getBlocks()){
			if(getHitbox().intersects(b.getHitbox()) && b.blockType == BlockType.Crate){
				world.spawnItem(b);
				world.removeCrate(b);
				world.playRandomSound(world.punchHit);
			}
		}
	}

	@Override
	public void update(GameContainer gc, int delta) throws SlickException, InterruptedException
	{
		//update projectile's location
		if(cycles - 1 == 2){
			if(delayDuration <= 0){
				if (getDistanceTravelled() >= maxRange)
				{
					if(lingerDuration <= 0){
						if(!isFlippedHorizontally){
							xSpawnLocation = xCoord = xSpawnLocation + hitbox.getWidth();
						}
						else{
							xSpawnLocation = xCoord = xSpawnLocation - hitbox.getWidth();							
						}
						ySpawnLocation = yCoord = ySpawnLocation - hitbox.getHeight()/2;
						maxRange -= hitbox.getHeight()/2;	
						lingerDuration = 250;
						delayDuration = 125;
						cycles--;

					}
					else
						lingerDuration -= delta;
				}
				else{
					xCoord += xVelocity;
					yCoord += yVelocity;
				}
			}
			else{
				delayDuration -= delta;
			}}
		else if(cycles - 1 == 1){
			if (getDistanceTravelled() >= maxRange)
			{
				if(lingerDuration <= 0){
					if(!isFlippedHorizontally){
						xSpawnLocation = xCoord = xSpawnLocation + hitbox.getWidth();
					}
					else{
						xSpawnLocation = xCoord = xSpawnLocation - hitbox.getWidth();							
					}
					ySpawnLocation = yCoord = ySpawnLocation - hitbox.getHeight()/2;
					maxRange -= hitbox.getHeight()/2;	
					lingerDuration = 250;
					cycles--;
				}
				else
					lingerDuration -= delta;
			}
			else{
				xCoord += xVelocity;
				yCoord += yVelocity;
			}
		}
		else{
			if (getDistanceTravelled() >= maxRange)	{
				if(lingerDuration <= 0){
					world.removeProjectile(this);
				}
				else
					lingerDuration -= delta;
			}
			else{
				xCoord += xVelocity;
				yCoord += yVelocity;
			}
		}
		//keeps rectangle in line with sprite
		setHitboxLocation(xCoord,yCoord);
	}
}
