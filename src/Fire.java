
public class Fire extends Item
{
	/*
	 * Damage Range Animation Speed Drop Startup, 
	 * 3 Infinite 0.5, 4.5 bps No 0.01, 0.7-1 
	 */
	public Fire(int x, int y, String i)
	{
		super( x,  y,  i);
		damage = 3;
		range = 100;
		speed = 5;
		dropChance = false;
		startUpTime = .01;
		reloadTime = .9;
	}
}
