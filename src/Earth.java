
public class Earth extends Item
{
	/*
	 * Weapon Chart

	Damage Range Animation Speed Drop Startup, Reload

EARTH  5  1(3 if above) 1 Yes 0.05, 0.01

	 */
	private int aboveRange = 3;
	
	public Earth(int x, int y, String i){
		super( x,  y,  i);
		damage = 5;
		range = 1;
		speed = 1; 
		dropChance = true;
		startUpTime = .05;
		reloadTime = .01;	
	}
}
