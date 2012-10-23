import java.util.ArrayList;
import org.newdawn.slick.Animation;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;

import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
//import org.newdawn.slick.tests.xml.Entity;

public class Character extends Entity{

	public int maxHealth, hP, damage, healthRegen, baseDamage, numRows, numCols;  
	public String name, itemName, auxName;
	public double range, baseRange;
	boolean hasDX = false;
	public int[] controls = new int[4];
	boolean hasAuxItem, hasItem;
	Item item;
	int wins;
	int jumpHeight;
	double xVelocity, yVelocity;
	boolean isMovingUp, isMovingRight, isMovingLeft, isMovingDown;
	boolean canMoveUp, canMoveRight, canMoveLeft, canMoveDown;
	boolean punched, hasHelp, animationInPlay;
	boolean jumpAvailable, isJumpingLeft, isJumpingRight, movingLeft, movingRight, punchLeft, punchRight;
	Auxillary auxItem;
	ArrayList<Animation> animationSet = new ArrayList<Animation>();
	Animation currentAnimation;
	int time;
	boolean attackFlag;
	private boolean isPunching;
	private boolean isFacingRight;
	private boolean isJumping;
	private boolean isIdle;
	private boolean isRunning;
	public int gravityCounter;

	//GameWorld gameWorld = new GameWorld();
	//Set<Body> bodies = new HashSet<Body>();


	public Character(int x, int y, String imageLocation)
	{
		super(x,y, imageLocation);
		hasAuxItem = false;
		hasItem = false;
		//Place holder numbers
		hP = 100;
		baseDamage = 2;
		wins = 0;
		setHitBoxSize(42,80);
		item = null;

		this.name = name;
		this.maxHealth = maxHealth;
		hP = maxHealth;
		damage = 5;
		healthRegen = 1;
		itemName = null;
		auxName = null;
		jumpAvailable = true;

		this.numRows = numRows;
		this.numCols = numCols;

	}

	public void setControls(int left, int up, int right, int down){
		controls[0] = left;
		controls[1] = up;
		controls[2] = right;
		controls[3] = down;
	}



	public Animation getAnimation(){
		return animation;
	}

	public String getName()
	{
		return name;
	}

	public int gethP()
	{
		return hP;
	}

	public void modifyHealth(int deltaHealth)
	{
		hP += deltaHealth;		
	}

	public int getDamage()
	{
		return damage;
	}

	public void pickUpItem(Item item)
	{
		damage = item.damage;
		range = item.range;
		itemName = item.name;
		this.item = item;
		hasItem = true;
	}


	public void dropitem()
	{
		damage = baseDamage;
		range = baseRange;
		itemName = null;
	}

	public void pickUpAux(Auxillary auxItem)
	{
		this.auxItem = auxItem;
		hasAuxItem = true;
	}

	public void useAux()
	{
		if (hasAuxItem == true)
		{
			auxItem.use();
			hasAuxItem = false;
			auxItem = null;
		}
	}

	public void attack()
	{
		if (hasItem)
		{
			//item.use();
		}
	}

	public void determineDirection()
	{
		//Moving RIGHT
		if (xVelocity > 0)
		{
			isMovingRight = true;
			isMovingLeft = false;
			//Moving UP-RIGHT
			if (yVelocity > 0)
			{
				isMovingUp = false;
				isMovingDown = true;
			}

			//Moving DOWN-RIGHT
			else if (yVelocity < 0)
			{
				isMovingUp = true;
				isMovingDown = false;
			}

			//Moving ONLY Right
			else
			{
				isMovingUp = false;
				isMovingDown = false;
			}	
		}
		else if (xVelocity < 0)
		{
			isMovingRight = false;
			isMovingLeft = true;
			//Moving UP-LEFT
			if (yVelocity > 0)
			{
				isMovingUp = false;
				isMovingDown = true;
			}

			//Moving DOWN-LEFT
			else if (yVelocity < 0)
			{
				isMovingUp = true;
				isMovingDown = false;
			}

			//Moving ONLY Left
			else
			{
				isMovingUp = false;
				isMovingDown = false;
			}
		}	
		//xVelocity = 0
		else
		{
			isMovingLeft = false;
			isMovingRight = false;
			//Moving only UP
			if (yVelocity > 0)
			{
				isMovingUp = false;
				isMovingDown = true;
			}

			//Moving only DOWN
			else if (yVelocity < 0)
			{
				isMovingUp = true;
				isMovingDown = false;
			}

			//NOT MOVING
			else
			{
				isMovingLeft= false;
				isMovingRight = false;
				isMovingUp = false;
				isMovingDown = false;
				return;
			}
		}
	}
	//}

