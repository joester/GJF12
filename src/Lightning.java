import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;


public class Lightning extends Item
{
/*
 *	Damage Range Animation Speed Drop Startup, Reload  
 *	2 		1		 0.1 		No		 0.01, 0.01 
 */
	
	public Lightning(int x, int y, String i, int xVel, int yVel){
		super(x, y, i, xVel, yVel);
		damage = 2;
		projectileRange = 400;
		projectileXSpeed = 3;
		dropChance = false;
		startUpTime = .01;
		reloadTime = .01;
		projectileImageLocation = "/assets/Art/Transformations/lightning.png";
		hitBoxXPosOffSet = 10;
		hitBoxYPosOffSet = 10;
		hitBoxXOffSet = 20;
		hitBoxYOffSet = 20;
		
	}	
}
