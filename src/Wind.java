import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;



public class Wind extends Item
{
/*
 * 	Damage Range Animation Speed Drop Startup, Reload 
 * WIND 1 3-4, (push 5) 0.2, 99 bps Yes 0.01, 1 
 */
	private int push = 5;
	
	public Wind(float x, float y, String i){
		super(x, y, i);
		
		projectileImageLocation = "assets/Art/Transformations/wind.png";
		try {
			projectileImage = new Image(projectileImageLocation);
		} catch (SlickException e) {
			e.printStackTrace();
		}
		
		damage = 2;
		projectileRange = 500;
		projectileXSpeed = 3;
		dropChance = true;
		startUpTime = .01;
		reloadTime = 1000;
		pXPosOffset = 10;
		pYPosOffset = 10;
		pXOffset = 20;
		pYOffset = 20;
	}
}
