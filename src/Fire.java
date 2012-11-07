import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;



public class Fire extends Item
{
	/*
	 * Damage Range Animation Speed Drop Startup, 
	 * 3 Infinite 0.5, 4.5 bps No 0.01, 0.7-1 
	 */
	public Fire(float x, float y, String i, World world)
	{
		super(x, y, i, world);
		
		projectileImageLocation = "assets/Art/Transformations/fireball.png";
		try {
			projectileImage = new Image(projectileImageLocation);
		} catch (SlickException e) {
			e.printStackTrace();
		}
		
		damage = 3;
		projectileRange = 1500;
		projectileXVelocity = 4;
		dropChance = false;
		startUpTime = 10;
		reloadTime = 600;
		projectileOffsets = new Rectangle(5,30,10,50);
	}
}
