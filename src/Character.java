import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;


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
