
public class Wind extends Items
{
/*
 * 	Damage Range Animation Speed Drop Startup, Reload 
 * WIND 1 3-4, (push 5) 0.2, 99 bps Yes 0.01, 1 
 */
	private int push = 5;
	
	public Wind(int x, int y, String i)
	{
		super( x,  y,  i);
		damage = 5;
		damage = 1;
		range = 4;
		speed = .2;
		dropChance = true;
		startUpTime = .01;
		reloadTime = 1;
	}
	
	public void use()
	{
		
	}
}
