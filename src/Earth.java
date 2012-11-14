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

		try {
			projectileRightImage = new Image("assets/Art/Transformations/Animations/earthsingle.png");
			projectileLeftImage = projectileRightImage.getFlippedCopy(true, false);
		} catch (SlickException e) {
			e.printStackTrace();
		}
		projectileWidth = projectileRightImage.getWidth();
		projectileHeight = projectileRightImage.getHeight();
		damage = 5;
		//projectileRange = 150;
		//projectileXVelocity = 3; 
		dropChance = true;
		startUpTime = .05;
		reloadTime = 1500;	
		projectileOffsets = new Rectangle(10,10,20,20);
	}
	
	@Override
	public void use(World world){
		float x = owner.getHitbox().getCenterX();
		float y = owner.getHitbox().getCenterY() - 84;
		if(owner.isFacingRight){
			world.getProjectiles().add(new EarthProjectile(x, y, this, world));
		}
		else{
			Projectile p = new EarthProjectile(x, y, this, world);
			world.getProjectiles().add(p);
			p.flip();
		}
	}
}
