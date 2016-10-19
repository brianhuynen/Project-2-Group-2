package GUILauncher;

import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;

import Graphics.Display;


public class Launcher extends JFrame{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected JPanel window=  new JPanel();
	private JButton play,option,help,quit;
	private Rectangle rplay,roption,rhelp,rquit;
	protected Configuration config = new Configuration();
	private int width = 500;
	private int height = 500;
	private int Bwidth = 80;
	private int Bheight = 40;
	
	public Launcher(int id){
	try{
		UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
	}catch(Exception e){
		e.printStackTrace();
	}
	
	setTitle("LAUNCHER");
	setSize(new Dimension(width,height));
	getContentPane().add(window);
	setDefaultCloseOperation(EXIT_ON_CLOSE);
	setLocationRelativeTo(null);
	setResizable(false);
	window.setLayout(null);
	setVisible(true);
	if (id == 0){
		drawButtons();
	}
	repaint();
}
	private void drawButtons(){
	int x = (int) (width/2)-(Bwidth/2);
	int yp = Bheight+20;
	int y = (height/2)- 2*(yp);
		play = new JButton("Play!");
		rplay = new Rectangle(x,y, Bwidth, Bheight);
		play.setBounds(rplay);
		window.add(play);
		play.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				String path ="resources/Settings/Config.xml";
				config.loadConfiguration(path);
				new RunGame();
				dispose();
				config.loadConfiguration(path);
			}
		});
	
		option = new JButton("Option");
		roption  = new Rectangle(x,y+yp, Bwidth, Bheight);
		option.setBounds(roption);
		window.add(option);
		option.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
			
				new Options();
				dispose();
			}
		});
		
		help = new JButton("Help");
		rhelp = new Rectangle(x,y+2*yp, Bwidth, Bheight);
		help.setBounds(rhelp);
		window.add(help);
        help.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				
			}
		});
		
        quit = new JButton("Quit");
		rquit = new Rectangle(x,y+3*yp, Bwidth, Bheight);
		quit.setBounds(rquit);
		window.add(quit);

		quit.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
				
			}
		});
	}
}
