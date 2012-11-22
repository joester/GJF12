import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.*;


import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;
import org.newdawn.slick.geom.Rectangle;

public abstract class Map{
	public class Location{
		int x,y;
		protected Location(int x,int y){
			this.x = x;
			this.y = y;
		}
	}
	World world;
	protected ArrayList<Location> spawnList;
	protected ArrayList<Location> crateList; 
	private String backgroundFileLocation;
	private String musicFileLocation;
	private Sound BGM;
	private Image background;
	protected ArrayList<Block> blockList;
	protected Timer timer;
	protected int numberOfCrates = 4;
	
	public Map(World world,String backgroundFileLocation, String musicFileLocation)
	{
		blockList = new ArrayList<Block>();
		this.world = world;
		this.backgroundFileLocation = backgroundFileLocation;
		this.musicFileLocation = musicFileLocation;
		try {
			setBackground(new Image(backgroundFileLocation));
			BGM = new Sound(musicFileLocation);
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	
	}
	
	public Map() {
		// TODO Auto-generated constructor stub
	}

	public void addBlock(int xCoordinate, int yCoordinate, String fileLocation, BlockType blockType, int xOffSet, int yOffSet, int sizeXOff,int sizeYOff)	
	{
		Rectangle offsets = new Rectangle(xOffSet,
								yOffSet,
								sizeXOff,
								sizeYOff);
		world.addBlock(new Block(xCoordinate, 	
								yCoordinate, 
								fileLocation, 
								blockType, 
								offsets));
	}
	
	public void addBlock(int xCoordinate, int yCoordinate, String fileLocation, BlockType blockType, Rectangle offsets)	
	{
		world.addBlock(new Block(xCoordinate, 	
								yCoordinate, 
								fileLocation, 
								blockType, 
								offsets));
	}
	
	public void crateRespawn(Location loc, String fileLocation, BlockType blockType, int xOffset, int yOffset, int sizeXOff, int sizeYOff)
	{
		Rectangle offsets = new Rectangle(xOffset,
				yOffset,
				sizeXOff,
				sizeYOff);
		world.addBlock(new Block(loc.x, loc.y,
				fileLocation, 
				blockType, 
				offsets));
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

	public Image getBackground() {
		return background;
	}

	public void setBackground(Image background) {
		this.background = background;
	}
	
	public Sound getBGM(){
		return BGM;
	}
	public void startTime(){
		
		timer = new Timer(15000, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					for(int i = 0; i < crateList.size(); i++){
							crateRespawn(crateList.get(i), "assets/Art/Stages/genericcrate.png", BlockType.Crate, 0, 21, 0 ,21);}
					timer.stop();}
			});	
		timer.start();
		
		numberOfCrates = 4;
	}
	public void stopTime(){
		timer.stop();
	}
}
	
