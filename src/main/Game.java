package main;

import java.awt.Color;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import MCTS.CallLocation;
import astar.Pathfinding;






public class Game {
	public boolean flagCaptured;
	/*	How coordinates work on the board:

		x = board.length
		y = board[0].length

		0 --------------> x
		| x x x x x x x x
		| x x x x x x x x
		| x x x x x x x x
		v x x x x x x x x
		y
	 */
	int turnCount = 0;
	public Cell[][] board;
	Field field;
	public Player currentPlayer;
	boolean success = false;
	int currentPlayer_ID;
	public Player player_1;
	public Player player_2;
	public boolean battled = false;
	Pieces won;
	Pieces lost;
	Player[] player = new Player[2];
	boolean gameOver;
	public boolean gameActive = false;
	RandomAlg rand;
	Pathfinding pathfinder;
//	LinkedList<int[]> path= new LinkedList<int[]>();
    ArrayList<Cell> path = new ArrayList<Cell>();

	int winner;
	boolean draw;
	boolean gameWon;
	int freecells;
	int turns;
	public boolean expectimax;
	public String[] playerTypeData;
	
	
	public Game(Player[] playerTypeData){
		player = playerTypeData;
		player_1 = player[0];
		player_2 = player[1];
		this.currentPlayer_ID = player_1.getPlayer_ID();
		Player currentPlayer = player_1;
		this.currentPlayer = currentPlayer;
		Field field = new Field();
		Cell [][] board = field.getBoard();
		this.field=field;
		this.board=board;
		this.gameOver = false;
	}
	


	public Game(String[] playerTypeData){
//		this.player_1 = player_1;
//		Player player_1 = new Player(1, Color.BLUE);
//		this.player_2 = player_2;
//		Player player_2 = new Player(2, Color.RED);
		this.playerTypeData=playerTypeData;
		player = SetPlayers(playerTypeData);
		player_1 = player[0];
		player_2 = player[1];
		this.currentPlayer_ID = player_1.getPlayer_ID();
		Player currentPlayer = player_1;
		this.currentPlayer = currentPlayer;
		Field field = new Field();
		Cell [][] board = field.getBoard();
		this.field=field;
		this.board=board;
		this.gameOver = false;
	}
	
	/***
	 * create a duplicate of the game properties
	 * 
	 * @return copy of the game for mcts
	 */
	public Game duplicateG(){

		Game g= new Game(playerTypeData);
		g.winner = winner;
		g.player_1 = this.player_1;
		g.player_2 = this.player_2;
		g.currentPlayer = this.currentPlayer;
		g.currentPlayer_ID = currentPlayer_ID;
		g.draw = draw;
		g.freecells = freecells;
		g.gameWon = gameWon;

		g.turnCount = turnCount;
//		g.player = SetPlayers(playerTypeData);
//		g.player_1 = player[0];
//		g.player_2 = player[1];

		
		Cell [][] board = new Cell[g.board.length][g.board[0].length];
		for (int i = 0; i < board.length; i++)
		{
			for (int j = 0; j < board[0].length; j++)
			{
				board[i][j] = (Cell) this.board[i][j].clone();
				
//				System.out.println("cloned cell " + i + j);
//				System.out.println("cell state = " + board[i][j].getCellState() 
//						+ "\n original cell state = " + this.board[i][j].getCellState());
			}
		}

		g.turnCount = turnCount;		
		//Cell[][] board = field.getBoard();


		g.board=board;
		g.gameOver = false;
		return g;
	}
	
	public int nPieces(int p)
	{
		int n = 0;
		//ArrayList<Pieces> pieces = new ArrayList<Pieces>();
		for(int i = 0; i<board.length; i++)
		{
			for( int j = 0; j<board[0].length; j++)
			{
				if( board[i][j].getCellState() == 1 && board[i][j].getContent().player_ID == p)
				{
					//pieces.add(board[i][j].getContent());
					n++;
				}
			}
		}
		return n;
	}
	
	/**
	 * Sets the type of players, either human or AI
	 * @param playerTypeData the player types, AI Or human
	 * @return The array of players
	 */
	public Player[] SetPlayers(String[] playerTypeData){
		Player[] player = new Player[2];
		if(playerTypeData[0] == "HumanPlayer"){
			player[0] = new HumanPlayer(1, Color.BLUE); //ID, Piece Colour
		}
		else if(playerTypeData[0] == "AIPlayer"){
			player[0] = new AIPlayer(1, Color.BLUE, 1); //ID, Piece Colour, Algorithm ID
		}
        else if(playerTypeData[0] == "RandAIPlayer"){
		    player[0] = new AIPlayer(1, Color.BLUE, 3);
        }
		else if(playerTypeData[0] == "MCTS"){
			player[0] = new AIPlayer(1, Color.BLUE, 2); //ID, Piece Colour, Algorithm ID
        }



		if(playerTypeData[1] == "HumanPlayer"){
			player[1] = new HumanPlayer(2, Color.RED);
		}
		else if(playerTypeData[1] == "AIPlayer"){
			player[1] = new AIPlayer(2, Color.RED, 1);
		}
        else if(playerTypeData[1] == "RandAIPlayer"){
            player[1] = new AIPlayer(2, Color.RED, 3);
        }
		else if(playerTypeData[1] == "MCTS"){
			player[1] = new AIPlayer(2, Color.RED, 2); //ID, Piece Colour, Algorithm ID
        }
		return player;
	}
	
