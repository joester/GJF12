import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

public class Platform extends MapEntity{
	int distance;
	public Platform(int x, int y, String imageLocation) {
		super(x, y, imageLocation);
	}

	public void setTravelDistance(int distance){
		this.distance = distance;
	}
	
	@Override
	public void render(GameContainer gc, Graphics g)
	{
		image.draw(xCoord, yCoord);
		
	}

	@Override
	public void update(GameContainer gc, int delta) throws SlickException, InterruptedException
	{
		super.update(gc, delta);	
		xCoord += Math.sin(Math.toRadians(delta)) * distance;	
	}
}
