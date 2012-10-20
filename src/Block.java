import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
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
		blockType  = BlockType.Passable;
		image = null;
	}
	
	public BlockType getBlockType()
	{
		return blockType;
	}
	
	public Rectangle getRectangle()
	{
		return hitBox;
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
	
	public Rectangle getRectangle(){
		return hitBox;
	}


	@Override
	public void render(GameContainer arg0, Graphics arg1)
	{
		image.draw(xCoord, yCoord, 1);	
	}


	@Override
	public void init(GameContainer arg0) throws SlickException
	{
		
	}


	@Override
	public void update(GameContainer gc, int delta)
	{
		// TODO Auto-generated method stub
		
	}

  }