package GUILauncher;

import javax.swing.JFrame;
import Graphics.Display;

public class RunGame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String Title = "Stratego";
	private Display disp;

	public RunGame() {
		
		
		setTitle(Title);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(true);
		setVisible(true);
		
		disp = new Display();
		setSize(disp.getGameWidth(),disp.getGameHeight());
		add(disp);
		revalidate();
		
		
		disp.start();

	}
}
