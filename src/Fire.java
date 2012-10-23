

public class Fire extends Item
{
	/*
	 * Damage Range Animation Speed Drop Startup, 
	 * 3 Infinite 0.5, 4.5 bps No 0.01, 0.7-1 
	 */
	public Fire(int x, int y, String i, int xVelocity, int yVelocity)
	{
		super(x, y, i, xVelocity, yVelocity);
		damage = 3;
		projectileRange = 400;
		projectileXSpeed = 3;
		dropChance = false;
		startUpTime = .01;
		reloadTime = .9;
		projectileImageLocation = "/assets/Art/Transformations/fireball.png";
		hitBoxXPosOffSet = 5;
		hitBoxYPosOffSet = 5;
		hitBoxXOffSet = 10;
		hitBoxYOffSet = 10;
	}
}