	/**
	 * Place pieces on the board
	 * @param x x-coordinate where you want to place the piece
	 * @param y y-coordinate where you want to place the piece
	 * @param piece piece that you want to place
	 */
	public void addPiece(int x, int y, Pieces piece){
		if(!inBound(x,y)){
			success = false;
		}
		else {
			if(board[x][y].getCellState()!=0){
			success = false;
			//return error
			//should also check if area is available to player
			}
			else{
				if(availableCell(y, currentPlayer)){
					currentPlayer.piecesCoord.add(piece);
					currentPlayer.numberPieces++;
					success = true;
					piece.setPosition(x, y);
					board[x][y].setCellState(1);
					System.out.println(x + y + "cell state = " + board[x][y].getCellState());
					board[x][y].setContent(piece);
				}
				else{
					success = false;
				}
			}
		}
	}
	/**
	 * Removes piece from board
	 * @param x x-coordinate  of the piece you want to remove
	 * @param y y-coordinate of the piece you want to remove
	 */
	public void removePiece(int x, int y){
		if(board[x][y].getCellState() != 1){
			success = false;
		}
		else if(board[x][y].getContent().getPlayer_ID() == currentPlayer.getPlayer_ID() ||
				gameActive){
			//for(int i=0; i<currentPlayer.piecesCoord.size(); i++){
			int i = 0;
			boolean done = false;
			while(!done && i < currentPlayer.piecesCoord.size()) {
				if(currentPlayer.piecesCoord.get(i) == board[x][y].getContent()){
//					System.out.println("Player " + currentPlayer.getPlayer_ID() + " loses one piece");
					currentPlayer.piecesCoord.remove(i);
					currentPlayer.numberPieces--;
				}
				i++;
			}
		success = true;
		
		}
		else{
			success = false;
		}
	}
	public void removeOtherPiece(int x, int y){
		if(board[x][y].getCellState() != 1){
			success = false;
		}
		else if(board[x][y].getContent().getPlayer_ID() == currentPlayer.getPlayer_ID() ||
				gameActive){
			//for(int i=0; i<currentPlayer.piecesCoord.size(); i++){
			Player player = null;
			if (currentPlayer == player_1){
				player = player_2;
			} else {
				player = player_1;
			}
			int j = 0;
			boolean done = false;
			while(!done && j < player.piecesCoord.size()) {
				if(player.piecesCoord.get(j) == board[x][y].getContent()){
//					System.out.println("Player " + player.getPlayer_ID() + " loses one piece");
					player.piecesCoord.remove(j);
					player.numberPieces--;
				}
				j++;
			}
			success = true;

		}
		else{
			success = false;
		}
	}

	/**
	 * Moves piece on the board
	 * @param x1 x-coordinate of current place of the piece
	 * @param y1 y-coordinate of the current place of the piece
	 * @param x2 x-coordinate of where you want to move the piece to
	 * @param y2 y-coordinate of where you want to move the piece to
	 */
	public void movePiece(int x1, int y1, int x2, int y2){
		System.out.println("moving from " + x1 + y1 + " to " + x2 + y2);
		if( inBound(x2,y2) && validMove(x1,y1,x2,y2) && board[x2][y2].getCellState()==0){
			board[x2][y2].setContent(board[x1][y1].getContent());
			board[x1][y1].getContent().setPosition(x2, y2);
			board[x1][y1].setCellState(0);
			board[x2][y2].setCellState(1);
//			removePiece(x1,y1);
			battled = false;
			success = true;
			
		}
		else if(inBound(x2,y2) && board[x2][y2].getCellState()==1 && currentPlayer.getPlayer_ID() != board[x2][y2].getContent().getPlayer_ID()
			&& validMove(x1,y1,x2,y2)){
			handleBattle(x1,y1,x2,y2);
			//System.out.println("Score player 1 = " + player_1.offBoard + "; Score player 2 = " + player_2.offBoard);
			battled = true;
			success = true;
		}
		else{
			battled = false;
			success = false;
			//return error
		}
//		System.out.println(currentPlayer.getPlayer_ID() + " moved " + x1 + "," + y1 + " to " + x2 + ","  + y2 + ", " + success);
		//currentPlayer.printKnown();
		if(success)
		System.out.println("move done");
		printBoard();
	}


