import java.util.ArrayList;
import java.util.List;
import org.newdawn.slick.geom.Rectangle;


public class IceMap extends Map
{
	String backgroundFileLocation;
	
	public IceMap(GameWorld gW)
	{
		super(gW);
		backgroundFileLocation = "";
	}
	
	public void buildMap()
	{
		//addBlock( X-Coordinate, Y-Coordinate, blockFileLocation, blockType );
		addBlock();
	}
	
}
