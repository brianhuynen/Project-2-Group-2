import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;

public class Game {
	
	Cell[][] board;
	Field field;
	Player currentPlayer;
	boolean success = false;
	int currentPlayer_ID;
	Player player_1;
	Player player_2;
	boolean battled = false;
	Pieces won;
	Pieces lost;
	Player[] player = new Player[2];
	boolean gameOver;
	public boolean gameActive = false;
	RandomAlg rand;
	//int[] score;

	public Game(String[] playerTypeData){
//		this.player_1 = player_1;
//		Player player_1 = new Player(1, Color.BLUE);
//		this.player_2 = player_2;
//		Player player_2 = new Player(2, Color.RED);

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
	}

	public Player[] SetPlayers(String[] playerTypeData){
		Player[] player = new Player[2];
		if(playerTypeData[0] == "HumanPlayer"){
			player[0] = new HumanPlayer(1, Color.BLUE); //ID, Piece Colour
		}
		else{
			player[0] = new AIPlayer(1, Color.BLUE, 1); //ID, Piece Colour, Algorithm ID
		}

		if(playerTypeData[1] == "HumanPlayer"){
			player[1] = new HumanPlayer(2, Color.RED);
		}
		else{
			player[1] = new AIPlayer(2, Color.RED, 1);
		}
		return player;
	}
	
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
					success = true;
					piece.setPosition(x, y);
					board[x][y].setCellState(1);
					board[x][y].setContent(piece);
				}
				else{
					success = false;
				}
			}
		}
	}
	
	public void removePiece(int x, int y){
		if(board[x][y].getCellState() != 1){
			success = false;
		}
		else if(board[x][y].getContent().getPlayer_ID() == currentPlayer.getPlayer_ID() ||
				gameActive){
			for(int i=0; i<currentPlayer.piecesCoord.size(); i++){
				if(currentPlayer.piecesCoord.get(i) == board[x][y].getContent()){
					currentPlayer.piecesCoord.remove(i);
					break;
				}
			}
		board[x][y].setCellState(0);
		board[x][y].setContent(null);
		success = true;
		
		}
		else{
			success = false;
		}
	}

	
	public void movePiece(int x1, int y1, int x2, int y2){
		if( inBound(x2,y2) && validMove(x1,y1,x2,y2) && board[x2][y2].getCellState()==0){
			board[x2][y2].setContent(board[x1][y1].getContent());
			board[x1][y1].getContent().setPosition(x2, y2);
			board[x2][y2].setCellState(1);
			removePiece(x1,y1);
			battled = false;
			success = true;
			
		}
		else if(inBound(x2,y2) && board[x2][y2].getCellState()==1 && currentPlayer.getPlayer_ID() != board[x2][y2].getContent().getPlayer_ID()
			&& validMove(x1,y1,x2,y2)){
			handleBattle(x1,y1,x2,y2);
			System.out.println("Score player 1 = " + player_1.offBoard + "; Score player 2 = " + player_2.offBoard);
			battled = true;
			success = true;
		}
		else{
			battled = false;
			success = false;
			//return error
		}
//		System.out.println(currentPlayer.getPlayer_ID() + " moved " + x1 + "," + y1 + " to " + x2 + ","  + y2 + ", " + success);
	}


	public boolean validMove(int x1, int y1, int x2, int y2){
		if(board[x1][y1].getContent().getPlayer_ID() != currentPlayer.getPlayer_ID()){
			//return error
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


	
	

	public void handleBattle(int x1, int y1, int x2, int y2){
		Pieces attack = board[x1][y1].getContent();
		Pieces defense = board[x2][y2].getContent();

		System.out.println("Battle between rank " + attack.getRank() + "(" + attack.getPlayer_ID() + ") and rank " + defense.getRank() + "(" + defense.getPlayer_ID() + ")");

		if(defense.getRank()==0){
			endgame();
			//findScore();
		}
		if((attack.getRank() != 1 && defense.getRank() != 10) || 
			(attack.getRank()!=3 && defense.getRank()!= 11)) {
			//attack wins
			if (attack.getRank() > defense.getRank()) {
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
				board[x2][y2].setContent(attack);
				board[x1][y1].getContent().setPosition(x2, y2);
				removePiece(x1, y1);
				//findScore();
//				System.out.println("Win");
			}

			//draw
			if (attack.getRank() == defense.getRank()) {
				if (currentPlayer == player_1) {
					player_1.offBoard ++;
					player_2.offBoard ++;
				} else {
					player_1.offBoard ++;
					player_2.offBoard ++;
				}
				removePiece(x1, y1);
				removePiece(x2, y2);
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
			else {
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
				//findScore();
//				System.out.println("Loss");
			}
		}

		//attack wins
		else{
			won = attack;
			lost = defense;
			if(!attack.known){
				makeKnown(attack);
			}
			if(defense.known){
				unknow(defense);
			}
			if(currentPlayer == player_1){
				player_1.offBoard ++;
			}
			if(currentPlayer == player_2){
				player_2.offBoard++;
			}
			board[x2][y2].setContent(attack);
			board[x1][y1].getContent().setPosition(x2, y2);
			removePiece(x1,y1);
			//findScore();
			System.out.println("Dismantle/ Spy Win");
		}
	}
	
	public boolean inBound(int x, int y){
		if(x>11 || x <1 || y >11 || y<1){
			return false;
		}
		return true;
	}
	
	public Player getCurrentPlayer(){
		if(currentPlayer_ID == 1){
			return player_1;
		}
		else{
			return player_2;
		}
	}
	
	public void changeTurn(){
		if(getCurrentPlayer() == player_1){
			currentPlayer_ID = 2;
			currentPlayer = player_2;
		}
		else{
			currentPlayer_ID = 1;
			currentPlayer = player_1;
		}
		
		//Needs to check for unmovable pieces to endgame
	}
	
	public boolean availableCell(int y, Player player){
		if(player.getPlayer_ID() == 1 && y > 4){
			return false;
		}
		else if(player.getPlayer_ID() == 2 && y < 7){
			return false;
		}
		return true;
	}
		
	public void endgame(){
		gameOver = true;
	}
	
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
	public void makeKnown(Pieces p){
		p.known = true;
		if(p.getPlayer_ID() == 1){
			player_2.knownPieces.add(p);
		}
		else{
			player_1.knownPieces.add(p);
		}
	}
	
	public Cell[][] duplicate(Player player){
		Cell[][] duplicate = new Cell[12][12];
		
		for(int i=0; i<board[0].length; i++){
			for(int j=0; j<board.length; j++){
				duplicate[i][j].setCellState(board[i][j].getCellState());
				
				if(board[i][j].getCellState() == 1){
					duplicate[i][j].setContent(board[i][j].getContent());
				}
			}
		}
		return duplicate;
	}
	
	public Cell[][] duplicate_unknown(Player player){
		Cell[][] duplicate = new Cell[12][12];
		for(int i=0; i<board.length; i++){
			for(int j =0; j<board[0].length; j++){
				duplicate[i][j].setCellState(board[i][j].getCellState());
				if( ( board[i][j].getCellState() == 1 ) && 
						( board[i][j].getContent().getPlayer_ID() == player.getPlayer_ID() ||
						board[i][j].getContent().known ) ){
					duplicate[i][j].setContent(board[i][j].getContent());
				}
			}
		}
		return duplicate;
	}

	public void autofill() {
		final int[][] PLAYER1 = {{1, 3, 5, 11, 0, 5, 11, 9, 8, 7},
				{3, 4, 4, 11, 11, 11, 11, 8, 7, 7},
				{3, 4, 4, 5, 5, 2, 6, 6, 6, 6},
				{2, 2, 2, 2, 3, 10, 2, 2, 2, 3}};
		//Hardcoded piece placement of player 2
		final int[][] PLAYER2 = {{3, 2, 2, 2, 11, 1, 2, 2, 2, 3},
				{5, 5, 4, 4, 4, 4, 3, 3, 3, 2},
				{5, 5, 6, 11, 11, 2, 11, 7, 8, 10},
				{6, 6, 6, 11, 0, 7, 11, 7, 8, 9}};
		if (currentPlayer_ID == 1) {
			for (int y = 0; y < PLAYER1[0].length; y++) {
				for (int x = 0; x < PLAYER1.length; x++) {
					int r = PLAYER1[x][y];
					if (getCurrentPlayer().pieceIsAvailable(r)) {
						Pieces piece = new Pieces(r, " ", currentPlayer_ID);
						addPiece(y + 1, x + 1, piece);
						if (success) {
							getCurrentPlayer().pieces[r]--;
							GUI.frame.repaint();
						}
					}
				}
			}
		} else {
			for (int x = 0; x < PLAYER2.length; x++) {
				for (int y = 0; y < PLAYER2[0].length; y++) {
					int r = PLAYER2[x][y];
					if (getCurrentPlayer().pieceIsAvailable(r)) {
						Pieces piece = new Pieces(r, " ", currentPlayer_ID);
						addPiece(y + 1, x + 7, piece);
						if (success) {
							getCurrentPlayer().pieces[r]--;
							GUI.frame.repaint();
						}
					}
				}
			}
		}
	}
	
	/*public void findScore()
	{
		score = new int[2];
		if(currentPlayer.getPlayer_ID() == player_1.getPlayer_ID())
		{
			score[0]+=currentPlayer.offBoard;
		}
		else if(currentPlayer.getPlayer_ID() == player_2.getPlayer_ID())
		{
			score[1]+=currentPlayer.offBoard;
		}
	}*/
	

}

