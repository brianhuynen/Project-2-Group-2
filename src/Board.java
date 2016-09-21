// TODO Change the board such that it just accepts an array of pieces instead of two arrays (as it is right now).
// Make sure to separate the game logic from the Piece and Board class and put it in the Game class.

public class Board {
	
	private Cell[][] board = new Cell[10][10];
	private Cell cell;
	
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
	
	public void placePiece(int[] coord, Piece piece){
		if(board[coord[0]][coord[1]] instanceof EmptyCell){
			OccupiedCell newState = new OccupiedCell(piece);
			board[coord[0]][coord[1]] = newState;
		}
	}
	
	public void removePiece(int[] coord){
		EmptyCell newState = new EmptyCell();
		board[coord[0]][coord[1]] = newState;
	}
	
	public void movePiece(int [] coord, int[] newCoord){	
		if(validMove(coord,newCoord)){
			Piece piece = board[coord[0]][coord[1]].getContent();
			removePiece(coord);
			board[newCoord[0]][newCoord[1]] = new OccupiedCell(piece);
			board[newCoord[0]][newCoord[1]].setContent(piece);
		}
	}
	
	public boolean validMove(int [] coord, int[] newCoord){
		if(board[newCoord[0]][newCoord[1]] instanceof ImpassableCell ||
				(newCoord[0]!= coord[0] && newCoord[1]!= coord[1])){
			return false;
		}
		
		
		else{
			Piece piece = board[coord[0]][coord[1]].getContent();
			int spaces;
			if(coord[0] == newCoord[0]){
				spaces = Math.abs(coord[1]-newCoord[1]);
			}
			else{
				spaces = Math.abs(coord[0]-newCoord[0]);				
			}
			
			if(board[newCoord[0]][newCoord[1]] instanceof EmptyCell && piece.validWalk(spaces)){
				
				return true;
			}
			else if(board[newCoord[0]][newCoord[1]] instanceof OccupiedCell && piece.validWalk(spaces)){
				Piece defensePiece = board[newCoord[0]][newCoord[1]].getContent();
				if(piece.win(defensePiece) == piece){
					return true;
				}
				else{
					removePiece(coord);
					return false;
				}
			}
			
			return false;
		}
	}
	public void printBoard(){
		for (int i = 0; i< boardData.length; i++){
			for (int j = 0; j< boardData[0].length; j++){
				if (board[i][j] instanceof ImpassableCell){
					System.out.print("I ");
				}
				else {
					if (board[i][j] instanceof EmptyCell){
				
					System.out.print("E ");
					}
				
					if(board[i][j] instanceof OccupiedCell){
					System.out.print("O");
					}
				}
			}
			System.out.println();
			
		}
		if(board[0][0] instanceof OccupiedCell){
			System.out.println("I AM WORKING!");
		}
	}
}
