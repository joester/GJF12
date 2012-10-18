import java.io.IOException;
import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.newdawn.slick.opengl.*;
import org.newdawn.slick.util.*;
import org.jbox2d.collision.*;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.World;

public class GameWorld
{
	
	Texture background;
	World gameWorld = new World(new Vec2(0, -9.8f), false);
	
	public void init() throws IOException {	 

	background = TextureLoader.getTexture("JPEG", ResourceLoader.getResourceAsStream("assets/background.jpeg"));

	}
	
	public static void main (String[] args) {
		GameWorld gameworld = new GameWorld();
		gameworld.start();

		}
	
	public World getWorld()
	{
		return gameWorld;
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

public void collision()
{
	
}

public void draw()
{

}

public void update()
{
	draw();	
}
		

}

