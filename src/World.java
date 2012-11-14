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

public class World
{
	public final int winsNeeded = 3;
	private List<Block> blocks;
	private List<Platform> platforms;
	private List<Projectile> projectiles;
	private List<Character> characters;
	private List<Item> items;
	private List<Item> itemsOnMap;
	private List<Map> maps;
	private Map map;

	private IceMap iceMap = new IceMap(this,"assets/Art/Background/bg_ice.jpg", "assets/SFX/music/Ice.wav");
	private LavaMap lavaMap = new LavaMap(this,"assets/Art/Background/bg_volcano.jpg", "assets/SFX/music/Volcano.wav");
	private SpaceMap spaceMap = new SpaceMap(this,"assets/Art/Background/bg_space.jpg", "assets/SFX/music/Space.wav");
	//ClockMap clockMap = new ClockMap(this,"assets/Art/Background/bg_space.jpg","assets/SFX/music/Ice.wav");
	private ControllerManager controllerManager;
	private Image background;

	public ArrayList<Sound> punchHit;
	public ArrayList<Sound> punchMiss;
	private ArrayList<Block> cratesToRemove;
	private ArrayList<Item> itemsToRemove;
	private ArrayList<Projectile> projectilesToRemoved;
	private int numberOfPlayers;
	private List<Character> players;
	private Sound BGM;

	public World(ControllerManager cm){
		controllerManager = cm;
		numberOfPlayers = 2;
	}

	//Initialization
	public void init() throws IOException, SlickException 
	{	 
		blocks = new ArrayList<Block>();
		platforms = new ArrayList<Platform>();
		projectiles = new ArrayList<Projectile>();
		characters = new ArrayList<Character>();
		projectilesToRemoved =  new ArrayList<Projectile>();
		itemsToRemove = new ArrayList<Item>();
		cratesToRemove = new ArrayList<Block>();
		items = new ArrayList<Item>();
		itemsOnMap = new ArrayList<Item>();
		maps = new ArrayList<Map>();

		iceMap.buildMap();
		map = iceMap;
		loadSounds();
		setBackgroundImage();
		loadChars();
	}

	public void loadChars() throws SlickException{
		List<Map.Location> characterSpawns = map.getCharacterSpawns();
		players = new ArrayList<Character>();
		for(int i = 0; i < getNumberOfPlayers() ; i++){
			int j = i+1;
			Map.Location loc = characterSpawns.get(i);
			Character c = new Character(loc.x, loc.y, "player" + j, this);
			c.setPlayerID(i);
			c.init();
			c.renderEnt(c.image, c.image.getWidth() / 3, c.image.getHeight(),300);
			getPlayers().add(c);
			characters.add(c);
		}
	}

