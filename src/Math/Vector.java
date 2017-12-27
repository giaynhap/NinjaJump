package Math;

public class Vector {
	private float x,y;
	public Vector()
	{
		x = 0 ;
		y = 0;
	}
	public Vector(float x,float y)
	{
		this.x = x ;
		this.y = y;
	}
	public float getX(){
		return x;
	}
	public float getY(){
		return y;
	}
	public int getIX(){
		return (int)x;
	}
	public int getIY(){
		return (int)y;
	}
	public void setX(float x)
	{
		this.x= x;
	}
	public void setY(float y)
	{
		this.y= y;
	}
}
