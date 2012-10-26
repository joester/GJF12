import java.util.ArrayList;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;


public class IceProjectile extends Projectile {
	private int delayDuration;
	private int lingerDuration;
	private int cycles;
	private boolean isRight;

	public IceProjectile(int xSpawnLocation, int ySpawnLocation, Image image,
			int projectileXSpeed, int projectileYSpeed, int damage,
			int projectileRange, boolean isRight, int hitBoxXPosOffSet, int hitBoxYPosOffSet, 
			int hitBoxXOffSet, int hitBoxYOffSet, Character c, GameWorld gW) {
		super(xSpawnLocation, ySpawnLocation, image, projectileXSpeed,
				projectileYSpeed, damage, projectileRange, hitBoxXPosOffSet,
				hitBoxYPosOffSet, hitBoxXOffSet, hitBoxYOffSet, c, gW);
		delayDuration = 250;
		lingerDuration = 125;
		cycles = 3;
		this.isRight = isRight;
	}

	@Override
	public void checkCollisions(){
		for (Character c : gW.getListOfCharacters())
		{
			if(owner != c){
				if (getHitBox().intersects(c.getHitBox()))
				{			
					c.modifyHealth(getDamage());
					gW.removeProjectile(this);
				}
			}
		}
		for (Projectile p : gW.getListOfProjectiles())
		{
			if(p != this && p.getOwner() != owner){
				if (getHitBox().intersects(p.getHitBox()))
				{			
					gW.removeProjectile(p);
				}
			}
		}
	}

	@Override
	public void update(GameContainer gc, int delta) throws SlickException, InterruptedException
	{
		//update projectile's location
		if(cycles - 1 == 2){
			if(delayDuration <= 0){

				if (getDistanceTravelled() >= maxRange)
				{
					if(lingerDuration <= 0){
						if(isRight){
							currentXLocation = (int) (xSpawnLocation + hitBox.getWidth());
						}
						else{
							currentXLocation = (int) (xSpawnLocation - hitBox.getWidth());							
						}
						currentYLocation = (int) (ySpawnLocation - hitBox.getHeight()/2);
						maxRange -= (int) (hitBox.getHeight()/2);	
						xSpawnLocation = currentXLocation;
						ySpawnLocation = currentYLocation;
						lingerDuration = 250;
						cycles--;

					}
					else
						lingerDuration -= delta;
				}
				else{
					currentXLocation += xVelocity;
					currentYLocation += yVelocity;
				}
			}
			else{
				delayDuration -= delta;
			}}
		else if(cycles - 1 == 1){
			if(delayDuration <= 0){
				if (getDistanceTravelled() >= maxRange)
				{
					if(lingerDuration <= 0){
						if(isRight){
							currentXLocation = (int) (xSpawnLocation + hitBox.getWidth());
						}
						else{
							currentXLocation = (int) (xSpawnLocation - hitBox.getWidth());							
						}
						currentYLocation = (int) (ySpawnLocation - hitBox.getHeight()/2);
						maxRange -= (int) (hitBox.getHeight()/2);	
						xSpawnLocation = currentXLocation;
						ySpawnLocation = currentYLocation;
						lingerDuration = 250;
						cycles--;
					}
					else
						lingerDuration -= delta;
				}
				else{
					currentXLocation += xVelocity;
					currentYLocation += yVelocity;
				}
			}
			else
				delayDuration -= delta;
		}
		else{
			if(delayDuration <= 0){
				if (getDistanceTravelled() >= maxRange)	{
					if(lingerDuration <= 0)
						gW.removeProjectile(this);
					else
						lingerDuration -= delta;
				}
				else{
					currentXLocation += xVelocity;
					currentYLocation += yVelocity;
				}
			}
			else
				delayDuration -= delta;
		}

		//keeps rectangle in line with sprite
		setHitBox(currentXLocation + hitBoxXPosOffSet, currentYLocation + hitBoxYPosOffSet);
	}
}
