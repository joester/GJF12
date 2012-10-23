
public class SpaceMap2 extends Map
{
	public SpaceMap2(GameWorld gW, String backgroundFileLocation) {
		super(gW, backgroundFileLocation);
		// TODO Auto-generated constructor stub
	}

	String backgroundFileLocation;
	
	public void buildMap()
	{
	
		addBlock(0,7,"/assets/Art/Stages/Space/block3.png", BlockType.Impassable);
		addBlock(0,2,"/assets/Art/Stages/Space/block3.png", BlockType.Impassable);
		
		//addBlock(1,7,"/assets/Art/Stages/Volcano/icicles.png",BlockType.Lethal);
		///addBlock(2,7,"/assets/Art/Stages/Volcano/icicles.png",BlockType.Lethal);
		//addBlock(3,7,"/assets/Art/Stages/Volcano/icicles.png",BlockType.Lethal);
		
		addBlock(3,4,"/assets/Art/Stages/Space/platform.png",BlockType.Passable);
		addBlock(2,6,"/assets/Art/Stages/Space/platform.png",BlockType.Passable);

		
		addBlock(4,7,"/assets/Art/Stages/Space/block.png",BlockType.Impassable);
		addBlock(5,7,"/assets/Art/Stages/Space/block.png",BlockType.Impassable);
		addBlock(6,7,"/assets/Art/Stages/Space/block.png",BlockType.Impassable);
		//addBlock(7,7,"/assets/Art/Stages/Volcano/icicles.png",BlockType.Lethal);
		addBlock(4,3,"/assets/Art/Stages/genericcrate.png",BlockType.Crate);
		addBlock(4,4,"/assets/Art/Stages/Space/block.png",BlockType.Impassable);
		addBlock(5,4,"/assets/Art/Stages/Space/block.png",BlockType.Impassable);
		
		
		addBlock(6,3,"/assets/Art/Stages/Space/platform.png",BlockType.Passable);
		addBlock(7,3, "/assets/Art/Stages/Space/platform.png",BlockType.Passable);
		addBlock(8,3,"/assets/Art/Stages/Space/platform.png",BlockType.Passable);
		addBlock(9,4,"/assets/Art/Stages/Space/block.png",BlockType.Impassable);
		addBlock(10,4,"/assets/Art/Stages/Space/block.png",BlockType.Impassable);
		addBlock(10,3,"/assets/Art/Stages/genericcrate.png",BlockType.Crate);
		addBlock(8,7,"/assets/Art/Stages/Space/block.png",BlockType.Impassable);
		addBlock(9,7,"/assets/Art/Stages/Space/block.png",BlockType.Impassable);
		addBlock(10,7,"/assets/Art/Stages/Space/block.png",BlockType.Impassable);
		addBlock(14,2,"/assets/Art/Stages/Space/block3.png",BlockType.Impassable);
		//addBlock(11,7,"/assets/Art/Stages/Volcano/icicles.png",BlockType.Lethal);
		addBlock(11,4,"/assets/Art/Stages/Space/platform.png",BlockType.Passable);
		//addBlock(12,7,"/assets/Art/Stages/Volcano/icicles.png",BlockType.Lethal);
		//addBlock(13,7,"/assets/Art/Stages/Volcano/icicles.png",BlockType.Lethal);
		addBlock(14,7,"/assets/Art/Stages/Space/block3.png",BlockType.Impassable);
		addBlock(12, 6, "/assets/Art/Stages/Space/platform.png", BlockType.Passable);
	}
	
}
