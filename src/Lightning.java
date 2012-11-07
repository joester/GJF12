import org.newdawn.slick.Animation;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.geom.Rectangle;



public class Lightning extends Item
{
/*
 *	Damage Range Animation Speed Drop Startup, Reload  
 *	2 		1		 0.1 		No		 0.01, 0.01 
 */
	
	public Lightning(float x, float y, String i, World world){
		super(x, y, i, world);
		
		int numFrames = 4;
		Image sheet;
		try {
			sheet = new Image("assets/Art/Transformations/Animations/lightning.png");
			projectileWidth = sheet.getWidth() / numFrames;
			projectileHeight = sheet.getHeight();
			SpriteSheet ss = new SpriteSheet(sheet, projectileWidth, projectileHeight);
			projectileRightAnimation = new Animation(ss, 100);	
			projectileRightAnimation.stopAt(projectileRightAnimation.getFrameCount() - 1);
			ss = new SpriteSheet(ss.getFlippedCopy(true, false), projectileWidth, projectileHeight);
			projectileLeftAnimation = new Animation(ss , 100);	
			projectileLeftAnimation.stopAt(projectileLeftAnimation.getFrameCount() - 1); 
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