	/**
	 * Checks if the move from (x1,y1) to (x2,y2) is valid.
	 * @param x1 current x-coordinate of the piece
	 * @param y1 current y-coordinate of the piece
	 * @param x2 x-coordinate where you want to move the piece
	 * @param y2 y-coordinate where you want to move the piece
	 * @return true if the move was valid. false if move was invalid
	 */
	public boolean validMove(int x1, int y1, int x2, int y2){
		if(board[x1][y1].getContent()==null)
		{
			System.out.println("no content");
		}
		if(expectimax)
			System.out.println("expectimax true");
		else
			System.out.println("expectimax false");
		if(!expectimax && board[x1][y1].getContent().getPlayer_ID() != currentPlayer.getPlayer_ID()){
			//return error
			System.out.println("in the bad if");
				return false;
		}
		int spaces;
		int a = 1;
		Pieces piece  = board[x1][y1].getContent();
		int rank = piece.getRank();
		//check if cell is impassable or move is diagonally or if piece is unmovable
		if(!piece.isMovable()|| board[x2][y2].getCellState()==-1 || ((x1!=x2)&&(y1!=y2))){
			return false;
		}
		if(x1==x2){
			if(y1>y2){ a = -1;}
			spaces = Math.abs(y1-y2);
		}
		else{
			if(x1>x2){a = -1;}
			spaces = Math.abs(x1-x2);
		}
		if(rank!=2 && spaces>1){
			return false;
		}
		else{
		//check if the piece won't jump over pieces or impassable cells
			if(rank==2 && spaces > 1 && y1==y2){
				if(a==1){
					for(int i=x1+1; i<x2; i++){
						if(board[i][y1].getCellState()!=0){
							return false;
						}
					}
				}
				if(a==-1){
					for(int i=x2-1; i>x1; i--){
						if(board[i][y1].getCellState()!=0){
							return false;
						}
					}
				}
			}
			if(rank==2 && spaces > 1 && x1==x2){
				if(a==1){
					for(int i=y1+1; i<y2; i++){
						if(board[x1][i].getCellState()!=0){
							return false;
						}
					}
				}
				if(a==-1){
					for(int i=y2-1; i>y1; i--){
						if(board[x1][i].getCellState()!=0){
							return false;
						}
					}
				}
			}
			return true;

		}

	}
	
	
	/**
	 * Finds all the coordinates of pieces which are able to move to another cell
	 * @param p player whose movable pieces coordinates who you want to find out
	 * @return list of coordinates of movable pieces
	 */
    public ArrayList<Pieces> findMovableCoords(Player p){
        ArrayList<Pieces> list = new ArrayList<Pieces>();
//        System.out.println("in find moovable coords");
        for (int x=1; x<board.length-1; x++){
            for (int y=1; y<board[0].length-1; y++){
//            	System.out.println("in 2nd for loop x = " + x + " y = " + y);
                //looks for piece
                Cell current = board[x][y];
       
                if(board[x][y].getCellState() == 1){
//                	System.out.println("in first if");
                    Cell right = board[x+1][y];
                    Cell left = board[x-1][y];
                    Cell up = board[x][y-1];
                    Cell down = board[x][y+1];
                    //checks adjacent cells (if chosen piece is movable)
                    //either a empty cell or opponent piece
                    if((right.getCellState() == 0 || left.getCellState() == 0 || down.getCellState() == 0 || up.getCellState() == 0) ||
                            ((right.getCellState() == 1 && right.getContent().getPlayer_ID() != current.getContent().getPlayer_ID()) ||
                                    (left.getCellState() == 1 && left.getContent().getPlayer_ID() != current.getContent().getPlayer_ID())||
                                    (down.getCellState() == 1 && down.getContent().getPlayer_ID() != current.getContent().getPlayer_ID())||
                                    (up.getCellState() == 1 && up.getContent().getPlayer_ID() != current.getContent().getPlayer_ID()))){
//                    	System.out.println("in if");
                    	if(current.getContent().getPlayer_ID() == p.getPlayer_ID()) {
                            //int[] coord = {x, y};
                            Pieces piece = board[x][y].getContent();
                            if(piece.getRank() != 0 && piece.getRank() != 11){
                            list.add(piece);
                            }
                        }
                    }
                }
            }
        }
//        if(list.size()==0){
//        	t.endgame();
//        }
        //System.out.println("listsize movables = " + list.size() + " ");
        return list;
    }

	
	
	public void repaint(){
		GUI.frame.repaint();
	}

