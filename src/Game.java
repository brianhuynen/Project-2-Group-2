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
boolean gameOver;

public boolean gameActive = false;

	public Game(){
		Player player_1 = new Player(1, Color.BLUE);
		this.player_1 = player_1;
		Player player_2 = new Player(2, Color.RED);
		this.player_2 = player_2;
		Player currentPlayer = player_1;
		this.currentPlayer = currentPlayer;
		Field field = new Field();
		Cell [][] board = field.getBoard();
		this.field=field;
		this.board=board;
		this.currentPlayer_ID = player_1.getPlayer_ID();
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
			battled = true;
			success = true;
		}
		else{
			battled = false;
			success = false;
			//return error
		}
		
	}
	public void ranMovePiece(){
		//finds coordinates of movable pieces
		ArrayList<int[]> movables = findMovableCoords(currentPlayer);

		Random rand = new Random();
		int i = rand.nextInt(movables.size());

		int[] coord = movables.get(i);

		ArrayList<Integer> directions = new ArrayList<Integer>();
		if (board[coord[0]+1][coord[1]].getCellState() == 0 || (board[coord[0]+1][coord[1]].getCellState() == 1 && board[coord[0]+1][coord[1]].getContent().getPlayer_ID() != board[coord[0]][coord[1]].getContent().getPlayer_ID())){
			directions.add(1);
		}
		if (board[coord[0]-1][coord[1]].getCellState() == 0 || (board[coord[0]-1][coord[1]].getCellState() == 1 && board[coord[0]-1][coord[1]].getContent().getPlayer_ID() != board[coord[0]][coord[1]].getContent().getPlayer_ID())){
			directions.add(2);
		}
		if (board[coord[0]][coord[1]+1].getCellState() == 0 || (board[coord[0]][coord[1]+1].getCellState() == 1 && board[coord[0]][coord[1]+1].getContent().getPlayer_ID() != board[coord[0]][coord[1]].getContent().getPlayer_ID())){
			directions.add(3);
		}
		if (board[coord[0]][coord[1]-1].getCellState() == 0 || (board[coord[0]][coord[1]-1].getCellState() == 1 && board[coord[0]][coord[1]-1].getContent().getPlayer_ID() != board[coord[0]][coord[1]].getContent().getPlayer_ID())){
			directions.add(4);
		}
		int j;
		if(directions.size() ==1){
			j = 0;
		}
		else {
			j = rand.nextInt(directions.size());
		}
		//1 = right, 2 = left, 3 = down, 4 = up
		if (directions.get(j) == 1){
			movePiece(coord[0], coord[1], coord[0]+1, coord[1]);
		}
		if (directions.get(j) == 2){
			movePiece(coord[0], coord[1], coord[0]-1, coord[1]);
		}
		if (directions.get(j) == 3){
			movePiece(coord[0], coord[1], coord[0], coord[1]+1);
		}
		if (directions.get(j) == 4){
			movePiece(coord[0], coord[1], coord[0], coord[1]-1);
		}
	}
	
	private ArrayList<int[]> findMovableCoords(Player p){
		ArrayList<int[]> list = new ArrayList<int[]>();
		for (int x=1; x<board.length-1; x++){
			for (int y=1; y<board[0].length-1; y++){
				//looks for piece
				Cell current = board[x][y];
				if(board[x][y].getCellState() == 1){
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
						if(current.getContent().getPlayer_ID() == p.getPlayer_ID()) {
							int[] coord = {x, y};
							list.add(coord);
						}
					}
				}
			}
		}
		System.out.print(list.size() + " ");
		return list;
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
		//check if cell is impassable or move is diagnoally or if piece is unmovable
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
		
		if(defense.getRank()==0){
			endgame();
		}
		if((attack.getRank() != 1 && defense.getRank() != 10) || 
			(attack.getRank()!=3 && defense.getRank()!= 11)){
			//attack wins
				if(attack.getRank()>defense.getRank()){
					if(!attack.known){
							makeKnown(attack);
					}
					if(currentPlayer == player_1){
						if(defense.known){
							unknow(defense);
						}
						player_1.offBoard += defense.getRank();
					}
					else{
						player_2.offBoard+= defense.getRank();
					}
					won = attack;
					lost = defense;
					board[x2][y2].setContent(attack);
					board[x1][y1].getContent().setPosition(x2, y2);
					removePiece(x1,y1);
					
				}
				//draw
				if(attack.getRank()==defense.getRank()){
					won = attack = lost;
					if(currentPlayer == player_1){
						player_1.offBoard += defense.getRank();
						player_2.offBoard+=attack.getRank();
					}
					else{
						player_1.offBoard+= attack.getRank();
						player_2.offBoard+= defense.getRank();
					}
					removePiece(x1,y1);
					removePiece(x2,y2);
					if(attack.known){
						unknow(attack);
					}
					if(defense.known){
						unknow(defense);
					}
					
				}
				//defense wins
				else{
					won = defense;
					lost = attack;
					if(!defense.known){
						makeKnown(defense);
					}
					if(attack.known){
						unknow(attack);
					}
					if(currentPlayer == player_1){
						player_2.offBoard += attack.getRank();
					}
					else{
						player_1.offBoard+= attack.getRank();
					}
					
					removePiece(x1,y1);
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
				player_1.offBoard += defense.getRank();
			}
			else{
				player_2.offBoard+= defense.getRank();
			}
			board[x2][y2].setContent(attack);
			board[x1][y1].getContent().setPosition(x2, y2);
			removePiece(x1,y1);
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
	

}

