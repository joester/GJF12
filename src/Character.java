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
int xVelocity, yVelocity;
String imageLocation;
boolean isMovingUp, isMovingRight, isMovingLeft, isMovingDown;
boolean punched, hasHelp, animationInPlay;
boolean[] isMoving = {isMovingUp, isMovingRight, isMovingLeft, isMovingDown};
boolean jumpAvailable;
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
		super.setHitBox(40, 84);
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
	
	public void pickupitem(Item item)
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
		if(hasDX){
			//animation.draw(xCoord, yCoord, 64, 64);
		if (xVelocity > 0)
		{
			isMovingRight = true;
			isMovingLeft = false;
			//Moving UP-RIGHT
			if (yVelocity > 0)
			{
				isMovingUp = true;
				isMovingDown = false;
				boolean[] newMoves = {true, true, false, false};
				isMoving = newMoves;
			}
			
			//Moving DOWN-RIGHT
			else if (yVelocity < 0)
			{
				isMovingUp = false;
				isMovingDown = true;
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
				isMovingUp = true;
				isMovingDown = false;
				boolean[] newMoves = {true, true, false, false};
				isMoving = newMoves;
			}
			
			//Moving DOWN-LEFT
			else if (yVelocity < 0)
			{
				isMovingUp = false;
				isMovingDown = true;
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
				isMovingUp = true;
				isMovingDown = false;
				boolean[] newMoves = {true, false, false, false};
				isMoving = newMoves;
			}
			
			//Moving only DOWN
			else if (yVelocity < 0)
			{
				isMovingUp = false;
				isMovingDown = true;
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
	}
		
	@Override
	public void init(GameContainer gc) throws SlickException{
		jumpHeight = 0;
		
		Image[] i = new Image[4];
		i[0] = (new Image("/assets/stand-0.png")).getFlippedCopy(true, false);
		i[1] = new Image("/assets/jump-spritesheet.png");
		i[2] = new Image("/assets/stand-spritesheet.png");
		i[3] = new Image("/assets/punch-spritesheet.png").getFlippedCopy(true, false);
		int[] cols = {1, 4, 3, 2};
		int count = 0;
		setControls(Input.KEY_A, Input.KEY_W, Input.KEY_D, Input.KEY_S);
		for(Image img : i){
			renderEnt(img, img.getWidth() / cols[count], img.getHeight());
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
		if(currentAnimation.equals(animationSet.get(1))){
			if(currentAnimation.getFrame() >= 1)
				yCoord -= 0.05 * time;
		}
		if(currentAnimation.isStopped()){
			currentAnimation = animationSet.get(2);
			currentAnimation.restart();
		}
	}

	@Override
	public void update(GameContainer gc, int delta) throws SlickException, InterruptedException
	{
		time = delta;
		
		currentAnimation.update(delta);
		//super.update(gc, delta);
		Input input = new Input(delta);
		if(xVelocity != 0){
			xCoord += .5 * delta * xVelocity;
			this.getHitBox().setX(xCoord);
			xVelocity = 0;
			currentAnimation = animationSet.get(2);
		}
		else if(input.isKeyDown(controls[1])){
			
			currentAnimation = animationSet.get(1);
			if(currentAnimation.isStopped()){
				currentAnimation.restart();
				currentAnimation = animationSet.get(2);
			}
			
		}
		else if(input.isKeyDown(controls[2])){
			xCoord += .5 * delta;
			currentAnimation = animationSet.get(2);
		}
		else if(input.isKeyDown(controls[3])){
			yCoord += .5 * delta;
			currentAnimation = animationSet.get(2);
			
		}
		else if(input.isKeyDown(Input.KEY_SPACE)){
			currentAnimation = animationSet.get(3);
			if(currentAnimation.isStopped()){
				currentAnimation.restart();
				currentAnimation = animationSet.get(2);
			}
		}
		
	}
	
}