	public void loadSounds() throws SlickException
	{
		setBGM(map.getBGM());

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

	//Updating and Rendering
	public void update(GameContainer gc, int delta) throws SlickException, InterruptedException
	{
		Input input = gc.getInput();
		for (Character c : characters)
		{
			c.handleInput(input);
			c.yVelocity += .45;
		}
		checkForCollisions(gc);

		ArrayList<Character> charactersToBeRemoved = new ArrayList<Character>();
		for (Character c: characters)
		{
			try{
				c.update(gc, delta);
				if(c.getHP() <= 0){
					charactersToBeRemoved.add(c);
				}
			}catch(Exception e){

			}			
		}
		for(Character c: charactersToBeRemoved){
			characters.remove(c);
		}

		projectilesToRemoved.clear();
		for (Projectile p : projectiles)
		{
			p.update(gc, delta);	
		}
		for (Projectile p : projectilesToRemoved)
			projectiles.remove(p);
		for (Item i : itemsOnMap)
		{
			i.update(gc, delta);
		}
	}

	public void render(GameContainer gc, Graphics g) throws SlickException{
		getBackground().draw();
		for(Item i : itemsOnMap)
		{
			i.render(gc, g);
		}

		for(Block b: blocks){
			try{
				b.render(gc, g);

			}
			catch(NullPointerException ex){
				blocks.remove(b);
			}
		}
		for(Projectile p : projectiles){
			p.render(gc, g);

		}
		for(Character c : characters){
			if(c.getHP() > 0){
				c.render(gc, g);
			}
		}
	}

	public void checkForCollisions(GameContainer gc){
		for(Character c : characters){
			c.checkCollisions(gc);
		}
		for(Projectile p : projectiles){
			p.checkCollisions();
		}
		for (Item i : itemsToRemove)
			itemsOnMap.remove(i);	
		itemsToRemove.clear();
		for (Block b :cratesToRemove)
			blocks.remove(b);
		cratesToRemove.clear();
		for (Projectile p : projectilesToRemoved)
			projectiles.remove(p);
		projectilesToRemoved.clear();
	}
/**
	public void assignActionToPlayer(GameContainer gc, int characterIndex,int delta){
		if(controllerManager != null){
			controllerManager.pollControllers();
			for(int i = 0; i < controllerManager.getControllerCount(); i++){
				Controller ctr = controllerManager.getController(i);

				if(ctr.getXAxisValue() < -0.75 && ctr.getYAxisValue() < 0.75){

					c.xVelocity = -1;
					c.canMoveLeft = true;
				}
				else if(ctr.getXAxisValue() > 0.75 && ctr.getYAxisValue() < 0.75){

					c.xVelocity = 3;
					c.canMoveRight = true;
				}
				if(ctr.isButtonPressed(Button.A.buttonID)){
					if(c.jumpAvailable){
						c.yVelocity = -7.5f;
						c.hasDX = false;
						c.jumpAvailable = false;
						c.canMoveUp = true;
					}
				}
				if(ctr.isButtonPressed(Button.B.buttonID)){
					for (Item item : itemsOnMap)
					{
						if (c.getHitbox().intersects(item.getHitbox()))
						{
							c.dropItem();
							c.pickUpItem(item);
							itemsToRemove.add(item);
						}
					}
					for (Item item :itemsToRemove)
					{
						itemsOnMap.remove(item);
					}
				}
				if(ctr.isButtonPressed(Button.X.buttonID)){
					c.attack();
				}
			}
		}
		c.determineDirection();
	}**/

	//Modifying world
	public void addBlock(Block block){
		blocks.add(block);
	}

	public void removeProjectile(Projectile projectileToRemove){
		projectilesToRemoved.add(projectileToRemove);
	}

	public void setNumberOfPlayers(int numberOfPlayers) {
		this.numberOfPlayers = numberOfPlayers;
	}

	public void setBGM(Sound BGM) {
		this.BGM = BGM;
	}

	public void removeCrate(Block crate){
		cratesToRemove.add(crate);
	}

	public Item chooseRandomItem()	{
		int itemID = (int)(5 * Math.random());
		switch(itemID){
		case 0: itemID = 0; return new Earth(0,0,"assets/Art/Transformations/icons/hammer.png", this); 
		case 1: itemID = 1; return new Fire(0,0,"assets/Art/Transformations/icons/bow.png", this); 
		case 2: itemID = 2; return new Ice(0,0,"assets/Art/Transformations/icons/shield.png", this); 
		case 3: itemID = 3; return new Lightning(0,0,"assets/Art/Transformations/icons/dagger.png", this); 
		case 4: itemID = 4; return new Wind(0,0,"assets/Art/Transformations/icons/fan.png", this); 
		default: return null;
		}
	}

	public void spawnItem(Block b){
		Item item = chooseRandomItem();
		item.setLocation(b.getX() + (b.getImage().getWidth() - item.getImage().getWidth())/2, 
				b.getY() + (b.getImage().getHeight() - item.getImage().getHeight())/2);
		item.setYSpawnLocation(item.getY());
		itemsOnMap.add(item);
		cratesToRemove.add(b);
	}

	public void removeItem(Item i) {
		itemsToRemove.add(i);
	}

	//Setting Up Rounds
	public void setNextRound() throws IOException, SlickException {

		blocks = new ArrayList<Block>();
		platforms = new ArrayList<Platform>();
		projectiles = new ArrayList<Projectile>();
		itemsOnMap = new ArrayList<Item>();
		map = getNextMap();
		map.buildMap();

		for(int i = 0; i < getNumberOfPlayers(); i++){
			Character c = getPlayers().get(i);
			c.reset();
			Map.Location loc = map.getCharacterSpawns().get(i);
			c.setLocation(loc.x * MapEntity.BLOCKSIZE, loc.y * MapEntity.BLOCKSIZE);
			c.setHitboxLocation(c.xCoord, c.yCoord);
		}
		characters = new ArrayList<Character>(getPlayers());
		setBackgroundImage();
		getBGM().stop();
		setBGM(map.getBGM());
		getBGM().loop();
	}

	public boolean checkIsRoundOver(){
		return characters.size() <= 1;
	}


	//World Access Methods
	public List<Block> getBlocks()
	{
		return blocks;
	}

	public List<Projectile> getProjectiles()
	{
		return projectiles;
	}

	public List<Character> getCharacters()
	{
		return characters;
	}

	public List<Item> getItemsOnMap() {
		return itemsOnMap;
	}

	public Map getMap()
	{
		return map;
	}

	private void setBackgroundImage(){
		background = map.getBackground();
	}

	private Map getNextMap() {
		if(map instanceof IceMap){
			return lavaMap;
		}
		else if(map instanceof LavaMap){
			return spaceMap;
		}
		else{
			return iceMap;
		}

	}

	public Image getBackground() {
		return background;
	}

	public Sound getBGM() {
		return BGM;
	}

	public List<Character> getPlayers() {
		return players;
	}

	public int getNumberOfPlayers() {
		return numberOfPlayers;
	}	
}

