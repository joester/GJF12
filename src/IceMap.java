import java.util.ArrayList;
import java.util.List;
import org.newdawn.slick.geom.Rectangle;


public class IceMap extends Map
{
	String backgroundFileLocation;
	
	public IceMap(GameWorld gW)
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
		addBlock(0,7,"/assets/Art/Stages/Volcano/iceblock.png", BlockType.Impassable);
		addBlock(1,7,"/assets/Art/Stages/Volcano/icicles.png",BlockType.Lethal);
		addBlock(2,7,"/assets/Art/Stages/Volcano/icicles.png",BlockType.Lethal);
		addBlock(3,7,"/assets/Art/Stages/Volcano/icicles.png",BlockType.Lethal);
		addBlock(2,6,"/assets/Art/Stages/Volcano/iceplatform.png",BlockType.Passable);
		//addBlock(3,7,"/assets/Art/Stages/Volcano/icicles.png",BlockType.Lethal);
		addBlock(4,7,"/assets/Art/Stages/Volcano/iceblock.png",BlockType.Impassable);
		addBlock(5,7,"/assets/Art/Stages/Volcano/iceblock.png",BlockType.Impassable);
		addBlock(6,7,"/assets/Art/Stages/Volcano/iceblock.png",BlockType.Impassable);
		addBlock(7,7,"/assets/Art/Stages/Volcano/icicles.png",BlockType.Impassable);
		addBlock(4,3,"/assets/Art/Stages/Volcano/crate.png",BlockType.Crate);
		addBlock(5,4,"/assets/Art/Stages/Volcano/iceblock.png",BlockType.Impassable);
		addBlock(6,4,"/assets/Art/Stages/Volcano/iceblock.png",BlockType.Impassable);
		//addBlock(5,0,"/assets/Art/Stages/Volcano/icicles-left.png",BlockType.Lethal);
		//in ice map the stalactites are gone?
		/**
		addBlock(6,0,"stalactites.png",BlockType.Lethal);
		addBlock(7,0,"stalactites.png",BlockType.Lethal);
		addBlock(8,0,"stalactites.png",BlockType.Lethal);
		addBlock(9,0,"stalactites.png",BlockType.Lethal);
		addBlock(10,0,"stalactites.png",BlockType.Lethal);
		**/
		
		//addBlock(11,0,"/assets/Art/Stages/Volcano/icicles-right.png",BlockType.Lethal);
		addBlock(7,4,"/assets/Art/Stages/Volcano/iceplatform.png",BlockType.Passable);
		addBlock(8,4, "/assets/Art/Stages/Volcano/iceplatform.png",BlockType.Passable);
		addBlock(9,4,"/assets/Art/Stages/Volcano/iceplatform.png",BlockType.Passable);
		addBlock(10,5,"/assets/Art/Stages/Volcano/iceblock.png",BlockType.Impassable);
		addBlock(11,5,"/assets/Art/Stages/Volcano/iceblock.png",BlockType.Impassable);
		addBlock(11,4,"/assets/Art/Stages/Volcano/crate.png",BlockType.Crate);
		addBlock(8,9,"/assets/Art/Stages/Volcano/icicles.png",BlockType.Lethal);
		addBlock(9,9,"/assets/Art/Stages/Volcano/iceblock.png",BlockType.Impassable);
		addBlock(10,9,"/assets/Art/Stages/Volcano/icicles.png",BlockType.Impassable);
		addBlock(11,9,"/assets/Art/Stages/Volcano/icicles.png",BlockType.Impassable);
		addBlock(12,9,"/assets/Art/Stages/Volcano/icicles.png",BlockType.Lethal);
		addBlock(13,9,"/assets/Art/Stages/Volcano/icicles.png",BlockType.Lethal);
		addBlock(14,9,"/assets/Art/Stages/Volcano/icicles.png",BlockType.Lethal);
		addBlock(15,9,"/assets/Art/Stages/Volcano/iceblock.png",BlockType.Impassable);
		addBlock(15,3, "/assets/Art/Stages/Volcano/iceblock.png",BlockType.Impassable);











	}
	
}
