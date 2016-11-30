import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.*;

public class GUI {

	public static Game game;
	private static String[] playerTypeData = new String[2];
	
	public GUI(){
		Game game = new Game(playerTypeData);
		this.game = game;
	}
	
	final static int FRAME_WIDTH = 800;
	final static int FRAME_HEIGHT = 700;
	final int BOARD_WIDTH = 500;
	final int BOARD_HEIGHT = 500;
	public static JFrame frame;

	//creates frame where you select the players
	public void selectPlayerFrame(int width, int height){
		JFrame startFrame = new JFrame();
		this.frame = startFrame;
		startFrame.setSize(width, height);
		startFrame.setTitle("Stratego");
		startFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel selectPlayer = selectPlayer();
		startFrame.add(selectPlayer, BorderLayout.CENTER);
		startFrame.setVisible(true);
	}
	 
	 //Creates the frame where the player can place its pieces
	public static void createStartFrame (JFrame startFrame){
			startFrame.dispose();
			frame = new JFrame();
			//this.frame = frame;
			frame.setSize(800, 800);
			frame.setTitle("Stratego");
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			JPanel inputPanel = inputPanel_1();
			GridComponent grid = new GridComponent();
			frame.add(grid);
			JPanel piece = piecePanel();
			frame.add(inputPanel, BorderLayout.EAST);
			frame.add(piece, BorderLayout.SOUTH);
			frame.setVisible(true);
			 
		}
	//Creates frame where you play the game
	
