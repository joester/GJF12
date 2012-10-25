

public class Ice extends Item
{
/*
 * Damage Range Animation Speed Drop Startup, 
 * 2 		1	 0.01			 No 0.25, 0.01 
 */
	
	public Ice(int x, int y, String i, int xVel, int yVel){
		super(x, y, i, xVel, yVel);
		damage = 4;
		projectileRange = 200;
		projectileXSpeed = 0;
		projectileYSpeed = -7;
		dropChance = false;
		startUpTime = .25;
		reloadTime = 1000;
		projectileImageLocation = "assets/Art/Transformations/ice.png";
		hitBoxXPosOffSet = 10;
		hitBoxYPosOffSet = 10;
		hitBoxXOffSet = 20;
		hitBoxYOffSet = 20;
	}
	
	public void use(GameWorld gW,Character c){
		int x;
		int y;
		projectileRange = (int) (2 * c.getHitBox().getHeight());
		if(c.isFacingRight){
			x = (int)(c.xCoord+c.getHitBox().getWidth()/2 + 30);
			y = (int)(c.yCoord + projectileRange);
			gW.listOfProjectiles.add(new IceProjectile(x, y,
					projectileImage, 
					projectileXSpeed, projectileYSpeed, 
					damage, projectileRange, true,
					hitBoxXPosOffSet,hitBoxYPosOffSet,hitBoxXOffSet,hitBoxYOffSet,
					c,gW));
		}
		else{
			x = (int)(c.xCoord - projectileImage.getWidth()/2 - 30);
			y = (int)(c.yCoord + projectileRange);
			gW.listOfProjectiles.add(new IceProjectile(x, y,
					projectileImage.getFlippedCopy(true, false),
					-projectileXSpeed, projectileYSpeed, 
					damage, projectileRange, false,
					hitBoxXPosOffSet,hitBoxYPosOffSet,hitBoxXOffSet,hitBoxYOffSet,
					c,gW));
		}
	}
}