	/**

	 * Handles battle between 2 pieces
	 * @param x1 x-coordinate of piece which is attacking
	 * @param y1 y-coordinate of piece which is attacking
	 * @param x2 x-coordinate of defense piece
	 * @param y2 y-coordinate of defense piece
	 */
	public void handleBattle(int x1, int y1, int x2, int y2){
		Pieces attack = board[x1][y1].getContent();
		Pieces defense = board[x2][y2].getContent();

//		System.out.println("Battle between rank " + attack.getRank() + "(" + attack.getPlayer_ID() +
//							") on (" + attack.getPosition()[0] + "," + attack.getPosition()[1] +
//							") and rank " + defense.getRank() + "(" + defense.getPlayer_ID() +
//							") on (" + defense.getPosition()[0] + "," + defense.getPosition()[1] + ")");
		if(defense.getRank()==0){
			flagCaptured = true;
			endgame();
			//findScore();
		}
		if((attack.getRank() == 1 && defense.getRank() == 10) ||
			(attack.getRank() == 3 && defense.getRank() == 11)) {
			//attack wins
			won = attack;
			lost = defense;
			if(!attack.known){
				makeKnown(attack);
			}
			if(defense.known){
				unknow(defense);
			}
			if(currentPlayer == player_1){
				player_1.offBoard += defense.getRank();
			}
			if(currentPlayer == player_2){
				player_2.offBoard += defense.getRank();
			}
			removeOtherPiece(x2,y2);
			board[x1][y1].setCellState(0);
			board[x1][y1].setContent(null);
			attack.setPosition(x2,y2);
			board[x2][y2].setContent(attack);
			//findScore();
//			System.out.println("Dismantle/ Spy Win");
		}//attack wins
		else if (attack.getRank() > defense.getRank()) {
				if (!attack.known) {
					makeKnown(attack);
				}
				if (currentPlayer == player_1) {
					if (defense.known) {
						unknow(defense);
					}
					player_1.offBoard ++;
				} else {
					player_2.offBoard ++;
				}
				won = attack;
				lost = defense;
				removeOtherPiece(x2,y2);
				board[x1][y1].setCellState(0);
				board[x1][y1].setContent(null);
				attack.setPosition(x2,y2);
				board[x2][y2].setContent(attack);
				//findScore();
//				System.out.println("Win");
			}

			//draw
			else if (attack.getRank() == defense.getRank()) {
				if (currentPlayer == player_1) {
					player_1.offBoard ++;
					player_2.offBoard ++;
				} else {
					player_1.offBoard ++;
					player_2.offBoard ++;
				}
				removePiece(x1, y1);
				board[x1][y1].setCellState(0);
				board[x1][y1].setContent(null);
				removeOtherPiece(x2, y2);
				board[x2][y2].setCellState(0);
				board[x2][y2].setContent(null);
				if (attack.known) {
					unknow(attack);
				}
				if (defense.known) {
					unknow(defense);
				}
				//findScore();
//				System.out.println("Draw");
			}
			//defense wins
			else if (attack.getRank() < defense.getRank()) {
				won = defense;
				lost = attack;
				if (!defense.known) {
					makeKnown(defense);
				}
				if (attack.known) {
					unknow(attack);
				}
				if (currentPlayer == player_1) {
					player_2.offBoard ++;
				} else {
					player_1.offBoard ++;
				}

				removePiece(x1, y1);
				board[x1][y1].setCellState(0);
				board[x1][y1].setContent(null);
				//findScore();
//				System.out.println("Loss");
			}
		}
	/**
	 * Checks if placement is inbound 
	 * @param x x-coordinate where player wants to place piece
	 * @param y y-coordinate where player wants to place piece
	 * @return true if placements is inbound, false if not
	 */
	public boolean inBound(int x, int y){
		if(x>11 || x <1 || y >11 || y<1){
			return false;
		}
		return true;
	}
	/**
	 * 
	 * @return player whos turn it is
	 */
	public Player getCurrentPlayer(){
		if(currentPlayer_ID == 1){
			return player_1;
		}
		else{
			return player_2;
		}
	}
	/**
	 * changes player turn
	 */
	public void changeTurn(){
		/**
		if(currentPlayer.getType() == "AIPlayer" && gameActive){
			
				RandomAlg rand = new RandomAlg(this, currentPlayer);
				rand.randomMove();
			
			GUI.frame.repaint();
		}
		*/
//		System.out.println("Player " + player_1.player_ID + " has " + player_1.numberPieces+ " pieces");
//		System.out.println("Player " + player_2.player_ID + " has " + player_2.numberPieces+ " pieces");
//		player_1.printPiecesCoord();
//		player_2.printPiecesCoord();
//		printBoard();
		turnCount++;
//		System.out.println("number of turns " + turnCount+ ";  player : "+ currentPlayer_ID);
		if(getCurrentPlayer() == player_1){
			currentPlayer_ID = 2;
			currentPlayer = player_2;
		}
		else{
			currentPlayer_ID = 1;
			currentPlayer = player_1;
		}

		/*if(findMovableCoords(currentPlayer).size()==0){
			endgame();
		}*/

	}
	/**
	 * Checks if the cell is available for placement (empty & in region to place)
	 * @param y y-coordinate to place piece
	 * @param player currentplayer
	 * @return true, if placement is valid
	 */
	public boolean availableCell(int y, Player player){
		if(player.getPlayer_ID() == 1 && y > 4){
			return false;
		}
		else if(player.getPlayer_ID() == 2 && y < 7){
			return false;
		}
		return true;
	}
	/**
	 * Ends the game
	 */
	public void endgame(){
		gameOver = true;
	}
	
