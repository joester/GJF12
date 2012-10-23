import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.lwjgl.input.Controller;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
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
	IceMap iceMap = new IceMap(this,"/assets/Art/Background/bg_ice.jpg");
	LavaMap lavaMap = new LavaMap(this,"/assets/Art/Background/bg_volcano.jpg");
	SpaceMap2 spaceMap = new SpaceMap2(this,"/assets/Art/Background/bg_ice.jpg");
	ClockMap clockMap = new ClockMap(this,"/assets/Art/Background/bg_ice.jpg");
	ControllerManager controllerManager;
	private Image background;

	ArrayList<Sound> punchHit;
	ArrayList<Sound> punchMiss;
	ArrayList<Block> removeCrates;
	
	public GameWorld(ControllerManager cm){
		controllerManager = cm;
	}

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
		loadChars();

		map = iceMap;
		setBackgroundImage();
	}

	public void loadSounds() throws SlickException
	{
		Sound background = new Sound("assets/SFX/Music/Rocket.wav");
		background.loop();
		
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
		
		punchHit = new ArrayList<Sound>();
		punchMiss = new ArrayList<Sound>();
		
		punchHit.add(punchHit1);
		punchHit.add(punchHit2);
		punchHit.add(punchHit3);
		
		punchMiss.add(punchMiss1);
		punchMiss.add(punchMiss2);
		punchMiss.add(punchMiss3);
		
		

	}

	public void playRandomSound(ArrayList<Sound> soundList)
	{
		soundList.get((int)(soundList.size() * Math.random())).play();
	}
	
	public void loadChars() throws SlickException{
		List<Character> chars = new ArrayList<Character>();
		chars.add(new Character(0, 0, "player1", this));
		chars.add(new Character(0, 0, "player2", this));
		chars.add(new Character(0, 0, "player3", this));
		chars.add(new Character(0, 0, "player4", this));
		for(Character c : chars){
			c.renderEnt(c.image, c.image.getWidth() / 3, c.image.getHeight());
			listOfCharacters.add(c);
			System.out.println("Character added");
		}
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
				if (r.intersects(b.getHitBox())){	
					System.out.println("1XCoord: " + b.hitBox.getX() + " YCoord: " +b.hitBox.getY() +  " Height: "+ b.getHitBox().getHeight()+ " Width: " + b.getHitBox().getWidth());
					System.out.println("2XCoord: " + b.hitBox.getX() + " YCoord: " +b.hitBox.getY() +  " Height: "+ b.getHitBox().getHeight()+ " Width: " + b.getHitBox().getWidth());
					System.out.println("CY: " + c.getY());
					if (b.getBlockType() == BlockType.Lethal){
						c.modifyHealth(c.getHP());
					}
					c.determineDirection();
					if (c.isMovingLeft && b.getBlockType() != BlockType.Passable){
						if(c.getX() >= b.getX() + b.getHitBox().getWidth())
							c.canMoveLeft = false;
						if (c.isMovingUp && b.getBlockType() != BlockType.Passable){
							if(c.getY() >= b.getY() + b.getHitBox().getHeight()){
								c.yVelocity = 0;
								c.jumpAvailable = false;
								c.canMoveUp = false;
							}
						}
						else if (c.isMovingDown){
							c.jumpAvailable = true;
							if(c.getHitBox().getY() + c.getHitBox().getHeight() < b.getHitBox().getY()){
								c.yVelocity = 0;
								c.canMoveDown = false;
							}
						}
					}
					else if(c.isMovingRight && b.getBlockType() != BlockType.Passable){
						//c.xVelocity = 0;
						if(c.getX() + c.getHitBox().getWidth() <= b.getX())
							c.canMoveRight = false;
						if (c.isMovingUp && b.getBlockType() != BlockType.Passable){
							if(c.getY() >= b.getHitBox().getY() + b.getHitBox().getHeight()){
								c.yVelocity = 0;
								c.jumpAvailable = false;
								c.canMoveUp = false;
							}
						}
						else if (c.isMovingDown){
							c.jumpAvailable = true;
							if(c.getHitBox().getY() + c.getHitBox().getHeight() < b.getHitBox().getY()){
								c.yVelocity = 0;
								c.canMoveDown = false;
							}
						}
					}
					else{
						if (c.isMovingUp && b.getBlockType() != BlockType.Passable){
							c.yVelocity = 0;
							c.jumpAvailable = false;
							c.canMoveUp = false;
						}
						else if (c.isMovingDown){
							c.jumpAvailable = true;
							if(c.getHitBox().getY() + c.getHitBox().getHeight() < b.getHitBox().getY()){
								c.yVelocity = 0;
								c.canMoveDown = false;
							}
						}
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
				if(r.getY() > gc.getHeight()){
					c.modifyHealth(c.getHP());
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
				if (p.getRectangle().intersects(c.getHitBox()))
				{			
					c.modifyHealth(p.damage);
					projectilesToBeRemoved.add(p);
				}

				for (Item i : itemsOnMap)
				{
					if (c.getHitBox().intersects(i.getHitBox()) && !c.hasItem)
					{
						c.pickUpItem(i);
						c.hasItem = true;
					}
				}
			}
			
			for (Character c : listOfCharacters)
			{
				for (Item i : itemsOnMap)
				{
					if (c.getHitBox().intersects(i.getHitBox()) && !c.hasItem)
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

		removeCrates = new ArrayList<Block>();
		for (Block b: listOfBlocks)
		{
			if (b.getBlockType() == BlockType.Crate)
			{
				for (Projectile p : listOfProjectiles)
				{
					if (p.getRectangle().intersects(b.getHitBox()))
					{
						Item toBeAdded = chooseRandomItem();
						toBeAdded.setLocation(b.getX() + 30,b.getY() + 30);
						itemsOnMap.add(toBeAdded);
						removeCrates.add(b);							
					}
				}	
//				for (Character c : listOfCharacters)
//				{
//					if (c.isMovingRight)
//					{
//						Item toBeAdded = chooseRandomItem();
//						toBeAdded.setLocation(b.getX() + 30,b.getY() + 30);
//						itemsOnMap.add(toBeAdded);
//						removeCrates.add(b);							
//					}
//				}
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
		for(int i = 0; i < listOfCharacters.size(); i++){
			assignActionToPlayer(gc,i,delta);
		}
		for (Character c : listOfCharacters)
		{
			c.yVelocity += .1;
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
		System.out.println(itemsOnMap.size());

	}

	public void render(GameContainer gc, Graphics g) throws SlickException{
		background.draw();
		for(Item i : itemsOnMap)
		{
			i.render(gc, g);
		}

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
			if(c.getHP() > 0){
				c.render(gc, g);
			}
		}
	}



	public Item chooseRandomItem()
	{
		return listOfItems.get((int)(listOfItems.size() * Math.random()));
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
		if(characterIndex == 0){
			if(input.isKeyDown(Input.KEY_A)){
				listOfCharacters.get(0).xVelocity = -1;
				c.canMoveLeft = true;
			}
			if(input.isKeyDown(Input.KEY_W)){
				c.yVelocity = -3;
				c.hasDX = false;
				c.jumpAvailable = false;
				c.canMoveUp = true;
			}
			if(input.isKeyDown(Input.KEY_D)){
				c.xVelocity = 1;
				c.canMoveRight = true;
			}
		}
		if(characterIndex == 1){
			if(input.isKeyDown(Input.KEY_J)){
				c.xVelocity = -1;
				c.canMoveLeft = true;
			}
			if(input.isKeyDown(Input.KEY_I)){
				c.yVelocity = -3;
				c.hasDX = false;
				c.jumpAvailable = false;
				c.canMoveUp = true;
			}
			if(input.isKeyDown(Input.KEY_L)){
				c.xVelocity = 1;
				c.canMoveRight = true;
			}
		}
		if(characterIndex == 2){
			if(input.isKeyDown(Input.KEY_LEFT)){
				c.xVelocity = -1;
				c.canMoveLeft = true;
			}
			if(input.isKeyDown(Input.KEY_UP)){
				c.yVelocity = -3;
				c.hasDX = false;
				c.jumpAvailable = false;
				c.canMoveUp = true;
			}
			if(input.isKeyDown(Input.KEY_RIGHT)){
				c.xVelocity = 1;
				c.canMoveRight = true;
			}
		}
		if(input.isKeyDown(Input.KEY_SPACE)){
			c.attack();
		}
		c.determineDirection();
		if(controllerManager != null){
			controllerManager.pollControllers();
			for(int i = 0; i < controllerManager.getControllerCount(); i++){
				Controller ctr = controllerManager.getController(i);

				if(ctr.getXAxisValue() < -0.75 && ctr.getYAxisValue() > -0.75 && ctr.getYAxisValue() < 0.75){

					c.xVelocity = -1;
					c.canMoveLeft = true;
				}
				else if(ctr.getXAxisValue() > 0.75 && ctr.getYAxisValue() > -0.75 && ctr.getYAxisValue() < 0.75){

					c.xVelocity = 1;
					c.canMoveRight = true;
				}
				if(ctr.isButtonPressed(Button.A.buttonID)){

					c.yVelocity = -3;
					c.hasDX = false;
					c.jumpAvailable = false;
					c.canMoveUp = true;
				}
			}
		}
		c.determineDirection();
	}

	private void setBackgroundImage(){
		try {
			background = new Image(map.getBackgroundFileLocation());
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}

