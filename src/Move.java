import java.util.ArrayList;

public class Move {

	public ArrayList<int [] > movesAvailable(Pieces piece, Cell[][] board){
		ArrayList<int [] > moves = new ArrayList<int []>();
		if(!piece.isMovable()){
			return null;
		}
		else{
			int [] position = piece.getPosition();
			int x = position[0];
			int y = position[1];
			int [] move = new int [2];
			if(piece.getRank()!= 2){
				if(board[x+1][y].getCellState() == 0){
					move[0] = x+1; move[1]= y;
					moves.add(move);
				}
				else if(board[x+1][y].getCellState() == 1 && board[x+1][y].getContent().getPlayer_ID() != piece.getPlayer_ID()){
					move[0] = x+1; move[1] = y;
					moves.add(move);
				}
				
				if(board[x-1][y].getCellState() == 0){
					move[0] = x-1; move[1]= y;
					moves.add(move);
				}
				else if(board[x-1][y].getCellState() == 1 && board[x-1][y].getContent().getPlayer_ID() != piece.getPlayer_ID()){
					move[0] = x-1; move[1] = y;
					moves.add(move);
				}
				if(board[x][y+1].getCellState() == 0){
					move[0] = x; move[1]= y+1;
					moves.add(move);
				}
				else if(board[x][y+1].getCellState() == 1 && board[x][y+1].getContent().getPlayer_ID() != piece.getPlayer_ID()){
					move[0] = x; move[1] = y+1;
					moves.add(move);
				}
				if(board[x][y-1].getCellState() == 0){
					move[0] = x; move[1]= y-1;
					moves.add(move);
				}
				else if(board[x][y-1].getCellState() == 1 && board[x][y-1].getContent().getPlayer_ID() != piece.getPlayer_ID()){
					move[0] = x; move[1] = y-1;
					moves.add(move);
				}	
			return moves;
				
			}
			else{
				for(int i = x+1;  i<board.length; i++){
					if(board[i][y].getCellState() == -1){
						i = 20;
					}
					if(board[i][y].getCellState() == 0){
						move[0]=i; move[1] = y;
						moves.add(move);
					}
					if(board[i][y].getCellState() == 1 && board[i][y].getContent().getPlayer_ID() != piece.getPlayer_ID()){
						move[0]=i; move[1] = y;
						moves.add(move);
						i = 20;
					}
				}
				for(int i = x-1;  i>0; i--){
					if(board[i][y].getCellState() == -1){
						i = 20;
					}
					if(board[i][y].getCellState() == 0){
						move[0]=i; move[1] = y;
						moves.add(move);
					}
					if(board[i][y].getCellState() == 1 && board[i][y].getContent().getPlayer_ID() != piece.getPlayer_ID()){
						move[0]=i; move[1] = y;
						moves.add(move);
						i = 20;
					}
				}
				
				for(int i = y+1;  i<board.length; i++){
					if(board[x][i].getCellState() == -1){
						i = 20;
					}
					if(board[x][i].getCellState() == 0){
						move[0]=x; move[1] = i;
						moves.add(move);
					}
					if(board[x][i].getCellState() == 1 && board[x][i].getContent().getPlayer_ID() != piece.getPlayer_ID()){
						move[0]=x; move[1] = i;
						moves.add(move);
						i = 20;
					}
				}
				
				for(int i = y-1;  i>0; i--){
					if(board[x][i].getCellState() == -1){
						i = 20;
					}
					if(board[x][i].getCellState() == 0){
						move[0]=x; move[1] = i;
						moves.add(move);
					}
					if(board[x][i].getCellState() == 1 && board[x][i].getContent().getPlayer_ID() != piece.getPlayer_ID()){
						move[0]=x; move[1] = i;
						moves.add(move);
						i = 20;
					}
				}
				
				return moves;
			}
			
			
			
		}
		
	}
}
