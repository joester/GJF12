

public class Fire extends Item
{
	/*
	 * Damage Range Animation Speed Drop Startup, 
	 * 3 Infinite 0.5, 4.5 bps No 0.01, 0.7-1 
	 */
	public Fire(int x, int y, String i, int xVelocity, int yVelocity)
	{
		super(x, y, i, xVelocity, yVelocity);
		damage = 2;
		projectileRange = 500;
		projectileXSpeed = 4;
		dropChance = false;
		startUpTime = .01;
		reloadTime = 600;
		projectileImageLocation = "/assets/Art/Transformations/fireball.png";
		hitBoxXPosOffSet = 5;
		hitBoxYPosOffSet = 30;
		hitBoxXOffSet = 10;
		hitBoxYOffSet = 50;
	}
}
