package Object;

import java.awt.image.BufferedImage;

import Math.Vector;

public class GameObject {
	private Vector pos;
	private Vector vec;
	private String name = "Object";
	protected int objType =0;
	protected Sprite currentSprite;
	public boolean isAlive = true;
	public GameObject()
	{
		pos= new Vector();
		vec = new Vector();
	}
	public void setName(String name)
	{
		this.name= name;
	}
	public String getName()
	{
		return this.name;
	}
	public Vector getPosition()
	{
		return pos;
	}
	public Vector getVelocity()
	{
		return vec;
	}
	public BufferedImage getSprite()
	{
		return null;
	}
	public void move()
	{
		
	}
	public Sprite getNodeSprite()
	{
		return currentSprite;
	}
}
