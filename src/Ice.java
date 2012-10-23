import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;


public class Ice extends Item
{
/*
 * Damage Range Animation Speed Drop Startup, 
 * 2 		1	 0.01			 No 0.25, 0.01 
 */
	
	public Ice(int x, int y, String i, int xVel, int yVel){
		super(x, y, i, xVel, yVel);
		damage = 2;
		projectileRange = 500;
		projectileXSpeed = 3;
		dropChance = false;
		startUpTime = .25;
		reloadTime = .01;
		projectileImageLocation = "/assets/Art/Transformations/ice.png";
		hitBoxXPosOffSet = 10;
		hitBoxYPosOffSet = 10;
		hitBoxXOffSet = 20;
		hitBoxYOffSet = 20;
	}
}
