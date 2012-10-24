import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;



public class Block extends MapEntity {	
	public Block(int x, int y, String imageLocation, BlockType b, int xOffSet,int yOffSet, int sizeXOff, int sizeYOff) {
		super(x, y,imageLocation);		
		blockType = b;
		setHitBox(getX() + xOffSet,getY() + yOffSet,MapEntity.BLOCKSIZE - sizeXOff, MapEntity.BLOCKSIZE - sizeYOff);
	}

	//Only use when block is Destructible
	public void destroyBlock() throws SlickException
	{
		blockType  = BlockType.Passable;
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
		
		return blockType == BlockType.Passable;
	}
	
	public boolean isImpassible(){
		
		return blockType == BlockType.Impassable;
	}


	@Override
	public void render(GameContainer gc, Graphics g)
	{
		image.draw(xCoord, yCoord, 1);	
		//g.draw(hitBox);
	}


	@Override
	public void init(GameContainer arg0) throws SlickException
	{
		
	}


	@Override
	public void update(GameContainer gc, int delta) throws SlickException, InterruptedException
	{
		super.update(gc, delta);		
	}

  }