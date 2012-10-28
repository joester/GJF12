import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;



public class Fire extends Item
{
	/*
	 * Damage Range Animation Speed Drop Startup, 
	 * 3 Infinite 0.5, 4.5 bps No 0.01, 0.7-1 
	 */
	public Fire(float x, float y, String i, GameWorld gW)
	{
		super(x, y, i, gW);
		
		projectileImageLocation = "assets/Art/Transformations/fireball.png";
		try {
			projectileImage = new Image(projectileImageLocation);
		} catch (SlickException e) {
			e.printStackTrace();
		}
		
		damage = 2;
		projectileRange = 500;
		projectileXVelocity = 4;
		dropChance = false;
		startUpTime = .01;
		reloadTime = 600;
		pXPosOffset = 5;
		pYPosOffset = 30;
		pXOffset = 10;
		pYOffset = 50;
	}
}
