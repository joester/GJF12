import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;



public class Block extends Entity {	
	public static final int BLOCKSIZE = 84;
	protected BlockType blockType;
	protected int maxCrateHealth;
	protected int currentCrateHealth;

	
	public Block(int x, int y, String imageLocation, BlockType blockType, Rectangle offsets) {
		super(x * BLOCKSIZE, y * BLOCKSIZE,imageLocation, null);		
		this.blockType = blockType;	
		hitbox.setSize(BLOCKSIZE, BLOCKSIZE);
		hitbox.setOffsets(offsets);
		maxCrateHealth = 3;
		currentCrateHealth = maxCrateHealth;
	
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
	public void render(GameContainer gc, Graphics g) throws SlickException
	{
		image.draw(xCoord, yCoord, 1);	
		super.render(gc,g);
	}


	@Override
	public void update(GameContainer gc, int delta) throws SlickException, InterruptedException
	{
		super.update(gc, delta);		
	}

  }