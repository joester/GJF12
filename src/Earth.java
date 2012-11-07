import org.newdawn.slick.Animation;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
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
		
		int numFrames = 1;
		Image sheet;
		try {
			sheet = new Image("assets/Art/Transformations/Animations/earthsingle.png");
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
		
		damage = 5;
		projectileRange = 150;
		projectileXVelocity = 3; 
		dropChance = true;
		startUpTime = .05;
		reloadTime = 1500;	
		projectileOffsets = new Rectangle(10,10,20,20);
	}
}
