import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;


public abstract class MapEntity extends Entity {
	public final int blockSize = 84;
	protected Rectangle hitBox;
	protected BlockType blockType;
	
	public MapEntity(int x, int y, String imageLocation){
		super(x,y,imageLocation);
	}
	
	public Rectangle getHitBox(){
		return hitBox;
	}
	
	public void setPosition(int x, int y){
		xCoord = x;
		yCoord = y;
	}
	
	public int getX(){
		return xCoord;
	}
	
	public int getY(){
		return yCoord;
	}
	
	public BlockType getBlockType(){
		return blockType;
	}
}
