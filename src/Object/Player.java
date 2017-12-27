package Object;

import java.awt.image.BufferedImage;

import Core.GameCore;
import Core.Timmer;
import Math.Vector;

public class Player extends GameObject {
	private Sprite spr_run;
	private Sprite spr_idle;
	private BufferedImage avatar;
	private Sprite spr_jump;
	private float[] resetpos = new float[2];
	public Container container = new Container();
	public Timmer time = new Timmer();
	public Player()
	{
		super();
		
	}
	public void setAvatar(BufferedImage av )
	{
		avatar = av;
	}
	public BufferedImage getAvatar(  )
	{
		return avatar ;
	}
	public void setSpriteRun(Sprite spr)
	{
		spr_run = spr.clone();
		currentSprite = spr_run;
	}
	public void setSpriteIdle(Sprite spr)
	{
		spr_idle = spr.clone();
	}
	public void setSpriteJump(Sprite spr)
	{
		spr_jump = spr.clone();
	}
	public BufferedImage getSprite()
	{
		return currentSprite.get();
	}
	public void reset(float f,float g)
	{
		time.init();
		this.isAlive = false;
		resetpos[0] =f;
		resetpos[1] = g;
		
	}
	public void play()
	{
		spr_run.play();
		spr_idle.play();
		spr_jump.play();
	}
	@Override
	public void move()
	{
		
		super.move();
		 currentSprite = spr_run;
		if( Math.abs(this.getVelocity().getY())>1) currentSprite = spr_jump;
		if( Math.abs(this.getVelocity().getX())<1) currentSprite = spr_idle; 
		Integer nitem = this.container.getItem("Speed");
		if (GameCore.instance().GameState!=0)
		{
			if (  nitem>0) 
			{
		 
				this.getVelocity().setX(8);
				this.container.addItem("Speed", -1);
			}
			else this.getVelocity().setX(5);
		}
		
		  nitem = this.container.getItem("Jump");
		if (GameCore.instance().GameState!=0)
		{
			if (  nitem>0) 
			{
		 
				this.getVelocity().setY(-12);
				this.getVelocity().setX(9);
				this.container.addItem("Jump", -1);
			}
			 
		}
		
		//if ( GameState==0) continue;
		 
		 if (time.getDelta()>5&& !this.isAlive)
		 {
			 this.getPosition().setX(resetpos[0]);
				this.getPosition().setY(resetpos[1]);
				this.isAlive = true;
		 }
		
	}
}
