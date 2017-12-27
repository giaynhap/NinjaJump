package Core;

import java.awt.Rectangle;

import Math.Vector;
import Object.GameObject;

public class Common {
	public static boolean CollisionRect(GameObject obj1,GameObject obj2,int addx,int addy)
	{
		Vector pos1 = obj1.getPosition();
		Vector pos2 = obj2.getPosition();
		int[] size1 = obj1.getNodeSprite().getSize();
		int[] size2 = obj2.getNodeSprite().getSize();
		Rectangle Rectangle1 = new Rectangle();
		Rectangle1.setBounds(pos1.getIX()+addx , pos1.getIY()+addy , size1[0], size1[1]);
		Rectangle Rectangle2 = new Rectangle();
		Rectangle2.setBounds(pos2.getIX() , pos2.getIY() , size2[0], size2[1]);
		 
		return Rectangle1.intersects(Rectangle2) ;
	}
	 
	 
	 
}
