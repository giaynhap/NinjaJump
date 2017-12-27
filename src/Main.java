import java.awt.EventQueue;

import javax.swing.JFrame;

import Core.GameCore;
import display.Paint;
import process.Render;
import resource.Resource;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Main {

	public static JFrame frame;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main window = new Main();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Main() {
		initialize();
	}


	private void initialize() {
		Resource.init();
		frame = new JFrame();
		frame.setBounds(100, 100, 800,600);
		Paint paint = new Paint(800,600);
		paint.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				GameCore.instance().moveHostController.onKeyBoardEvent(arg0.getKeyCode(), 0);
			 
			}
			@Override
			public void keyReleased(KeyEvent arg0) {
				
				GameCore.instance().moveHostController.onKeyBoardEvent(arg0.getKeyCode(), 1);
			
			}
		});
		GameCore.instance();
		Render ren =new Render(paint,frame);
		paint.setFocusable(true);
	 
		frame.getContentPane().add(paint);
	 
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}
