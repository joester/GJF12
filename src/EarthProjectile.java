import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;


public class EarthProjectile extends Projectile {
	float xCenterOfRotation;
	float yCenterOfRotation;
	float radius;
	float angularVelocity;
	float angle;
	float spinAngle;
	float spinAngularVelocity;
	protected Image imageLeft;
	protected Image imageRight;
	Image currentImage;
	public EarthProjectile(float xSpawnLocation, float ySpawnLocation,
			Item item, World world) {
		super(xSpawnLocation, ySpawnLocation, item, world);
		xCenterOfRotation = owner.getHitbox().getCenterX();
		yCenterOfRotation = owner.getHitbox().getCenterY();
		angle = 90; //PI/2
		imageRight = item.projectileRightImage;
		imageLeft = item.projectileLeftImage;
		radius = 84 + imageRight.getHeight()/2;
		spinAngularVelocity = 15;
	}
	
	public void checkCollisions() {
		for (Character c : world.getCharacters())
		{
			if(owner != c){
				if (getHitbox().intersects(c.getHitbox()))
				{			
					c.modifyHealth(damage);
					world.remove(this);
					world.punchHit.get((int) (3 * Math.random())).play();
				}
			}
		}
		for(Block b: world.getBlocks()){
			if(getHitbox().intersects(b.getHitbox())){
				if(b.blockType == BlockType.Crate){
					world.spawnItem(b);
					world.playRandomSound(world.punchHit);
				}
			}
		}
	}		
	
	public void render(GameContainer container, Graphics g)
			throws SlickException{	
		if(isFlippedHorizontally){
			currentImage = imageLeft;
			spinAngularVelocity = -15;
		}
		else{
			currentImage = imageRight;
			spinAngularVelocity = 15;
		}
		spinAngle += spinAngularVelocity;
		currentImage.setRotation(spinAngle);
		currentImage.draw(xCoord, yCoord);
		//g.draw(getHitbox());
	}
	
	@Override
	public void update(GameContainer container, int delta)
			throws SlickException, InterruptedException{
		if(isFlippedHorizontally){
			angularVelocity = 2; 
			spinAngularVelocity = 15;
			xCoord = xCenterOfRotation + (float) (radius*Math.cos(angle*Math.PI/180)) - imageRight.getWidth();
			yCoord = yCenterOfRotation - (float) (radius*Math.sin(angle*Math.PI/180));
		}
		else{
			angularVelocity = -2; 
			spinAngularVelocity = -15;
			xCoord = xCenterOfRotation + (float) (radius*Math.cos(angle*Math.PI/180));
			yCoord = yCenterOfRotation - (float) (radius*Math.sin(angle*Math.PI/180));
		}
		
		angle += angularVelocity;
		//Remove when angle = PI,0
		if(angle <= 0 || angle > 180){ 
			world.remove(this);
		}
		setHitboxLocation(xCoord,yCoord);
	}
}
