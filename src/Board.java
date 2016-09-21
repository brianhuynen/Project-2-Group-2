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
	
	public void printBoard(){
		for (int i = 0; i< boardData.length; i++){
			for (int j = 0; j< boardData[0].length; j++){
				if (board[i][j] instanceof ImpassableCell){
					System.out.print("I ");
				}
				else if (board[i][j] instanceof EmptyCell){
					System.out.print("E ");
				}
			}
			System.out.println();
		}
	}
}
