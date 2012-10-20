import org.newdawn.slick.Animation;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

public class Character extends Entity{
	
public int maxHealth, hP, damage, healthRegen, baseDamage;  
public String name, itemName, aux;
public double range, baseRange;
boolean hasDX = false;
public int[] controls = new int[4];

//GameWorld gameWorld = new GameWorld();
//Set<Body> bodies = new HashSet<Body>();


	public Character(String n, int h)
	{
		super(0, 0, "/res/Untitled.png");
		name = n;
		maxHealth = h;
		hP = h;
		damage = 5;
		healthRegen = 1;
		itemName = null;
		aux = null;
	}
	
	public void setMove(boolean isMoving){
		hasDX = isMoving;
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
	
	public void pickupitem(Items i)
	{
		damage = i.damage;
		range = i.range;
		itemName = i.name;
	}
	
	public void dropitem()
	{
		damage = baseDamage;
		range = baseRange;
		itemName = null;
	}
	
	public void pickUpAux(Auxillary i)
	{
		aux = i.name;
	}
	
	public void useAux()
	{
		
	}	
	
	@Override
	public void init(GameContainer gc) throws SlickException
	{
		renderEnt("/res/Untitled.png", 128, 128);
		addFrame("/res/Untitled2.png", 128, 128);
	}
	
	@Override
	public void render(GameContainer gc, Graphics g) throws SlickException
	{
		if(hasDX){
			a.draw(xCoord, yCoord, 64, 64);
		}
		else{
			a.getCurrentFrame().draw(xCoord, yCoord, 64, 64);
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
