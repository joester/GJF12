import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.newdawn.slick.Sound;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.opengl.*;
import org.newdawn.slick.util.*;
import org.jbox2d.collision.*;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.World;

public class GameWorld
{
	
	Texture background;
	World gameWorld = new World(new Vec2(0, -9.8f), false);
	
	List<Block> listOfBlocks = new ArrayList<Block>();
	List<Projectile> listOfProjectiles = new ArrayList<Projectile>();
	List<Character> listOfCharacters = new ArrayList<Character>();
	List<Item> listOfItems = new ArrayList<Item>();
	List<Item> itemsOnMap = new ArrayList<Item>();
	
	
	listOfItems.add(new Earth(0, 0, "arg"));
	listOfItems.add(new Fire(0,0, "arg"));
	listOfItems.add(new Ice());
	listOfItems.add(new Lightning());
	listOfItems.add(new Wind());
	
	
	Map map;
		
	public void init() throws IOException 
	{	 
		background = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream
			(getMap().getBackgroundFileLocation()));
		loadSounds();
	}
	
	public static void main (String[] args) 
	{
		
		GameWorld gameworld = new GameWorld();
		gameworld.start();
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
	
	public void start()
	{		
		try
		{
			Display.setDisplayMode(new DisplayMode(1000,600));
			Display.setFullscreen(false);
			Display.create();
		}
		catch (LWJGLException e)
		{
			e.printStackTrace();
		}
	
	while (!Display.isCloseRequested()) {

		// render OpenGL here

		Display.update();
		}
		Display.destroy();
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
					listOfCharacters.remove(c);
				}
				if (c.getRectangle().instersects(b.getRectangle()))
					{
						 if (c.isMovingUp)
						 {
							//do nothing 						
						 }
						 if (c.isMovingDown)
						 {
							 c.jumpAvailable = true;
							 c.lockDownMovement();
						 }
						 
						 if (c.isMovingLeft)
						 {
							 c.lockLeftMovement();
						 }
						 if (c.isMovingRight)
						 {
							 c.lockRightMovement();
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
	
	
		
	public void update()
	{
		checkForCollisions();
		spawnItems();
		
		for (Block b : listOfBlocks)
		{
			b.update();
		}
		
		for (Projectile p : listOfProjectiles)
		{
			p.update();	
		}
		
		for (Character c: listOfCharacters)
		{
			c.update();
			
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

