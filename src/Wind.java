import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;



public class Wind extends Item
{
/*
 * 	Damage Range Animation Speed Drop Startup, Reload 
 * WIND 1 3-4, (push 5) 0.2, 99 bps Yes 0.01, 1 
 */
	private int push = 5;
	
	public Wind(float x, float y, String i, World world){
		super(x, y, i, world);
		
		projectileImageLocation = "assets/Art/Transformations/wind.png";
		try {
			projectileImage = new Image(projectileImageLocation);
		} catch (SlickException e) {
			e.printStackTrace();
		}
		
		damage = 2;
		projectileRange = 500;
		projectileXVelocity = 3;
		dropChance = true;
		startUpTime = .01;
		reloadTime = 1000;
		projectileOffsets = new Rectangle(10,20,20,40);
	}
}
