
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.awt.event.ActionEvent;
import javax.swing.*;

public class GUI {

	public static Game game;
	public static String[] playerTypeData = new String[2];

	public GUI() {
		// System.out.println(playerTypeData[0] + " " + playerTypeData[1]);
		// Game game = new Game(playerTypeData);
		// this.game = game;
	}

	// ***
	final static int FRAME_WIDTH = 800;
	final static int FRAME_HEIGHT = 700;
	final int BOARD_WIDTH = 500;
	final int BOARD_HEIGHT = 500;
	public static JFrame frame;

	/**
	 * Creates frame to select player-types
	 * 
	 * @param width
	 *            the width of the frame
	 * @param height
	 *            the height of the frame
	 */
	public void selectPlayerFrame(int width, int height) {
		JFrame startFrame = new JFrame();
		this.frame = startFrame;
		startFrame.setSize(width, height);
		startFrame.setTitle("Stratego");
		startFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel selectPlayer = selectPlayer();
		startFrame.add(selectPlayer, BorderLayout.CENTER);
		startFrame.setVisible(true);
	}

	/**
	 * Creates panel to choose players from
	 * 
	 * @return panel with buttons etc
	 */
	public static JPanel selectPlayer() {
		JPanel selectPlayer = new JPanel();
		JLabel label = new JLabel("Select type of player:");
		JButton startGame = new JButton("Start Game");
		JLabel player1 = new JLabel("player 1");
		JLabel player2 = new JLabel("player 2");
		String[] players1 = { "HumanPlayer", "AIPlayer", "MCTS" };
		final JComboBox<String> cb1 = new JComboBox<String>(players1);
		String[] players2 = { "HumanPlayer", "AIPlayer", "MCTS" };
		final JComboBox<String> cb2 = new JComboBox<String>(players2);

		startGame.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				System.out.println("Selected:" + cb1.getSelectedItem() + " " + cb2.getSelectedItem());

				playerTypeData[0] = (String) cb1.getSelectedItem();
				// game.player_1.setType(playerTypeData[0]);
				playerTypeData[1] = (String) cb2.getSelectedItem();
				// game.player_2.setType(playerTypeData[1]);

				game = new Game(playerTypeData);

				// System.out.println(playerTypeData[0] + " " +
				// playerTypeData[1]);
				if ((playerTypeData[1] == "AIPlayer" && playerTypeData[0] == "AIPlayer")
						|| (playerTypeData[1] == "MCTS" && playerTypeData[0] == "MCTS")
						|| (playerTypeData[1] == "AIPlayer" && playerTypeData[0] == "MCTS")
						|| (playerTypeData[1] == "MCTS" && playerTypeData[0] == "AIPlayer")) {
					createStartFrame(frame);
					game.autofill();
					game.changeTurn();
					game.autofill();
					game.changeTurn();
					createGamePanel(frame);
					gameLoop();
				}

				else if (playerTypeData[0] == "AIPlayer") {
					createStartFrame(frame);
					game.autofill();
					game.changeTurn();
				} else {
					createStartFrame(frame);
				}

			}
		});

		// FlowLayout layout = new FlowLayout();
		// selectPlayer.setLayout(layout);
		selectPlayer.add(label);
		selectPlayer.add(player1);
		selectPlayer.add(cb1);
		selectPlayer.add(player2);
		selectPlayer.add(cb2);
		selectPlayer.add(startGame);
		return selectPlayer;
	}

	/**
	 * Creates frame to place pieces
	 * 
	 * @param startFrame
	 *            current frame
	 */
	public static void createStartFrame(JFrame startFrame) {
		startFrame.dispose();
		frame = new JFrame();
		// this.frame = frame;
		frame.setSize(800, 800);
		frame.setTitle("Stratego");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel inputPanel = inputPanel_1();
		GridComponent grid = new GridComponent();
		frame.add(grid);
		// JPanel piece = piecePanel();
		frame.add(inputPanel, BorderLayout.EAST);
		// frame.add(piece, BorderLayout.SOUTH);
		frame.setVisible(true);
	}

	/**
	 * Adds component and panel to the game frame
	 * 
	 * @param frame1
	 *            current frame
	 */

	public static void createGamePanel(JFrame frame1) {
		game.gameActive = true;
		frame1.dispose();
		frame = new JFrame();
		frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
		frame.setTitle("Stratego");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		GridComponent grid = new GridComponent();
		frame.add(grid);
		JPanel input = inputPanel_2();
		frame.add(input, BorderLayout.SOUTH);
		frame.setVisible(true);
	}

	/**
	 * Creates panel that shows pieces left to place
	 * 
	 * @return panel
	 */
	/*
	 * 
	 * 
	 * /** Creates panel with buttons for placing/removing pieces on the board
	 * 
	 * @return panel
	 */
	// @michael Textfield made static
	private static JTextField xControl = new JTextField();
	private static JTextField rank = new JTextField();
	private static JTextField yControl = new JTextField();

	public static JPanel inputPanel_1() {
		JPanel control = new JPanel();
		control.setLayout(new BoxLayout(control, BoxLayout.Y_AXIS));
		JButton autofill = new JButton("Autofill");
		JButton add = new JButton("Add");
		JLabel rankPiece = new JLabel("Rank");

		JLabel xPos = new JLabel("X");

		JLabel yPos = new JLabel("Y");

		JButton done = new JButton("Done");
		autofill.addActionListener(new ActionListener() {

			public void actionPerformed(java.awt.event.ActionEvent arg0) {
				// game.clearPieces(); //Not sure whether necessary.
				game.autofill();
			}
		});
		done.addActionListener(new ActionListener() {

			// Check if all pieces have been placed

			public void actionPerformed(java.awt.event.ActionEvent arg0) {
				if (game.getCurrentPlayer().piecesIsEmpty()) {
					if (game.currentPlayer_ID == 1) {
						game.currentPlayer_ID = 2;
						game.currentPlayer = game.player_2;
						frame.repaint();
						if (playerTypeData[0] == "HumanPlayer"
								&& ((playerTypeData[1] == "AIPlayer") || (playerTypeData[1] == "MCTS"))) {
							game.autofill();
							game.changeTurn();
							createGamePanel(frame);
							gameLoop();
						}
					} else {
						game.currentPlayer_ID = 1;
						game.currentPlayer = game.player_1;
						createGamePanel(frame);
						gameLoop();

					}

				} else {
					// dialog box "place all your pieces"
					JOptionPane.showMessageDialog(frame, "Place all your pieces");

				}
			}

		});
		xControl.setMaximumSize(new Dimension(Integer.MAX_VALUE, xControl.getPreferredSize().height));
		yControl.setMaximumSize(new Dimension(Integer.MAX_VALUE, yControl.getPreferredSize().height));
		rank.setMaximumSize(new Dimension(Integer.MAX_VALUE, rank.getPreferredSize().height));
		add.addActionListener(new ActionListener() {

			public void actionPerformed(java.awt.event.ActionEvent arg0) {
				int x = Integer.parseInt(xControl.getText());
				int y = Integer.parseInt(yControl.getText());
				int r = Integer.parseInt(rank.getText());
				if (game.getCurrentPlayer().pieceIsAvailable(r)) {
					Pieces piece = new Pieces(r, " ", game.currentPlayer_ID);
					game.addPiece(x, y, piece);
					if (game.success) {
						game.getCurrentPlayer().pieces[r]--;
						frame.repaint();

					} else {
						if (!game.getCurrentPlayer().pieceIsAvailable(r)) {
							JOptionPane.showMessageDialog(frame, "Piece is not available");
						} else
							JOptionPane.showMessageDialog(frame, "Invalid placement");
					}
				}
				xControl.setText("");
				yControl.setText("");
				rank.setText("");

			}

		});
		JButton remove = new JButton("Remove");
		remove.addActionListener(new ActionListener() {

			public void actionPerformed(java.awt.event.ActionEvent e) {

				int x = Integer.parseInt(xControl.getText());
				int y = Integer.parseInt(yControl.getText());

				if (game.board[x][y].getContent() != null) {
					int r = game.board[x][y].getContent().getRank();
					game.removePiece(x, y);
					if (game.success) {
						game.getCurrentPlayer().pieces[r]++;
						frame.repaint();
					}
				}

				else {

					// give error "unable to remove piece"
					if (game.board[x][y].getContent() == null) {
						JOptionPane.showMessageDialog(frame, "There's no piece");
					} else {
						JOptionPane.showMessageDialog(frame, "Unable to remove opponents piece");
					}
				}

				xControl.setText("");
				yControl.setText("");
				rank.setText("");

			}

		});

		control.add(xPos);
		control.add(xControl);
		control.add(yPos);
		control.add(yControl);
		control.add(rankPiece);
		control.add(rank);
		control.add(add);
		control.add(autofill);
		control.add(remove);
		control.add(done, BorderLayout.SOUTH);
		return control;
	}

	/**
	 * Creates panel with buttons and JText to move pieces on the board
	 * 
	 * @return panel
	 */
	// @michael made Jtextfield static
	private static JTextField x1 = new JTextField(2);
	private static JTextField y1 = new JTextField(2);
	private static JTextField x2 = new JTextField(2);
	private static JTextField y2 = new JTextField(2);

	public static JPanel inputPanel_2() {
		JPanel inputPanel = new JPanel();

		JLabel from_X = new JLabel("From X");

		JLabel from_Y = new JLabel("From Y");

		JLabel to_Y = new JLabel("To Y");

		JLabel to_X = new JLabel("To X");
		JButton move = new JButton("Move");
		JButton findPath = new JButton("Find Path");
		move.addActionListener(new ActionListener() {

			public void actionPerformed(java.awt.event.ActionEvent e) {
				// System.out.println("Clicked!");
				int fromX = Integer.parseInt(x1.getText());
				int fromY = Integer.parseInt(y1.getText());
				int toX = Integer.parseInt(x2.getText());
				int toY = Integer.parseInt(y2.getText());
				game.movePiece(fromX, fromY, toX, toY);
				if (game.success) {
					if (game.battled && !game.gameOver) {
						JOptionPane.showMessageDialog(frame, "battled");
					} else if (game.battled && game.gameOver) {
						JOptionPane.showMessageDialog(frame, "Game Over");
						// create new start frame and refresh
					}
					game.changeTurn();

					frame.repaint();
					gameLoop();
				}

				else {
					if (game.board[fromX][fromY].getCellState() != 1) {
						JOptionPane.showMessageDialog(frame, "Empty cell");
					} else if (game.board[fromX][fromY].getContent().getPlayer_ID() != game.currentPlayer_ID) {
						JOptionPane.showMessageDialog(frame, "Unable to move opponents piece");
					} else
						JOptionPane.showMessageDialog(frame, "Invalid move");
				}
				x1.setText("");
				y1.setText("");
				x2.setText("");
				y2.setText("");
			}
		});
		findPath.addActionListener(new ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent e) {

//				game.path.clear();
//
//				int fromX = Integer.parseInt(x1.getText());
//				int fromY = Integer.parseInt(y1.getText());
//				int toX = Integer.parseInt(x2.getText());
//				int toY = Integer.parseInt(y2.getText());
				
				Pathfinding astar = new Pathfinding(game.board, game.currentPlayer.getPlayer_ID());
				astar.aStar(game.board[4][7], game.board[10][6]);
				
				// for(int i = 0; i<1; i++) {
				// RandomAlg rand = new RandomAlg(game, game.currentPlayer);
				// rand.randomMove();
//				try {
//					// game.findPath(fromX, fromY, toX, toY);
//				} catch (StackOverflowError err) {
//					System.out.print("\nfail... ");
//				}
//				System.out.println("\ndone");
//				System.out.println(game.path.size());
//				game.printPath();

				// int[] bposition = {9,10};
				// rand.makeFinalMove(bposition);
				// game.ranMovePiece();
				// MCTS mcts = new MCTS();
				// Node node = new Node(game);
				// mcts.playout(node, game, rand);
				// if(game.success) {
				// frame.repaint();
				// game.changeTurn();
				// gameLoop();
				// //JOptionPane.showMessageDialog(frame, "Turn");
				// }
				// }
			}
		});

		inputPanel.add(from_X);
		inputPanel.add(x1);
		inputPanel.add(from_Y);
		inputPanel.add(y1);
		inputPanel.add(to_X);
		inputPanel.add(x2);
		inputPanel.add(to_Y);
		inputPanel.add(y2);
		inputPanel.add(move);
		inputPanel.add(findPath);
		return inputPanel;
	}

	/**
	 * Draw board component
	 *
	 */
	// 12 = board height and width
	public static class GridComponent extends JComponent {

		// should go through the board and draw the numbers corresponding to the
		// rank
		// pieces should be color coded to player.
		public void paintComponent(Graphics g) {
			Graphics2D g2 = (Graphics2D) g;
			g2.setColor(Color.BLACK);
			for (int i = 0; i < 12; i++) {
				for (int j = 0; j < 12; j++) {
					Rectangle box = new Rectangle(50 + (50 * i), 50 + (50 * j), 50, 50);
					g2.setStroke(new BasicStroke(3));
					g2.draw(box);
					if (game.board[i][j].getCellState() == -1) {
						g2.fill(box);
					}
					if (game.board[i][j].getCellState() == 1
							&& game.currentPlayer_ID == game.board[i][j].getContent().getPlayer_ID()) {
						int x = (50 * i) + 60;
						int y = (50 * j) + 100;
						int rank = game.board[i][j].getContent().getRank();
						String s = "";
						if (rank != 0 && rank != 11 && rank != 10) {
							s = Integer.toString(rank);
						}
						if (rank == 0) {
							s = "F";
						}

						if (rank == 10) {
							s = "M";
						} else if (rank == 11) {
							s = "B";
						}

						g2.setColor(game.currentPlayer.pColor);

						g.setFont(new Font("default", Font.BOLD, 50));
						if (rank == 10) {
							// X-10 to set M in middle of cell
							g2.drawString(s, x - 10, y);
						} else {
							g2.drawString(s, x, y);
						}
						g2.setColor(Color.black);
					}
					if (game.board[i][j].getCellState() == 1
							&& game.currentPlayer_ID != game.board[i][j].getContent().getPlayer_ID()) {
						if (game.currentPlayer_ID == 1) {
							g2.setColor(Color.RED);
						} else {
							g2.setColor(Color.BLUE);
						}
						Rectangle box1 = new Rectangle(51 + (50 * i), 51 + (50 * j), 49, 49);
						g2.fill(box1);
						g2.setColor(Color.black);
					}

				}
			}
		}

	}

	/**
	 * Each time this method is called, it interrupts the thread for ms
	 * milliseconds.
	 * 
	 * @param ms
	 *            For how long the thread will be interrupted in milliseconds.
	 */
	public static void sleep(int ms) {
		try {
			Thread.sleep(ms);
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
	}

	public static void gameLoop() {
		if ((playerTypeData[0] == playerTypeData[1])
				&& ((playerTypeData[0] == "AIPlayer") || (playerTypeData[0] == "MCTS"))) {
			while (!game.gameOver && game.findMovableCoords(game.currentPlayer).size() != 0 && game.gameActive) {
if(playerTypeData[0] == "AIPlayer"){
				RandomAlg rand = new RandomAlg(game, game.currentPlayer);
				rand.randomMove();
				// rand.generateMovementHeur();
}
if(playerTypeData[0] == "MCTS"){
	 MCTS mcts= new MCTS(game, game.currentPlayer);
     
		mcts.setExplorationConstant(0.4);
		mcts.setTimeDisplay(true);
		Move move;
		mcts.setOptimisticBias(0.0d);
		mcts.setPessimisticBias(0.0d);
		mcts.setMoveSelectionPolicy(FinalSelectionPolicy.robustChild);
		int []scores = new int[3];
		while (!game.gameOver()){
				move = mcts.runMCTS(game, 1000, false);
				game.makeMove(move);
		}
			
			
			double []scr = game.getScore();
			if (scr[0] == 1.0) {
				scores[0]++; // player 1
			} else if (scr[1]==1.0) {
				scores[1]++; // player 2
			} else
				scores[2]++; // draw
			
			System.out.println(Arrays.toString(scr));
			System.out.println(Arrays.toString(scores));
}
				frame.repaint();
				frame.paint(frame.getGraphics());
				sleep(0);
				game.changeTurn();

				// gameLoop();

			}
			JOptionPane.showMessageDialog(frame, "END");
		}

		else if (playerTypeData[0] != playerTypeData[1]) {
			while (!game.gameOver && game.findMovableCoords(game.currentPlayer).size() != 0
					&& ((game.currentPlayer.getType() == "AIPlayer") || (game.currentPlayer.getType() == "MCTS"))) {
				if(playerTypeData[0] == "AIPlayer"){
					RandomAlg rand = new RandomAlg(game, game.currentPlayer);
					rand.randomMove();
					// rand.generateMovementHeur();
				}
				if(playerTypeData[0] == "MCTS"){
					MCTS mcts= new MCTS(game,game.currentPlayer);
	     
					mcts.setExplorationConstant(0.4);
					mcts.setTimeDisplay(true);
					Move move;
					mcts.setOptimisticBias(0.0d);
					mcts.setPessimisticBias(0.0d);
					mcts.setMoveSelectionPolicy(FinalSelectionPolicy.robustChild);
					int []scores = new int[3];
					while (!game.gameOver()){
						move = mcts.runMCTS(game, 1000, false);
						game.makeMove(move);
					}
				
				
				double []scr = game.getScore();
				if (scr[0] == 1.0) {
					scores[0]++; // player 1
				} else if (scr[1]==1.0) {
					scores[1]++; // player 2
				} else
					scores[2]++; // draw
				
				System.out.println(Arrays.toString(scr));
				System.out.println(Arrays.toString(scores));
	}
				game.changeTurn();
				frame.repaint();
			}
		}
	}

}
