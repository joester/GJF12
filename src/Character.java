
public class Character {
public int maxHealth;  
public int hP;
public int damage;
public int healthRegen;
public String name;
public int baseDamage;
public double range;
public double baseRange;
public int wins;
public int winsNeeded;
public String itemName;
public String aux;


	public Character(String n, int p, int h)
	{
		name=n;
		maxHealth = h;
		hP = h;
		damage = 5;
		healthRegen= 1;
		winsNeeded = p;
		itemName = null;
		aux = null;
	}

	public String getName()
	{
		return name;
	}

	public int gethP()
	{
		return hP;
	}
	
	public void getHit(Character c)
	{
		hP = hP - c.damage;
	}
	
	public int getDamage()
	{
	   return damage;
	}
	
	public void pickupitem(Items i)
	{
		damage= i.damage;
		range = i.range;
		itemName = i.name;
	}
	
	public void dropitem()
	{
		damage = baseDamage;
		range = baseRange;
		itemName = null;
	}
	
	public void attack(Character c)
	{
		c.getHit(this);
	}
	
	public void pickUpAux(Auxillary i)
	{
		aux = i.name;
	}
	
	public void useAux()
	{
		
	}
	
	
	
}
