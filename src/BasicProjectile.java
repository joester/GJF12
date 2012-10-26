import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Rectangle;


public class BasicProjectile extends Projectile {

	

	public BasicProjectile(int xSpawnLocation, int ySpawnLocation, Image image,
			int xVelocity, int yVelocity, int damage, int maxRange,
			Rectangle hitBox, Character owner, GameWorld gW) {
		super(xSpawnLocation, ySpawnLocation, image, xVelocity, yVelocity, damage,
				maxRange, hitBox, owner, gW);
		// TODO Auto-generated constructor stub
	}
	
	public BasicProjectile(int xSpawnLocation, int ySpawnLocation, Image image,
			int projectileXSpeed, int projectileYSpeed, int damage,
			int projectileRange, int hitBoxXPosOffSet, int hitBoxYPosOffSet,
			int hitBoxXOffSet, int hitBoxYOffSet, Character c, GameWorld gW) {
		super(xSpawnLocation, ySpawnLocation, image, projectileXSpeed,
				projectileYSpeed, damage, projectileRange, hitBoxXPosOffSet,
				hitBoxYPosOffSet, hitBoxXOffSet, hitBoxYOffSet, c, gW);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void checkCollisions() {
		for (Character c : gW.getListOfCharacters())
		{
			if(owner != c){
				if (getHitBox().intersects(c.getHitBox()))
				{			
					c.modifyHealth(damage);
					gW.removeProjectile(this);
				}
			}
		}

		for(Block b: gW.getListOfBlocks()){
			if(b.blockType == BlockType.Impassable){
				if (getHitBox().intersects(b.getHitBox()))
				{			
					gW.removeProjectile(this);
				}

			}
		}
	}		

}
