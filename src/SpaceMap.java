import java.util.ArrayList;

public class SpaceMap extends Map
{
	public SpaceMap(GameWorld gW, String backgroundFileLocation, String musicFileLocation) {
		super(gW, backgroundFileLocation, musicFileLocation);
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
		
		
		//addBlock(0,0,"/assets/Art/Stages/Space/block.png", BlockType.Impassable); 
		//addBlock(0,0,"/assets/Art/Stages/Space/laser.png",BlockType.Lethal);
		//addBlock(0,0,"/assets/Art/Stages/Space/platform.png",BlockType.Passable);
		//addBlock(0,0,"/assets/Art/Stages/Space/crate.png",BlockType.Crate);
		
		addBlock(0,0,"/assets/Art/Stages/Space/block.png", BlockType.Impassable,0,0,0,0); 
		addBlock(0,1,"/assets/Art/Stages/Space/laser-vert.png",BlockType.Lethal,15,0,30,0);
		addBlock(0,2,"/assets/Art/Stages/Space/laser-vert.png",BlockType.Lethal,15,0,30,0);
		addBlock(0,3,"/assets/Art/Stages/Space/laser-vert.png",BlockType.Lethal,15,0,30,0);
		addBlock(0,4,"/assets/Art/Stages/Space/laser-vert.png",BlockType.Lethal,15,0,30,0);
		addBlock(0,5,"/assets/Art/Stages/Space/laser-vert.png",BlockType.Lethal,15,0,30,0);
		addBlock(0,6,"/assets/Art/Stages/Space/laser-vert.png",BlockType.Lethal,15,0,30,0);
		addBlock(0,7,"/assets/Art/Stages/Space/block.png", BlockType.Impassable,0,0,0,0); 
		
		addBlock(1,3,"/assets/Art/Stages/Space/platform.png",BlockType.Passable,0,0,0,63);
		addBlock(1,6,"/assets/Art/Stages/Space/block.png", BlockType.Impassable,0,0,0,0);
		addBlock(1,7,"/assets/Art/Stages/Space/laser-hori.png",BlockType.Lethal,0,15,0,30);
		
		addBlock(2,3,"/assets/Art/Stages/Space/platform.png",BlockType.Passable,0,0,0,63);
		addBlock(2,7,"/assets/Art/Stages/Space/laser-hori.png",BlockType.Lethal,0,15,0,30);
		
		addBlock(3,7,"/assets/Art/Stages/Space/block.png", BlockType.Impassable,0,0,0,0); 
		
		addBlock(4,0,"/assets/Art/Stages/Space/block.png", BlockType.Impassable,0,0,0,0);
		addBlock(4,4,"/assets/Art/Stages/Space/block.png", BlockType.Impassable,0,0,0,0);
		addBlock(4,7,"/assets/Art/Stages/Space/block.png", BlockType.Impassable,0,0,0,0);
		
		addBlock(5,0,"/assets/Art/Stages/Space/block.png", BlockType.Impassable,0,0,0,0);
		addBlock(5,4,"/assets/Art/Stages/Space/block.png", BlockType.Impassable,0,0,0,0);
		addBlock(5,7,"/assets/Art/Stages/Space/laser-hori.png",BlockType.Lethal,0,15,0,30);
		
		addBlock(6,0,"/assets/Art/Stages/Space/block.png", BlockType.Impassable,0,0,0,0);
		addBlock(6,4,"/assets/Art/Stages/Space/block.png", BlockType.Impassable,0,0,0,0);
		addBlock(6,7,"/assets/Art/Stages/Space/laser-hori.png",BlockType.Lethal,0,15,0,30);
		
		addBlock(7,0,"/assets/Art/Stages/Space/block.png", BlockType.Impassable,0,0,0,0);
		addBlock(7,7,"/assets/Art/Stages/Space/laser-hori.png",BlockType.Lethal,0,15,0,30);
		
		addBlock(8,0,"/assets/Art/Stages/Space/block.png", BlockType.Impassable,0,0,0,0);
		addBlock(8,4,"/assets/Art/Stages/Space/block.png", BlockType.Impassable,0,0,0,0);
		addBlock(8,7,"/assets/Art/Stages/Space/laser-hori.png",BlockType.Lethal,0,15,0,30);
		
		addBlock(9,0,"/assets/Art/Stages/Space/block.png", BlockType.Impassable,0,0,0,0);
		addBlock(9,4,"/assets/Art/Stages/Space/block.png", BlockType.Impassable,0,0,0,0);
		addBlock(9,7,"/assets/Art/Stages/Space/laser-hori.png",BlockType.Lethal,0,15,0,30);
		
		addBlock(10,0,"/assets/Art/Stages/Space/block.png", BlockType.Impassable,0,0,0,0);
		addBlock(10,4,"/assets/Art/Stages/Space/block.png", BlockType.Impassable,0,0,0,0);
		addBlock(10,7,"/assets/Art/Stages/Space/block.png",BlockType.Impassable,0,0,0,0);
		
		addBlock(11,7,"/assets/Art/Stages/Space/block.png", BlockType.Impassable,0,0,0,0); 
		
		addBlock(12,3,"/assets/Art/Stages/Space/platform.png",BlockType.Passable,0,0,0,63);
		addBlock(12,7,"/assets/Art/Stages/Space/laser-hori.png",BlockType.Lethal,0,15,0,30);
		
		addBlock(13,3,"/assets/Art/Stages/Space/platform.png",BlockType.Passable,0,0,0,63);
		addBlock(13,6,"/assets/Art/Stages/Space/block.png", BlockType.Impassable,0,0,0,0);
		addBlock(13,7,"/assets/Art/Stages/Space/laser-hori.png",BlockType.Lethal,0,15,0,30);
		
		addBlock(14,0,"/assets/Art/Stages/Space/block.png", BlockType.Impassable,0,0,0,0); 
		addBlock(14,1,"/assets/Art/Stages/Space/laser-vert.png",BlockType.Lethal,15,0,30,0);
		addBlock(14,2,"/assets/Art/Stages/Space/laser-vert.png",BlockType.Lethal,15,0,30,0);
		addBlock(14,3,"/assets/Art/Stages/Space/laser-vert.png",BlockType.Lethal,15,0,30,0);
		addBlock(14,4,"/assets/Art/Stages/Space/laser-vert.png",BlockType.Lethal,15,0,30,0);
		addBlock(14,5,"/assets/Art/Stages/Space/laser-vert.png",BlockType.Lethal,15,0,30,0);
		addBlock(14,6,"/assets/Art/Stages/Space/laser-vert.png",BlockType.Lethal,15,0,30,0);
		addBlock(14,7,"/assets/Art/Stages/Space/block.png", BlockType.Impassable,0,0,0,0); 
		setCharacterSpawns();
		setCrateSpawnPoints();
	}
	protected void setCharacterSpawns() {
		spawnList = new ArrayList<Location>();
		spawnList.add(new Location(2,1));
		spawnList.add(new Location(1,5));
		spawnList.add(new Location(12,1));
		spawnList.add(new Location(13,5));
	}
	protected void setCrateSpawnPoints(){
		crateList = new ArrayList<Location>();
		crateList.add(new Location(5,3));
		crateList.add(new Location(9,3));
		crateList.add(new Location(4,6));
		crateList.add(new Location(10,6));
	}
}