
public class ClockMap extends Map { 
	
String backgroundFileLocation;
	
	public ClockMap(GameWorld gW)
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
		
		addBlock(1,4,"/assets/Art/Stages/Clock/platform.png", BlockType.Passable);
		
		addBlock(2,4,"/assets/Art/Stages/Clock/platform.png", BlockType.Passable);
		
		addBlock(3,1,"/assets/Art/Stages/Space/block.png", BlockType.Impassable); 
		addBlock(3,6,"/assets/Art/Stages/Space/block.png", BlockType.Impassable); 
		
		addBlock(4,1,"/assets/Art/Stages/Space/block.png", BlockType.Impassable);
		addBlock(4,6,"/assets/Art/Stages/Space/block.png", BlockType.Impassable);
		
		addBlock(5,0,"/assets/Art/Stages/Space/block.png", BlockType.Impassable);
		addBlock(5,1,"/assets/Art/Stages/Space/block.png", BlockType.Impassable);
		addBlock(5,6,"/assets/Art/Stages/Space/block.png", BlockType.Impassable);
		addBlock(5,7,"/assets/Art/Stages/Space/block.png", BlockType.Impassable);
		
		addBlock(6,3,"/assets/Art/Stages/Clock/platform.png", BlockType.Passable);	
		addBlock(6,4,"/assets/Art/Stages/Clock/platform.png", BlockType.Passable);
		addBlock(6,5,"/assets/Art/Stages/Clock/platform.png", BlockType.Passable);
		
		addBlock(7,3,"/assets/Art/Stages/Clock/platform.png", BlockType.Passable);	
		addBlock(7,4,"/assets/Art/Stages/Clock/platform.png", BlockType.Passable);
		addBlock(7,5,"/assets/Art/Stages/Clock/platform.png", BlockType.Passable);
		
		addBlock(8,3,"/assets/Art/Stages/Clock/platform.png", BlockType.Passable);	
		addBlock(8,4,"/assets/Art/Stages/Clock/platform.png", BlockType.Passable);
		addBlock(8,5,"/assets/Art/Stages/Clock/platform.png", BlockType.Passable);
		
		addBlock(9,0,"/assets/Art/Stages/Space/block.png", BlockType.Impassable);
		addBlock(9,1,"/assets/Art/Stages/Space/block.png", BlockType.Impassable);
		addBlock(9,6,"/assets/Art/Stages/Space/block.png", BlockType.Impassable);
		addBlock(9,7,"/assets/Art/Stages/Space/block.png", BlockType.Impassable);
		
		addBlock(10,1,"/assets/Art/Stages/Space/block.png", BlockType.Impassable);
		addBlock(10,6,"/assets/Art/Stages/Space/block.png", BlockType.Impassable);
		
		addBlock(11,1,"/assets/Art/Stages/Space/block.png", BlockType.Impassable); 
		addBlock(11,6,"/assets/Art/Stages/Space/block.png", BlockType.Impassable);
		
		addBlock(12,4,"/assets/Art/Stages/Clock/platform.png", BlockType.Passable);
		
		addBlock(13,4,"/assets/Art/Stages/Clock/platform.png", BlockType.Passable);
		
		

		

  }
}
