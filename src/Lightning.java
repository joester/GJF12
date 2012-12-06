import java.util.ArrayList;

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
	public int maxProjectiles = 3;
	public ArrayList<LightningProjectile> projectiles = new ArrayList<LightningProjectile>(); 
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
		reloadTime = 2000;
		projectileOffsets = new Rectangle(10,10,20,20);
	}	

	@Override
	public void use(World world)
	{		

		if(owner.isFacingRight){
			float x = owner.getHitbox().getCenterX();
			float y = owner.getHitbox().getY();
			LightningProjectile p = new LightningProjectile(x,y,this,world);
			world.getProjectiles().add(p);
			if(projectiles.size() == maxProjectiles){
				world.getProjectiles().remove(projectiles.remove(0));
			}
			projectiles.add(p);

		}
		else{
			float x = owner.getHitbox().getCenterX() - projectileWidth;
			float y = owner.getHitbox().getY();
			LightningProjectile p = new LightningProjectile(x, y, this, world);
			world.getProjectiles().add(p);
			if(projectiles.size() == maxProjectiles){
				world.getProjectiles().remove(projectiles.remove(0));
			}
			projectiles.add(p);
			p.flip();
			p.setXVelocity(-projectileXVelocity);
		}
	}

	public void remove(Projectile p){
		projectiles.remove(p);
	}
}

