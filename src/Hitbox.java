import org.newdawn.slick.geom.Rectangle;

@SuppressWarnings("serial")
public class Hitbox extends Rectangle{
	protected Rectangle offsets;
	public Hitbox(float x, float y, float width, float height) {
		super(x, y, width, height);
		offsets = new Rectangle(0,0,0,0);
	}
	
	public void setOffsets(Rectangle offsets){
		this.offsets = offsets;
		setBounds(x,y,width,height);
	}
	
	@Override
	public void setSize(float w, float h){
		super.setSize(w - offsets.getWidth(), h - offsets.getHeight());
	}
	
	@Override
	public void setBounds(float x, float y, float w, float h){
		this.x = x + offsets.getX(); 
		this.y = y + offsets.getY(); 
		this.width = w - offsets.getWidth();
		this.height = h - offsets.getHeight();
	}
	
	@Override
	public void setLocation(float x, float y){
		super.setLocation(x + offsets.getX(),y + offsets.getY());
	}
	
	public float getTop(){
		return y;
	}
	
	public float getBottom(){
		return y + height;
	}
	
	public float getLeft(){
		return x;
	}
	
	public float getRight(){
		return x + width;
	}
}
