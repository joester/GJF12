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
	public String name, itemName;
	public double range, baseRange;
	boolean hasAuxItem, hasItem;
	Item item;
	int wins;
	int jumpHeight;
	float xVelocity, yVelocity;
	boolean isMovingUp, isMovingRight, isMovingLeft, isMovingDown;
	boolean canMoveUp, canMoveRight, canMoveLeft, canMoveDown;
	boolean jumpAvailable, isJumpingLeft, isJumpingRight, movingLeft, movingRight, punchLeft, punchRight;
	ArrayList<Animation> animationList = new ArrayList<Animation>();
	Animation currentAnimation;
	private boolean isPunching;
	boolean isFacingRight;
	private boolean isJumping;
	//private boolean isIdle;
	private boolean isRunning;
	private boolean isKnockedBack;
	private float KBDistance;
	private boolean hit, isHit;
	private boolean canMove = true;
	private int timeElapsed;

	Projectile punchProjectile; 

	int[] setOne = {Input.KEY_A, Input.KEY_W, Input.KEY_D, Input.KEY_S};
	int[] setTwo = {Input.KEY_LEFT, Input.KEY_UP, Input.KEY_RIGHT, Input.KEY_DOWN};
	private double baseAttackCoolDown;
	private double attackCoolDown;
	public int playerID;
	int channelingTime;
	private int hitAnimationTime;



	public Character(int x, int y, String player, World world)
	{
		super(x * Block.BLOCKSIZE + Block.BLOCKSIZE/2,y * Block.BLOCKSIZE + Block.BLOCKSIZE, player, world);
		
		name = player;
		hasItem = false;
		baseAttackCoolDown = 600;
		attackCoolDown = 0;
		//Place holder numbers
		HP = 100;
		baseDamage = 1;
		wins = 0;  
		setHitboxSize(42,80);
		item = null;

		HP = maxHealth;
		damage = 5;
		healthRegen = 1;
		itemName = null;
		jumpAvailable = true;
	}


	//public Animation getAnimation(){
	//return animation;
	//}

	public String displayHP()
	{
		return "" + HP + "/" + maxHealth;
	}


	public int getHP()
	{
		return HP;
	}

	public void modifyHealth(int deltaHealth)
	{
		HP -= deltaHealth;	
		isHit = true;
		hitAnimationTime = 200;
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


	public void attack()
	{
		if(attackCoolDown <= 0){
			isPunching = true;
			channelingTime = 400;
			if (hasItem)
			{
				item.use(world);
				attackCoolDown = item.reloadTime;
			}
			else{
				if(isFacingRight){
					punchProjectile = new PunchProjectile(hitbox.getCenterX(), hitbox.getY() + 20, this, world);
				}
				else{
					punchProjectile = new PunchProjectile(hitbox.getCenterX() - 40, hitbox.getY()+ 20, this, world);
				}
				world.getProjectiles().add(punchProjectile);	
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

	public void loadAnimations() throws SlickException{
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

			if(img.equals(i[3]) || img.equals(i[8])){
				if(name.substring(0,5).equals("sable")){
					cols[count] = 7;
				}
				renderEnt(img, img.getWidth() / cols[count], img.getHeight(),100);
			}
				
			else
				renderEnt(img, img.getWidth() / cols[count], img.getHeight(),300);
			if(toFlipped){
				for(int j = cols[count] - 1; j >= 0; j --){
					imagelist[imageListTrack] = animation.getImage(j); 
					imageListTrack += 1;
				}
				if(img.equals(i[8]))
					animation = new Animation(imagelist, 100);
				else
					animation = new Animation(imagelist, 300);
			}

			count++;

			animation.stopAt(animation.getFrameCount());
			animationList.add(animation);
		}
		currentAnimation = animationList.get(2);
	}

	@Override
	public void render(GameContainer gc, Graphics g) throws SlickException
	{
		currentAnimation.draw(xCoord - currentAnimation.getWidth()/2, yCoord - currentAnimation.getHeight());
		if(currentAnimation.isStopped()){
			currentAnimation.restart();
		}
		//g.draw(hitbox);
		super.render(gc,g);
	}

	
	private void setMovement(){	
		if(xVelocity < 0){
			if(canMoveLeft){
				xCoord += xVelocity;
			}
		}
		else if(xVelocity > 0){
			if(canMoveRight){
				xCoord += xVelocity;
			}
		}
		if(yVelocity > 0){
			if(canMoveDown){
				yCoord += yVelocity;
			}
		}
		else if(yVelocity < 0){
			if(canMoveUp){
				yCoord += yVelocity;
			}
		}
		
	}

	@Override
	public void update(GameContainer gc, int delta) throws SlickException, InterruptedException
	{
		determineDirection();
		timeElapsed = delta;

		if(isPunching){	
			if(!isMovingUp && !isMovingDown){
				canMove = false;
				xVelocity = yVelocity = 0;
			}
			channelingTime -= delta;

			if(channelingTime <= 0){
				canMove = true;
				isPunching = false;
			}
		}
		if(isHit && !isKnockedBack){	
			hitAnimationTime -= delta;
			if(hitAnimationTime <= 0){
				isHit = false;
				hitAnimationTime = 0;
			}
		}
		
		if(canMove){
			setMovement();
		}
		
		if(isKnockedBack && KBDistance > 0 ){
			KBDistance -= Math.abs(xVelocity);
		}
		else{			
			isKnockedBack = false;
			isJumping = (canMoveDown && isMovingDown) || (isMovingUp && canMoveUp) || (isMovingUp && canMoveDown) || (!isMovingUp && canMoveDown);
			jumpAvailable = !canMoveDown;
			isRunning = !isJumping && xVelocity != 0;
			//isIdle = !isJumping && !isRunning;
			xVelocity = 0;
		}
		
		if(!isKnockedBack && canMove){
			if(isMovingRight){
				isFacingRight = true;
			}
			else if(isMovingLeft){
				isFacingRight = false;
			}
		}	
		selectAnimation(delta);
		setHitboxLocation(xCoord - hitbox.getWidth()/2,yCoord - hitbox.getHeight());
		//super.update(gc, delta);	

		attackCoolDown -= delta;
		if(attackCoolDown < 0){
			attackCoolDown = 0;
		}		
		
		canMoveUp = true;
		canMoveDown = true;
		canMoveRight = true;
		canMoveLeft = true;
	}	

	private void selectAnimation(int delta){
		if(isFacingRight){
			if(isJumping && !jumpAvailable){
				if(currentAnimation != animationList.get(0))
					currentAnimation = animationList.get(0);
			}
			else if(isPunching && !isJumping){
				if(currentAnimation != animationList.get(1))
					currentAnimation = animationList.get(1);
			}
			else if(isRunning){
				if(currentAnimation != animationList.get(3)){
					currentAnimation = animationList.get(3);
				}
			}
			else if(isHit  || isKnockedBack){
				if(currentAnimation != animationList.get(4)){
					currentAnimation = animationList.get(4);
				}
			}
			else if(isPunching && isJumping){
				//Replace with midair attack animation by index.
				if(currentAnimation != animationList.get(1)){
					currentAnimation = animationList.get(1);
				}
			}
			else{
				if(currentAnimation != animationList.get(2)){
					currentAnimation = animationList.get(2);
				}
			}


		}
		else{
			if(isJumping && !jumpAvailable){
				if(currentAnimation !=  animationList.get(5))
					currentAnimation = animationList.get(5);	
			}
			else if(isPunching){
				if(currentAnimation !=  animationList.get(6))
					currentAnimation = animationList.get(6);
			}
			else if(isRunning){
				if(currentAnimation != animationList.get(8)){
					currentAnimation = animationList.get(8);
				}
			}
			else if(isHit || isKnockedBack){
				if(currentAnimation != animationList.get(9)){
					currentAnimation = animationList.get(9);
				}
			}
			else if(isPunching && isJumping){
				//Replace with flipped midair attack animation by index.
				if(currentAnimation != animationList.get(6)){
					currentAnimation = animationList.get(6);
				}
			}
			else{
				if(currentAnimation !=  animationList.get(7)){
					currentAnimation = animationList.get(7);
				}
			}
		}
		
		
		isJumping = false;
		//isPunching = false;
		//isIdle = false;
		isRunning = false;
	}

	public void setPlayerID(int playerID){
		this.playerID = playerID;
	}

	public void reset() {
		isJumping = false;
		isPunching = false;
		//isIdle = false;
		isHit = false;
		isRunning = false;
		isMovingRight = false;
		isMovingUp = isMovingRight = isMovingLeft = isMovingDown = false;
		isKnockedBack = false;
		KBDistance = 0;
		canMoveUp = canMoveRight = canMoveLeft =  canMoveDown = false;
		isJumpingLeft = isJumpingRight = movingLeft = movingRight = false;
		xVelocity = 0;
		yVelocity = 0;
		hasAuxItem = false;
		hasItem = false;
		attackCoolDown = 0;
		canMove = true;
		KBDistance = 0;
		channelingTime = 0;
		hitAnimationTime = 0;
		
		//Place holder numbers
		item = null;

		HP = maxHealth;

		itemName = null;
		jumpAvailable = true;	
	}

	public void checkCollisions(GameContainer gc){
		Rectangle r = new Rectangle(getHitbox().getX() + xVelocity, getHitbox().getY() + yVelocity, getHitbox().getWidth(), getHitbox().getHeight());
		for (Block b : getWorld().getBlocks()){
			if (r.intersects(b.getHitbox())){	
				if (b.getBlockType() == BlockType.Lethal){
					modifyHealth(getHP());
				}
				determineDirection();
				if (isMovingLeft && b.getBlockType() != BlockType.Passable){
					if(getHitbox().getLeft() >= b.getHitbox().getRight())
						if(getHitbox().getBottom() > b.getHitbox().getTop()){
							//xCoord = b.getHitbox().getRight() + getHitbox().getWidth()/2;
							canMoveLeft = false;
							isKnockedBack = false;
						}	
					if (isMovingUp && b.getBlockType() != BlockType.Passable){
						if(getHitbox().getTop() >= b.getHitbox().getBottom()){
							yVelocity = 0;
							yCoord = b.getHitbox().getBottom() + getHitbox().getHeight();
							jumpAvailable = false;
							canMoveUp = false;
						}
					}
					else if (isMovingDown){
						jumpAvailable = false;
						if(getHitbox().getBottom() <= b.getHitbox().getTop()){
							yVelocity = 0;
							yCoord = b.getHitbox().getTop();
							canMoveDown = false;
							jumpAvailable = true;
						}
					}

				}
				else if(isMovingRight && b.getBlockType() != BlockType.Passable){
					if(getHitbox().getRight() <= b.getHitbox().getLeft())
						if(getHitbox().getBottom() > b.getHitbox().getTop()){
							//xCoord = b.getHitbox().getLeft() - getHitbox().getWidth()/2;
							canMoveRight = false;
							isKnockedBack = false;
						}
					if (isMovingUp && b.getBlockType() != BlockType.Passable){
						if(getHitbox().getTop() >= b.getHitbox().getBottom()){
							yVelocity = 0;
							yCoord = b.getHitbox().getBottom() + getHitbox().getHeight();
							jumpAvailable = false;
							canMoveUp = false;
						}
					}
					else if (isMovingDown){
						jumpAvailable = false;
						if(getHitbox().getBottom() <= b.getHitbox().getTop()){
							yVelocity = 0;
							yCoord = b.getHitbox().getTop();
							canMoveDown = false;
							jumpAvailable = true;
						}
					}
				}
				else{
					if (isMovingUp && b.getBlockType() != BlockType.Passable){
						yVelocity = 0;
						yCoord = b.getHitbox().getBottom() + getHitbox().getHeight();
						jumpAvailable = false;
						canMoveUp = false;
					}
					else if (isMovingDown){
						jumpAvailable = false;
						if(getHitbox().getBottom() <= b.getHitbox().getTop()){
							yVelocity = 0;
							yCoord = b.getHitbox().getTop();
							canMoveDown = false;
							jumpAvailable = true;
						}
					}
				}
			}
			if(r.getX() <= 0){
				canMoveLeft = false;
				isKnockedBack = false;
			}
			if(r.getX() + r.getWidth() >= gc.getWidth()){
				canMoveRight = false;
				isKnockedBack = false;
			}
			if(r.getY() <= 0){
				canMoveUp = false;
				yVelocity = 0;
			}
			if(r.getY() > gc.getHeight()){
				modifyHealth(getHP());
			}
		}
		for (Item i : getWorld().getItemsOnMap())
		{
			if (getHitbox().intersects(i.getHitbox()) && !hasItem)
			{
				pickUpItem(i);
				getWorld().remove(i);
			}
		}
	}

	public void handleInput(Input input){
		if(!isKnockedBack && !isHit){
			if(playerID == 0){
				if(input.isKeyDown(Input.KEY_A)){
					xVelocity = -3;
					canMoveLeft = true;
				}
				if(input.isKeyDown(Input.KEY_W)){
					if(jumpAvailable){
						yVelocity = -7.5f;
						jumpAvailable = false;
						canMoveUp = true;
					}
				}

				if(input.isKeyDown(Input.KEY_SPACE))
					attack();

				if(input.isKeyDown(Input.KEY_Q)){
					for (Item i : getWorld().getItemsOnMap())
					{
						if (getHitbox().intersects(i.getHitbox()))
						{
							dropItem();
							pickUpItem(i);
							getWorld().remove(i);
						}
					}
				}
				if(input.isKeyDown(Input.KEY_D)){
					xVelocity = 3;
					canMoveRight = true;
				}
			}
			else if(playerID == 1){
				if(input.isKeyDown(Input.KEY_J) || input.isKeyDown(Input.KEY_NUMPAD4)){ 
					xVelocity = -3;
					canMoveLeft = true;
				}
				if(input.isKeyDown(Input.KEY_I) || input.isKeyDown(Input.KEY_NUMPAD8)){ //KEY_NUMPAD8
					if(jumpAvailable){
						yVelocity = -7.5f;
						jumpAvailable = false;
						canMoveUp = true;
					}
				}
				if(input.isKeyDown(Input.KEY_O) || input.isKeyDown(Input.KEY_NUMPAD7)){//KEY_NUMPAD7
					for (Item i : getWorld().getItemsOnMap())
					{
						if (getHitbox().intersects(i.getHitbox()))
						{
							dropItem();
							pickUpItem(i);
							getWorld().remove(i);
						}
					}
				}
				if(input.isKeyDown(Input.KEY_L) || input.isKeyDown(Input.KEY_NUMPAD6)){//NUMPAD6
					xVelocity = 3;
					canMoveRight = true;
				}
				if(input.isKeyDown(Input.KEY_U) || input.isKeyDown(Input.KEY_NUMPAD0)){//NUMPAD0
					attack();
				}
			}
		}
	}
	
	public void knockBack(float distance, float velocity){
		isPunching = false;
		canMove = true;
		channelingTime = 0;
		KBDistance = distance;
		xVelocity = velocity;
		isKnockedBack = true;
	}
}
