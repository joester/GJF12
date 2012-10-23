import org.newdawn.slick.geom.Rectangle;

public abstract class Map {
	
	GameWorld gW;
	String backgroundFileLocation;
	public Map(GameWorld gW)
	{
		this.gW = gW;
	}
	
	public void addBlock(int xCoordinate, int yCoordinate, String fileLocation, BlockType blockType)	
	{
		gW.addBlock(new Block(	xCoordinate, 
								yCoordinate, 
								fileLocation, 
								blockType, 
								new Rectangle(	xCoordinate * MapEntity.BLOCKSIZE, 
												yCoordinate * MapEntity.BLOCKSIZE, 
												MapEntity.BLOCKSIZE, 
												MapEntity.BLOCKSIZE)));
	}
	
	public abstract void buildMap();
	
	public String getBackgroundFileLocation()
	{
		return backgroundFileLocation;
	}
}
