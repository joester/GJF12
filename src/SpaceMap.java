public class SpaceMap extends Map
{
	String backgroundFileLocation;
	
	public SpaceMap(GameWorld gW)
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
		addBlock(0,0,"/assets/Volcano/block.png", BlockType.Impassible);
		addBlock(0,0,"/assets/Volcano/lava.png",BlockType.Lethal);
		addBlock(0,0,"/assets/Volcano/lava.png",BlockType.Lethal);
		addBlock(0,0,"/assets/Volcano/lava.png",BlockType.Lethal);
		addBlock(0,0,"/assets/Volcano/platform.png",BlockType.Passible);
		addBlock(0,0,"/assets/Volcano/lava.png",BlockType.Lethal);
		addBlock(0,0,"/assets/Volcano/lava.png",BlockType.Impassible);
		addBlock(0,0,"/assets/Volcano/lava.png",BlockType.Impassible);
		addBlock(0,0,"/assets/Volcano/lava.png",BlockType.Impassible);
		addBlock(0,0,"/assets/Volcano/lava.png",BlockType.Impassible);
		addBlock(0,0,"/assets/Volcano/crate.png",BlockType.Crate);
		addBlock(0,0,"/assets/Volcano/block.png",BlockType.Impassible);
		addBlock(0,0,"/assets/Volcano/block.png",BlockType.Impassible);
		addBlock(0,0,"/assets/Volcano/stalactites-leftedge.png",BlockType.Lethal);
		addBlock(0,0,"/assets/Volcano/stalactites.png",BlockType.Lethal);
		addBlock(0,0,"/assets/Volcano/stalactites.png",BlockType.Lethal);
		addBlock(0,0,"/assets/Volcano/stalactites.png",BlockType.Lethal);
		addBlock(0,0,"/assets/Volcano/stalactites.png",BlockType.Lethal);
		addBlock(0,0,"/assets/Volcano/stalactites.png",BlockType.Lethal);
		addBlock(0,0,"/assets/Volcano/stalactites-rightedge.png",BlockType.Lethal);
		addBlock(0,0,"/assets/Volcano/platform.png",BlockType.Passible);
		addBlock(0,0,"/assets/Volcano/platform.png",BlockType.Passible);
		addBlock(0,0,"/assets/Volcano/platform.png",BlockType.Passible);
		addBlock(0,0,"/assets/Volcano/block.png",BlockType.Impassible);
		addBlock(0,0,"/assets/Volcano/block.png",BlockType.Impassible);
		addBlock(0,0,"/assets/Volcano/crate.png",BlockType.Crate);
		addBlock(0,0,"/assets/Volcano/lava.png",BlockType.Lethal);
		addBlock(0,0,"/assets/Volcano/block.png",BlockType.Impassible);
		addBlock(0,0,"/assets/Volcano/lava.png",BlockType.Impassible);
		addBlock(0,0,"/assets/Volcano/lava.png",BlockType.Impassible);
		addBlock(0,0,"/assets/Volcano/lava.png",BlockType.Lethal);
		addBlock(0,0,"/assets/Volcano/lava.png",BlockType.Lethal);
		addBlock(0,0,"/assets/Volcano/lava.png",BlockType.Lethal);
		addBlock(0,0,"/assets/Volcano/block.png",BlockType.Impassible);
		addBlock(0,0, "/assets/Volcano/block.png",BlockType.Impassible);


	}
	
}