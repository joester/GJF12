import org.newdawn.slick.SlickException;



public class Block extends MapEntity {
	
	public Block(int x, int y,String imageLocation, BlockType b) {
		super(x, y,imageLocation);
		blockType = b;
	}
	

	//Only use when block is Destructible
	public void destroyBlock() throws SlickException
	{
		blockType  = BlockType.Passible;
		image = null;
	}
	
	//checks on what type the blocks are.
	public boolean isLethal(){
		
		if(blockType == BlockType.Lethal)
			return true;
		else
			return false;
	}
	
	public boolean isPassible(){
		
		if(blockType == BlockType.Passible)
			return true;
		else
			return false;
	}
	public boolean isImpassible(){
		
		if(blockType == BlockType.Impassible)
			return true;
		else
			return false;
	}
	
	public boolean isPlatform(){
		
		if(blockType == BlockType.Platform)
			return true;
		else
			return false;
	}
	public boolean isDestructible(){
		if(blockType == BlockType.Destructible)
			return true;
		else
			return false;
	}
  }