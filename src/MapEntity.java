import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;


public abstract class MapEntity extends Entity {
	public static final int BLOCKSIZE = 84;
	protected BlockType blockType;
	
	public MapEntity(int x, int y, String imageLocation){
		super(x,y,imageLocation);
	}
	
	public void setPosition(int x, int y){
		xCoord = x;
		yCoord = y;
	}
	
	public int getX(){
		return (int) getHitBox().getX();
	}
	
	public int getY(){
		return (int) getHitBox().getY();
	}
	
	public BlockType getBlockType(){
		return blockType;
	}

	public int getNumberOfPlayers(){
		return 0;
	}
}