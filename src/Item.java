import org.newdawn.slick.Animation;
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
	protected Image projectileLeftImage;
	protected Image projectileRightImage;
	protected Animation projectileLeftAnimation;
	protected Animation projectileRightAnimation;
	protected int projectileWidth;
	protected int projectileHeight;
	protected Hitbox projectileHitbox;
	protected Rectangle projectileOffsets;
	protected Character owner;
	private final int floatRange = 8;
	private float ySpawnLocation;
	
	public Item (float x, float y, String fileLocation,World world)
	{	 
		super(x, y, fileLocation, world);
		//Sets the hit box
		setHitboxSize(45, 45);	
		ySpawnLocation = yCoord;
		yVelocity = .25f;
	}

	@Override
	public void render(GameContainer gc, Graphics g) throws SlickException
	{
		super.render(gc, g);
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



	public void use(World world)
	{		
		if(owner.isFacingRight){
			float x = owner.getHitbox().getCenterX();
			float y = owner.getHitbox().getY();
			world.getProjectiles().add(new Projectile(x, y,this, world));
		}
		else{
			float x = owner.getHitbox().getCenterX() - projectileWidth;
			float y = owner.getHitbox().getY();
			Projectile p = new Projectile(x, y, this, world);
			world.getProjectiles().add(p);
			p.flip();
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