	/**
	 * Removes a piece p from the list of known pieces from the opponents player
	 * @param p piece
	 */
	public void unknow(Pieces p){
		if(p.getPlayer_ID() == 1){
			for(int i=0; i< player_2.knownPieces.size(); i++){
				if(player_2.knownPieces.get(i)== p){
					player_2.knownPieces.remove(i);
				}
			}
		}
		if(p.getPlayer_ID() == 2){
			for(int i=0; i< player_1.knownPieces.size(); i++){
				if(player_1.knownPieces.get(i)== p){
					player_1.knownPieces.remove(i);
				}
			}
		}
	}
	
	/**
	 * Adds piece to list of known pieces of opponents player
	 * @param p known opponents piece
	 */
	public void makeKnown(Pieces p){
		p.known = true;
		if(p.getPlayer_ID() == 1){
			player_2.knownPieces.add(p);
		}
		else{
			player_1.knownPieces.add(p);
		}
	}
//	/**
//	 * Duplicates the board with only own pieces and known opponent pieces
//	 * @return the duplicate board
//	 */
//	public Cell[][] duplicate(){
//		Cell[][] duplicate = new Cell[12][12];
//		
//		for(int i=0; i<board[0].length; i++){
//			for(int j=0; j<board.length; j++){
//				duplicate[i][j].setCellState(board[i][j].getCellState());
//				
//				if(board[i][j].getCellState() == 1){
//					duplicate[i][j].setContent(board[i][j].getContent());
//				}
//			}
//		}
//		return duplicate;
//	}
//	/**
//	 * Duplicates board with only the pieces of player
//	 * @param player
//	 * @return duplicate board
//	 */
//	public Cell[][] duplicate_unknown(Player player){
//		Cell[][] duplicate = new Cell[12][12];
//		for(int i=0; i<board.length; i++){
//			for(int j =0; j<board[0].length; j++){
//				duplicate[i][j].setCellState(board[i][j].getCellState());
//				if( ( board[i][j].getCellState() == 1 ) && 
//						( board[i][j].getContent().getPlayer_ID() == player.getPlayer_ID() ||
//						board[i][j].getContent().known ) ){
//					duplicate[i][j].setContent(board[i][j].getContent());
//				}
//			}
//		}
//		return duplicate;
//	}
	/**
	 * fills the board with default placement setups which are generated by the Setups class.
	 */
	public void autofill() {
		Setups s = new Setups();
		int[][] setup = s.randomPreset(currentPlayer_ID);
		for (int y = 0; y < setup[0].length; y++) {
			for (int x = 0; x < setup.length; x++) {
				int r = setup[x][y];
				if (getCurrentPlayer().pieceIsAvailable(r)) {
					Pieces piece = new Pieces(r, " ", currentPlayer_ID);
					if(currentPlayer_ID == 1) {
						addPiece(y + 1, x + 1, piece);
					}
					else if(currentPlayer_ID == 2) {
						addPiece(y + 1, x + 7, piece);
					}
					if (success) {
						getCurrentPlayer().pieces[r]--;
						GUI.frame.repaint();
					}
				}
			}
		}
	}

	public void printBoard(){
		System.out.println();
		for(int y = 0; y<board[0].length; y++){
			for(int x = 0; x<board.length; x++){
				if(board[x][y].getCellState() == 1) {
					if (board[x][y].getContent().getRank() < 10) {
						System.out.print(" " + board[x][y].getContent().getRank() + ",");
					} else {
						System.out.print(board[x][y].getContent().getRank() + ",");
					}
				} else if(board[x][y].getCellState() == -1){
					System.out.print("-1,");
				} else if(board[x][y].getCellState() == 0){
					System.out.print("  ,");
				}
			}
			System.out.println();
		}
	}

	public void findPath(int fromX, int fromY, int toX, int toY){
	    pathfinder = new Pathfinding(board, currentPlayer_ID);
        System.out.println(currentPlayer.getPlayer_ID() + ": Trying to find a path from (" + fromX + "," + fromY + ") to (" + toX + "," + toY + ")...");
        path = pathfinder.aStar(board[fromX][fromY], board[toX][toY]);
    }

	public void makeMove(Move move) {
		movePiece(move.piece.position[0], move.piece.position[1], move.newCoords[0], move.newCoords[1]);
		//move.printMove();
	}
	
	public void makeMove2(Move2 move) {
		movePiece(move.from[0], move.from[1], move.to[0], move.to[1]);
		//move.printMove();
	}
	
