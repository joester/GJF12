import java.util.ArrayList;

import org.newdawn.slick.geom.Rectangle;

public abstract class Map {
	public class Location{
		int x,y;
		protected Location(int x,int y){
			this.x = x;
			this.y = y;
		}
	}
	GameWorld gW;
	protected ArrayList<Location> spawnList;
	private String backgroundFileLocation;
	public Map(GameWorld gW,String backgroundFileLocation)
	{
		this.gW = gW;
		this.backgroundFileLocation = backgroundFileLocation;
	}
	
	public void addBlock(int xCoordinate, int yCoordinate, String fileLocation, BlockType blockType, int xOffSet, int yOffSet, int sizeXOff,int sizeYOff)	
	{
		gW.addBlock(new Block(	xCoordinate, 	
								yCoordinate, 
								fileLocation, 
								blockType, 
								xOffSet,
								yOffSet,
								sizeXOff,
								sizeYOff));
	}
	
	public abstract void buildMap();
	
	public String getBackgroundFileLocation()
	{
		return backgroundFileLocation;
	}
	
	public abstract void setCharacterSpawns();

	public ArrayList<Location> getCharacterSpawns() {
		// TODO Auto-generated method stub
		return spawnList;
	} 
}
