package Object;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

import Core.Common;
import resource.Resource;

public class ItemSpeed extends Item{

	 private ArrayList<Player> lstPlayer = new  ArrayList<Player>();
	 public ItemSpeed()
	 {
		 this.setName("Speed");
		 this.currentSprite = Resource.sprItem;
	 }
	 @Override
	 public BufferedImage getSprite()
	{
		return currentSprite.get(0);
	}
	 @Override
	 public void add(Player player )
	 {
		 if (! check(player))
		 {
			 player.container.addItem(this.getName(),100);
			 lstPlayer.add(player);
		 }
	 }
	 @Override
	 public boolean check(Player player)
	 {
		 if (lstPlayer.indexOf(player)>=0) return true;
		 return false;
	 }
}
