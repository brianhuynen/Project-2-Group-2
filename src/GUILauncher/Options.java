package GUILauncher;

import java.awt.Choice;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;



public class Options extends Launcher{


	private JButton graphics,sound,game,back,ok;
	private JTextField tWidth,tHeight;
	private JLabel lWidth,lHeight;
	private Choice resolution = new Choice();
	private Rectangle rgraphics,rsound,rgame,rback,rresolution,rok;
	private int width = 550;
	private int height = 550;
	private int Bwidth = 100;
	private int Bheight = 50;
	private int w = 0;
	private int h = 0;
	public Options(){
		super(1);
		setTitle("Options");
		setSize(new Dimension(width,height));
		setLocationRelativeTo(null);
		drawButtons();

	}

	private void drawButtons(){
		final int x = (int) (width/2)-(Bwidth/2);
		final int xp = Bwidth + 20;
		final int yp = Bheight + 20;
		final int y = (height/2)- 2*(yp);
		graphics = new JButton("Graphics");
		rgraphics = new Rectangle(x,y, Bwidth, Bheight);
		graphics.setBounds(rgraphics);
		window.add(graphics);
		graphics.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				lWidth = new JLabel("Width :");
				lWidth.setBounds(x+xp,y, Bwidth, Bheight/2);
				lHeight = new JLabel("Height :");
				lHeight.setBounds(x+xp,y+yp, Bwidth, Bheight/2);
				tWidth = new JTextField();
				tWidth.setBounds(x+xp+xp/2,y, Bwidth, Bheight/2);
				tHeight = new JTextField();
				tHeight.setBounds(x+xp+xp/2,y+yp, Bwidth, Bheight/2);
				window.add(lWidth);
				window.add(lHeight);
				window.add(tWidth);
				window.add(tHeight);

				rresolution = new Rectangle(x+xp,y+2*yp, Bwidth, Bheight);
				resolution.setBounds(rresolution);
				resolution.add("640, 440");
				resolution.add("800, 600");
				resolution.add("1024, 768");
				resolution.select(1);
				window.add(resolution);

				ok = new JButton("OK");
				rok = new Rectangle(x+xp,y+3*yp, Bwidth, Bheight);
				ok.setBounds(rok);
				window.add(ok);
				repaint();
				ok.addActionListener(new ActionListener() {

					public void actionPerformed(ActionEvent e) {

						drop();
						dispose();
						new Options();
						config.saveConfiguration("width", parseWidth());
						config.saveConfiguration("height", parseHeight());
					}
				});
			}
		});

		sound = new JButton("Sound");
		rsound  = new Rectangle(x,y+yp, Bwidth, Bheight);
		sound.setBounds(rsound);
		window.add(sound);
		sound.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {


			}
		});

		game = new JButton("Game");
		rgame = new Rectangle(x,y+2*yp, Bwidth, Bheight);
		game.setBounds(rgame);
		window.add(game);
		game.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {


			}
		});

		back = new JButton("Back");
		rback = new Rectangle(x,y+3*yp, Bwidth, Bheight);
		back.setBounds(rback);
		window.add(back);

		back.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				
				drop();
				dispose();
				new Launcher(0);
				
			}
		});
	}
	private void drop(){
		int selection = resolution.getSelectedIndex();


		if (selection == 0){
			w = 640;
			h = 440;
		}
		if (selection == 1 || selection == -1){
			w = 800;
			h = 600;
		}
		if (selection == 2){
			w = 1024;
			h = 768;
		}
		
	}
	private int parseWidth(){
		try{
		int tw = Integer.parseInt(tWidth.getText());
		return tw;
		}catch(NumberFormatException e){
		drop();
		return w;
		}
	}
	private int parseHeight(){
		try{
		int th = Integer.parseInt(tHeight.getText());
		return th;
		}catch(NumberFormatException e){
			drop();
			return h;
		}
	}
}
