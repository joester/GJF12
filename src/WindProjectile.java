
public class WindProjectile extends Projectile {
	
	private float KBDistance;
	public WindProjectile(float xSpawnLocation, float ySpawnLocation,
			Wind item, World world) {
		super(xSpawnLocation, ySpawnLocation, item, world);
		KBDistance = item.projectileKBDistance;
	}
	
	@Override
	public void checkCollisions() {
		for (Character c : world.getCharacters())
		{
			if(owner != c){
				if (getHitbox().intersects(c.getHitbox()))
				{			
					c.modifyHealth(damage);
					if(xVelocity > 0)
						c.knockBack(KBDistance, 3);
					else
						c.knockBack(KBDistance, -3);
					world.remove(this);
					world.punchHit.get((int) (3 * Math.random())).play();
				}
			}
		}
		for(Block b: world.getBlocks()){
			if(getHitbox().intersects(b.getHitbox())){
				if(b.blockType == BlockType.Impassable){		
					world.remove(this);
				}
				else if(b.blockType == BlockType.Crate){
					world.spawnItem(b);
					world.playRandomSound(world.punchHit);
					world.remove(this);
				}
			}
		}
	}		
	

}
