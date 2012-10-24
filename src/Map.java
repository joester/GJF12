import java.util.ArrayList;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;

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
	protected ArrayList<Location> crateList; 
	private String backgroundFileLocation;
	private String musicFileLocation;
	public Sound BGM;
	public Image background;
	protected ArrayList<Block> blockList;
	public Map(GameWorld gW,String backgroundFileLocation, String musicFileLocation)
	{
		blockList = new ArrayList<Block>();
		this.gW = gW;
		this.backgroundFileLocation = backgroundFileLocation;
		this.musicFileLocation = musicFileLocation;
		try {
			background = new Image(backgroundFileLocation);
			BGM = new Sound(musicFileLocation);
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
	
	public String getMusicFileLocation()
	{
		return musicFileLocation;
	}
	
	protected abstract void setCharacterSpawns();
	protected abstract void setCrateSpawnPoints();
	public ArrayList<Location> getCharacterSpawns() {
		return spawnList;
	} 
	public ArrayList<Location> getCrateSpawns() {
		return crateList;
	} 
	public ArrayList<Block> getBlockList(){
		return blockList;
	}
}
