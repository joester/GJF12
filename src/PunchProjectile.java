
public class PunchProjectile extends Projectile {
	public PunchProjectile(float xSpawnLocation, float ySpawnLocation,
			Character character, World world) {
		super(xSpawnLocation, ySpawnLocation, world);
		owner = character;
		maxRange = 0;
		damage = character.baseDamage;
		setHitboxSize(20,40);
	}
}
