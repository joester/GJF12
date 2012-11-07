import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;

public class Earth extends Item
{
	/*
	 * Weapon Chart

	Damage Range Animation Speed Drop Startup, Reload

EARTH  5  1(3 if above) 1 Yes 0.05, 0.01

	 */	
	public Earth(float x, float y, String i, World world){
		super(x, y, i, world);
		
		projectileImageLocation = "assets/Art/Transformations/boulder.png";
		try {
			projectileImage = new Image(projectileImageLocation);
		} catch (SlickException e) {
			e.printStackTrace();
		}
		
		damage = 5;
		projectileRange = 150;
		projectileXVelocity = 3; 
		dropChance = true;
		startUpTime = .05;
		reloadTime = 1500;	
		projectileOffsets = new Rectangle(10,10,20,20);
	}
}
