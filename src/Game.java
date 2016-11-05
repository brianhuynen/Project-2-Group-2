
public class Game {
	
Cell[][] board;
Field field;
int currentPlayer;

	public Game(){
		int currentPlayer =1;
		Field field = new Field();
		Cell [][] board = field.getBoard();
		this.field=field;
		this.board=board;
		this.currentPlayer = currentPlayer;
	}
	
	public void addPiece(int x, int y, Pieces piece){
		if(board[x][y].getCellState()!=0){
			//return error
		}
		else{
			board[x][y].setCellState(1);
			board[x][y].setContent(piece);
		}
	}
	
	public void removePiece(int x, int y){
		board[x][y].setCellState(0);
		board[x][y].setContent(null);
	}
	
	public void movePiece(int x1, int y1, int x2, int y2){
		if( validMove(x1,y1,x2,y2) && board[x2][y2].getCellState()==0){
			board[x2][y2].setContent(board[x1][y1].getContent());
			board[x2][y2].setCellState(1);
			removePiece(x1,y1);
			
		}
		else if(board[x2][y2].getCellState()==1 && currentPlayer != board[x2][y2].getContent().getPlayer_ID()
			&& validMove(x1,y1,x2,y2)){
			handleBattle(x1,y1,x2,y2);
		}
		else{
			//return error
		}
		
	}
	
	public boolean validMove(int x1, int y1, int x2, int y2){
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
	
	

}

