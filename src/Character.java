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
int xCoord, yCoord, jumpHeight;
double xVelocity, yVelocity;
String imageLocation;
boolean isMovingUp, isMovingRight, isMovingLeft, isMovingDown;
boolean canMoveUp, canMoveRight, canMoveLeft, canMoveDown;
boolean punched, hasHelp, animationInPlay;
boolean[] isMoving = {isMovingUp, isMovingRight, isMovingLeft, isMovingDown};
boolean jumpAvailable, isJumpingLeft, isJumpingRight, movingLeft, movingRight, punchLeft, punchRight;
Auxillary auxItem;
ArrayList<Animation> animationSet = new ArrayList<Animation>();
Animation currentAnimation;
int time;
Rectangle hitBox;

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
		super.setHitBox(x ,y ,42,84);
		hitBox = getHitBox();
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
	
	public void setMove(boolean isMoving){
		hasDX = isMoving;
	}
	
	public Rectangle getRectangle()
	{
		return hitBox;
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
		//if(hasDX){
			//animation.draw(xCoord, yCoord, 64, 64);
		if (xVelocity > 0)
		{
			isMovingRight = true;
			isMovingLeft = false;
			//Moving UP-RIGHT
			if (yVelocity > 0)
			{
				isMovingUp = false;
				isMovingDown = true;
				boolean[] newMoves = {true, true, false, false};
				isMoving = newMoves;
			}
			
			//Moving DOWN-RIGHT
			else if (yVelocity < 0)
			{
				isMovingUp = true;
				isMovingDown = false;
				boolean[] newMoves = {false, true, false, true};
				isMoving = newMoves;
			}
			
			//Moving ONLY Right
			else
			{
				isMovingUp = false;
				isMovingDown = false;
				boolean[] newMoves = {false, true, false, false};
				isMoving = newMoves;
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
				boolean[] newMoves = {true, true, false, false};
				isMoving = newMoves;
			}
			
			//Moving DOWN-LEFT
			else if (yVelocity < 0)
			{
				isMovingUp = true;
				isMovingDown = false;
				boolean[] newMoves = {false, false, true, true};
				isMoving = newMoves;
			}
			
			//Moving ONLY Left
			else
			{
				isMovingUp = false;
				isMovingDown = false;
				boolean[] newMoves = {false, false, true, false};
				isMoving = newMoves;
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
				boolean[] newMoves = {true, false, false, false};
				isMoving = newMoves;
			}
			
			//Moving only DOWN
			else if (yVelocity < 0)
			{
				isMovingUp = true;
				isMovingDown = false;
				boolean[] newMoves = {false, false, false, true};
				isMoving = newMoves;
			}
			
			//NOT MOVING
			else
			{
				isMovingLeft= false;
				isMovingRight = false;
				isMovingUp = false;
				isMovingDown = false;
				boolean[] newMoves = {false, false, false, false};
				isMoving = newMoves;
			}
		}
		}
	//}
		
	@Override
	public void init(GameContainer gc) throws SlickException{
		jumpHeight = 0;
		
		Image[] i = new Image[6];
		i[0] = new Image("/assets/jump-spritesheet.png");
		i[1] = new Image("/assets/stand-spritesheet.png");
		i[2] = new Image("/assets/punch-spritesheet.png");
		i[3] = new Image("/assets/jump-spritesheet.png").getFlippedCopy(true, false);
		i[4] = new Image("/assets/stand-spritesheet.png").getFlippedCopy(true, false);
		i[5] = new Image("/assets/punch-spritesheet.png").getFlippedCopy(true, false);
		int[] cols = {4, 3, 2};
		int count = 0;
		boolean toFlipped = false;
		setControls(Input.KEY_A, Input.KEY_W, Input.KEY_D, Input.KEY_S);
		for(Image img : i){
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
		
		System.out.println(animationSet.size());
	}
	
	@Override
	public void render(GameContainer gc, Graphics g) throws SlickException
	{
		currentAnimation.draw(xCoord, yCoord);
		if(currentAnimation.equals(animationSet.get(0))){
			isJumpingRight = true;
		}
		if(currentAnimation.equals(animationSet.get(1))){
			isMovingRight = true;
		}
		if(currentAnimation.equals(animationSet.get(2))){
			punchRight = true;
		}
		if(currentAnimation.equals(animationSet.get(3))){
			isJumpingLeft = true;
		}
		if(currentAnimation.equals(animationSet.get(4))){
			isMovingRight = true;
		}
		if(currentAnimation.equals(animationSet.get(5))){
			isJumpingRight = true;
		}
		
		if(currentAnimation.isStopped()){
			currentAnimation.restart();
			if(isMovingRight){
				currentAnimation = animationSet.get(1);
				isMovingLeft = false;
			}
			if(isMovingLeft){
				currentAnimation = animationSet.get(4);
				isMovingRight = false;
			}
			punchLeft = false;
			punchRight = false;
			isJumpingLeft = false;
			isJumpingRight = false;
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
		
		//yCoord += yVelocity;
		
		setHitBox(xCoord, yCoord);
		currentAnimation.update(delta);
		//super.update(gc, delta);
		Input input = new Input(delta);
//		if(xVelocity != 0){
//			//xCoord += .5 * delta * xVelocity;
//			this.getHitBox().setX(xCoord);
//			xVelocity = 0;
//			currentAnimation = animationSet.get(2);
//		}
//		if(yVelocity != 0){
//			//yCoord += .5 * delta * yVelocity;
//			this.getHitBox().setY(yCoord);
//			yVelocity = 0;
//			currentAnimation = animationSet.get(2);
//		}
		
		if(input.isKeyDown(controls[0])){
			//xCoord += .5 * delta;
			//xVelocity = -1;
			currentAnimation = animationSet.get(4);
		}
		
		if(input.isKeyDown(controls[1])){
			
			if(!punchLeft && !punchRight){
				if(movingRight){
					currentAnimation = animationSet.get(0);
					isJumpingRight = true;
				}
				if(movingLeft){
					currentAnimation = animationSet.get(3);
					isJumpingLeft = true;
				}
				
				
			}
			
			
		}
		if(input.isKeyDown(controls[2])){
			//xCoord += .5 * delta;
			//xVelocity = 1;
			movingRight = true;
			movingLeft = false;
			currentAnimation = animationSet.get(1);
		}
		
		// 3 is UP
		//if(input.isKeyDown(controls[3]) && jumpAvailable){
			//jumpAvailable = false;			
			//yCoord += .5 * delta;
			//yVelocity += 3;
			//currentAnimation = animationSet.get(2);
			
		//}
		if(input.isKeyDown(Input.KEY_SPACE)){
			if(!isJumpingLeft && !isJumpingRight){
				if(movingLeft){
					punchLeft = true;
					currentAnimation = animationSet.get(5);
				}
				if(movingRight){
					punchRight = true;
					currentAnimation = animationSet.get(2);
				}
			}
		}
		
		canMoveUp = true;
		canMoveDown = true;
		canMoveRight = true;
		canMoveLeft = true;
	}
	
}
