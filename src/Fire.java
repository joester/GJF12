import org.newdawn.slick.Animation;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.geom.Rectangle;



public class Fire extends Item
{
	/*
	 * Damage Range Animation Speed Drop Startup, 
	 * 3 Infinite 0.5, 4.5 bps No 0.01, 0.7-1 
	 */
	public Fire(float x, float y, String i, World world)
	{
		super(x, y, i, world);
		
		int numFrames = 2;
		Image sheet;
		try {
			sheet = new Image("assets/Art/Transformations/Animations/fire.png");
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
		projectileRange = 1500;
		projectileXVelocity = 4;
		dropChance = false;
		startUpTime = 10;
		reloadTime = 600;
		projectileOffsets = new Rectangle(5,30,10,50);
	}
	
	@Override
	public void use(World world)
	{		
		if(owner.isFacingRight){
			float x = owner.getHitbox().getCenterX();
			float y = owner.getHitbox().getY();
			world.getProjectiles().add(new FireProjectile(x, y,this, world));
		}
		else{
			float x = owner.getHitbox().getCenterX() - projectileWidth;
			float y = owner.getHitbox().getY();
			Projectile p = new FireProjectile(x, y, this, world);
			world.getProjectiles().add(p);
			p.flip();
			p.setXVelocity(-projectileXVelocity);
		}
	}

}
