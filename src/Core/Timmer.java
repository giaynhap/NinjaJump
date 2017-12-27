package Core;

public class Timmer {
	private long lastTime = 0;
	private float deltaTime = 0.0f;
	public Timmer()
	{
		init();
	}
	public void init()
	{
		lastTime = System.currentTimeMillis();
	}
	public float getDelta()
	{
		long current = System.currentTimeMillis();
		deltaTime = (current- lastTime) / 1000.0f;
		return deltaTime;
	}
	public float getDelta(boolean reset)
	{
		
		long current = System.currentTimeMillis();
		deltaTime = (current- lastTime) / 1000.0f;
		if (reset ) init();
		return deltaTime;
	}
}
