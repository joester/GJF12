

public abstract class MapEntity extends Entity {
	public static final int BLOCKSIZE = 84;
	protected BlockType blockType;
	
	public MapEntity(int x, int y, String imageLocation){
		super(x,y,imageLocation,null);
	}
	
	public void setPosition(int x, int y){
		xCoord = x;
		yCoord = y;
	}
	
	public BlockType getBlockType(){
		return blockType;
	}

	public int getNumberOfPlayers(){
		return 0;
	}
}