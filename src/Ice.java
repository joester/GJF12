
public class Ice extends Item
{
/*
 * Damage Range Animation Speed Drop Startup, 
 * 2 		1	 0.01			 No 0.25, 0.01 
 */
	
	public Ice(int x, int y, String i, int xVel, int yVel){
		super(x, y, i, xVel, yVel);
		damage = 2;
		range = 200;
		speed = .01;
		dropChance = false;
		startUpTime = .25;
		reloadTime = .01;
		
	}
}
