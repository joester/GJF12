package gamejam.fighter;

public class Lightning extends Items
{
/*
 *	Damage Range Animation Speed Drop Startup, Reload  
 *	2 		1		 0.1 		No		 0.01, 0.01 
 */
	
	public Lightning(int x, int y, String i)
	{
		super( x,  y,  i);
		damage = 5;
		damage = 2;
		range = 1;
		speed = .1;
		dropChance = false;
		startUpTime = .01;
		reloadTime = .01;
	}
	public void use()
	{
		
	}
	
}
