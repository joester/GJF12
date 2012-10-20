import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;


public class Items extends Entity {

public double range;
public int damage; 
public String name;
public ArrayList<BufferedImage> pics= new ArrayList<BufferedImage>();
BufferedImage img = null;
protected int xCoord;
protected int yCoord;

  public Items (int x, int y, String imageLocation)
  {
	  super( x, y, imageLocation);
	  
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
}
