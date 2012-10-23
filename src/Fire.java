import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;


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
		range = 400;
		speed = 5;
		dropChance = false;
		startUpTime = .01;
		reloadTime = .9;
		projectileImageLocation = "/assets/Art/Transformations/fireball.png";
		  try {
				pImage = new Image(projectileImageLocation);
			} catch (SlickException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
}
