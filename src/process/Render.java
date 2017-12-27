package process;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.font.FontRenderContext;
import java.awt.font.TextLayout;
import java.util.ArrayList;

import javax.swing.JFrame;

import Controller.MoveController;
import Core.GameCore;
import Object.Camera;
import Object.Item;
import Object.Land;
import Object.Player;
import display.Paint;
import resource.Resource;
public class Render implements Runnable {
	
	private Paint mPaint ;
	private Graphics2D hGraphics;
	JFrame frame ;
	private Camera cam;
	public ArrayList<Player> lstPlayer ;
	public ArrayList<Land> lstMap ;
	public ArrayList<Item> lstItem ;
	public Render(Paint Paint, JFrame frame )
	{
		lstPlayer = GameCore.instance().lstPlayer;
		lstMap = GameCore.instance().lstMap;
		lstItem = GameCore.instance().lstItem;
		this.frame = frame;
		mPaint= Paint;
		Resource.sprNinjaRun.play();
		Thread th = new Thread(this);
		th.start();
		this.cam= GameCore.instance().MainCamera;
		applyQualityRenderingHints(mPaint.getGraphics());
		
	}
	int a=0;
	public void render()
	{
		
		if (lstPlayer==null|| lstPlayer.size()<1) return;
		hGraphics = mPaint.getGraphics();
		if (hGraphics==null) return;
		hGraphics.clearRect(0,0, mPaint.width, mPaint.height);
		
		 
		for (Player player : lstPlayer)
		{
			if (!player.isAlive)  continue;
			hGraphics.drawImage(player.getSprite(), player.getPosition().getIX()-this.cam.getPosition().getIX(), player.getPosition().getIY()-this.cam.getPosition().getIY(),60,(int)(72*60.0/80.0), null);
		}
		for (Land land : lstMap)
		hGraphics.drawImage(land.getSprite(),land.getPosition().getIX()-this.cam.getPosition().getIX(),land.getPosition().getIY()-this.cam.getPosition().getIY()-20,Resource.sprLand.getSize()[0],Resource.sprLand.getSize()[1],null);
		for (Item item :lstItem)
			hGraphics.drawImage(item.getSprite(),item.getPosition().getIX()-this.cam.getPosition().getIX(),item.getPosition().getIY()-this.cam.getPosition().getIY()-20,40,35,null);	
		UI();
		frame.repaint();
	}
	private int gameCOuntLas = 0;
	private int effFontSize1 = 0;
	private void UI()
	{
		if ( GameCore.instance().GameState ==0)
		{
			float  delta =GameCore.instance().time.getDelta();
			if (delta<10)
			{
				int Time=   (int)Math.floor(delta);
				if (gameCOuntLas != Time)  effFontSize1 = 100;
					gameCOuntLas = Time; 
				if (effFontSize1>0) effFontSize1-=effFontSize1/2+1;
				//  Font gfont = Resource.gameFont.deriveFont(1,40);
				//	FontMetrics metrics = hGrap.getFontMetrics(gfont);
			//	hGraphics.setFont(gfont);
				
			 	drawStringBorder(100+effFontSize1,(9-Time)+"",mPaint.width/2,mPaint.height/2); 
			 	
			}
			
		}
		hGraphics.fillRoundRect(mPaint.width/2-200,20, 400, 10,5,5);
		for (Player player : lstPlayer)
		{
		
			float pPost = player.getPosition().getX()/ GameCore.instance().StLength*400.0f;
			
			hGraphics.drawImage(player.getAvatar(), mPaint.width/2-200+(int)(pPost)-10, 10, 20, 20, null);
		}
		Player curPlayer =  MoveController.instance().getPlayer();
		if (!curPlayer.isAlive)
		{
			hGraphics.setFont(Resource.gameFont.deriveFont(1,14));
			hGraphics.drawString("Respawn "+(5-(int)curPlayer.time.getDelta()), mPaint.width/2-50, mPaint.height-120);
			hGraphics.fillRoundRect(mPaint.width/2-50,mPaint.height-100, (int)(curPlayer.time.getDelta()/5*100), 10,5,5);
		}
		
		hGraphics.setFont(Resource.gameFont.deriveFont(1,14));
		hGraphics.drawString("Time "+(int)GameCore.instance().Gametime.getDelta(), mPaint.width/2-10, 60);
		
 
	}
	
	public void drawStringBorder(int fontsize,String s,int x,int y)
	{
		Font gfont = Resource.gameFont.deriveFont(1,100+fontsize);
		FontMetrics metrics = hGraphics.getFontMetrics(gfont);
		hGraphics.setFont(gfont);
	 
		FontRenderContext frc = hGraphics.getFontRenderContext();
        TextLayout textTl = new TextLayout(s,gfont, frc);
        Shape outline = textTl.getOutline(null);
        hGraphics.setColor(Color.BLACK);
        hGraphics.setStroke(new BasicStroke(5));
        hGraphics.translate(x-metrics.stringWidth(s)/2,y);
        hGraphics.draw(outline);
        hGraphics.translate(-x+metrics.stringWidth(s)/2,-y);
        hGraphics.setColor(Color.white);
        hGraphics.drawString(s,x-metrics.stringWidth(s)/2,y);
	}
	@Override
	public void run() {
		 while (true)
		 {
			 try {
				render();
			
				Thread.sleep(10);	
			} catch (InterruptedException e) {
				e.printStackTrace();
			} 
		 }
	}
	public static void applyQualityRenderingHints(Graphics2D g2d) {
	    //    g2d.setRenderingHint(RenderingHints.KEY_ALPHA_INTERPOLATION, RenderingHints.VALUE_ALPHA_INTERPOLATION_QUALITY);
	     //   g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
	        g2d.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_QUALITY);
	        g2d.setRenderingHint(RenderingHints.KEY_DITHERING, RenderingHints.VALUE_DITHER_ENABLE);
	        g2d.setRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS, RenderingHints.VALUE_FRACTIONALMETRICS_ON);
	        g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
	        g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
	        g2d.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_PURE);
	    }
}
