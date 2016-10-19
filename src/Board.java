// TODO Change the board such that it just accepts an array of pieces instead of two arrays (as it is right now).
// Make sure to separate the game logic from the Piece and Board class and put it in the Game class.

public class Board {
	private int s;
	private int e;
	private Cell[][] board = new Cell[10][10];
	// Final integers for movement handling
	final int FLAG = 2, LOSS = -1, DRAW = 0, WIN = 1,  //for the battle handling
			CAN_MOVE = 1, CANNOT_MOVE = -1, END = 0,   //for the move handling
			LOST_PIECE = -2, VALID = 1, INVALID = -1;  //for the move validation
	
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
	
	public void placePlayerPieces(Player[] players){
		for (int n = 0; n<players.length; n++){
			for (int i = 0; i<players[n].getPieces().length; i++){
				placePiece(players[n].getPieces()[i].getPosition(), players[n].getPieces()[i]);
			}
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
	//Returns the name of the player to which the piece belongs
	public String getPID(int x, int y){
		if(board[x][y] instanceof OccupiedCell){
			return board[x][y].getContent().getPID().getName();
		}
		return null;
	}
	// The movement handler
	public int handleMovement(Position p, Position newP){
		if (validMove(p, newP) == FLAG){
			return END;
		}
		if (validMove(p, newP) == VALID){
			Piece piece =  board[p.getX()][p.getY()].getContent();
			removePiece(p);
			OccupiedCell newState = new OccupiedCell(piece);
			board[newP.getX()][newP.getY()] = newState;		
			
			System.out.println("Moved (" + p.getX() + "," + p.getY() + ") to (" 
					+ newP.getX() + "," + newP.getY() + ")\n");
			
			return CAN_MOVE;
		}
		else if (validMove(p,newP) == DRAW){
			removePiece(p);
			removePiece(newP);		
			
			System.out.println("Moved (" + p.getX() + "," + p.getY() + ") to (" 
					+ newP.getX() + "," + newP.getY() + ") and battled with result draw.\n");
			
			return CAN_MOVE;
		}
		else if (validMove(p,newP) == LOST_PIECE){
			removePiece(p);		
			
			System.out.println("Moved (" + p.getX() + "," + p.getY() + ") to (" 
					+ newP.getX() + "," + newP.getY() + ") and battled with result loss.\n");
			
			return CAN_MOVE;
		}
		return CANNOT_MOVE;
	}
	//The movement validator.
	public int validMove(Position p, Position newP){
		if(checkIfSamePosition(p,newP)){
			return INVALID;
		}
		if(checkIfImpassable(newP)){
			return INVALID;
		}
		if(pieceIsMovable(p)){
			if (isEmpty(newP) && validMovement(p,newP)){
					return VALID;
				}
			
			if(checkIfBattleEnsues(p,newP)){
		
				Piece offensePiece = board[p.getX()][p.getY()].getContent();
				Piece defensePiece = board[newP.getX()][newP.getY()].getContent();
				
				if(handleBattle(offensePiece, defensePiece) == WIN)
					return VALID;
				if(handleBattle(offensePiece, defensePiece) == DRAW){
					return DRAW;
				}
				if(handleBattle(offensePiece, defensePiece) == FLAG){
					return FLAG;
				}
				else { 
					return LOST_PIECE;
				}
			} 
		}
		return INVALID;
	}
	//The battle handler
	private int handleBattle(Piece offensePiece, Piece defensePiece){
		int offense = offensePiece.getRank();
		int defense = defensePiece.getRank();
		if (defense == 0){
			return FLAG;
		} else if((offense != 1 && defense != 10 || offense != 3 && defense != 11)){
			if (offense > defense)
				return WIN;
			else if (offense == defense)
				return DRAW;
			else 
				return LOSS;
		}
		return WIN;
	}
	private boolean checkIfImpassable(Position p){ return board[p.getX()][p.getY()] instanceof ImpassableCell; }
	private boolean checkIfSamePosition(Position p, Position newP){ return p.getX() == newP.getX() && p.getY() == newP.getY(); }
	private boolean checkIfBattleEnsues(Position p, Position newP){
		return board[newP.getX()][newP.getY()] instanceof OccupiedCell && 
				getPID(p.getX(),p.getY())!= getPID(newP.getX(),newP.getY());
	}
	private boolean pieceIsMovable(Position p){ return 0 < getContent(p).getRank() && getContent(p).getRank() < 11 ;}
	private boolean isEmpty(Position p){ return board[p.getX()][p.getY()] instanceof EmptyCell; }
	
	private boolean validMovement(Position p, Position newP){
		Piece piece = board[p.getX()][p.getY()].getContent();
		int distance = distance(p,newP);
		if(p.getX()!=newP.getX() && p.getY()!=newP.getY()){
			return false;
		}
		if (distance==1){
			return true;
		}
		
		if(distance>1 && piece.getRank()!=2){
			return false;
		}
		
		if(piece.getRank() == 2 && distance>1){
			if(p.getX()==newP.getX()){
				if(p.getY()>newP.getY()){
					s = newP.getY();
					 e = p.getY();
				}
				else{
					 s = p.getY();
					 e = newP.getY();
				}
				for(int i=s+1; i<e; i++){
					if(board[p.getX()][i] instanceof OccupiedCell){
						return false;
					}
				}
			}
			else{
				if(p.getX()>newP.getX()){
					s = newP.getX();
					 e = p.getX();
				}
				else{
					 s = p.getX();
					 e = newP.getX();
				}
				for(int i=s+1; i<e; i++){
					if(board[i][p.getY()] instanceof OccupiedCell){
						System.out.println("I AM");
						return false;
					}
				}

			}
		}
		return true;	
	}

	private int distance(Position p, Position newP){
		if(p.getX() == newP.getX()){
			return Math.abs(p.getY()-newP.getY());
		}
		return Math.abs(p.getX()-newP.getX());
	}
	
	public Piece getContent(Position p){
		if (board[p.getX()][p.getY()] instanceof OccupiedCell){
			return board[p.getX()][p.getY()].getContent();
		} else
			return null;
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
	public void printBoard(){
		for (int i = 0; i< boardData.length; i++){
			for (int j = 0; j< boardData[0].length; j++){
				if (board[i][j] instanceof ImpassableCell){
					System.out.print("[X] ");
				}
				else if (board[i][j] instanceof EmptyCell){
					System.out.print("[ ] ");
				}
				else if(board[i][j] instanceof OccupiedCell){
					Piece p = ((OccupiedCell) board[i][j]).getContent();
					if (0 < p.getRank() && p.getRank() < 10)
						System.out.print(p.getRank() + "," + p.getPID().getName() + " ");
					else if (p.getRank() == 0)
						System.out.print("F," + p.getPID().getName() + " ");
					else if (p.getRank() == 1)
						System.out.print("S," + p.getPID().getName() + " ");
					else if (p.getRank() == 10)
						System.out.print("M," + p.getPID().getName() + " ");
					else if (p.getRank() == 11)
						System.out.print("B," + p.getPID().getName() + " ");
				}
			}
			System.out.println();
		}
	}
}