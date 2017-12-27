package Object;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Sprite implements Runnable{
	private int size[] = new int []{80,72};
	private int curFrame = 0;
	private int MaxFrame = 0;
	private int fps = 10;
	private boolean isPlay =false;
	private ArrayList<BufferedImage> sprFrame = new ArrayList<BufferedImage>();
	public Sprite clone()
	{
		Sprite nSprite= new Sprite();
		nSprite.curFrame = 0;
		nSprite.fps = 10;
		nSprite.isPlay =false;
		nSprite.size[0]= this.size[0];
		nSprite.size[1] = this.size[1];
		nSprite.MaxFrame = this.MaxFrame;
		
		for (BufferedImage img : this.sprFrame) {
			nSprite.sprFrame.add(img);
		}
		
		return nSprite;
	}
	public Sprite()
	{
		Thread th = new Thread(this);
		th.start();
	}
	
	public void addFrame(BufferedImage mframe)
	{
		sprFrame.add(mframe) ;
	}
	public void play()
	{
		isPlay = true;
	}
	public void stop()
	{
		isPlay = false;
	}
	public void removeFrame(int index)
	{
		sprFrame.remove(index);
	}
	public BufferedImage get()
	{
		return sprFrame.get(curFrame);
	}
	public BufferedImage get(int index)
	{
		return sprFrame.get(index);
	}
	public void doFrame()
	{
		curFrame++;
		if (curFrame>= MaxFrame ) curFrame =0;
	}
	public int[] getSize() {
		return size;
	}
	public void setSize(int[] size) {
		this.size = size;
	}
	public int getCurFrame() {
		return curFrame;
	}
	public void setCurFrame(int curFrame) {
		this.curFrame = curFrame;
	}
	public int getMaxFrame() {
		return MaxFrame;
	}
	public void setMaxFrame(int maxFrame) {
		MaxFrame = maxFrame;
	}
	public int getFps() {
		return fps;
	}
	public void setFps(int fps) {
		this.fps = fps;
	}

	@Override
	public void run() {
	 while(true)
	 {
		 try {
			Thread.sleep(fps);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		 if (!isPlay) continue;
		 doFrame();
	 }
	}
}
