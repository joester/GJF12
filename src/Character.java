
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;


public class Character extends Entity{
	
	public int maxHealth;  
	public int hP;
	public int damage;
	public int healthRegen;
	public int wins;
	public int baseDamage;
	//public int winsNeeded;
	
	public String name;
	public boolean hasItem;
	public Items item;
	public Auxillary aux;
	public boolean hasAux;
	public double range;
	public double baseRange;
	
	public Image charImg = null;
	
	/**
	 public ArrayList<BufferedImage> pics= new ArrayList<BufferedImage>();
	 BufferedImage img = null;
	 GameWorld gameWorld = new GameWorld();
	 Set<Body> bodies = new HashSet<Body>();
	 **/


	public Character(int x,int y, String imageLocation)
	{
			super(x,y, imageLocation);
			hasAux = false;
			hasItem = false;
			//Place holder numbers
			hP = 20;
			baseDamage = 2;
			wins = 0;	
	/**
		name = n;
		maxHealth = h;
		hP = h;
		damage = 5;
		healthRegen = 1;
		winsNeeded = p;
		itemName = null;
		aux = null;
	 */
	}

	
/**
 * 

	
	public void createCollisionBox()
	{
		BodyDef boxDef = new BodyDef();
		boxDef.position.set(0, 0);  // change coordinates
		boxDef.type = BodyType.KINEMATIC;
		PolygonShape boxShape = new PolygonShape();
		boxShape.setAsBox(40, 40); // change size
		Body box = gameWorld.getWorld().createBody(boxDef);
		FixtureDef boxFixture = new FixtureDef();
		boxFixture.density = 1;
		boxFixture.shape = boxShape;
		box.createFixture(boxFixture);
		bodies.add(box);		
	}
	
	
	**/


	public int gethP()
	{
		return hP;
	}
	
	public void modifyHealth(int deltaHealth)
	{
		hP += deltaHealth;
		
	}
	
	public int getDamage()
	{
	   return damage;
	}
	
	public void pickupitem(Items i)
	{
		damage = i.damage;
		range = i.range;
		item= i;
		hasItem = true;
	}
	
	public void pickupAux(Auxillary a)
	{
		aux = a;
		hasAux = true;
	}
	public void dropitem()
	{
		damage = baseDamage;
		range = baseRange;
		item = null;
		hasItem = false;
	}
	public String useAux()
	{
		if (hasAux= true)
				{
					aux.use();
					hasAux = false;
					aux = null;
					return "";
				}
		else
		{
			return "No Auxillary";
		}
	}
	
	public void attack()
	{
		if (hasItem)
		{
		item.use();
		}
		else
		{
			//TODO write basic attack code, unknown values for now. 
			
		}
	}
	
}
