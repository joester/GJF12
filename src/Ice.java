import org.newdawn.slick.Animation;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.geom.Rectangle;



public class Ice extends Item
{
/*
 * Damage Range Animation Speed Drop Startup, 
 * 2 		1	 0.01			 No 0.25, 0.01 
 */
	
	public Ice(float x, float y, String i, World world){
		super(x, y, i, world);
		
		int numFrames = 7;
		Image sheet;
		try {
			sheet = new Image("assets/Art/Transformations/Animations/ice.png");
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
		
		damage = 4;
		//projectileRange = 200;
		//projectileXVelocity = 0;
		//projectileYVelocity = -7;
		dropChance = false;
		startUpTime = .25;
		reloadTime = 2000;
		projectileOffsets = new Rectangle(10,10,20,0);
	}
	
	@Override
	public void use(World world){
		float x;
		float y;
		projectileRange = 2 * owner.getHitbox().getHeight();
		if(owner.isFacingRight){
			x = owner.getHitbox().getCenterX() + 30;
			y = owner.getHitbox().getY(); //+ projectileRange;
			world.getProjectiles().add(new IceProjectile(x, y, this, world));
		}
		else{
			x = owner.getHitbox().getCenterX() - projectileWidth - 30;
			y = owner.getHitbox().getY(); //+ projectileRange;
			Projectile p = new IceProjectile(x, y, this, world);
			world.getProjectiles().add(p);
			p.flip();
		}
	}
}
