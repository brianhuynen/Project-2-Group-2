import javax.swing.*;


import java.awt.*;
import java.awt.event.ActionListener;

public class GUI {

	public static Game game;
	
	public GUI(){
		Game game = new Game();
		this.game = game;
	}
	
	final static int FRAME_WIDTH = 800;
	final static int FRAME_HEIGHT = 700;
	final int BOARD_WIDTH = 500;
	final int BOARD_HEIGHT = 500;
	public static JFrame frame;
	
	 public static void main(String[] args) {
		 
		 GUI gui = new GUI(); 
		 gui.createStartFrame(800,800);		 
		}
	 
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
			frame.add(piece,BorderLayout.SOUTH);
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
			if(i!= 0 && i!=11){
				rank = Integer.toString(i);
			}
			else{
				if(i==0){
				 rank = "F";
				}
				else{
				rank = "B";
				}
			}
			JLabel label = new JLabel(rank);
			// new Jlabel how many left of the piece type
			piecePanel.add(label);
			
		}
		return piecePanel;
	}
	//Creates inputpanel for placing pieces on board
	
	
	public static JPanel inputPanel_1(){
		JPanel control = new JPanel();
		control.setLayout(new BoxLayout(control,BoxLayout.Y_AXIS));
		JButton add = new JButton("Add");
		JLabel rankPiece = new JLabel("Rank");
		JTextField rank = new JTextField();
		JLabel xPos = new JLabel("X");
		JTextField xControl = new JTextField();
		JLabel yPos = new JLabel("Y");
		JTextField yControl = new JTextField();
		JButton done = new JButton("Done");
		done.addActionListener(new ActionListener(){
			
			//Check if all pieces have been placed

			@Override
			public void actionPerformed(java.awt.event.ActionEvent arg0) {
				if(game.currentPlayer ==1){
					game.currentPlayer = 2;
					frame.repaint();
				}
				else{
					game.currentPlayer = 1;
					createGamePanel(frame);
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
				Pieces piece = new Pieces(r," ",game.currentPlayer);
				game.addPiece(x, y, piece);
				frame.repaint();
				
				
			}
			
		});
		JButton remove = new JButton("Remove");
		remove.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(java.awt.event.ActionEvent e) {
				
				int x = Integer.parseInt(xControl.getText());
				int y = Integer.parseInt(yControl.getText());
				game.removePiece(x, y);
				frame.repaint();
				
			}
			
		});
		
		control.add(xPos);
		control.add(xControl);
		control.add(yPos);
		control.add(yControl);
		control.add(rankPiece);
		control.add(rank);
		control.add(add);
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
				int fromX = Integer.parseInt(x1.getText());
				int fromY = Integer.parseInt(y1.getText());
				int toX = Integer.parseInt(x2.getText());
				int toY = Integer.parseInt(y2.getText());
				game.movePiece(fromX,fromY,toX,toY);
				if(game.currentPlayer == 1){
					game.currentPlayer = 2;
				}
				else{
					game.currentPlayer = 1;
				}
				
				frame.repaint();
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
			if(game.board[i][j].getCellState()== 1 && game.currentPlayer == game.board[i][j].getContent().getPlayer_ID() ){
				int x = (50*i)+ 60;
				int y = (50*j)+ 100;
				g2.setColor(Color.BLUE);
				g.setFont(new Font("default", Font.BOLD, 60));
				g2.drawString(Integer.toString(game.board[i][j].getContent().getRank()),x,y);
				g2.setColor(Color.black);
			}
			if(game.board[i][j].getCellState()== 1 && game.currentPlayer != game.board[i][j].getContent().getPlayer_ID() ){
				g2.setColor(Color.RED);
				Rectangle box1 = new Rectangle(51+(50*i),51+(50*j),49,49);
				g2.fill(box1);
				g2.setColor(Color.black);
			}
			}
		}
	}

	}


}

	
