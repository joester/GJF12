import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;



public class Block extends MapEntity {
	
	Rectangle hitBox;
	
	public Block(int x, int y, String imageLocation, BlockType b, Rectangle hitBox) {
		super(x, y,imageLocation);		
		blockType = b;
		this.hitBox = hitBox;
	}
	

	//Only use when block is Destructible
	public void destroyBlock() throws SlickException
	{
		blockType  = BlockType.Passible;
		image = null;
	}
	
	public BlockType getBlockType()
	{
		return blockType;
	}
	
	//checks on what type the blocks are.
	public boolean isLethal(){		
		return blockType == BlockType.Lethal;
	}
	
	public boolean isPassible(){
		
		return blockType == BlockType.Passible;
	}
	
	public boolean isImpassible(){
		
		return blockType == BlockType.Impassible;
	}
	
	public boolean isPlatform(){		
		return blockType == BlockType.Platform;
	}
	
	public boolean isDestructible(){
		return blockType == BlockType.Destructible;

	}
  }