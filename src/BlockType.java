public enum BlockType {
	Impassable(0,true), Lethal(21,true), Crate(0,true), Passable(63,false);
	public int yHitBoxOffSet;
	public boolean offSetFromTop;
	private BlockType(int yOffs, boolean offSetTop){
		yHitBoxOffSet = yOffs;
		offSetFromTop = offSetTop;
	}
}
