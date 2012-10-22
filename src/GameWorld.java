import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;
import org.newdawn.slick.geom.Rectangle;

public class GameWorld
{

	List<Block> listOfBlocks = new ArrayList<Block>();
	List<Platform> listOfPlatforms = new ArrayList<Platform>();
	List<Projectile> listOfProjectiles = new ArrayList<Projectile>();
	List<Character> listOfCharacters = new ArrayList<Character>();
	List<Item> listOfItems = new ArrayList<Item>();
	List<Item> itemsOnMap = new ArrayList<Item>();
	String testString = "GameWorld Loaded.";
	Map map;
	IceMap iceMap = new IceMap(this);
	LavaMap lavaMap = new LavaMap(this);

	public void init() throws IOException, SlickException 
	{	 
		String earthFileLocation = "assets/Art/Transformations/icons/hammer.png";
		String fireFileLocation = "assets/Art/Transformations/icons/bow.png";
		String iceFileLocation = "assets/Art/Transformations/icons/shield.png";
		String lightningFileLocation = "assets/Art/Transformations/icons/dagger.png";
		String windFileLocation = "assets/Art/Transformations/icons/fan.png";
		
		listOfItems.add(new Earth(0, 0, earthFileLocation));		
		listOfItems.add(new Fire(0, 0, fireFileLocation));
		listOfItems.add(new Ice(0, 0, iceFileLocation));
		listOfItems.add(new Lightning(0, 0, lightningFileLocation));
		listOfItems.add(new Wind(0, 0, windFileLocation));
		
		loadSounds();
		Character c = new Character(0, 0, "/assets/stand-spritesheet.png");
		c.renderEnt(c.image, c.image.getWidth() / 3, c.image.getHeight());
		listOfCharacters.add(c);
		System.out.println("Character added");
		map = iceMap;
	}

	public void loadSounds() throws SlickException
	{
		ArrayList<Sound> sounds = new ArrayList<Sound>();
		Sound punchHit1 = new Sound("assets/SFX/punch1Final.wav");
		Sound punchHit2 = new Sound("assets/SFX/punch2Final.wav");
		Sound punchHit3 = new Sound("assets/SFX/punch3Final.wav");
		Sound punchMiss1 = new Sound("assets/SFX/punchMiss1Final.wav");
		Sound punchMiss2 = new Sound("assets/SFX/punchMiss2Final.wav");
		Sound punchMiss3 = new Sound("assets/SFX/punchMiss3Final.wav");
		Sound bonesCrack = new Sound("assets/SFX/bonesCrackFinal.wav");
		Sound breakCrate = new Sound("assets/SFX/breakCrateFinal.wav");
		Sound clockBell = new Sound("assets/SFX/clockBellFinal.wav");
		Sound earthquake = new Sound("assets/SFX/earthquakesFinal.wav");
		Sound clockPulse = new Sound("assets/SFX/clockPulseFinal.wav");
		Sound rockHammer = new Sound("assets/SFX/rockHammerFinal.wav");
		Sound run = new Sound("assets/SFX/runFinal.wav");
		Sound shock = new Sound("assets/SFX/shockFinal.wav");
		Sound spikes = new Sound("assets/SFX/spikesFallFinal.wav");
		Sound ice = new Sound("assets/SFX/iceFinal.wav");
		Sound movingSteel = new Sound("assets/SFX/movingSteelFinal.wav");

	}

