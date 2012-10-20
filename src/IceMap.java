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
		//addSpawn(0,2,"arg", BlockType.CrateSpawn);
		addBlock(0,9,"arg", BlockType.Impassible);
		addBlock(1,9,"arg",BlockType.Lethal);
		addBlock(2,9,"arg",BlockType.Lethal);
		addBlock(3,9,"arg",BlockType.Lethal);
		addBlock(3,8,"arg",BlockType.Passible);
		addBlock(4,9,"arg",BlockType.Lethal);
		addBlock(5,9,"arg",BlockType.Impassible);
		addBlock(6,9,"arg",BlockType.Impassible);
		addBlock(7,9,"arg",BlockType.Impassible);
		addBlock(8,9,"arg",BlockType.Impassible);
		addBlock(5,4,"arg",BlockType.Crate);
		addBlock(5,5,"arg",BlockType.Impassible);
		addBlock(6,5,"arg",BlockType.Impassible);
		addBlock(5,0,"arg",BlockType.Lethal);
		addBlock(6,0,"arg",BlockType.Lethal);
		addBlock(7,0,"arg",BlockType.Lethal);
		addBlock(8,0,"arg",BlockType.Lethal);
		addBlock(9,0,"arg",BlockType.Lethal);
		addBlock(10,0,"arg",BlockType.Lethal);
		addBlock(11,0,"arg",BlockType.Lethal);
		addBlock(7,4,"arg",BlockType.Passible);
		addBlock(8,4,"arg",BlockType.Passible);
		addBlock(9,4,"arg",BlockType.Passible);
		addBlock(10,5,"arg",BlockType.Impassible);
		addBlock(11,5,"arg",BlockType.Impassible);
		addBlock(11,4,"arg",BlockType.Crate);
		addBlock(8,9,"arg",BlockType.Lethal);
		addBlock(9,9,"arg",BlockType.Impassible);
		addBlock(10,9,"arg",BlockType.Impassible);
		addBlock(11,9,"arg",BlockType.Impassible);
		addBlock(12,9,"arg",BlockType.Lethal);
		addBlock(13,9,"arg",BlockType.Lethal);
		addBlock(14,9,"arg",BlockType.Lethal);
		addBlock(15,9,"arg",BlockType.Impassible);
		addBlock(15,3, "arg",BlockType.Impassible);











	}
	
}
