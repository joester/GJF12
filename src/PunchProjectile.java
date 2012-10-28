
public class PunchProjectile extends Projectile {
	public PunchProjectile(float xSpawnLocation, float ySpawnLocation,
			Character character, GameWorld gW) {
		super(xSpawnLocation, ySpawnLocation, gW);
		owner = character;
		maxRange = 0;
		damage = character.baseDamage;
		setHitBoxSize(20,40);
	}
}
