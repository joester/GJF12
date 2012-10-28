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

	public IceProjectile(float xSpawnLocation, float ySpawnLocation, Image image,
			float projectileXSpeed, float projectileYSpeed, int damage,
			float projectileRange, boolean isRight, float hitBoxXPosOffset, float hitBoxYPosOffset, 
			float hitBoxXOffset, float hitBoxYOffset, Character c, GameWorld gW) {
		super(xSpawnLocation, ySpawnLocation, image, projectileXSpeed,
				projectileYSpeed, damage, projectileRange, hitBoxXPosOffset,
				hitBoxYPosOffset, hitBoxXOffset, hitBoxYOffset, c, gW);
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
							xSpawnLocation = xCoord = xSpawnLocation + hitBox.getWidth();
						}
						else{
							xSpawnLocation = xCoord = xSpawnLocation - hitBox.getWidth();							
						}
						ySpawnLocation = yCoord = ySpawnLocation - hitBox.getHeight()/2;
						maxRange -= hitBox.getHeight()/2;	
						lingerDuration = 250;
						delayDuration = 125;
						cycles--;

					}
					else
						lingerDuration -= delta;
				}
				else{
					xCoord += xVelocity;
					yCoord += yVelocity;
				}
			}
			else{
				delayDuration -= delta;
			}}
		else if(cycles - 1 == 1){
			if (getDistanceTravelled() >= maxRange)
			{
				if(lingerDuration <= 0){
					if(isRight){
						xSpawnLocation = xCoord = xSpawnLocation + hitBox.getWidth();
					}
					else{
						xSpawnLocation = xCoord = xSpawnLocation - hitBox.getWidth();							
					}
					ySpawnLocation = yCoord = ySpawnLocation - hitBox.getHeight()/2;
					maxRange -= hitBox.getHeight()/2;	
					lingerDuration = 250;
					cycles--;
				}
				else
					lingerDuration -= delta;
			}
			else{
				xCoord += xVelocity;
				yCoord += yVelocity;
			}
		}
		else{
			if (getDistanceTravelled() >= maxRange)	{
				if(lingerDuration <= 0){
					gW.removeProjectile(this);
				}
				else
					lingerDuration -= delta;
			}
			else{
				xCoord += xVelocity;
				yCoord += yVelocity;
			}
		}
		//keeps rectangle in line with sprite
		setHitBoxLocation(xCoord,yCoord);
	}
}
