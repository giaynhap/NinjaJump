package Object;

import Core.Timmer;
import Math.Vector;

public class Camera  extends GameObject{
 private GameObject object =null;
 	private int srcSize[] = new int[]{800,600};
 	private Timmer time = new Timmer();
 	private float mvTime =2.0f;
	public Camera(int x, int y)
	{
		super();
		srcSize[0] = x;
		srcSize[1] = y;
		time.init();
		objType = 1;
	}
	public  void setObject(GameObject obj)
	{
		this.object = obj;
	}
	public void move()
	{
		if (object==null) return;
		Vector pos = object.getPosition();
		float deltaTime= 1;
		
		float x = (pos.getX()-srcSize[0]/2.0f)*deltaTime + (1- deltaTime)*this.getPosition().getX();
		float y = (pos.getY()-srcSize[1]/2.0f)*deltaTime + (1- deltaTime)*this.getPosition().getY();
		x= Math.max(0,x);
		y= Math.max(0,y);
		this.getPosition().setX(x);
		this.getPosition().setY(y);
	}
	public float distance(float x, float y,float x2,float y2)
	{
		return (float) Math.sqrt((x-x2)*(x-x2)+(y-y2)*(y-y2));
	}

}
