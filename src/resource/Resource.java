package resource;

import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import Object.Sprite;

public class Resource {
	private static BufferedImage sprite_ninja,spriteLand1,spriteLand2,spriteItem;
	public static Sprite sprNinjaRun,sprNinjaIdle,sprNinjaJump;
	public static BufferedImage spriteAvatar;
	public static Sprite sprItem;
	public static Sprite sprLand;
	 public static Font gameFont ;
	public static void init()
	{
		try {
			
			LoadSprite();
			
			initFrame();
			
			LoadFont();
			
		} catch (IOException | FontFormatException e) {
			e.printStackTrace();
		}
	}
	public static void LoadFont()  throws  FontFormatException, IOException
	{
		gameFont = Font.createFont(Font.TRUETYPE_FONT,  Resource.class.getResourceAsStream("BankGthd.ttf"));
		 
	}
	public static void LoadSprite() throws IOException
	{
		sprite_ninja = ImageIO.read(   Resource.class.getResourceAsStream("ninja.png"));
		spriteLand1 = ImageIO.read(   Resource.class.getResourceAsStream("land1.png"));
		spriteLand2 = ImageIO.read(   Resource.class.getResourceAsStream("land2.png"));
		spriteItem = ImageIO.read(   Resource.class.getResourceAsStream("item.png"));
		spriteAvatar = ImageIO.read(   Resource.class.getResourceAsStream("ninjaicon.png"));
	}
	public static void initFrame()
	{
		sprNinjaRun = new Sprite();
		sprNinjaRun.setMaxFrame(6);
		sprNinjaRun.setFps(100);
		sprNinjaRun.setSize(new int[]{60,(int)(72*60.0/80.0)});
		
		for (int i =0 ;i<6;i++)
		{
			sprNinjaRun.addFrame(sprite_ninja.getSubimage((i+1)*80, 0, 80, 72));
		}
		
		
		sprNinjaIdle=   new Sprite(); 
		sprNinjaIdle.setSize(new int[]{60,(int)(72*60.0/80.0)});
		sprNinjaIdle.addFrame(sprite_ninja.getSubimage(0, 0, 80, 72));
		
		sprNinjaJump =  new Sprite(); 
		sprNinjaJump.setSize(new int[]{60,(int)(72*60.0/80.0)});
		sprNinjaJump.addFrame(sprite_ninja.getSubimage(7*80, 0, 80, 72));
		
		
		sprLand = new Sprite();
		sprLand.setMaxFrame(2);
		sprLand.setFps(999);
		sprLand.setSize(new int[]{300,168});
		sprLand.addFrame(spriteLand2);
		sprLand.addFrame(spriteLand1);
		
		sprItem = new Sprite();
		sprItem.setMaxFrame(2);
		sprItem.setFps(999);
		sprItem.setSize(new int[]{40,35});
		sprItem.addFrame(spriteItem.getSubimage(0, 0, 40, 35));
		sprItem.addFrame(spriteItem.getSubimage(40, 0, 40, 35));
	}
	
}
