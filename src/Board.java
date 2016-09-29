// TODO Change the board such that it just accepts an array of pieces instead of two arrays (as it is right now).
// Make sure to separate the game logic from the Piece and Board class and put it in the Game class.

public class Board {
	
	private Cell[][] board = new Cell[10][10];
	
	final int[][] boardData = {{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
		 	 				   { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
		 	 				   { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
		 	 				   { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
		 	 				   { 0, 0,-1,-1, 0, 0,-1,-1, 0, 0},
		 	 				   { 0, 0,-1,-1, 0, 0,-1,-1, 0, 0},
		 	 				   { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
		 	 				   { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
		 	 				   { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
		 	 				   { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}};;
	
	public Board(){
		for (int i = 0; i< boardData.length; i++){
			for (int j = 0; j< boardData[0].length; j++){
				if (boardData[i][j] == -1)
					board[i][j] = new ImpassableCell();
				else
					board[i][j] = new EmptyCell();
			}
		}
	}
	
	public void placePlayerPieces(Piece[] pieces){
		for (int i = 0; i<pieces.length; i++){
			placePiece(pieces[i].getPosition(), pieces[i]);
		}
	}
	
	public void placePiece(Position p, Piece piece){
		if(board[p.getX()][p.getY()] instanceof EmptyCell){
			OccupiedCell newState = new OccupiedCell(piece);
			board[p.getX()][p.getY()] = newState;
		}
	}
	
	public void removePiece(Position p){
		EmptyCell newState = new EmptyCell();
		board[p.getX()][p.getY()] = newState;
	}
	
	public void movePiece(Position p, Position newP){	
		if(validMove(p, newP)){
			Piece piece =  board[p.getX()][p.getY()].getContent();
			removePiece(p);
			OccupiedCell newState = new OccupiedCell(piece);
			board[newP.getX()][newP.getY()] = newState;
		}
	}
	
	public boolean validMove(Position p, Position newP){
		if(board[newP.getX()][newP.getY()] instanceof ImpassableCell ||
				(newP.getX() != p.getX() && newP.getY() != p.getY())){
			return false;
		}
		
		
		else{
			Piece piece = board[p.getX()][p.getY()].getContent();
			int spaces;
			if(p.getX() == newP.getX()){
				spaces = Math.abs(p.getY()-newP.getY());
			}
			else{
				spaces = Math.abs(p.getX()-newP.getX());				
			}
			
			if(board[newP.getX()][newP.getY()] instanceof EmptyCell && piece.validWalk(spaces)){
				
				return true;
			}
			else if(board[newP.getX()][newP.getY()] instanceof OccupiedCell && piece.validWalk(spaces)){
				Piece defensePiece = board[newP.getX()][newP.getY()].getContent();
				if(piece.win(defensePiece) == piece){
					return true;
				}
				else{
					removePiece(p);
					return false;
				}
			}
			
			return false;
		}
	}
	
	public void resetBoard(){
		for (int i = 0; i< boardData.length; i++){
			for (int j = 0; j< boardData[0].length; j++){
				if (boardData[i][j] == -1)
					board[i][j] = new ImpassableCell();
				else
					board[i][j] = new EmptyCell();
			}
		}
	}
	
	public Piece getContent(Position p){
		if (board[p.getX()][p.getY()] instanceof OccupiedCell){
			return board[p.getX()][p.getY()].getContent();
		} else
			return null;
	}

	public void printBoard(){
		for (int i = 0; i< boardData.length; i++){
			for (int j = 0; j< boardData[0].length; j++){
				if (board[i][j] instanceof ImpassableCell){
					System.out.print("I ");
				}
				else if (board[i][j] instanceof EmptyCell){	
					System.out.print("E ");
//					System.out.print("  ");
				}
				else if(board[i][j] instanceof OccupiedCell){
					Piece p = ((OccupiedCell) board[i][j]).getContent();
					if (0 < p.getRank() && p.getRank() < 10)
						System.out.print(p.getRank() + " ");
					else if (p.getRank() == 0)
						System.out.print("F ");
					else if (p.getRank() == 10)
						System.out.print("M ");
					else if (p.getRank() == 11)
						System.out.print("B ");
				}
			}
			System.out.println();
		}
	}
}
