import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;



public class Lightning extends Item
{
/*
 *	Damage Range Animation Speed Drop Startup, Reload  
 *	2 		1		 0.1 		No		 0.01, 0.01 
 */
	
	public Lightning(float x, float y, String i, World world){
		super(x, y, i, world);
		
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
		projectileOffsets = new Rectangle(10,10,20,20);
	}	
}
