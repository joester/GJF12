
public class Lightning extends Item
{
/*
 *	Damage Range Animation Speed Drop Startup, Reload  
 *	2 		1		 0.1 		No		 0.01, 0.01 
 */
	
	public Lightning(int x, int y, String i, int xVel, int yVel){
		super(x, y, i, xVel, yVel);
		damage = 2;
		range = 100;
		speed = .1;
		dropChance = false;
		startUpTime = .01;
		reloadTime = .01;
	}	
}
