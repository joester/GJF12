public class Block extends MapEntity {
	public Block(int x, int y,String imageLocation) {
		super(x, y,imageLocation);
		setHitBox(Map.TILE_SIZE,Map.TILE_SIZE);
		blockType = BlockType.Impassible;
	}
}