	public void reverseMove2(Move2 move)
	{
		movePiece(move.to[0], move.to[1], move.from[0], move.from[1]);
	}

	public int getQuantityOfPlayers() {
		
		return 2;
	}
/**
 * strategy method for MCTS
 * @param location
 * @return
 */
	public ArrayList<Move> getMoves(CallLocation location) {

		// RETURN A LIST OF THE CURRENT PLAYER'S POSSIBLE MOVES
		ArrayList<Move> listOfMoves = new ArrayList<Move>();
		ArrayList<Move> movesAllowed = movesAvailable();
		

		// IN THE CASE OF PLAYOUT
		if (location == CallLocation.playout) {
			

			listOfMoves.addAll(movesAllowed);
			System.out.println(movesAllowed.size());
		}
		else
		{
		}

		return listOfMoves;
	}
	 
	 /**
     * Find coordinates of pieces who can make a move
     * @return list of coordinates which can make a move
     */
    public ArrayList<Pieces> findMovablePieces(Player p){
        ArrayList<Pieces> list = new ArrayList<Pieces>();
        for (int x=1; x<board.length-1; x++){
            for (int y=1; y<board[0].length-1; y++){
                //looks for piece
                Cell current = board[x][y];
       
                if(board[x][y].getCellState() == 1){
                	System.out.println("cell state is 1");
                    Cell right = board[x+1][y];
                    Cell left = board[x-1][y];
                    Cell up = board[x][y-1];
                    Cell down = board[x][y+1];
                    //checks adjacent cells (if chosen piece is movable)
                    //either a empty cell or opponent piece
                    if((right.getCellState() == 0 || left.getCellState() == 0 || down.getCellState() == 0 || up.getCellState() == 0) ||
                            ((right.getCellState() == 1 && right.getContent().getPlayer_ID() != current.getContent().getPlayer_ID()) ||
                                    (left.getCellState() == 1 && left.getContent().getPlayer_ID() != current.getContent().getPlayer_ID())||
                                    (down.getCellState() == 1 && down.getContent().getPlayer_ID() != current.getContent().getPlayer_ID())||
                                    (up.getCellState() == 1 && up.getContent().getPlayer_ID() != current.getContent().getPlayer_ID()))){
                    	System.out.println("in if");
                        if(current.getContent().getPlayer_ID() == p.player_ID) {
                            //int[] coord = {x, y};
                            Pieces piece = board[x][y].getContent();
                            if(piece.getRank() != 0 && piece.getRank() != 11){
                            list.add(piece);
                            }
                        }
                    }
                }
            }
        }
//        if(list.size()==0){
//        	endgame();
//        	
//        }
     System.out.println("listsize movables = " + list.size() + " ");
        return list;
    }
    
    /**
     * Makes a list of all available moves
     * @param board
     * @return list of all available moves
     */
    public ArrayList<Move2> movesAvailable2(Player p){
		ArrayList<Move2> moves = new ArrayList<Move2>();
		ArrayList<Pieces> movables = findMovableCoords(p);
		
		for (int i=0; i<movables.size(); i++){
			Pieces piece = movables.get(i);
			int x =piece.getPosition()[0];
			int y = piece.getPosition()[1];
			
			if(board[x][y].getCellState()!= 2){
				if(board[x+1][y].getCellState() == 0){
					Move2 move = new Move2(x,y,x+1,y);
					moves.add(move);
				}
				else if(board[x+1][y].getCellState() == 1 
						&& board[x+1][y].getContent().getPlayer_ID() != piece.getPlayer_ID()){
					int[] newCoords = new int[2];
					newCoords[0] = x+1; newCoords[1]= y;
					Move2 move = new Move2(x,y,x+1,y);
					moves.add(move);
				}
				
				if(board[x-1][y].getCellState() == 0){
					Move2 move = new Move2(x,y,x-1,y);
					moves.add(move);
				}
				else if(board[x-1][y].getCellState() == 1 
						&& board[x-1][y].getContent().getPlayer_ID() != piece.getPlayer_ID()){
					
					Move2 move = new Move2(x,y,x-1,y);
					moves.add(move);
				}
				if(board[x][y+1].getCellState() == 0){
					Move2 move = new Move2(x,y,x,y+1);
					moves.add(move);
				}
				else if(board[x][y+1].getCellState() == 1 
						&& board[x][y+1].getContent().getPlayer_ID() != piece.getPlayer_ID()){
					Move2 move = new Move2(x,y,x,y+1);
					moves.add(move);
				}
				if(board[x][y-1].getCellState() == 0){
					Move2 move = new Move2(x,y,x,y-1);
					moves.add(move);
				}
				else if(board[x][y-1].getCellState() == 1 
						&& board[x][y-1].getContent().getPlayer_ID() != piece.getPlayer_ID()){
					Move2 move = new Move2(x,y,x,y-1);
					moves.add(move);
				}
			}
			else{
				for(int j = x+1;  j<board.length; j++){
					if(board[j][y].getCellState() == -1){
						j = 20;
					}
					else if(board[j][y].getCellState() == 0){
						Move2 move = new Move2(x,y,j,y);
						moves.add(move);
					}
					else if(board[j][y].getCellState() == 1 
							&& board[j][y].getContent().getPlayer_ID() != piece.getPlayer_ID()){
						Move2 move = new Move2(x,y,j,y);
						moves.add(move);
						j = 20;
					}
				}
				for(int j = x-1;  j>0; j--){
					if(board[j][y].getCellState() == -1){
						j = -20;
					}
					else if(board[j][y].getCellState() == 0){
						Move2 move = new Move2(x,y,j,y);
						moves.add(move);
					}
					else if(board[j][y].getCellState() == 1 
							&& board[j][y].getContent().getPlayer_ID() != piece.getPlayer_ID()){
						Move2 move = new Move2(x,y,j,y);
						moves.add(move);
						j = -20;
					}
				}
				
				for(int j = y+1;  j<board.length; j++){
					if(board[x][j].getCellState() == -1){
						j = 20;
					}
					else if(board[x][j].getCellState() == 0){
						Move2 move = new Move2(x,y,x,j);
						moves.add(move);
					}
					else if(board[x][j].getCellState() == 1 
							&& board[x][j].getContent().getPlayer_ID() != piece.getPlayer_ID()){
						Move2 move = new Move2(x,y,x,j);
						moves.add(move);
						j = 20;
					}
				}
				for(int j = y-1;  j>0; j--){
					if(board[x][j].getCellState() == -1){
						j = -20;
					}
					else if(board[x][j].getCellState() == 0){
						Move2 move = new Move2(x,y,x,j);
						moves.add(move);
					}
					else if(board[x][j].getCellState() == 1 
							&& board[x][j].getContent().getPlayer_ID() != piece.getPlayer_ID()){
						Move2 move = new Move2(x,y,x,j);
						moves.add(move);
						j = -20;
					}
				}
			}
		}
		//System.out.println(moves.size() +" possible moves for player "+ currentPlayer_ID);
		return moves;
    }
    


