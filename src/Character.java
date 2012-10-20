import org.newdawn.slick.Animation;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
//import org.newdawn.slick.tests.xml.Entity;

public class Character extends Entity{
	
public int maxHealth, hP, damage, healthRegen, baseDamage;  
public String name, itemName, auxName;
public double range, baseRange;
boolean hasDX = false;
public int[] controls = new int[4];
boolean hasAux, hasItem;
Item item;
int wins;
int x, y;
int xVelocity, yVelocity;
String imageLocation;
boolean isMovingUp;
boolean isMovingRight;
boolean isMovingLeft;
boolean isMovingDown;
Auxillary auxItem;

//GameWorld gameWorld = new GameWorld();
//Set<Body> bodies = new HashSet<Body>();


	public Character(int x, int y, String imageLocation, String name, int maxHealth)
	{
		super(x,y, imageLocation);
		hasAux = false;
		hasItem = false;
		//Place holder numbers
		hP = 20;
		baseDamage = 2;
		wins = 0;	
			
		super.setHitBox(x, y, 40, 40);
		hitBox = getHitBox();
		item = null;
		
		this.name = name;
		this.maxHealth = maxHealth;
		hP = maxHealth;
		damage = 5;
		healthRegen = 1;
		itemName = null;
		auxName = null;
		
	}
	public void update()
	{
		x += xVelocity;
		y += yVelocity;
		getRectangle().setBounds(x, y, getRectangle().getWidth(), getRectangle().getHeight());
		
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
		return a;
	}
	
	public void setX(int DX, int delta){
		xCoord += DX * 0.05 * delta;
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
	{}
	
	public void useAux()
	{
		auxName = auxItem.name;
		if (hasAux == true)
			{
				auxName.use();
				hasAux = false;
				auxName = null;
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
		if (xVelocity > 0)
		{
			//Moving UP-RIGHT
			if (yVelocity > 0)
			{
				isMovingUp = true;
				isMovingRight = true;
				isMovingLeft = false;
				isMovingDown = false;
			}
			
			//Moving DOWN-RIGHT
			else if (yVelocity < 0)
			{
				isMovingUp = false;
				isMovingRight = true;			
				isMovingLeft = false;
				isMovingDown = true;
			}
			
			//Moving ONLY Right
			else
			{
				isMovingUp = false;
				isMovingRight = true;			
				isMovingLeft = false;
				isMovingDown = false;
			}
			
		
		}
		
		else if (xVelocity < 0)
		{
			//Moving UP-LEFT
			if (yVelocity > 0)
			{
				isMovingUp = true;
				isMovingRight = true;
				isMovingLeft = false;
				isMovingDown = false;
			}
			
			//Moving DOWN-LEFT
			else if (yVelocity < 0)
			{
				isMovingUp = false;
				isMovingRight = false;			
				isMovingLeft = true;
				isMovingDown = true;
			}
			
			//Moving ONLY Left
			else
			{
				isMovingUp = false;
				isMovingRight = false;			
				isMovingLeft = true;
				isMovingDown = false;
			}
			
		}
		
		//xVelocity = 0
		else
		{
			//Moving only UP
			if (yVelocity > 0)
			{
				isMovingUp = true;
				isMovingRight = false;			
				isMovingLeft = false;
				isMovingDown = false;
			}
			
			//Moving only DOWN
			else if (yVelocity < 0)
			{
				isMovingUp = false;
				isMovingRight = false;			
				isMovingLeft = false;
				isMovingDown = true;
			}
			
			//NOT MOVING
			else
			{
				isMovingUp = false;
				isMovingRight = false;			
				isMovingLeft = false;
				isMovingDown = false;
			}
		}
		
	}

	@Override
	public void update(GameContainer gc, int delta) throws SlickException, InterruptedException
	{
		Input input = new Input(delta);
		if(input.isKeyDown(controls[0])){
			xCoord -= .5 * delta;
			hasDX = true;
		}
		if(input.isKeyDown(controls[1])){
			yCoord -= .5 * delta;
			hasDX = true;
		}
		if(input.isKeyDown(controls[2])){
			xCoord += .5 * delta;
			hasDX = true;
		}
		if(input.isKeyDown(controls[3])){
			yCoord += .5 * delta;
			hasDX = true;
		}
		for(int i : controls){
			if(input.isKeyDown(i)){
				hasDX = true;
				break;
			}
			hasDX = false;
		}
	}
}
