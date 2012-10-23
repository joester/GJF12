import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;


public class Item extends Entity {
public int range;
public double speed, startUpTime, reloadTime;
public int damage;
public int xVelocity;
public int yVelocity;
public String name;
public boolean dropChance;
public ArrayList<BufferedImage> pics= new ArrayList<BufferedImage>();
BufferedImage img = null;
String fileLocation;

  public Item (int x, int y, String fileLocation)
  {	 
	  super(x, y, fileLocation);
	  //Sets the hit box
	  super.setHitBox(x, y, 45, 45);	  

	  this.fileLocation = fileLocation;
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
	

	@Override
	public void render(GameContainer gc, Graphics g) throws SlickException
	{
		g.draw(hitBox);
		image.draw(xCoord, yCoord);
		
	}

	@Override
	public void init(GameContainer arg0) throws SlickException
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(GameContainer gc, int delta) throws SlickException,
		InterruptedException
	{
		super.update(gc, delta);		
	}
	
	
	
	public void use(GameWorld gW)
	{		
	    gW.listOfProjectiles.add(new Projectile(xCoord, yCoord, xVelocity, yVelocity, range, hitBox));
	}
}
