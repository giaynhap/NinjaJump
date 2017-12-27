package Object;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

import Core.Common;
import resource.Resource;

public class ItemJump extends Item{

	 private ArrayList<Player> lstPlayer = new  ArrayList<Player>();
	 public ItemJump()
	 {
		 this.setName("Jump");
		 this.currentSprite = Resource.sprItem;
	 }
	 @Override
	 public BufferedImage getSprite()
	{
		return currentSprite.get(1);
	}
	 @Override
	 public void add(Player player )
	 {
		 if (! check(player))
		 {
			 player.container.addItem(this.getName(),1);
			 player.container.addItem("Speed",50);
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
