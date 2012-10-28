import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;



public class Ice extends Item
{
/*
 * Damage Range Animation Speed Drop Startup, 
 * 2 		1	 0.01			 No 0.25, 0.01 
 */
	
	public Ice(float x, float y, String i){
		super(x, y, i);
		
		projectileImageLocation = "assets/Art/Transformations/ice.png";
		try {
			projectileImage = new Image(projectileImageLocation);
		} catch (SlickException e) {
			e.printStackTrace();
		}
		
		damage = 4;
		projectileRange = 200;
		projectileXSpeed = 0;
		projectileYSpeed = -7;
		dropChance = false;
		startUpTime = .25;
		reloadTime = 1000;
		pXPosOffset = 10;
		pYPosOffset = 10;
		pXOffset = 20;
		pYOffset = 20;
	}
	
	@Override
	public void use(GameWorld gW,Character c){
		float x;
		float y;
		projectileRange = 2 * c.getHitBox().getHeight();
		if(c.isFacingRight){
			x = c.xCoord+c.getHitBox().getWidth()/2 + 30;
			y = c.yCoord + projectileRange;
			gW.getListOfProjectiles().add(new IceProjectile(x, y,
					projectileImage, 
					projectileXSpeed, projectileYSpeed, 
					damage, projectileRange, true,
					pXPosOffset,pYPosOffset,pXOffset,pYOffset,
					c,gW));
		}
		else{
			x = c.xCoord - projectileImage.getWidth()/2 - 30;
			y = c.yCoord + projectileRange;
			gW.getListOfProjectiles().add(new IceProjectile(x, y,
					projectileImage.getFlippedCopy(true, false),
					-projectileXSpeed, projectileYSpeed, 
					damage, projectileRange, false,
					pXPosOffset,pYPosOffset,pXOffset,pYOffset,
					c,gW));
		}
	}
}
