import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Earth extends Item
{
	/*
	 * Weapon Chart

	Damage Range Animation Speed Drop Startup, Reload

EARTH  5  1(3 if above) 1 Yes 0.05, 0.01

	 */	
	public Earth(float x, float y, String i){
		super(x, y, i);
		
		projectileImageLocation = "assets/Art/Transformations/boulder.png";
		try {
			projectileImage = new Image(projectileImageLocation);
		} catch (SlickException e) {
			e.printStackTrace();
		}
		
		damage = 5;
		projectileRange = 150;
		projectileXSpeed = 3; 
		dropChance = true;
		startUpTime = .05;
		reloadTime = 1500;	
		pXPosOffset = 10;
		pYPosOffset = 10;
		pXOffset = 20;
		pYOffset = 20;
	}
}
