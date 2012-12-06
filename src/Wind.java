import org.newdawn.slick.Animation;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.geom.Rectangle;



public class Wind extends Item
{
/*
 * 	Damage Range Animation Speed Drop Startup, Reload 
 * WIND 1 3-4, (push 5) 0.2, 99 bps Yes 0.01, 1 
 */
	public float projectileKBDistance;

	public Wind(float x, float y, String i, World world){
		super(x, y, i, world);
		
		int numFrames = 5;
		Image sheet;
		try {
			sheet = new Image("assets/Art/Transformations/Animations/wind.png");
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
		
	
		damage = 2;
		projectileRange = 100;
		projectileXVelocity = 3;
		dropChance = true;
		startUpTime = .01;
		reloadTime = 1000;
		projectileOffsets = new Rectangle(10,20,20,40);
		projectileKBDistance = 168;
	}
	
	public void use(World world)
	{		
		if(owner.isFacingRight){
			float x = owner.getHitbox().getCenterX();
			float y = owner.getHitbox().getY();
			world.getProjectiles().add(new WindProjectile(x, y,this, world));
		}
		else{
			float x = owner.getHitbox().getCenterX() - projectileWidth;
			float y = owner.getHitbox().getY();
			Projectile p = new WindProjectile(x, y, this, world);
			world.getProjectiles().add(p);
			p.flip();
			p.setXVelocity(-projectileXVelocity);
		}
	}
}
