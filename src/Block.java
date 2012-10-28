import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;



public class Block extends Entity {	
	public static final int BLOCKSIZE = 84;
	protected BlockType blockType;
	public Block(int x, int y, String imageLocation, BlockType blockType, int hitBoxXPosOffset,int hitBoxYPosOffset, int hitBoxXOffset, int hitBoxYOffset) {
		super(x * BLOCKSIZE, y * BLOCKSIZE,imageLocation, null);		
		this.blockType = blockType;	
		hitBox.setSize(BLOCKSIZE, BLOCKSIZE);
		setHitBoxOffsets(hitBoxXPosOffset,hitBoxYPosOffset,hitBoxXOffset,hitBoxYOffset);
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

	@Override
	public void render(GameContainer gc, Graphics g)
	{
		image.draw(xCoord, yCoord, 1);	
		g.draw(hitBox);
	}


	@Override
	public void update(GameContainer gc, int delta) throws SlickException, InterruptedException
	{
		super.update(gc, delta);		
	}

  }