	public static void createGamePanel(JFrame frame1){
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
	public static JPanel selectPlayer(){
		JPanel selectPlayer = new JPanel();
		JLabel label = new JLabel("Select type of player:");
		JButton startGame = new JButton("Start Game");
		JLabel player1 = new JLabel("player 1");
		JLabel player2 = new JLabel("player 2");
		String[] players1 = {"HumanPlayer", "AIPlayer"};
		final JComboBox<String> cb1 = new JComboBox<String>(players1);
		String[] players2 = {"HumanPlayer", "AIPlayer"};
		final JComboBox<String> cb2 = new JComboBox<String>(players2);
		playerTypeData[0] = (String) cb1.getSelectedItem();
		playerTypeData[1] = (String) cb2.getSelectedItem();
		startGame.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				createStartFrame(frame);
				}
		});{

		}
		//FlowLayout layout = new FlowLayout();
		//selectPlayer.setLayout(layout);
		selectPlayer.add(label);
		selectPlayer.add(player1);
		selectPlayer.add(cb1);
		selectPlayer.add(player2);
		selectPlayer.add(cb2);
		selectPlayer.add(startGame);
		return selectPlayer;
	}

	//Panel that shows the rank of pieces and how many are left to be placed
	// Need adjustments!
	
	public static JPanel piecePanel(){
		String rank ="";
		JPanel piecePanel = new JPanel();
		for(int i=0; i<12; i++){
			if(i!= 0 && i!=10 && i!=11){
				rank = Integer.toString(i);
			}
			else{
				if(i==0){
				 	rank = "F";
				}
				else if (i==10) {
					rank = "M";
				}
				else{
					rank = "B";
				}
			}
			JLabel label = new JLabel("Piece " + rank +" : "+ game.currentPlayer.pieces[i]);
			// new Jlabel how many left of the piece type
			piecePanel.add(label);
			
		}
		return piecePanel;
	}
	//Creates inputpanel for placing pieces on board
	public static JPanel inputPanel_1(){
		JPanel control = new JPanel();
		control.setLayout(new BoxLayout(control,BoxLayout.Y_AXIS));
		JButton autofill = new JButton("Autofill");
		JButton add = new JButton("Add");
		JLabel rankPiece = new JLabel("Rank");
		JTextField rank = new JTextField();
		JLabel xPos = new JLabel("X");
		 JTextField xControl = new JTextField();
		JLabel yPos = new JLabel("Y");
		 JTextField yControl = new JTextField();
		JButton done = new JButton("Done");
		autofill.addActionListener(new ActionListener(){

			public void actionPerformed(java.awt.event.ActionEvent arg0){
				//Hardcoded piece placement of player 1
				for(int i = 0; i<playerTypeData.length; i++) {
					if (game.playerTypeData[i] == "AIPlayer") {
						autofill();
					}
					else{
						//what to do here?
					}
				}
			}
		});
		done.addActionListener(new ActionListener(){
			
			//Check if all pieces have been placed

			public void actionPerformed(java.awt.event.ActionEvent arg0) {
				if(game.getCurrentPlayer().piecesIsEmpty()){
					if(game.currentPlayer_ID == 1){
						game.currentPlayer_ID = 2;
						game.currentPlayer = game.player_2;
						frame.repaint();
					}
					else{
						game.currentPlayer_ID = 1;
						game.currentPlayer = game.player_1;
						createGamePanel(frame);
					}
					
				}
				else{
					//dialog box "place all your pieces"
					JOptionPane.showMessageDialog(frame, "Place all your pieces");

				}
		}
			
		});
		xControl.setMaximumSize( 
			     new Dimension(Integer.MAX_VALUE, xControl.getPreferredSize().height) );
		yControl.setMaximumSize( 
			     new Dimension(Integer.MAX_VALUE, yControl.getPreferredSize().height) );
		rank.setMaximumSize(new Dimension(Integer.MAX_VALUE,rank.getPreferredSize().height));
		add.addActionListener(new ActionListener(){
			

			public void actionPerformed(java.awt.event.ActionEvent arg0) {
				int x = Integer.parseInt(xControl.getText());
				int y = Integer.parseInt(yControl.getText());
				int r = Integer.parseInt(rank.getText());
				if(game.getCurrentPlayer().pieceIsAvailable(r)){
					Pieces piece = new Pieces(r," ",game.currentPlayer_ID);
					game.addPiece(x, y, piece);
					if(game.success){
						game.getCurrentPlayer().pieces[r]--;
						frame.repaint();
						
					}
					else{
						if(!game.getCurrentPlayer().pieceIsAvailable(r)){
							JOptionPane.showMessageDialog(frame, "Piece is not available");
						}
						else
							JOptionPane.showMessageDialog(frame, "Invalid placement");
					}
				}
				xControl.setText("");
				yControl.setText("");
				rank.setText("");

			}
			
		});
		JButton remove = new JButton("Remove");
		remove.addActionListener(new ActionListener(){

			public void actionPerformed(java.awt.event.ActionEvent e) {

				int x = Integer.parseInt(xControl.getText());
				int y = Integer.parseInt(yControl.getText());
				
				if(game.board[x][y].getContent()!= null){
				 int r = game.board[x][y].getContent().getRank();
				 game.removePiece(x, y);
				 if(game.success){
					 game.getCurrentPlayer().pieces[r]++;
						frame.repaint();
				  }
				}
				
			
				else{
					
					//give error "unable to remove piece"
					if(game.board[x][y].getContent()== null){
					JOptionPane.showMessageDialog(frame,"There's no piece");
					}
					else{
						JOptionPane.showMessageDialog(frame,"Unable to remove opponents piece" );
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
		control.add(done,BorderLayout.SOUTH);
		return control;
	}
	//Creates input panel for the game play
	
	public static void autofill() {
		final int[][] PLAYER1 = {{1, 3, 5, 11, 0, 5, 11, 9, 8, 7},
				{3, 4, 4, 11, 11, 11, 11, 8, 7, 7},
				{3, 4, 4, 5, 5, 2, 6, 6, 6, 6},
				{2, 2, 2, 2, 3, 10, 2, 2, 2, 3}};
		//Hardcoded piece placement of player 2
		final int[][] PLAYER2 = {{3, 2, 2, 2, 11, 1, 2, 2, 2, 3},
				{5, 5, 4, 4, 4, 4, 3, 3, 3, 2},
				{5, 5, 6, 11, 11, 2, 11, 7, 8, 10},
				{6, 6, 6, 11, 0, 7, 11, 7, 8, 9}};
		if (game.currentPlayer_ID == 1) {
			for (int y = 0; y < PLAYER1[0].length; y++) {
				for (int x = 0; x < PLAYER1.length; x++) {
					int r = PLAYER1[x][y];
					if (game.getCurrentPlayer().pieceIsAvailable(r)) {
						Pieces piece = new Pieces(r, " ", game.currentPlayer_ID);
						game.addPiece(y + 1, x + 1, piece);
						if (game.success) {
							game.getCurrentPlayer().pieces[r]--;
							frame.repaint();
						}
					}
				}
			}
		} else {
			for (int x = 0; x < PLAYER2.length; x++) {
				for (int y = 0; y < PLAYER2[0].length; y++) {
					int r = PLAYER2[x][y];
					if (game.getCurrentPlayer().pieceIsAvailable(r)) {
						Pieces piece = new Pieces(r, " ", game.currentPlayer_ID);
						game.addPiece(y + 1, x + 7, piece);
						if (game.success) {
							game.getCurrentPlayer().pieces[r]--;
							frame.repaint();
						}
					}
				}
			}
		}
	}

	public static JPanel inputPanel_2(){
		JPanel inputPanel = new JPanel();
		JTextField x1 = new JTextField(2);
		JLabel from_X = new JLabel("From X");
		JTextField y1 = new JTextField(2);
		JLabel from_Y = new JLabel("From Y");
		JTextField y2 = new JTextField(2);
		JLabel to_Y = new JLabel("To Y");
		JTextField x2 = new JTextField(2);
		JLabel to_X = new JLabel("To X");
		JButton move = new JButton("Move");
		JButton ranMove = new JButton("Random");
		move.addActionListener( new ActionListener(){
		
			public void actionPerformed(java.awt.event.ActionEvent e) {
				//System.out.println("Clicked!");
				int fromX = Integer.parseInt(x1.getText());
				int fromY = Integer.parseInt(y1.getText());
				int toX = Integer.parseInt(x2.getText());
				int toY = Integer.parseInt(y2.getText());
				game.movePiece(fromX,fromY,toX,toY);
				if(game.success){
					if(game.battled  && !game.gameOver){
						JOptionPane.showMessageDialog(frame, "battled");
					}
					else if(game.battled && game.gameOver){
						JOptionPane.showMessageDialog(frame, "Game Over");
						//create new start frame and refresh
					}
					game.changeTurn();
					
					frame.repaint();
				}
				
				else{
					if(game.board[fromX][fromY].getCellState()!=1){
						JOptionPane.showMessageDialog(frame, "Empty cell");
					}
					else if(game.board[fromX][fromY].getContent().getPlayer_ID()!= game.currentPlayer_ID){
						JOptionPane.showMessageDialog(frame, "Unable to move opponents piece");
					}
					else
						JOptionPane.showMessageDialog(frame, "Invalid move");
				}
				x1.setText("");
				y1.setText("");
				x2.setText("");
				y2.setText("");
			}
		});
		ranMove.addActionListener( new ActionListener(){
			public void actionPerformed(java.awt.event.ActionEvent e){
				for(int i = 0; i<1; i++) {
					game.ranMovePiece();
					if(game.success) {
						frame.repaint();
						game.changeTurn();
						//JOptionPane.showMessageDialog(frame, "Turn");
					}
				}
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
		inputPanel.add(ranMove);
		return inputPanel;
	}


	//12 = board height and width
	public static class GridComponent extends JComponent{


	//should go through the board and draw the numbers corresponding to the rank
	// pieces should be color coded to player.
	public void paintComponent(Graphics g){
		Graphics2D g2 = (Graphics2D) g;
		g2.setColor(Color.BLACK);
		for(int i=0; i<12; i++){
			for(int j=0; j<12; j++){
		Rectangle box = new Rectangle(50+(50*i),50+(50*j),50,50);
			g2.setStroke(new BasicStroke(3));
			g2.draw(box);
			if(game.board[i][j].getCellState()== -1){
				g2.fill(box);
			}
			if(game.board[i][j].getCellState()== 1 && game.currentPlayer_ID == game.board[i][j].getContent().getPlayer_ID() ){
				int x = (50*i)+ 60;
				int y = (50*j)+ 100;
				int rank = game.board[i][j].getContent().getRank();
				String s ="";
				if(rank != 0 && rank!= 11 && rank!=10){
					s = Integer.toString(rank);
				}
				if(rank == 0){
					s = "F";
				}

				if(rank == 10){
					s = "M";
				}
				else if(rank == 11){
					s = "B";
				}

					g2.setColor(game.currentPlayer.pColor);

				g.setFont(new Font("default", Font.BOLD, 50));
				if(rank ==10){
					//X-10 to set M in middle of cell
					g2.drawString(s, x-10, y);
				} 
				else {
					g2.drawString(s, x, y);
				}
					g2.setColor(Color.black);
			}
			if(game.board[i][j].getCellState()== 1 && game.currentPlayer_ID != game.board[i][j].getContent().getPlayer_ID() ){
				if(game.currentPlayer_ID == 1){
					g2.setColor(Color.RED);
				}
				else{
					g2.setColor(Color.BLUE);
				}
				Rectangle box1 = new Rectangle(51+(50*i),51+(50*j),49,49);
				g2.fill(box1);
				g2.setColor(Color.black);
			}
			
			}
		}
	}

	}

	public static void sleep(int ms){
		try {
			Thread.sleep(ms);
		} catch(InterruptedException e){
			Thread.currentThread().interrupt();
		}
	}



}