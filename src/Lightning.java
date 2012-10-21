
public class Lightning extends Item
{
/*
 *	Damage Range Animation Speed Drop Startup, Reload  
 *	2 		1		 0.1 		No		 0.01, 0.01 
 */
	
	public Lightning(int x, int y, String i)
	{
		super( x,  y,  i);
		damage = 2;
		range = 1;
		speed = .1;
		dropChance = false;
		startUpTime = .01;
		reloadTime = .01;
	}
	public void use()
	{
		//not final
		Projectile lightningBolt = new Projectile(xCoord, yCoord, xVelocity, yVelocity, range, hitBox);
	}
	
}