	@Override
	public void init(GameContainer gc) throws SlickException{
		jumpHeight = 0;

		Image[] i = new Image[6];
		i[0] = new Image("/assets/Art/jump-spritesheet.png");
		i[1] = new Image("/assets/punch-spritesheet.png");
		i[2] = new Image("/assets/Art/stand-spritesheet.png");
		i[3] = new Image("/assets/Art/jump-spritesheet.png").getFlippedCopy(true, false);
		i[4] = new Image("/assets/punch-spritesheet.png").getFlippedCopy(true, false);
		i[5] = new Image("/assets/Art/stand-spritesheet.png").getFlippedCopy(true, false);
		int[] cols = {4, 2,3};
		int count = 0;
		boolean toFlipped = false;
		setControls(Input.KEY_A, Input.KEY_W, Input.KEY_D, Input.KEY_S);
		for(Image img : i){
			if(img.equals(i[0]) || img.equals(i[4])){
				img = img.getSubImage(0, 25, img.getWidth(), img.getHeight() - 25);
			}
			if(count == 3){
				count = 0;
				toFlipped = true;
			}
			Image[] imagelist = new Image[cols[count]];
			int imageListTrack = 0;
			renderEnt(img, img.getWidth() / cols[count], img.getHeight());
			if(toFlipped){
				for(int j = cols[count] - 1; j >= 0; j --){
					imagelist[imageListTrack] = animation.getImage(j); 
					imageListTrack += 1;
				}
				animation = new Animation(imagelist, 300);
			}

			count += 1;
			
			animation.stopAt(animation.getFrameCount() - 1);
			animationSet.add(animation);
		}
		currentAnimation = animationSet.get(2);
	}

	@Override
	public void render(GameContainer gc, Graphics g) throws SlickException
	{
		currentAnimation.draw(xCoord, yCoord);
		if(currentAnimation.isStopped()){
			currentAnimation.restart();
		}
		g.draw(hitBox);
	}

	@Override
	public void update(GameContainer gc, int delta) throws SlickException, InterruptedException
	{
		time = delta;
		if(xVelocity < 0){
			if(canMoveLeft){
				xCoord += xVelocity;
			}
		}
		else{
			if(canMoveRight){
				xCoord += xVelocity;
			}
		}
		
		
		if(yVelocity > 0){
			if(canMoveDown){
				yCoord += yVelocity;
			}
		}
		else{
			if(canMoveUp){
				yCoord += yVelocity;
			}
		}
		//System.out.println((canMoveDown && isMovingDown) || (canMoveUp && isMovingUp));
		//System.out.println(yVelocity);
		//System.out.println(xVelocity);
		isJumping = !(Math.abs(yVelocity) < 1) && (canMoveDown || isMovingDown);

		isRunning = !isJumping && xVelocity != 0;
		isIdle = !isJumping && !isRunning;
		
		if(isMovingRight){
			isFacingRight = true;
		}
		else if(isMovingLeft){
			isFacingRight = false;
		}
		selectAnimation();
		setHitBox(xCoord, yCoord);
		currentAnimation.update(1);


		xVelocity = 0;
		gravityCounter++;

		canMoveUp = true;
		canMoveDown = true;
		canMoveRight = true;
		canMoveLeft = true;
	}	

	private void selectAnimation(){
		if(isFacingRight){
			if(isJumping){
				if(currentAnimation !=  animationSet.get(0))
					currentAnimation = animationSet.get(0);
			}
			else if(isPunching){
				if(currentAnimation !=  animationSet.get(1))
					currentAnimation = animationSet.get(1);
			}
			else if(isIdle || isRunning){
				if(currentAnimation !=  animationSet.get(2)){
					currentAnimation = animationSet.get(2);
				}
			}
		}
		else{
			if(isJumping){
				if(currentAnimation !=  animationSet.get(3))
					currentAnimation = animationSet.get(3);	
			}
			else if(isPunching){
				if(currentAnimation !=  animationSet.get(4))
					currentAnimation = animationSet.get(4);
			}
			else if(isIdle || isRunning){
				if(currentAnimation !=  animationSet.get(5)){
					currentAnimation = animationSet.get(5);
				}
			}
		}
		isJumping = false;
		isPunching = false;
		isIdle = false;
	}
	
	public void resetGravityCounter(){
		gravityCounter = 0;
	}
}
