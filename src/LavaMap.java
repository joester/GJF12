import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.*;

public class LavaMap extends Map
{
	public LavaMap(World world, String backgroundFileLocation, String musicFileLocation) {
		super(world, backgroundFileLocation, musicFileLocation);
	}

	public void buildMap()
	{
		//addBlock( X-Coordinate, Y-Coordinate, blockFileLocation, blockType );
		//"arg" is a placeholder fileLocation
		//addBlock(0,3, "arg", BlockType.Impassible);
		/*
		 * addSpawn(0,2,"arg", BlockType.CrateSpawn);
		 * addSpawn(15,2, "arg", BlockType.CrateSpawn);
		 * etc
		 */
		 
		addBlock(0,7,"assets/Art/Stages/Volcano/submergededge.png", BlockType.Impassable,0,0,0,8);
		addBlock(0,2,"assets/Art/Stages/Volcano/edge-right.png", BlockType.Impassable,0,0,0,8);
		addBlock(1,7,"assets/Art/Stages/Volcano/lava.png",BlockType.Lethal,0,21,0,21);
		addBlock(2,7,"assets/Art/Stages/Volcano/lava.png",BlockType.Lethal,0,21,0,21);
		addBlock(3,7,"assets/Art/Stages/Volcano/lava.png",BlockType.Lethal,0,21,0,21);
		addBlock(3,4,"assets/Art/Stages/Volcano/platform.png",BlockType.Passable,0,0,0,63);
		addBlock(2,6,"assets/Art/Stages/Volcano/platform.png",BlockType.Passable,0,0,0,63);
		
		addBlock(4,7,"assets/Art/Stages/Volcano/submerged-left.png",BlockType.Impassable,0,0,0,8);
		addBlock(5,7,"assets/Art/Stages/Volcano/submergedblock.png",BlockType.Impassable,0,0,0,8);
		addBlock(6,7,"assets/Art/Stages/Volcano/submergededge.png",BlockType.Impassable,0,0,0,8);
		addBlock(7,7,"assets/Art/Stages/Volcano/lava.png",BlockType.Lethal,0,21,0,21);
		addBlock(4,4,"assets/Art/Stages/Volcano/edge.png",BlockType.Impassable,0,0,0,8);
		addBlock(5,4,"assets/Art/Stages/Volcano/edge-right.png",BlockType.Impassable,0,0,0,8);
		
		addBlock(4,0,"assets/Art/Stages/Volcano/stalactites-leftedge.png",BlockType.Lethal,0,0,0,21);
		addBlock(5,0,"assets/Art/Stages/Volcano/stalactites.png",BlockType.Lethal,0,0,0,21);
		addBlock(6,0,"assets/Art/Stages/Volcano/stalactites.png",BlockType.Lethal,0,0,0,21);
		addBlock(7,0,"assets/Art/Stages/Volcano/stalactites.png",BlockType.Lethal,0,0,0,21);
		addBlock(8,0,"assets/Art/Stages/Volcano/stalactites.png",BlockType.Lethal,0,0,0,21);
		addBlock(9,0,"assets/Art/Stages/Volcano/stalactites.png",BlockType.Lethal,0,0,0,21);
		addBlock(10,0,"assets/Art/Stages/Volcano/stalactites-rightedge.png",BlockType.Lethal,0,0,0,21);
		
		addBlock(6,3,"assets/Art/Stages/Volcano/platform.png",BlockType.Passable,0,0,0,63);
		addBlock(7,3,"assets/Art/Stages/Volcano/platform.png",BlockType.Passable,0,0,0,63);
		addBlock(8,3,"assets/Art/Stages/Volcano/platform.png",BlockType.Passable,0,0,0,63);
		addBlock(9,4,"assets/Art/Stages/Volcano/edge.png",BlockType.Impassable,0,0,0,8);
		addBlock(10,4,"assets/Art/Stages/Volcano/edge-right.png",BlockType.Impassable,0,0,0,8);
		addBlock(8,7,"assets/Art/Stages/Volcano/submerged-left.png",BlockType.Impassable,0,0,0,8);
		addBlock(9,7,"assets/Art/Stages/Volcano/submergedblock.png",BlockType.Impassable,0,0,0,8);
		addBlock(10,7,"assets/Art/Stages/Volcano/submergededge.png",BlockType.Impassable,0,0,0,8);
		addBlock(11,7,"assets/Art/Stages/Volcano/lava.png",BlockType.Lethal,0,21,0,21);
		addBlock(12,7,"assets/Art/Stages/Volcano/lava.png",BlockType.Lethal,0,21,0,21);
		addBlock(13,7,"assets/Art/Stages/Volcano/lava.png",BlockType.Lethal,0,21,0,21);
		addBlock(14,7,"assets/Art/Stages/Volcano/submerged-left.png",BlockType.Impassable,0,0,0,8);
		addBlock(12,6,"assets/Art/Stages/Volcano/platform.png",BlockType.Passable,0,0,0,63);
		addBlock(11,4,"assets/Art/Stages/Volcano/platform.png",BlockType.Passable,0,0,0,63);
		addBlock(14, 2,"assets/Art/Stages/Volcano/edge.png",BlockType.Impassable,0,0,0,8);
		
		addBlock(4,3,"assets/Art/Stages/genericcrate.png",BlockType.Crate,0,21,0,21);
		addBlock(10,3,"assets/Art/Stages/genericcrate.png",BlockType.Crate,0,21,0,21);
		addBlock(9,6,"assets/Art/Stages/genericcrate.png",BlockType.Crate,0,21,0,21);
		addBlock(5,6,"assets/Art/Stages/genericcrate.png",BlockType.Crate,0,21,0,21);

		
		setCharacterSpawns();
		setCrateSpawnPoints();
		
		
		
		
	}

	@Override
	protected void setCharacterSpawns() {
		spawnList = new ArrayList<Location>();
		spawnList.add(new Location(0,1));
		spawnList.add(new Location(14,1));
		spawnList.add(new Location(0,6));
		spawnList.add(new Location(14,6));
	}
	protected void setCrateSpawnPoints() {
		crateList = new ArrayList<Location>();
		crateList.add(new Location(4,3));
		crateList.add(new Location(9,6));
		crateList.add(new Location(5,6));
		crateList.add(new Location(10,3));
	}

}
