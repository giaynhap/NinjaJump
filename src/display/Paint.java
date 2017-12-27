package display;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Paint extends JPanel {
	public int width,height;
	public JLabel lb ;
	private BufferedImage canvas;
	private Graphics2D hGraphics;

	private Color BackgroundColor = new Color(66, 134, 244);
	public Paint(int width,int height)
	{
		super();
		this.width = width;
		this.height = height;
		this.init();
	}

	public void init()
	{
		
		canvas = new BufferedImage(width,height,BufferedImage.TYPE_4BYTE_ABGR);
		lb = new JLabel(new ImageIcon(canvas));
		hGraphics = canvas.createGraphics();
		this.add(lb);
		hGraphics.setBackground(BackgroundColor);
		hGraphics.clearRect(0, 0, width,height);
		
	}
	public Graphics2D getGraphics()
	{
		return hGraphics;
	}
	public void repaint2()
	{
		lb.repaint();
		 
	}
	public void repaint()
	{
		 super.repaint();
		 
	}
}
