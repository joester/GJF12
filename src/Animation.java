import java.util.ArrayList;

import org.newdawn.slick.opengl.Texture;
//import org.lwjgl.Sys;

public class Animation
{
	private ArrayList<Frame> frames;
	private long totTime;
	private long curTime;
	private int frameIndex;
	
	public Animation(){
		frames = new ArrayList<Frame>();
		totTime = 0;
		start();
	}
	
	public synchronized void start(){
		curTime = 0;
		frameIndex = 0;
	}
	
	public void addFrame(Texture img, long imgTime){
		totTime += imgTime;
		frames.add(new Frame(img, imgTime));
	}
	
	public synchronized void updateFrame(long timePassed){
		if(frames.size() > 1){
			curTime += timePassed;
		}
		if(curTime >= totTime){
			frameIndex = 0;
			curTime = curTime % timePassed;
		}
		while(curTime > getFrame(frameIndex).time){
			frameIndex ++;
		}
	}
	
	public Texture getImage(){
		if(frames.size() == 0){
			return null;
		}
		return getFrame(frameIndex).img;
	}
	
	
	
	private Frame getFrame(int i){
		return frames.get(i);
	}
	
	public class Frame{
		Texture img;
		long time;
		public Frame(Texture img, long time){
			this.img = img;
			this.time = time;
		}
	}
}
