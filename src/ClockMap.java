import java.util.ArrayList;

public class ClockMap extends Map { 

	public ClockMap(World world, String backgroundFileLocation, String musicFileLocation) {
		super(world, backgroundFileLocation, musicFileLocation);
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


		//addBlock(0,0,"assets/Art/Stages/Space/block.png", BlockType.Impassable); 
		//addBlock(0,0,"assets/Art/Stages/Space/laser.png",BlockType.Lethal);
		//addBlock(0,0,"assets/Art/Stages/Space/platform.png",BlockType.Passable);
		//addBlock(0,0,"assets/Art/Stages/Space/crate.png",BlockType.Crate);

		addBlock(1,4,"assets/Art/Stages/Clock/platform.png", BlockType.Passable,0,0,0,63);

		addBlock(2,4,"assets/Art/Stages/Clock/platform.png", BlockType.Passable,0,0,0,63);

		addBlock(3,1,"assets/Art/Stages/Space/block.png", BlockType.Impassable,0,0,0,0); 
		addBlock(3,6,"assets/Art/Stages/Space/block.png", BlockType.Impassable,0,0,0,0); 

		addBlock(4,1,"assets/Art/Stages/Space/block.png", BlockType.Impassable,0,0,0,0);
		addBlock(4,6,"assets/Art/Stages/Space/block.png", BlockType.Impassable,0,0,0,0);

		addBlock(5,0,"assets/Art/Stages/Space/block.png", BlockType.Impassable,0,0,0,0);
		addBlock(5,1,"assets/Art/Stages/Space/block.png", BlockType.Impassable,0,0,0,0);
		addBlock(5,6,"assets/Art/Stages/Space/block.png", BlockType.Impassable,0,0,0,0);
		addBlock(5,7,"assets/Art/Stages/Space/block.png", BlockType.Impassable,0,0,0,0);

		addBlock(6,3,"assets/Art/Stages/Clock/platform.png", BlockType.Passable,0,0,0,63);	
		addBlock(6,4,"assets/Art/Stages/Clock/platform.png", BlockType.Passable,0,0,0,63);
		addBlock(6,5,"assets/Art/Stages/Clock/platform.png", BlockType.Passable,0,0,0,63);

		addBlock(7,3,"assets/Art/Stages/Clock/platform.png", BlockType.Passable,0,0,0,63);	
		addBlock(7,4,"assets/Art/Stages/Clock/platform.png", BlockType.Passable,0,0,0,63);
		addBlock(7,5,"assets/Art/Stages/Clock/platform.png", BlockType.Passable,0,0,0,63);

		addBlock(8,3,"assets/Art/Stages/Clock/platform.png", BlockType.Passable,0,0,0,63);	
		addBlock(8,4,"assets/Art/Stages/Clock/platform.png", BlockType.Passable,0,0,0,63);
		addBlock(8,5,"assets/Art/Stages/Clock/platform.png", BlockType.Passable,0,0,0,63);

		addBlock(9,0,"assets/Art/Stages/Space/block.png", BlockType.Impassable,0,0,0,0);
		addBlock(9,1,"assets/Art/Stages/Space/block.png", BlockType.Impassable,0,0,0,0);
		addBlock(9,6,"assets/Art/Stages/Space/block.png", BlockType.Impassable,0,0,0,0);
		addBlock(9,7,"assets/Art/Stages/Space/block.png", BlockType.Impassable,0,0,0,0);

		addBlock(10,1,"assets/Art/Stages/Space/block.png", BlockType.Impassable,0,0,0,0);
		addBlock(10,6,"assets/Art/Stages/Space/block.png", BlockType.Impassable,0,0,0,0);

		addBlock(11,1,"assets/Art/Stages/Space/block.png", BlockType.Impassable,0,0,0,0); 
		addBlock(11,6,"assets/Art/Stages/Space/block.png", BlockType.Impassable,0,0,0,0);

		addBlock(12,4,"assets/Art/Stages/Clock/platform.png", BlockType.Passable,0,0,0,63);

		addBlock(13,4,"assets/Art/Stages/Clock/platform.png", BlockType.Passable,0,0,0,63);
		
		setCharacterSpawns();
	}
	public void setCharacterSpawns() {
		spawnList = new ArrayList<Map.Location>();
		spawnList.add(new Location(0,1));
		spawnList.add(new Location(14,1));
		spawnList.add(new Location(0,6));
		spawnList.add(new Location(14,6));
	}
	@Override
	protected void setCrateSpawnPoints() {
		// TODO Auto-generated method stub
		
	}
}
