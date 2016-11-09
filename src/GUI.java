import javax.swing.*;


import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.*;

public class GUI {

	public static Game game;
	public static int click = 0;
	
	
	public GUI(){
		Game game = new Game();
		this.game = game;
	}
	
	final static int FRAME_WIDTH = 800;
	final static int FRAME_HEIGHT = 700;
	final int BOARD_WIDTH = 500;
	final int BOARD_HEIGHT = 500;
	public static JFrame frame;
	
	
	 
	 //Creates the frame where the player can place its pieces
	public void createStartFrame (int width, int height){
			JFrame frame = new JFrame();
			this.frame = frame;
			frame.setSize(width, height);
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
				final int[][] PLAYER1 = {{ 1, 3, 5,11, 0, 5,11, 9, 8, 7},
						 				 { 3, 4, 4,11,11,11,11, 8, 7, 7},
						 				 { 3, 4, 4, 5, 5, 2, 6, 6, 6, 6},
						 				 { 2, 2, 2, 2, 3,10, 2, 2, 2, 3}};
				//Hardcoded piece placement of player 2
				final int[][] PLAYER2 = {{ 3, 2, 2, 2,11, 1, 2, 2, 2, 3},
							 			 { 5, 5, 4, 4, 4, 4, 3, 3, 3, 2},
							 			 { 5, 5, 6,11,11, 2,11, 7, 8,10},
							 			 { 6, 6, 6,11, 0, 7,11, 7, 8, 9}};
				if(game.currentPlayer_ID == 1){
					for (int y=0; y<PLAYER1[0].length; y++){
						for(int x=0; x<PLAYER1.length; x++){
							int r = PLAYER1[x][y];
							if(game.getCurrentPlayer().pieceIsAvailable(r)){
								Pieces piece = new Pieces(r," ",game.currentPlayer_ID);
								game.addPiece(y+1, x+1, piece);
								if(game.success){
									game.getCurrentPlayer().pieces[r]--;
									frame.repaint();
								}
							}
						}
					}
				} else {
					for (int x=0; x<PLAYER2.length; x++){
						for(int y=0; y<PLAYER2[0].length; y++){
							int r = PLAYER2[x][y];
							if(game.getCurrentPlayer().pieceIsAvailable(r)){
								Pieces piece = new Pieces(r," ",game.currentPlayer_ID);
								game.addPiece(y+1, x+7, piece);
								if(game.success){
									game.getCurrentPlayer().pieces[r]--;
									frame.repaint();
								}
							}
						}
					}
				}
				
			}
		});
		done.addActionListener(new ActionListener(){
			
			//Check if all pieces have been placed

			@Override
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
			
			//Need to check if piece is available

			@Override
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
						JOptionPane.showMessageDialog(frame, "Piece placed outside board");
					}
				}
				xControl.setText("");
				yControl.setText("");
				rank.setText("");

			}
			
		});
		JButton remove = new JButton("Remove");
		remove.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(java.awt.event.ActionEvent e) {

				int x = Integer.parseInt(xControl.getText());
				int y = Integer.parseInt(yControl.getText());
				int r = game.board[x][y].getContent().getRank();
				game.removePiece(x, y);
				if(game.success){
					game.getCurrentPlayer().pieces[r]++;
					frame.repaint();
				}
				else{
					JOptionPane.showMessageDialog(frame,"Unable to remove this piece" );
					//give error "unable to remove piece"
					JOptionPane.showMessageDialog(frame,"Unable to remove piece");
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
		move.addActionListener( new ActionListener(){
		
			@Override
			public void actionPerformed(java.awt.event.ActionEvent e) {
				//System.out.println("Clicked!");
				int fromX = Integer.parseInt(x1.getText());
				int fromY = Integer.parseInt(y1.getText());
				int toX = Integer.parseInt(x2.getText());
				int toY = Integer.parseInt(y2.getText());
				game.movePiece(fromX,fromY,toX,toY);
				if(game.success){
					game.changeTurn();
					
					frame.repaint();
				}
				x1.setText("");
				y1.setText("");
				x2.setText("");
				y2.setText("");
				else{
					JOptionPane.showMessageDialog(frame, "Invalid move");
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


}

	
