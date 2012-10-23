public enum BlockType {
	Impassable(0), Lethal(21), Crate(0), Passable(0);
	public int yHitBoxOffSet;
	private BlockType(int yOffs){
		yHitBoxOffSet = yOffs;
	}
}
