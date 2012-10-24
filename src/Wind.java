

public class Wind extends Item
{
/*
 * 	Damage Range Animation Speed Drop Startup, Reload 
 * WIND 1 3-4, (push 5) 0.2, 99 bps Yes 0.01, 1 
 */
	private int push = 5;
	
	public Wind(int x, int y, String i, int xVel, int yVel){
		super(x, y, i, xVel, yVel);
		damage = 2;
		projectileRange = 500;
		projectileXSpeed = 3;
		dropChance = true;
		startUpTime = .01;
		reloadTime = 1000;
		projectileImageLocation = "/assets/Art/Transformations/wind.png";
		hitBoxXPosOffSet = 10;
		hitBoxYPosOffSet = 10;
		hitBoxXOffSet = 20;
		hitBoxYOffSet = 20;
	}
}
