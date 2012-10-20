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
boolean punched, jumped, hasHelp;
boolean[] isMoving = {isMovingUp, isMovingRight, isMovingLeft, isMovingDown};
Auxillary auxItem;
ArrayList<Animation> animationSet = new ArrayList<Animation>();
long helpTimer;
long startTime = System.nanoTime();

//GameWorld gameWorld = new GameWorld();
//Set<Body> bodies = new HashSet<Body>();


	public Character(int x, int y, String imageLocation, String name, int maxHealth,
		int numRows, int numCols)
	{
		super(x,y, imageLocation);
		hasAuxItem = false;
		hasItem = false;
		//Place holder numbers
		hP = 100;
		baseDamage = 2;
		wins = 0;
		helpTimer = 5000000000L;
			
		//super.setHitBox(x, y, 40, 40);
		hitBox = getHitBox();
		item = null;
		
		this.name = name;
		this.maxHealth = maxHealth;
		hP = maxHealth;
		damage = 5;
		healthRegen = 1;
		itemName = null;
		//aux = null;
		//aux = null;
		auxName = null;
		jumpAvailable = true;
		
		this.numRows = numRows;
		this.numCols = numCols;
	}
	public void update()
	{
		xCoord += xVelocity;
		yCoord += yVelocity;
		getRectangle().setBounds(xCoord, yCoord, getRectangle().getWidth(), getRectangle().getHeight());
		
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
			//Moving UP-RIGHT
			if (yVelocity > 0)
			{
				boolean[] newMoves = {true, true, false, false};
				isMoving = newMoves;
			}
			
			//Moving DOWN-RIGHT
			else if (yVelocity < 0)
			{
				boolean[] newMoves = {false, true, false, true};
				isMoving = newMoves;
			}
			
			//Moving ONLY Right
			else
			{
				boolean[] newMoves = {false, true, false, false};
				isMoving = newMoves;
			}	
		}
		
		
		else if (xVelocity < 0)
		{
			//Moving UP-LEFT
			if (yVelocity > 0)
			{
				boolean[] newMoves = {true, true, false, false};
				isMoving = newMoves;
			}
			
			//Moving DOWN-LEFT
			else if (yVelocity < 0)
			{
				boolean[] newMoves = {false, false, true, true};
				isMoving = newMoves;
			}
			
			//Moving ONLY Left
			else
			{
				boolean[] newMoves = {false, false, true, false};
				isMoving = newMoves;
			}
		}
		
		//xVelocity = 0
		else
		{
			//Moving only UP
			if (yVelocity > 0)
			{
				boolean[] newMoves = {true, false, false, false};
				isMoving = newMoves;
			}
			
			//Moving only DOWN
			else if (yVelocity < 0)
			{
				boolean[] newMoves = {false, false, false, true};
				isMoving = newMoves;
			}
			
			//NOT MOVING
			else
			{
				boolean[] newMoves = {false, false, false, false};
				isMoving = newMoves;
			}
		}
		}
	}
		
	@Override
	public void init(GameContainer gc) throws SlickException{
		Image[] i = new Image[4];
		i[0] = (new Image("/assets/stand-0.png")).getFlippedCopy(true, false);
		i[1] = new Image("/assets/jump-spritesheet.png");
		i[2] = new Image("/assets/stand-spritesheet.png");
		i[3] = new Image("/assets/punch-spritesheet.png").getFlippedCopy(true, false);
		int[] cols = {1, 4, 3, 2};
		int count = 0;
		for(Image img : i){
			renderEnt(img, img.getWidth() / cols[count], img.getHeight());
			count += 1;
			animationSet.add(animation);
		}
	}
	
	@Override
	public void render(GameContainer gc, Graphics g) throws SlickException
	{
		if(!hasDX){
			if(punched){
				animationSet.get(3).draw(xCoord, yCoord);
			}
			else{
				animationSet.get(1).draw(xCoord, yCoord);
			}
			
		}
		if(hasDX){
			if(jumped){
				animationSet.get(1).draw(xCoord, yCoord);
			}
			else{
				animationSet.get(2).draw(xCoord, yCoord);
			}
		}
		if(hasHelp){
			g.drawRect(xCoord, yCoord, 10, 10);
		}
		jumped = false;
		punched = false;
		hasDX = true;
	}

	@Override
	public void update(GameContainer gc, int delta) throws SlickException, InterruptedException
	{
		Input input = new Input(delta);
		if(input.isKeyDown(controls[0])){
			xCoord -= .5 * delta;
		}
		if(input.isKeyDown(controls[1])){
			yCoord -= jumpHeight * delta;
			hasDX = false;
			jumped = true;
		}
		if(input.isKeyDown(controls[2])){
			xCoord += .5 * delta;
		}
		if(input.isKeyDown(controls[3])){
			yCoord += .5 * delta;
			
		}
		if(input.isKeyDown(Input.KEY_SPACE)){
			punched = true;
			hasDX = false;
		}
		else{
			hasDX = true;
		}
		startTime = System.nanoTime() - startTime;
		if(helpTimer >= 5000000000L){
			helpTimer = 5000000000L;
			hasHelp = true;
		}
		else{
			helpTimer += startTime / 1000;
		}
		if(hasHelp){
			if(input.isKeyDown(Input.KEY_E)){
				yCoord -= jumpHeight * 4 * delta;
				helpTimer = 0;
				hasHelp = false;
			}
		}
		
	}
	
}