	/**
     * Makes a list of all available moves
     * @return list of all available moves
     */
    public ArrayList<Move> movesAvailable(){
		ArrayList<Move> moves = new ArrayList<Move>();
		ArrayList<Pieces> movables = findMovablePieces(currentPlayer);

		/*for(int i=0; i<movables.size(); i++)
		{
			System.out.println("movable x = " + movables.get(i).position[0] + " y = " + movables.get(i).position[1]);
		}*/

		for (int i=0; i<movables.size(); i++){
			Pieces piece = movables.get(i);
			int x =piece.getPosition()[0];
			int y = piece.getPosition()[1];
			
			
			if(board[x][y].getCellState()!= 2){
				if(board[x+1][y].getCellState() == 0){
					int[] newCoords = new int[2];
					newCoords[0] = x+1; newCoords[1]= y;
					Move move = new Move(piece, newCoords);
					moves.add(move);
				}
				else if(board[x+1][y].getCellState() == 1 && board[x+1][y].getContent().getPlayer_ID() != piece.getPlayer_ID()){
					int[] newCoords = new int[2];
					newCoords[0] = x+1; newCoords[1]= y;
					Move move = new Move(piece, newCoords);
					moves.add(move);
				}
				
				if(board[x-1][y].getCellState() == 0){
					int[] newCoords = new int[2];
					newCoords[0] = x-1; newCoords[1]= y;
					Move move = new Move(piece, newCoords);
					moves.add(move);
				}
				else if(board[x-1][y].getCellState() == 1 && board[x-1][y].getContent().getPlayer_ID() != piece.getPlayer_ID()){
					int[] newCoords = new int[2];
					newCoords[0] = x-1; newCoords[1] = y;
					Move move = new Move(piece, newCoords);
					moves.add(move);
				}
				if(board[x][y+1].getCellState() == 0){
					int[] newCoords = new int[2];
					newCoords[0] = x; newCoords[1]= y+1;
					Move move = new Move(piece, newCoords);
					moves.add(move);
				}
				else if(board[x][y+1].getCellState() == 1 && board[x][y+1].getContent().getPlayer_ID() != piece.getPlayer_ID()){
					int[] newCoords = new int[2];
					newCoords[0] = x; newCoords[1] = y+1;
					Move move = new Move(piece, newCoords);
					moves.add(move);
				}
				if(board[x][y-1].getCellState() == 0){
					int[] newCoords = new int[2];
					newCoords[0] = x; newCoords[1]= y-1;
					Move move = new Move(piece, newCoords);
					moves.add(move);
				}
				else if(board[x][y-1].getCellState() == 1 && board[x][y-1].getContent().getPlayer_ID() != piece.getPlayer_ID()){
					int[] newCoords = new int[2];
					newCoords[0] = x; newCoords[1] = y-1;
					Move move = new Move(piece, newCoords);
					moves.add(move);
				}	
				
			}
			else{
				for(int j = x+1;  j<board.length; j++){
					if(board[j][y].getCellState() == -1){
						j = 20;
					}
					else if(board[j][y].getCellState() == 0){
						int[] newCoords = new int[2];
						newCoords[0]=j; newCoords[1] = y;
						Move move = new Move(piece, newCoords);
						moves.add(move);
					}
					else if(board[j][y].getCellState() == 1 && board[j][y].getContent().getPlayer_ID() != piece.getPlayer_ID()){
						int[] newCoords = new int[2];
						newCoords[0]=j; newCoords[1] = y;
						Move move = new Move(piece, newCoords);
						moves.add(move);
						j = 20;
					}
				}
				for(int j = x-1;  j>0; j--){
					if(board[j][y].getCellState() == -1){
						j = -20;
					}
					else if(board[j][y].getCellState() == 0){
						int[] newCoords = new int[2];
						newCoords[0]=j; newCoords[1] = y;
						Move move = new Move(piece, newCoords);
						moves.add(move);
					}
					else if(board[j][y].getCellState() == 1 && board[j][y].getContent().getPlayer_ID() != piece.getPlayer_ID()){
						int[] newCoords = new int[2];
						newCoords[0]=j; newCoords[1] = y;
						Move move = new Move(piece, newCoords);
						moves.add(move);
						j = -20;
					}
				}
				
				for(int j = y+1;  j<board.length; j++){
					if(board[x][j].getCellState() == -1){
						j = 20;
					}
					else if(board[x][j].getCellState() == 0){
						int[] newCoords = new int[2];
						newCoords[0]=x; newCoords[1] = j;
						Move move = new Move(piece, newCoords);
						moves.add(move);
					}
					else if(board[x][j].getCellState() == 1 && board[x][j].getContent().getPlayer_ID() != piece.getPlayer_ID()){
						int[] newCoords = new int[2];
						newCoords[0]=x; newCoords[1] = j;
						Move move = new Move(piece, newCoords);
						moves.add(move);
						j = 20;
					}
				}
				
				for(int j = y-1;  j>0; j--){
					if(board[x][j].getCellState() == -1){
						j = -20;
					}
					else if(board[x][j].getCellState() == 0){
						int[] newCoords = new int[2];
						newCoords[0]=x; newCoords[1] = j;
						Move move = new Move(piece, newCoords);
						moves.add(move);
					}
					else if(board[x][j].getCellState() == 1 && board[x][j].getContent().getPlayer_ID() != piece.getPlayer_ID()){
						int[] newCoords = new int[2];
						newCoords[0]=x; newCoords[1] = j;
						Move move = new Move(piece, newCoords);
						moves.add(move);
						j = -20;
					}
				}
			}
			
		}
		System.out.println(moves.size() +" possible moves for player "+ currentPlayer_ID);
		return moves;
    }
    
    
    
