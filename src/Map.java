import org.newdawn.slick.geom.Rectangle;

public class Map {
	
	GameWorld gW;
	String backgroundFileLocation;
	public Map(GameWorld gW)
	{
		this.gW = gW;
	}
	
	public void addBlock(int xCoordinate, int yCoordinate, String fileLocation, BlockType blockType)	
	{
		gW.addBlock(new Block (xCoordinate * MapEntity.BLOCKSIZE, yCoordinate * MapEntity.BLOCKSIZE, 
				fileLocation, blockType, 
				new Rectangle(xCoordinate, yCoordinate, MapEntity.BLOCKSIZE, MapEntity.BLOCKSIZE)));
	}
	
	public String getBackgroundFileLocation()
	{
		return backgroundFileLocation;
	}
}
