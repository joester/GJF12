import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;



public class Wind extends Item
{
/*
 * 	Damage Range Animation Speed Drop Startup, Reload 
 * WIND 1 3-4, (push 5) 0.2, 99 bps Yes 0.01, 1 
 */
	public float projectileKBDistance;
	
	public Wind(float x, float y, String i, World world){
		super(x, y, i, world);
		
		projectileImageLocation = "assets/Art/Transformations/wind.png";
		try {
			projectileImage = new Image(projectileImageLocation);
		} catch (SlickException e) {
			e.printStackTrace();
		}
		
		damage = 2;
		projectileRange = 100;
		projectileXVelocity = 3;
		dropChance = true;
		startUpTime = .01;
		reloadTime = 1000;
		projectileOffsets = new Rectangle(10,20,20,40);
		projectileKBDistance = 168;
	}
	
	public void use(World world)
	{		
		if(owner.isFacingRight){
			float x = owner.xCoord+owner.getHitbox().getWidth()/2;
			float y = owner.yCoord;
			world.getProjectiles().add(new WindProjectile(x, y,this, world));
		}
		else{
			float x = owner.xCoord - projectileImage.getWidth()/2;
			float y = owner.yCoord;
			Projectile p = new WindProjectile(x, y, this, world);
			world.getProjectiles().add(p);
			p.flipImage(true, false);
			p.setXVelocity(-projectileXVelocity);
		}
	}
}