	public double[] getMoveWeights() {
//		double []gscore;
//		gscore = new double[getQuantityOfPlayers()];
		
		return null;
	}
	
	/**
	 * 0 = captured flag
	 * 1 = win for defense/loss for attack
	 * 2 = loss for defense/win for attack
	 * 3 = draw
	 * @param defense
	 * @param attack
	 * @return 0 for captured flag; 1 for win for defense; 2 for win for attack; 3 for draw
	 */
	public int handleBattleEM(int attack, int defense)
	{
		int result = 10;
		if(defense==0){
			result = 0;
		}
		/**
		 *attack wins
		 */
		if((attack == 1 && defense== 10) ||
			(attack == 3 && defense == 11)) {
			result = 2;
		}
		else if (attack > defense) 
		{
			result = 2;
		}

		else if (attack == defense) 
		{
			result = 3;
		}
			//defense wins
		else if (attack < defense) {
			result = 1;
		}
		return result;
	}
	
	
/**This method checks two game over conditions:
 * If the player cannot move, or;
 * If the player does not own a flag;
 * @return
 */
	public Player oppositePlayer(){
    	if(currentPlayer == player_1){
    		return player_2;
		} else {
    		return player_1;
		}
	}
	public boolean gameOver() {
        if(findMovableCoords(currentPlayer).size() != 0){
            for(Pieces piece: currentPlayer.piecesCoord) {
                if (piece.getRank() == 0) {
                    return false;
                }
            }
        }
        winner = oppositePlayer().getPlayer_ID()-1;
     
		return true;
	}
/**
 * can be passed on with playerData
 * @return
 */
	public double[] getScore() {
		double []score;
		score = new double[getQuantityOfPlayers()];
		if (!draw) {
			score[winner] = 1.0d;
		} else {
			score[0] = 0.5d;
			score[1] = 0.5d;
		}

		return score;
		
	}
}

