import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;


public class Item extends Entity {
	protected double startUpTime, reloadTime;
	protected int damage;
	protected float xVelocity;
	protected float yVelocity;
	protected String name;
	protected boolean dropChance;
	
	//fields pertaining to instantiating projectiles
	protected float projectileRange;
	protected float projectileXVelocity;
	protected float projectileYVelocity;
	protected String projectileImageLocation;
	protected Image projectileImage;
	protected Rectangle projectileHitBox;
	protected float pXPosOffset, pYPosOffset;
	protected float pXOffset, pYOffset;
	protected Character owner;
	private final int floatRange = 8;
	private float ySpawnLocation;

	
	public Item (float x, float y, String fileLocation,GameWorld gW)
	{	 
		super(x, y, fileLocation, gW);
		//Sets the hit box
		setHitBoxSize(45, 45);	
		ySpawnLocation = yCoord;
		yVelocity = .25f;
	}

	@Override
	public void render(GameContainer gc, Graphics g) throws SlickException
	{
		//g.draw(hitBox);
		image.draw(xCoord, yCoord);
	}

	@Override
	public void update(GameContainer gc, int delta) throws SlickException,
	InterruptedException
	{
		yCoord += yVelocity;
		if(Math.abs(yCoord - ySpawnLocation) > floatRange)
				yVelocity *= -1;
		
		super.update(gc, delta);
	}



	public void use(GameWorld gW)
	{		
		if(owner.isFacingRight){
			float x = owner.xCoord+owner.getHitBox().getWidth()/2;
			float y = owner.yCoord;
			gW.getListOfProjectiles().add(new Projectile(x, y,this, gW));
		}
		else{
			float x = owner.xCoord - projectileImage.getWidth()/2;
			float y = owner.yCoord;
			Projectile p = new Projectile(x, y, this, gW);
			gW.getListOfProjectiles().add(p);
			p.flipImage(true, false);
			p.setXVelocity(-projectileXVelocity);
		}
	}

	public void setYSpawnLocation(float y) {
		ySpawnLocation = y;
	}
	
	public Character getOwner(){
		return owner;
	}
	
	public void setOwner(Character owner){
		this.owner = owner;
	}
}
