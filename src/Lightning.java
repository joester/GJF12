import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;



public class Lightning extends Item
{
/*
 *	Damage Range Animation Speed Drop Startup, Reload  
 *	2 		1		 0.1 		No		 0.01, 0.01 
 */
	
	public Lightning(float x, float y, String i, GameWorld gW){
		super(x, y, i, gW);
		
		projectileImageLocation = "assets/Art/Transformations/lightning.png";
		try {
			projectileImage = new Image(projectileImageLocation);
		} catch (SlickException e) {
			e.printStackTrace();
		}
		
		damage = 3;
		projectileRange = 600;
		projectileXVelocity = 3;
		dropChance = false;
		startUpTime = .01;
		reloadTime = 800;
		pXPosOffset = 10;
		pYPosOffset = 10;
		pXOffset = 20;
		pYOffset = 20;
		
	}	
}
