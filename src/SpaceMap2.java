
public class SpaceMap2 extends Map{
String backgroundFileLocation;
	
	public SpaceMap2(GameWorld gW)
	{
		super(gW);
		backgroundFileLocation = "";
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
		
		addBlock(0,0,"/assets/Art/Stages/Space/block.png", BlockType.Impassable); 
		addBlock(0,1,"/assets/Art/Stages/Space/laser-vert.png",BlockType.Lethal);
		addBlock(0,2,"/assets/Art/Stages/Space/laser-vert.png",BlockType.Lethal);
		addBlock(0,3,"/assets/Art/Stages/Space/laser-vert.png",BlockType.Lethal);
		addBlock(0,4,"/assets/Art/Stages/Space/laser-vert.png",BlockType.Lethal);
		addBlock(0,5,"/assets/Art/Stages/Space/laser-vert.png",BlockType.Lethal);
		addBlock(0,6,"/assets/Art/Stages/Space/laser-vert.png",BlockType.Lethal);
		addBlock(0,7,"/assets/Art/Stages/Space/block.png", BlockType.Impassable); 
		
		addBlock(1,2,"/assets/Art/Stages/Space/platform.png",BlockType.Passable);
		addBlock(1,4,"/assets/Art/Stages/Space/block.png", BlockType.Impassable);
		addBlock(1,6,"/assets/Art/Stages/Space/block.png", BlockType.Impassable);
		addBlock(1,7,"/assets/Art/Stages/Space/laser-hori.png",BlockType.Lethal);
		
		addBlock(2,2,"/assets/Art/Stages/Space/platform.png",BlockType.Passable);
		addBlock(2,4,"/assets/Art/Stages/Space/block.png", BlockType.Impassable);
		addBlock(2,7,"/assets/Art/Stages/Space/laser-hori.png",BlockType.Lethal);
		
		addBlock(3,4,"/assets/Art/Stages/Space/block.png", BlockType.Impassable);
		addBlock(3,7,"/assets/Art/Stages/Space/block.png", BlockType.Impassable);
		
		addBlock(4,6,"/assets/Art/Stages/Space/block.png", BlockType.Impassable);
		addBlock(4,7,"/assets/Art/Stages/Space/block.png", BlockType.Impassable);
		
		addBlock(5,6,"/assets/Art/Stages/Space/block.png", BlockType.Impassable);
		addBlock(5,7,"/assets/Art/Stages/Space/laser-hori.png",BlockType.Lethal);
		
		addBlock(6,6,"/assets/Art/Stages/Space/block.png", BlockType.Impassable);
		addBlock(6,7,"/assets/Art/Stages/Space/laser-hori.png",BlockType.Lethal);

		addBlock(7,6,"/assets/Art/Stages/Space/block.png", BlockType.Impassable);
		addBlock(7,7,"/assets/Art/Stages/Space/laser-hori.png",BlockType.Lethal);
		
		addBlock(8,6,"/assets/Art/Stages/Space/block.png", BlockType.Impassable);
		addBlock(8,7,"/assets/Art/Stages/Space/laser-hori.png",BlockType.Lethal);
		
		addBlock(9,6,"/assets/Art/Stages/Space/block.png", BlockType.Impassable);
		addBlock(9,7,"/assets/Art/Stages/Space/laser-hori.png",BlockType.Lethal);
		
		addBlock(10,6,"/assets/Art/Stages/Space/block.png", BlockType.Impassable);
		addBlock(10,7,"/assets/Art/Stages/Space/block.png", BlockType.Impassable);
		
		addBlock(11,4,"/assets/Art/Stages/Space/block.png", BlockType.Impassable);
		addBlock(11,7,"/assets/Art/Stages/Space/block.png", BlockType.Impassable);
		
		addBlock(12,2,"/assets/Art/Stages/Space/platform.png",BlockType.Passable);
		addBlock(12,4,"/assets/Art/Stages/Space/block.png", BlockType.Impassable);
		addBlock(12,7,"/assets/Art/Stages/Space/laser-hori.png",BlockType.Lethal);
		
		addBlock(13,2,"/assets/Art/Stages/Space/platform.png",BlockType.Passable);
		addBlock(13,4,"/assets/Art/Stages/Space/block.png", BlockType.Impassable);
		addBlock(13,6,"/assets/Art/Stages/Space/block.png", BlockType.Impassable);
		addBlock(13,7,"/assets/Art/Stages/Space/laser-hori.png",BlockType.Lethal);
		
		addBlock(14,0,"/assets/Art/Stages/Space/block.png", BlockType.Impassable); 
		addBlock(14,1,"/assets/Art/Stages/Space/laser-vert.png",BlockType.Lethal);
		addBlock(14,2,"/assets/Art/Stages/Space/laser-vert.png",BlockType.Lethal);
		addBlock(14,3,"/assets/Art/Stages/Space/laser-vert.png",BlockType.Lethal);
		addBlock(14,4,"/assets/Art/Stages/Space/laser-vert.png",BlockType.Lethal);
		addBlock(14,5,"/assets/Art/Stages/Space/laser-vert.png",BlockType.Lethal);
		addBlock(14,6,"/assets/Art/Stages/Space/laser-vert.png",BlockType.Lethal);
		addBlock(14,7,"/assets/Art/Stages/Space/block.png", BlockType.Impassable); 
		
		
		
		
		
		
}
}
