import java.awt.Color;
public class Game {
	
Cell[][] board;
Field field;
Player currentPlayer;
boolean success = false;
int currentPlayer_ID; 
Player player_1;
Player player_2;

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
					success = true;
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
		if(board[x][y].getContent().getPlayer_ID() == currentPlayer.getPlayer_ID()){
		board[x][y].setCellState(0);
		board[x][y].setContent(null);
		success = true;
		}
		else{
			success = false;
			//return error
		}
	}
	
	public void movePiece(int x1, int y1, int x2, int y2){
		if( inBound(x2,y2) && validMove(x1,y1,x2,y2) && board[x2][y2].getCellState()==0){
			board[x2][y2].setContent(board[x1][y1].getContent());
			board[x2][y2].setCellState(1);
			removePiece(x1,y1);
			success = true;
			
		}
		else if(inBound(x2,y2) && board[x2][y2].getCellState()==1 && currentPlayer.getPlayer_ID() != board[x2][y2].getContent().getPlayer_ID()
			&& validMove(x1,y1,x2,y2)){
			handleBattle(x1,y1,x2,y2);
			success = true;
		}
		else{
			success = false;
			//return error
		}
		
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
					for(int i=x1; i<x2; i++){
						if(board[i][y1].getCellState()!=0){
							return false;
						}
					}
				}
				if(a==-1){
					for(int i=x1; i>x2; i--){
						if(board[i][y1].getCellState()!=0){
							return false;
						}
					}
				}
			}
			if(rank==2 && spaces > 1 && x1==x2){
				if(a==1){
					for(int i=y1; i<y2; i++){
						if(board[x1][i].getCellState()!=0){
							return false;
						}
					}
				}
				if(a==-1){
					for(int i=y1; i>y2; i--){
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
			//endgame();
		}
		if((attack.getRank() != 1 && defense.getRank() != 10) || 
			(attack.getRank()!=3 && defense.getRank()!= 11)){
				if(attack.getRank()>defense.getRank()){
					board[x2][y2].setContent(attack);
					removePiece(x1,y1);
				}
				else if(attack.getRank()==defense.getRank()){
					removePiece(x1,y1);
					removePiece(x2,y2);
				}
				else{
					removePiece(x1,y1);
				}
		}
		else{
			board[x2][y2].setContent(attack);
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
	

}

