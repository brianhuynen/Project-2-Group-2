// TODO Change the board such that it just accepts an array of pieces instead of two arrays (as it is right now).
// Make sure to separate the game logic from the Piece and Board class and put it in the Game class.

public class Board {
	
	private int[][] board = new int[10][10]; //Represents the board as values empty (0),
											 //occupied (1) or impassable (-1).
	private Piece[][] pieces = new Piece[10][10];
	private Piece piece;
	private Position pos;
	
	/*
	 * + - - - - - > y
	 * | 0 0 0 0 0
	 * | 0 0 0 0 0
	 * | 0 0 0 0 0
	 * | 0 0 0 0 0
	 * | 0 0 0 0 0
	 * v
	 * x
	 */
	
	
	public Board(){
		//hard coded board
		board = new int[][] {{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
						 	 { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
						 	 { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
						 	 { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
						 	 { 0, 0,-1,-1, 0, 0,-1,-1, 0, 0},
						 	 { 0, 0,-1,-1, 0, 0,-1,-1, 0, 0},
						 	 { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
						 	 { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
						 	 { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
						 	 { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}};
	}
	
	public void placePiece(Piece piece, Position pos){
		// If position is occupied, don't place.
		if(!isOccupied(pos) && isValid(pos)){
			place(piece, pos);
			System.out.println("Placed piece " + piece.getRank() + " on " + piece.getPosition().toString());
		}
		else
			System.out.println("You cannot place that here...");
	}
	private void place(Piece piece, Position pos){
		// places the piece
		pieces[pos.getX()][pos.getY()] = piece;
		set(1, pos);
	}
	
	public void movePiece(Piece piece, Position pos){
		
		// save old location
		Position old = piece.getPosition();
		if (isValid(pos) && !isOccupied(pos)){ // If the position you want to move the piece to is valid, set prev location empty and new location occupied
			piece.setPosition(pos);
			pieces[old.getX()][old.getY()] = null;
			pieces[pos.getX()][pos.getY()] = piece;
			set(0, old);
			set(1, pos);
			System.out.println("\nMoved piece "  + piece.getRank() + " to " + pos.toString());
		}
		else if(isValid(pos)){ // If there's anotherpiece, they fight (currently indifferent of player)
			int result = piece.compareTo(pieces[pos.getX()][pos.getY()]);
			System.out.println("\nPiece "  + piece.getRank() + " battled piece " + pieces[pos.getX()][pos.getY()].getRank() + " on " + pos.toString());
			if (result == -1){ // your piece lost
				pieces[old.getX()][old.getY()] = null;
				set(0, old);
				System.out.println("and lost.");
			}
			if (result == 0){ // tie
				pieces[old.getX()][old.getY()] = null;
				set(0, old);
				pieces[pos.getX()][pos.getY()] = null;
				set(0, pos);
				System.out.println("and tied.");
			}
			if (result == 1){ // win
				pieces[old.getX()][old.getY()] = null;
				set(0, old);
				piece.setPosition(pos);
				pieces[pos.getX()][pos.getY()] = piece;
				System.out.println("and won.");
			}
		}
	}
	
	public boolean isValid(Position pos){
		return getCoordinate(pos) != -1;
	}
	public boolean isOccupied(Position pos){
		return getCoordinate(pos) == 1;
	}
	private int getCoordinate(Position pos){
		return board[pos.getX()][pos.getY()];
	}
	
	public void set(int state, Position pos){
		if ( -1 <= state && state <= 1)
			
			board[pos.getX()][pos.getY()] = state;
	}
	
	public void printboard(){
		System.out.println();
		for (int i = 0; i < board.length; i++){
			for (int j = 0; j < board[0].length; j++){
				if (board[i][j] == -1)
					System.out.print("E ");
				else
					System.out.print(board[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println();
		for (int i = 0; i < board.length; i++){
			for (int j = 0; j < board[0].length; j++){
				if (pieces[i][j] != null)
					System.out.print(pieces[i][j].getRank() + " ");
				else
					System.out.print(0 + " ");
			}
			System.out.println();
		}
	}
}
