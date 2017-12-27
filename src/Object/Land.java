package Object;

import java.awt.image.BufferedImage;

import resource.Resource;

public class Land extends GameObject{
	private int indexLand = 0;
	 
	public int getIndexLand() {
		return indexLand;
	}
 
	public Land()
	{
		super();
		this.setName("Land");
		objType = -1;
		currentSprite = Resource.sprLand;
	}
	
	@Override
	public BufferedImage getSprite()
	{
		return currentSprite.get(indexLand);
	}

	public void setIndexLand(int nextInt) {
		this.indexLand = nextInt;
		
	}
}

