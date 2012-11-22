import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.Timer;


public class IceMap extends Map
{
	public IceMap(World world, String backgroundFileLocation, String musicFileLocation) {
		super(world, backgroundFileLocation, musicFileLocation);
		// TODO Auto-generated constructor stub
	}
	public void buildMap()
	{
		//addBlock( X-Coordinate, Y-Coordinate, blockFileLocation, blockType );
		//"arg" is a placeholder fileLocation
		//addBlock(0,3, "arg", BlockType.Impassable);
		/*
		 * addSpawn(0,2,"arg", BlockType.CrateSpawn);
		 * addSpawn(15,2, "arg", BlockType.CrateSpawn);
		 * etc
		 */
		
		addBlock(0,7,"assets/Art/Stages/Volcano/icerightedge.png", BlockType.Impassable,0,0,0,8);
		addBlock(0,2,"assets/Art/Stages/Volcano/icerightedge.png", BlockType.Impassable,0,0,0,8);
		addBlock(1,7,"assets/Art/Stages/Volcano/icicles.png",BlockType.Lethal,0,21,0,21);
		addBlock(2,7,"assets/Art/Stages/Volcano/icicles.png",BlockType.Lethal,0,21,0,21);
		addBlock(3,7,"assets/Art/Stages/Volcano/icicles.png",BlockType.Lethal,0,21,0,21);
		addBlock(3,4,"assets/Art/Stages/Volcano/iceplatform.png",BlockType.Passable,0,0,0,63);
		addBlock(2,6,"assets/Art/Stages/Volcano/iceplatform.png",BlockType.Passable,0,0,0,63);
		//addBlock(3,7,"assets/Art/Stages/Volcano/icicles.png",BlockType.Lethal);
		addBlock(4,7,"assets/Art/Stages/Volcano/iceleftedge.png",BlockType.Impassable,0,0,0,8);
		addBlock(5,7,"assets/Art/Stages/Volcano/iceblock.png",BlockType.Impassable,0,0,0,8);
		addBlock(6,7,"assets/Art/Stages/Volcano/icerightedge.png",BlockType.Impassable,0,0,0,8);
		addBlock(7,7,"assets/Art/Stages/Volcano/icicles.png",BlockType.Lethal,0,21,0,21);
		addBlock(4,4,"assets/Art/Stages/Volcano/iceleftedge.png",BlockType.Impassable,0,0,0,8);
		addBlock(5,4,"assets/Art/Stages/Volcano/icerightedge.png",BlockType.Impassable,0,0,0,8);
		
		//addBlock(11,0,"assets/Art/Stages/Volcano/icicles-right.png",BlockType.Lethal);
		addBlock(6,3,"assets/Art/Stages/Volcano/iceplatform.png",BlockType.Passable,0,0,0,63);
		addBlock(7,3, "assets/Art/Stages/Volcano/iceplatform.png",BlockType.Passable,0,0,0,63);
		addBlock(8,3,"assets/Art/Stages/Volcano/iceplatform.png",BlockType.Passable,0,0,0,63);
		addBlock(9,4,"assets/Art/Stages/Volcano/iceleftedge.png",BlockType.Impassable,0,0,0,8);
		addBlock(10,4,"assets/Art/Stages/Volcano/icerightedge.png",BlockType.Impassable,0,0,0,8);
		addBlock(8,7,"assets/Art/Stages/Volcano/iceleftedge.png",BlockType.Impassable,0,0,0,8);
		addBlock(9,7,"assets/Art/Stages/Volcano/iceblock.png",BlockType.Impassable,0,0,0,8);
		addBlock(10,7,"assets/Art/Stages/Volcano/icerightedge.png",BlockType.Impassable,0,0,0,8);
		addBlock(14,2,"assets/Art/Stages/Volcano/iceleftedge.png",BlockType.Impassable,0,0,0,8);
		addBlock(11,7,"assets/Art/Stages/Volcano/icicles.png",BlockType.Lethal,0,21,0,21);
		addBlock(11,4,"assets/Art/Stages/Volcano/iceplatform.png",BlockType.Passable,0,0,0,63);
		addBlock(12,7,"assets/Art/Stages/Volcano/icicles.png",BlockType.Lethal,0,21,0,21);
		addBlock(13,7,"assets/Art/Stages/Volcano/icicles.png",BlockType.Lethal,0,21,0,21);
		addBlock(14,7,"assets/Art/Stages/Volcano/iceleftedge.png",BlockType.Impassable,0,0,0,8);
		addBlock(12, 6, "assets/Art/Stages/Volcano/iceplatform.png", BlockType.Passable,0,0,0,63);
		
		addBlock(5,6,"assets/Art/Stages/genericcrate.png",BlockType.Crate,0,21,0,21);
		addBlock(9,6,"assets/Art/Stages/genericcrate.png",BlockType.Crate,0,21,0,21);
		addBlock(4,3,"assets/Art/Stages/genericcrate.png",BlockType.Crate,0,21,0,21);
		addBlock(10,3,"assets/Art/Stages/genericcrate.png",BlockType.Crate,0,21,0,21);

		setCharacterSpawns();
		setCrateSpawnPoints();	
		
	
	}

	@Override
	protected void setCharacterSpawns() {
		spawnList = new ArrayList<Location>();
		spawnList.add(new Location(0,1));
		spawnList.add(new Location(14,1));
		spawnList.add(new Location(0,6));
		spawnList.add(new Location(14,6));}
	
	protected void setCrateSpawnPoints() {
		crateList = new ArrayList<Location>();
		crateList.add(new Location(4,3));
		crateList.add(new Location(9, 6));
		crateList.add(new Location(5,6));
		crateList.add(new Location(10,3));
	}

		
}
