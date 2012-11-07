import java.util.ArrayList;

public class SpaceMap2 extends Map
{
	public SpaceMap2(World world, String backgroundFileLocation, String musicFileLocation) {
		super(world, backgroundFileLocation, musicFileLocation);
		// TODO Auto-generated constructor stub
	}

	String backgroundFileLocation;
	
	public void buildMap()
	{
	
		addBlock(0,7,"assets/Art/Stages/Space/block2.png", BlockType.Impassable,0,0,0,8);
		addBlock(0,2,"assets/Art/Stages/Space/block2.png", BlockType.Impassable,0,0,0,8);
		
		addBlock(1,7,"assets/Art/Stages/Space/laser-hori.png",BlockType.Lethal,0,15,0,30);
		addBlock(2,7,"assets/Art/Stages/Space/laser-hori.png",BlockType.Lethal,0,15,0,30);
		addBlock(3,7,"assets/Art/Stages/Space/laser-hori.png",BlockType.Lethal,0,15,0,30);
		
		addBlock(3,4,"assets/Art/Stages/Space/platform.png",BlockType.Passable,0,0,0,63);
		addBlock(2,6,"assets/Art/Stages/Space/platform.png",BlockType.Passable,0,0,0,63);

		
		addBlock(4,7,"assets/Art/Stages/Space/block.png",BlockType.Impassable,0,0,0,8);
		addBlock(5,7,"assets/Art/Stages/Space/block.png",BlockType.Impassable,0,0,0,8);
		addBlock(6,7,"assets/Art/Stages/Space/block.png",BlockType.Impassable,0,0,0,8);
		addBlock(7,7,"assets/Art/Stages/Space/laser-hori.png",BlockType.Lethal,0,15,0,30);
		addBlock(4,3,"assets/Art/Stages/genericCrate.png",BlockType.Crate,0,21,0,21);
		addBlock(4,4,"assets/Art/Stages/Space/block.png",BlockType.Impassable,0,0,0,8);
		addBlock(5,4,"assets/Art/Stages/Space/block.png",BlockType.Impassable,0,0,0,8);
		
		
		addBlock(6,3,"assets/Art/Stages/Space/platform.png",BlockType.Passable,0,0,0,63);
		addBlock(7,3, "assets/Art/Stages/Space/platform.png",BlockType.Passable,0,0,0,63);
		addBlock(8,3,"assets/Art/Stages/Space/platform.png",BlockType.Passable,0,0,0,63);
		addBlock(9,4,"assets/Art/Stages/Space/block.png",BlockType.Impassable,0,0,0,8);
		addBlock(10,4,"assets/Art/Stages/Space/block.png",BlockType.Impassable,0,0,0,8);
		addBlock(10,3,"assets/Art/Stages/genericCrate.png",BlockType.Crate,0,21,0,21);
		addBlock(8,7,"assets/Art/Stages/Space/block.png",BlockType.Impassable,0,0,0,8);
		addBlock(9,7,"assets/Art/Stages/Space/block.png",BlockType.Impassable,0,0,0,8);
		addBlock(10,7,"assets/Art/Stages/Space/block.png",BlockType.Impassable,0,0,0,8);
		addBlock(14,2,"assets/Art/Stages/Space/block2.png",BlockType.Impassable,0,0,0,8);
		addBlock(11,7,"assets/Art/Stages/Space/laser-hori.png",BlockType.Lethal,0,15,0,30);
		addBlock(11,4,"assets/Art/Stages/Space/platform.png",BlockType.Passable,0,0,0,63);
		addBlock(12,7,"assets/Art/Stages/Space/laser-hori.png",BlockType.Lethal,0,15,0,30);
		addBlock(13,7,"assets/Art/Stages/Space/laser-hori.png",BlockType.Lethal,0,15,0,30);
		addBlock(14,7,"assets/Art/Stages/Space/block2.png",BlockType.Impassable,0,0,0,8);
		addBlock(12, 6, "assets/Art/Stages/Space/platform.png", BlockType.Passable,0,0,0,63);
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
		crateList.add(new Location(6,13));
		crateList.add(new Location(7,2));
		crateList.add(new Location(8,13));
		crateList.add(new Location(4,3));
		crateList.add(new Location(10,3));
	}
	
}
