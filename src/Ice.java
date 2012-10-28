import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;



public class Ice extends Item
{
/*
 * Damage Range Animation Speed Drop Startup, 
 * 2 		1	 0.01			 No 0.25, 0.01 
 */
	
	public Ice(float x, float y, String i, GameWorld gW){
		super(x, y, i, gW);
		
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
		pXPosOffset = 10;
		pYPosOffset = 10;
		pXOffset = 20;
		pYOffset = 0;
	}
	
	@Override
	public void use(GameWorld gW){
		float x;
		float y;
		projectileRange = 2 * owner.getHitBox().getHeight();
		if(owner.isFacingRight){
			x = owner.xCoord+owner.getHitBox().getWidth()/2 + 30;
			y = owner.yCoord + projectileRange;
			gW.getListOfProjectiles().add(new IceProjectile(x, y, this, gW));
		}
		else{
			x = owner.xCoord - projectileImage.getWidth()/2 - 30;
			y = owner.yCoord + projectileRange;
			Projectile p = new IceProjectile(x, y, this, gW);
			gW.getListOfProjectiles().add(p);
			p.flipImage(true, false);
		}
	}
}
