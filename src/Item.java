import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;


public class Item extends Entity {
protected int range;
protected int speed;
protected double startUpTime, reloadTime;
protected int damage;
protected int xVelocity;
protected int yVelocity;
protected String name;
protected boolean dropChance;
protected ArrayList<BufferedImage> pics= new ArrayList<BufferedImage>();
BufferedImage img = null;
protected String projectileImageLocation;
protected Image pImage;

  public Item (int x, int y, String fileLocation, int xVel, int yVel)
  {	 
	  super(x, y, fileLocation);
	  //Sets the hit box
	  super.setHitBox(x, y, 45, 45);	
	  
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
	
	
	
	public void use(GameWorld gW,Character c)
	{		
		if(c.isFacingRight)
			c.gW.listOfProjectiles.add(new Projectile(c.xCoord, c.yCoord,pImage, xVelocity, yVelocity, range, hitBox,c,gW));
		
		else{
			 c.gW.listOfProjectiles.add(new Projectile(c.xCoord, c.yCoord, pImage,-xVelocity, yVelocity, range, hitBox, c, gW));
		}
		System.out.println("ajskljqklslsa");
	}
}
