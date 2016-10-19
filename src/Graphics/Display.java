package Graphics;

//fire@WILL!
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.util.Random;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Cell.CardCell;
import Cell.Cell;
import Cell.EmptyCell;
import Cell.ImpassableCell;
import Cell.OccupiedCell;
import GUILauncher.Game;
import GUILauncher.Launcher;
import GameBoard.Board;
import Player.AIPlayer;
import Player.HumanPlayer;
import Player.Player;

public class Display extends JPanel implements Runnable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	final static boolean DEBUG = true;
	
	public static int width = 1000;
	public static int height = 1000;
	private Game game;
	private Board board;
	private Player[] players;
	private int side = 12;
	private Cell[][] buttons;
	private boolean running;
	private Thread thread;
	
	

	public Display() {
		Dimension size = new Dimension(getGameWidth(), getGameHeight());
		setPreferredSize(size);
		setMinimumSize(size);
		setMaximumSize(size);

		this.setBackground(Color.black);
		this.setLayout(new GridLayout(side, side));

		setupGame();
		

	}

	private void setupGame() {
			players = popoutBox();
		// make board
			
			
				board = new Board();
			//	board.createPlayerCards(players[0]);
				//board.createPlayerCards(players[1]);
				board.resetBoard();
				board.setCurrentPlayer(players[0]);
				
				makePlayerCards(players);
				board.printBoard();
				//board.printMatrix();

				buttons = new Cell[side][side];
				game = new Game(players, board);
				// create the cell objects in cell[][]

				for (int i = 0; i < buttons.length; i++) {
					for (int j = 0; j < buttons.length; j++) {
						
						buttons[i][j] = board.getGrid()[i][j];

						add(buttons[i][j]);
					}
				}
	}

	private void makePlayerCards(Player[] players) {

		for (int i = 0; i < players.length; i++) {

			System.out.println("CREATING CARDS FOR: " + players[i].getName() + " ID: " + players[i].getID());
			board.createPlayerCards(players[i]);
			
		}

	}

	public int getGameWidth() {

		return width;

	}

	public int getGameHeight() {

		return height;

	}

	private Player[] popoutBox() {

		players = new Player[2];
		
		String[] names= {"General Buttnaked", "Major Screw-loose", "Commandant Tin-can", "Christopher Calculus", "Pharaoh Tutankamun"};
		
		String[] items = { "HUMAN VS HUMAN", "HUMAN VS AI", "AI VS AI"};
		JComboBox combo = new JComboBox(items);
		
		int randomName1 = new Random().nextInt(names.length);//(int)Math.random()*names.length;
		int randomName2 = new Random().nextInt(names.length);
		
		
		
		// set names for fields
		JTextField field1 = new JTextField(names[randomName1]);
		JTextField field2 = new JTextField(names[randomName2]);

		JPanel panel = new JPanel(new GridLayout(0, 1));
		panel.add(combo);
		panel.add(new JLabel("PLAYER 1 NAME"));
		panel.add(field1);
		panel.add(new JLabel("PLAYER 2 NAME"));
		panel.add(field2);
		
		
		int result = JOptionPane.showConfirmDialog(null, panel, "Test",
				JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
		if (result == JOptionPane.OK_OPTION) {
			System.out.println(combo.getSelectedItem() + " " + field1.getText()
					+ " " + field2.getText());
		} else {
		
			//sys
			System.out.println("Cancelled");
		}

			
			switch(combo.getSelectedIndex()){
			
			case 0:{
				
				// top field human 
				players[0] = new HumanPlayer(field1.getText());
				// bottom field human
				players[1] = new HumanPlayer(field2.getText());
				break;
			}
			
			case 1:{
				
			// HUMAN VS AI
				
				// top field AI 
				players[0] = new AIPlayer(field1.getText());
				// bottom field human
				players[1] = new HumanPlayer(field2.getText());
				break;
			}
			default:{
				// AI VS AI
				// top field AI 
				players[0] = new AIPlayer(field1.getText());
				// bottom field AI
				players[1] = new AIPlayer(field1.getText());
				break;
			}
			
			
				
			}
			
				
			System.out.println(players[0].getName());
			System.out.println(players[1].getName());
		
		return players;
	}

	public void run() {
		while(running){
			game.run();
			
		}
		
		
	}


	public synchronized void start() {
		if (running)
			return;
		running = true;
		thread = new Thread(this);
		thread.start();
		System.out.println(" Working ");
		
		

	}

	public synchronized void stop() {
		if (!running)
			return;
		running = false;
		try {
			thread.join();
		} catch (Exception e) {
			e.printStackTrace();
			// System.exit(0);
			new Launcher(0);
		}
	}

	public void tick() {

		

		
	}


}
