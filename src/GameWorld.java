import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.opengl.*;
import org.newdawn.slick.util.*;
import org.jbox2d.collision.*;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.World;

public class GameWorld
{
	
	World gameWorld = new World(new Vec2(0, -9.8f), false);
	
	List<Block> listOfBlocks = new ArrayList<Block>();
	List<Platform> listOfPlatforms = new ArrayList<Platform>();
	List<Projectile> listOfProjectiles = new ArrayList<Projectile>();
	List<Character> listOfCharacters = new ArrayList<Character>();
	List<Item> listOfItems = new ArrayList<Item>();
	List<Item> itemsOnMap = new ArrayList<Item>();
	
	Map map;
		
	public void init() throws IOException 
	{	 
		listOfItems.add(new Earth());
		listOfItems.add(new Fire());
		listOfItems.add(new Ice());
		listOfItems.add(new Lightning());
		listOfItems.add(new Wind());
		loadSounds();
	}
	
	public static void main (String[] args) 
	{
		
		GameWorld gameworld = new GameWorld();
	}
	
	public void loadSounds()
	{
		Sound punchHit1 = new Sound("assets/SFX/punch1Final.mp3");
		Sound punchHit2 = new Sound("assets/SFX/punch2Final.mp3");
		Sound punchHit3 = new Sound("assets/SFX/punch3Final.mp3");
		Sound punchMiss1 = new Sound("assets/SFX/punchMiss1Final.mp3");
		Sound punchMiss2 = new Sound("assets/SFX/punchMiss2Final.mp3");
		Sound punchMiss3 = new Sound("assets/SFX/punchMiss3Final.mp3");
		Sound bonesCrack = new Sound("assets/SFX/bonesCrackFinal.mp3");
		Sound breakCrate = new Sound("assets/SFX/breakCrateFinal.mp3");
		Sound clockBell = new Sound("assets/SFX/clockBellFinal.mp3");
		Sound rockHammer = new Sound("assets/SFX/rockHammerFinal.mp3");
		Sound run = new Sound("assets/SFX/runFinal.mp3");
		Sound shock = new Sound("assets/SFX/shockFinal.mp3");
		Sound spikes = new Sound("assets/SFX/spikesFall.mp3");
		Sound ice = new Sound("assets/SFX/iceFinal.mp3");
		
	}
	
	public Map getMap()
	{
		return map;
	}
	
	public World getWorld()
	{
		return gameWorld;
	}
	
	public List<Block> getListOfBlocks()
	{
		return listOfBlocks;
	}
	
	public List<Projectile> getListOfProjectiles()
	{
		return listOfProjectiles;
	}
	
	public List<Character> getListOfCharacters()
	{
		return listOfCharacters;
	}
	

	public void checkForCollisions()
	{
		//checking for character's collision with blocks
		for (Character c : listOfCharacters)
		{
			for (Block b : listOfBlocks)
			{
				if (b.getBlockType() == BlockType.Lethal)
				{
					c.hitBox = null;
					listOfCharacters.remove(c);
				}
				if (c.getRectangle().intersects(b.getRectangle()))
					{
						 if (c.isMovingUp && b.getBlockType() == BlockType.Passable)
						 {
							//do nothing 						
						 }
						 
						 else
						 {
							 c.yVelocity = 0;
						 }
						 
						 if (c.isMovingDown)
						 {
							 c.jumpAvailable = true;
							 c.yVelocity = 0;
						 }
						 
						 if (c.isMovingLeft)
						 {
							 c.xVelocity = 0;
						 }
						 if (c.isMovingRight)
						 {
							 c.xVelocity = 0;
						 }
						 
					}
			
				
			}
		}
		
		for (Character c : listOfCharacters)
		{
			for (Projectile p : listOfProjectiles)
			{
				if (c.getRectangle().intersects(p.getRectangle()))
				{			
					c.modifyHealth(-p.damage);
					listOfProjectiles.remove(p);
				}
			}
		}
	}
	
	
		
	public void update(GameContainer gc, int delta)
	{
		checkForCollisions();
		spawnItems();
		
		for (Projectile p : listOfProjectiles)
		{
			p.update(gc, delta);	
		}
		
		for (Character c: listOfCharacters)
		{
			c.update();
			
		}
		for(Platform plat : listOfPlatforms){
			plat.update(gc, delta);
		}
		
	}
	
	public void render(GameContainer gc, Graphics g){
		for(Block b: listOfBlocks){
			try{
				b.render(gc, g);
			}
			catch(NullPointerException ex){
				listOfBlocks.remove(b);
			}
		}
		for(Projectile p : listOfProjectiles){
			p.render(gc, g);
			
		}
		for(Platform plat : listOfPlatforms){
			plat.render(gc, g);
		}
	}
	
	public Item chooseRandomItem()
	{
		return listOfItems.get((int)(listOfItems.size() * Math.random()));
	}
	
	public void spawnItems()
	{
		for (Block block : listOfBlocks)
		{
			if(block.getBlockType() == BlockType.Crate && Math.random() < .2)
			{
				Item toBeSpawned = chooseRandomItem();
				toBeSpawned.setX(block.getX());
				toBeSpawned.setY(block.getY());
				itemsOnMap.add(toBeSpawned);
			}
		}
	}

	public void addBlock(Block block){
		listOfBlocks.add(block);
	}

}

