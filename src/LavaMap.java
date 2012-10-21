
public class LavaMap extends Map
{
	String backgroundFileLocation;
	
	public LavaMap(GameWorld gW)
	{
		super(gW);
		backgroundFileLocation = "";
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
		addBlock(0,7,"/assets/Art/Stages/Volcano/submergededge.png", BlockType.Impassable);
		addBlock(0,2,"/assets/Art/Stages/Volcano/block.png", BlockType.Impassable);
		addBlock(1,7,"/assets/Art/Stages/Volcano/lava.png",BlockType.Lethal);
		addBlock(2,7,"/assets/Art/Stages/Volcano/lava.png",BlockType.Lethal);
		addBlock(3,7,"/assets/Art/Stages/Volcano/lava.png",BlockType.Lethal);
		addBlock(3,4,"/assets/Art/Stages/Volcano/platform.png",BlockType.Passable);
		addBlock(2,6,"/assets/Art/Stages/Volcano/platform.png",BlockType.Passable);
		
		addBlock(4,7,"/assets/Art/Stages/Volcano/submerged-left.png",BlockType.Impassable);
		addBlock(5,7,"/assets/Art/Stages/Volcano/submergedblock.png",BlockType.Impassable);
		addBlock(6,7,"/assets/Art/Stages/Volcano/submergededge.png",BlockType.Impassable);
		addBlock(7,7,"/assets/Art/Stages/Volcano/lava.png",BlockType.Lethal);
		addBlock(4,3,"/assets/Art/Stages/Volcano/crate.png",BlockType.Crate);
		addBlock(4,4,"/assets/Art/Stages/Volcano/block.png",BlockType.Impassable);
		addBlock(5,4,"/assets/Art/Stages/Volcano/block.png",BlockType.Impassable);
		
		addBlock(4,0,"/assets/Art/Stages/Volcano/stalactites-leftedge.png",BlockType.Lethal);
		addBlock(5,0,"/assets/Art/Stages/Volcano/stalactites.png",BlockType.Lethal);
		addBlock(6,0,"/assets/Art/Stages/Volcano/stalactites.png",BlockType.Lethal);
		addBlock(7,0,"/assets/Art/Stages/Volcano/stalactites.png",BlockType.Lethal);
		addBlock(8,0,"/assets/Art/Stages/Volcano/stalactites.png",BlockType.Lethal);
		addBlock(9,0,"/assets/Art/Stages/Volcano/stalactites.png",BlockType.Lethal);
		addBlock(10,0,"/assets/Art/Stages/Volcano/stalactites-rightedge.png",BlockType.Lethal);
		
		addBlock(6,3,"/assets/Art/Stages/Volcano/platform.png",BlockType.Passable);
		addBlock(7,3,"/assets/Art/Stages/Volcano/platform.png",BlockType.Passable);
		addBlock(8,3,"/assets/Art/Stages/Volcano/platform.png",BlockType.Passable);
		addBlock(9,4,"/assets/Art/Stages/Volcano/block.png",BlockType.Impassable);
		addBlock(10,4,"/assets/Art/Stages/Volcano/block.png",BlockType.Impassable);
		addBlock(10,3,"/assets/Art/Stages/Volcano/crate.png",BlockType.Crate);
		addBlock(8,7,"/assets/Art/Stages/Volcano/submerged-left.png",BlockType.Impassable);
		addBlock(9,7,"/assets/Art/Stages/Volcano/submergedblock.png",BlockType.Impassable);
		addBlock(10,7,"/assets/Art/Stages/Volcano/submergededge.png",BlockType.Impassable);
		addBlock(11,7,"/assets/Art/Stages/Volcano/lava.png",BlockType.Lethal);
		addBlock(12,7,"/assets/Art/Stages/Volcano/lava.png",BlockType.Lethal);
		addBlock(13,7,"/assets/Art/Stages/Volcano/lava.png",BlockType.Lethal);
		addBlock(14,7,"/assets/Art/Stages/Volcano/submerged-left.png",BlockType.Impassable);
		addBlock(12,6,"/assets/Art/Stages/Volcano/platform.png",BlockType.Passable);
		addBlock(11,4,"/assets/Art/Stages/Volcano/platform.png",BlockType.Passable);
		addBlock(14, 2,"/assets/Art/Stages/Volcano/block.png",BlockType.Impassable );


	}
	
}
