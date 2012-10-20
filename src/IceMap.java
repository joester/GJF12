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
		addBlock(0,3, "arg", BlockType.Impassible);
		/*
		 * addSpawn(0,2,"arg", BlockType.CrateSpawn);
		 * addSpawn(15,2, "arg", BlockType.CrateSpawn);
		 * etc
		 */
		addBlock(0,9,"block.psd", BlockType.Impassible);
		addBlock(1,9,"lava.png",BlockType.Lethal);
		addBlock(2,9,"lava.png",BlockType.Lethal);
		addBlock(3,9,"lava.png",BlockType.Lethal);
		addBlock(3,8,"platform.png",BlockType.Passible);
		addBlock(4,9,"lava.png",BlockType.Lethal);
		addBlock(5,9,"lava.psd",BlockType.Impassible);
		addBlock(6,9,"lava.psd",BlockType.Impassible);
		addBlock(7,9,"lava.psd",BlockType.Impassible);
		addBlock(8,9,"lava.psd",BlockType.Impassible);
		addBlock(5,4,"crate.psd",BlockType.Crate);
		addBlock(5,5,"block.psd",BlockType.Impassible);
		addBlock(6,5,"block.psd",BlockType.Impassible);
		addBlock(5,0,"stalactites-leftedge",BlockType.Lethal);
		addBlock(6,0,"stalactites.png",BlockType.Lethal);
		addBlock(7,0,"stalactites.png",BlockType.Lethal);
		addBlock(8,0,"stalactites.png",BlockType.Lethal);
		addBlock(9,0,"stalactites.png",BlockType.Lethal);
		addBlock(10,0,"stalactites.png",BlockType.Lethal);
		addBlock(11,0,"stalactites-rightedge.png",BlockType.Lethal);
		addBlock(7,4,"platform.png",BlockType.Passible);
		addBlock(8,4,"platform.png",BlockType.Passible);
		addBlock(9,4,"platform.png",BlockType.Passible);
		addBlock(10,5,"block.psd",BlockType.Impassible);
		addBlock(11,5,"block.psd",BlockType.Impassible);
		addBlock(11,4,"crate.psd",BlockType.Crate);
		addBlock(8,9,"lava.png",BlockType.Lethal);
		addBlock(9,9,"block.psd",BlockType.Impassible);
		addBlock(10,9,"lava.psd",BlockType.Impassible);
		addBlock(11,9,"lava.psd",BlockType.Impassible);
		addBlock(12,9,"lava.png",BlockType.Lethal);
		addBlock(13,9,"lava.png",BlockType.Lethal);
		addBlock(14,9,"lava.png",BlockType.Lethal);
		addBlock(15,9,"block.psd",BlockType.Impassible);
		addBlock(15,3, "block.psd",BlockType.Impassible);











	}
	
}
