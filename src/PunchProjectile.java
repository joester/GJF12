import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;


public class PunchProjectile extends Projectile {
	int lingerDuration = 50;
	public PunchProjectile(float xSpawnLocation, float ySpawnLocation,
			Character character, World world) {
		super(xSpawnLocation, ySpawnLocation, world);
		owner = character;
		maxRange = 0;
		damage = character.baseDamage;
		setHitboxSize(40,40);
	}
	
	@Override
	public void render(GameContainer gc, Graphics g) throws SlickException
	{
		//Do nothing
	}
	
	@Override
	public void update(GameContainer gc, int delta) throws SlickException, InterruptedException
	{
		if(lingerDuration <= 0){
			world.remove(this);
		}
		else
			lingerDuration -= delta;
	}
}
