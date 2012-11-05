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

	public int HP, damage, healthRegen, baseDamage, numRows, numCols;  
	final int maxHealth = 20;
	public String name, itemName, auxName;
	public double range, baseRange;
	boolean hasDX = false;
	public int[] controls = new int[4];
	boolean hasAuxItem, hasItem;
	Item item;
	int wins;
	int jumpHeight;
	float xVelocity, yVelocity;
	boolean isMovingUp, isMovingRight, isMovingLeft, isMovingDown;
	boolean canMoveUp, canMoveRight, canMoveLeft, canMoveDown;
	boolean jumpAvailable, isJumpingLeft, isJumpingRight, movingLeft, movingRight, punchLeft, punchRight;
	Auxillary auxItem;
	ArrayList<Animation> animationSet = new ArrayList<Animation>();
	Animation currentAnimation;
	private boolean isPunching;
	boolean isFacingRight;
	private boolean isJumping;
	private boolean isIdle;
	private boolean isRunning;
	boolean hit, isHit;

	Projectile punchProjectile; 

	int[] setOne = {Input.KEY_A, Input.KEY_W, Input.KEY_D, Input.KEY_S};
	int[] setTwo = {Input.KEY_LEFT, Input.KEY_UP, Input.KEY_RIGHT, Input.KEY_DOWN};
	private double baseAttackCoolDown;
	private double attackCoolDown;
	public int playerID;

	//GameWorld gameWorld = new GameWorld();
	//Set<Body> bodies = new HashSet<Body>();


	public Character(int x, int y, String player, GameWorld gW)
	{
		super(x * Block.BLOCKSIZE,y * Block.BLOCKSIZE, player, gW);
		name = player;
		hasAuxItem = false;
		hasItem = false;
		baseAttackCoolDown = 600;
		attackCoolDown = 0;
		//Place holder numbers
		HP = 100;
		baseDamage = 1;
		wins = 0;  
		setHitBoxSize(42,80);
		item = null;

		HP = maxHealth;
		damage = 5;
		healthRegen = 1;
		itemName = null;
		auxName = null;
		jumpAvailable = true;
	}

	public void setControls(int[] set){
		controls = set;
	}



	//public Animation getAnimation(){
	//return animation;
	//}

	public String displayHP()
	{
		return "" + HP + "/" + maxHealth;
	}

	public String getName()
	{
		return name;
	}

	public int getHP()
	{
		return HP;
	}

	public void modifyHealth(int deltaHealth)
	{
		HP -= deltaHealth;	
		isHit = true;
	}

	public int getDamage()
	{
		return damage;
	}

	public void pickUpItem(Item item)
	{
		damage = item.damage;
		range = item.projectileRange;
		itemName = item.name;
		this.item = item;
		hasItem = true;
		item.setOwner(this);
	}


	public void dropItem()
	{
		hasItem = false;
		damage = baseDamage;
		range = baseRange;
		itemName = null;
		item.setOwner(null);
	}

	public void pickUpAux(Auxillary auxItem)
	{
		this.auxItem = auxItem;
		hasAuxItem = true;
	}

	//	public void useAux()
	//	{
	//		if (hasAuxItem == true)
	//		{
	//			auxItem.use();
	//			hasAuxItem = false;
	//			auxItem = null;
	//		}
	//	}

	public void attack()
	{
		if(attackCoolDown <= 0){
			isPunching = true;
			if (hasItem)
			{
				item.use(gW);
				attackCoolDown = item.reloadTime;
			}
			else{
				if(isFacingRight){
					punchProjectile = new PunchProjectile(getX() + hitBox.getWidth(), getY(), this, gW);
					punchProjectile.setLocation(getX() + hitBox.getWidth(),getY());
				}
				else{
					punchProjectile = new PunchProjectile(getX(), getY()+ hitBox.getWidth(), this, gW);
					punchProjectile.setLocation(getX() - punchProjectile.getHitBox().getWidth(),getY());
				}
				gW.getListOfProjectiles().add(punchProjectile);	
				for (Block b : gW.getListOfBlocks())
				{
					
					gW.removeProjectile(punchProjectile);
				}
				attackCoolDown = baseAttackCoolDown;
			}

		}
	}

	public void determineDirection()
	{
		//Moving RIGHT
		if (xVelocity > 0)
		{
			isMovingRight = true;
			isMovingLeft = false;
			//Moving DOWN-RIGHT
			if (yVelocity > 0)
			{
				isMovingUp = false;
				isMovingDown = true;
			}

			//Moving UP-RIGHT
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
			//Moving DOWN-LEFT
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
			//Moving only DOWN
			if (yVelocity > 0)
			{
				isMovingUp = false;
				isMovingDown = true;
			}

			//Moving only UP
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

	public void init() throws SlickException{
		jumpHeight = 0;

		Image[] i = new Image[10];
		i[0] = new Image("assets/Art/Characters/" + name + "/jump-spritesheet.png");
		i[1] = new Image("assets/Art/Characters/" + name + "/punch-spritesheet.png");
		i[2] = new Image("assets/Art/Characters/" + name + "/stand-spritesheet.png");
		i[3] = new Image("assets/Art/Characters/" + name + "/run-spritesheet.png");
		i[4] = new Image("assets/Art/Characters/" + name + "/hurt-spritesheet.png");
		i[5] = new Image("assets/Art/Characters/" + name + "/jump-spritesheet.png").getFlippedCopy(true, false);
		i[6] = new Image("assets/Art/Characters/" + name + "/punch-spritesheet.png").getFlippedCopy(true, false);
		i[7] = new Image("assets/Art/Characters/" + name + "/stand-spritesheet.png").getFlippedCopy(true, false);
		i[8] = new Image("assets/Art/Characters/" + name + "/run-spritesheet.png").getFlippedCopy(true, false);
		i[9] = new Image("assets/Art/Characters/" + name + "/hurt-spritesheet.png").getFlippedCopy(true, false);
		int[] cols = {4,2,3,9,2};
		int count = 0;
		boolean toFlipped = false;
		for(Image img : i){
			if(img.equals(i[0]) || img.equals(i[5])){
				img = img.getSubImage(0, 25, img.getWidth(), img.getHeight() - 25);
			}
			if(count == 5){
				count = 0;
				toFlipped = true;
			}
			Image[] imagelist = new Image[cols[count]];
			int imageListTrack = 0;

			if(img.equals(i[3]) || img.equals(i[8]))
				renderEnt(img, img.getWidth() / cols[count], img.getHeight(),100);
			else
				renderEnt(img, img.getWidth() / cols[count], img.getHeight(),300);
			if(toFlipped){
				for(int j = cols[count] - 1; j >= 0; j --){
					imagelist[imageListTrack] = animation.getImage(j); 
					imageListTrack += 1;
				}
				if(img.equals(i[3]) || img.equals(i[8]))
					animation = new Animation(imagelist, 100);
				else
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
		g.draw(hitBox);
		currentAnimation.draw(xCoord, yCoord);
		if(currentAnimation.isStopped()){
			currentAnimation.restart();
		}
	}

	@Override
	public void update(GameContainer gc, int delta) throws SlickException, InterruptedException
	{
		hit = false;
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
		
		isJumping = (canMoveDown && isMovingDown) || (isMovingUp && canMoveUp) || (isMovingUp && canMoveDown) || (!isMovingUp && canMoveDown);

		jumpAvailable = !canMoveDown;
		isRunning = !isJumping && xVelocity != 0;
		isIdle = !isJumping && !isRunning;

		if(isMovingRight){
			isFacingRight = true;
		}
		else if(isMovingLeft){
			isFacingRight = false;
		}
		selectAnimation();
		setHitBoxLocation(xCoord, yCoord);


		xVelocity = 0;
	
		attackCoolDown -= delta;
		if(attackCoolDown < 0){
			attackCoolDown = 0;
		}
		canMoveUp = true;
		canMoveDown = true;
		canMoveRight = true;
		canMoveLeft = true;
	}	

	private void selectAnimation(){
		if(isFacingRight){
			if(isJumping && !jumpAvailable){
				if(currentAnimation != animationSet.get(0))
					currentAnimation = animationSet.get(0);
			}
			else if(isPunching){
				if(currentAnimation != animationSet.get(1))
					currentAnimation = animationSet.get(1);
			}
			else if(isIdle){
				if(currentAnimation != animationSet.get(2)){
					currentAnimation = animationSet.get(2);
				}
			}
			else if(isRunning){
				if(currentAnimation != animationSet.get(3)){
					currentAnimation = animationSet.get(3);
				}
			}
			else if(isHit){
				if(currentAnimation != animationSet.get(4)){
					currentAnimation = animationSet.get(4);
				}
			}
		}
		else{
			if(isJumping && !jumpAvailable){
				if(currentAnimation !=  animationSet.get(5))
					currentAnimation = animationSet.get(5);	
			}
			else if(isPunching){
				if(currentAnimation !=  animationSet.get(6))
					currentAnimation = animationSet.get(6);
			}
			else if(isIdle){
				if(currentAnimation !=  animationSet.get(7)){
					currentAnimation = animationSet.get(7);
				}
			}
			else if(isRunning){
				if(currentAnimation != animationSet.get(8)){
					currentAnimation = animationSet.get(8);
				}
			}
			else if(isHit){
				if(currentAnimation != animationSet.get(9)){
					currentAnimation = animationSet.get(9);
				}
			}
		}
		isJumping = false;
		isPunching = false;
		isIdle = false;
		isRunning = false;
		isMovingRight = false;
	}

	public void setPlayerID(int playerID){
		this.playerID = playerID;
	}

	public void reset() {
		isJumping = false;
		isPunching = false;
		isIdle = false;
		isRunning = false;
		isMovingRight = false;
		isMovingUp = isMovingRight = isMovingLeft = isMovingDown = false;;
		canMoveUp = canMoveRight = canMoveLeft =  canMoveDown = false;
		isJumpingLeft = isJumpingRight = movingLeft = movingRight = punchLeft = punchRight = false;
		
		hasAuxItem = false;
		hasItem = false;
		attackCoolDown = 0;
		//Place holder numbers
		item = null;

		HP = maxHealth;

		itemName = null;
		auxName = null;
		jumpAvailable = true;
		
	}
}
