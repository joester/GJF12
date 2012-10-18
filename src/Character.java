import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.*;


import javax.imageio.ImageIO;
import org.jbox2d.dynamics.*;
import org.jbox2d.collision.shapes.*;
import org.jbox2d.common.Vec2;


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
public ArrayList<BufferedImage> pics= new ArrayList<BufferedImage>();
BufferedImage img = null;
GameWorld gameWorld = new GameWorld();
Set<Body> bodies = new HashSet<Body>();

	public Character(String n, int p, int h)
	{
		name = n;
		maxHealth = h;
		hP = h;
		damage = 5;
		healthRegen = 1;
		winsNeeded = p;
		itemName = null;
		aux = null;
	}
	
	public void render()
	{
		for (Body body : bodies)
		{
			if (body.getType() == BodyType.KINEMATIC)
			{
				glPushMatrix();
				Vec2 bodyPosition = body.getPosition().mul(30);
				glTranslatef(bodyPosition.x, bodyPosition.y, 0);
				glPopMatrix();
				
			}
		}
		
	}
	

	public void input()
	{
		
	}
	
	public void collisionLogic()
	{
		gameWorld.getWorld().step(1/60f, 8, 3);
	}
	
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
	
	public void whatever(BufferedImage i, int w, int h)
	{
		// divide width of the BufferedImage by the wdth set by parameter to get number of rows. do same for height/columns. 
		int rows = i.getWidth(null) / w;
		int columns = i.getHeight(null) / h;
		for(int x = 0; x < rows; x++)
		{
			for(int y = 0; y < columns; y++)
			{
				pics.add(i.getSubimage(x*w, y*h, w, h));
			}
		}
	}
	public void imageReciever(String s)
	{
		try 
		{
			img = ImageIO.read(new File(s));
		}
		catch(IOException e)
		{
			
		}
	}
	public String getName()
	{
		return name;
	}

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
		itemName = i.name;
	}
	
	public void dropitem()
	{
		damage = baseDamage;
		range = baseRange;
		itemName = null;
	}
	
	public void pickUpAux(Auxillary i)
	{
		aux = i.name;
	}
	
	public void useAux()
	{
		
	}
	
	
	
}
