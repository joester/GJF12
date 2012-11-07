import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;



public class Ice extends Item
{
/*
 * Damage Range Animation Speed Drop Startup, 
 * 2 		1	 0.01			 No 0.25, 0.01 
 */
	public Ice(float x, float y, String i, World world){
		super(x, y, i, world);
		
		projectileImageLocation = "assets/Art/Transformations/ice.png";
		try {
			projectileImage = new Image(projectileImageLocation);
		} catch (SlickException e) {
			e.printStackTrace();
		}
		
		damage = 4;
		projectileRange = 200;
		projectileXVelocity = 0;
		projectileYVelocity = -7;
		dropChance = false;
		startUpTime = .25;
		reloadTime = 1000;
		projectileOffsets = new Rectangle(10,10,20,0);
	}
	
	@Override
	public void use(World world){
		float x;
		float y;
		projectileRange = 2 * owner.getHitbox().getHeight();
		if(owner.isFacingRight){
			x = owner.xCoord+owner.getHitbox().getWidth()/2 + 30;
			y = owner.yCoord + projectileRange;
			world.getProjectiles().add(new IceProjectile(x, y, this, world));
		}
		else{
			x = owner.xCoord - projectileImage.getWidth()/2 - 30;
			y = owner.yCoord + projectileRange;
			Projectile p = new IceProjectile(x, y, this, world);
			world.getProjectiles().add(p);
			p.flipImage(true, false);
		}
	}
}
