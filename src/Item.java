import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;


public class Item extends Entity {
public int range = 400;
public double speed, startUpTime, reloadTime;
public int damage;
public int xVelocity;
public int yVelocity;
public String name;
public boolean dropChance;
public ArrayList<BufferedImage> pics= new ArrayList<BufferedImage>();
BufferedImage img = null;
String fileLocation;

  public Item (int x, int y, String fileLocation, int xVel, int yVel)
  {	 
	  super(x, y, fileLocation);
	  //Sets the hit box
	  super.setHitBox(x, y, 45, 45);	  

	  this.fileLocation = fileLocation;
	  
	  this.xVelocity = xVel;
	  this.yVelocity = yVel;
  }
  
	@Override
	public void render(GameContainer gc, Graphics g) throws SlickException
	{
		g.draw(hitBox);
		image.draw(xCoord, yCoord);
		
	}

	@Override
	public void init(GameContainer gc) throws SlickException
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(GameContainer gc, int delta) throws SlickException,
		InterruptedException
	{
		super.update(gc, delta);		
	}
	
	
	
	public void use(Character c)
	{		
		if(c.isFacingRight)
			c.gW.listOfProjectiles.add(new Projectile((int)(c.xCoord + c.getHitBox().getWidth()), c.yCoord, xVelocity, yVelocity,damage, range, hitBox, c.gW, c));
		
		else{
			 c.gW.listOfProjectiles.add(new Projectile(c.xCoord, c.yCoord, -xVelocity, yVelocity,damage, range, hitBox, c.gW, c));
		}
		System.out.println("ajskljqklslsa");
	}
}