	public Map getMap()
	{
		return map;
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


	public void checkForCollisions(GameContainer gc){
		ArrayList<Character> toBeRemoved = new ArrayList<Character>();
		//checking for character's collision with blocks
		for (Character c : listOfCharacters){
			Rectangle r = new Rectangle((int)(c.xCoord + c.xVelocity), (int)(c.yCoord + c.yVelocity), c.getHitBox().getWidth(), c.getHitBox().getHeight());
			for (Block b : listOfBlocks){
				if (r.intersects(b.getRectangle())){	
					
					
					if (b.getBlockType() == BlockType.Lethal){
						System.out.println("died");
						//toBeRemoved.add(c);
					}
					c.determineDirection();
					//System.out.println(c.yVelocity);
					if (c.isMovingUp && b.getBlockType() != BlockType.Passable){
						c.yVelocity = 0;
						c.jumpAvailable = false;
						c.canMoveUp = false;
						
					}
					else if (c.isMovingDown){
						c.jumpAvailable = true;
						if(c.getHitBox().getY() + c.getHitBox().getHeight() < b.getHitBox().getY())
							c.yVelocity = 0;
						//c.canMoveDown = false;
					}
					if (c.isMovingLeft && b.getBlockType() != BlockType.Passable){
						c.canMoveLeft = false;
						if(c.getHitBox().getY() + c.getHitBox().getHeight()> b.getHitBox().getY())
							c.canMoveDown = true;
					}
					else if(c.isMovingRight && b.getBlockType() != BlockType.Passable){
						//c.xVelocity = 0;
						c.canMoveRight = false;
						if(c.getHitBox().getY() + c.getHitBox().getHeight()> b.getHitBox().getY())
							c.canMoveDown = true;
					}						 
				}
				if(r.getX() <= 0){
					c.canMoveLeft = false;
				}
				if(r.getX() + r.getWidth() >= gc.getWidth()){
					c.canMoveRight = false;
				}
				if(r.getY() <= 0){
					c.canMoveUp = false;
				}
			}
		}

		for (Character c : toBeRemoved)
			listOfCharacters.remove(c);

		
		ArrayList<Projectile> projectilesToBeRemoved = new ArrayList<Projectile>();
		for (Projectile p : listOfProjectiles)
		{
			for (Character c : listOfCharacters)
			{
				if (p.getRectangle().intersects(c.getRectangle()))
				{			
					c.modifyHealth(-p.damage);
					listOfProjectiles.remove(p);
				}
			
				for (Item i : itemsOnMap)
				{
					if (c.getRectangle().intersects(i.getHitBox()) && !c.hasItem)
					{
						c.pickUpItem(i);
						c.hasItem = true;
					}
				}
			}
			
			for(Block b: listOfBlocks){
				if(!b.isPassible()){
					projectilesToBeRemoved.add(p);
				}
			}
		}
		
		ArrayList<Block> removeCrates = new ArrayList<Block>();
		for (Block b: listOfBlocks)
		{
			if (b.getBlockType() == BlockType.Crate)
			{
				for (Projectile p : listOfProjectiles)
				{
					if (p.getRectangle().intersects(b.getRectangle()))
						{
							Item toBeAdded = chooseRandomItem();
							toBeAdded.setX(b.getX());
							toBeAdded.setY(b.getY());
							itemsOnMap.add(toBeAdded);
							removeCrates.add(b);							
						}
				}			 
			}
		}
		
		for (Block b :removeCrates)
		{
			listOfBlocks.remove(b);
		}
		
		for (Projectile p : projectilesToBeRemoved)
			listOfProjectiles.remove(p);
	}

	public void init(GameContainer gc) throws SlickException{
		for(Character c : listOfCharacters){
			c.init(gc);
		}
		map.buildMap();
	}



	public void update(GameContainer gc, int delta) throws SlickException, InterruptedException
	{
		if(listOfCharacters.size() > 0){
			assignActionToPlayer(gc,0,delta);
		}
		for (Character c : listOfCharacters)
		{
			c.yVelocity += .1;
			//System.out.println(c.yVelocity);
		}
		checkForCollisions(gc);
		
		
		
		
	
		//spawnItems();
	
		for (Projectile p : listOfProjectiles)
		{
			p.update(gc, delta);	
		}

		for (Character c: listOfCharacters)
		{
			try{
				c.update(gc, delta);
				//System.out.println("Character updated");
			}catch(Exception e){

			}			
		}
		
		for(Platform plat : listOfPlatforms){
			plat.update(gc, delta);
		}

		for (Item i : itemsOnMap)
		{
			i.update(gc, delta);
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
		for(Character c : listOfCharacters){
			try{
				c.render(gc, g);
			}
			catch(Exception e){
				e.printStackTrace();
			}

		}
	}



	public Item chooseRandomItem()
	{
		return listOfItems.get((int)(listOfItems.size() * Math.random()) - 1);
	}

//	public void spawnItems()
//	{
//		for (Block block : listOfBlocks)
//		{
//			if(block.getBlockType() == BlockType.Crate && Math.random() < .2)
//			{
//				Item toBeSpawned = chooseRandomItem();
//				toBeSpawned.setX(block.getX());
//				toBeSpawned.setY(block.getY());
//				itemsOnMap.add(toBeSpawned);
//			}
//		}
//	}

	public void addBlock(Block block){
		listOfBlocks.add(block);
	}
	
	public void assignActionToPlayer(GameContainer gc, int characterIndex,int delta){
		Character c = listOfCharacters.get(characterIndex);
		Input input = gc.getInput();
		if(input.isKeyDown(c.controls[0])){
			c.setMove(true);
			c.xVelocity = -1;
			c.canMoveLeft = true;
		}
		if(input.isKeyDown(c.controls[1])){
			c.setMove(true);
			c.yVelocity = -3;
			c.hasDX = false;
			c.jumpAvailable = false;
			c.canMoveUp = true;
		}
		if(input.isKeyDown(c.controls[2])){
			c.setMove(true);
			c.xVelocity = 1;
			c.canMoveRight = true;
		}
		c.determineDirection();
	}

}

