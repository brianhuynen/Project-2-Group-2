import java.util.ArrayList;
import java.util.Random;

/**
 * Created by esther on 11/23/16.
 */
public class RandomAlg {

    private Cell[][] board;
    private Player player;
    private Game game;

    public RandomAlg(Game game, Player player){
        this.board = game.board;
        this.player = player;
        this.game = game;
    }
    /**
     * Chooses a random move
     * @return random move
     */
    public Move generateMovement(){
        //finds coordinates of movable pieces
        ArrayList<Move> moves = movesAvailable(board);

        Random rand = new Random();
        System.out.println("moves = " + moves.size());
        /*for ( int i = 0; i<moves.size(); i++)
        {
        	moves.get(i).printMove();
        }*/
        if(moves.size() != 0){
	        int i = rand.nextInt(moves.size());
	        return moves.get(i);
        }
        else{
        	game.endgame();
        	return null;
        }
        
    }
    /**
     * Makes a random move
     */
    public void randomMove()
    {
    	Move move = generateMovement();
    	move.checkBattle(board);
    	game.movePiece(move.piece.position[0], move.piece.position[1], move.newCoords[0], move.newCoords[1]);
    }
    
    /**
     * Find coordinates of pieces who can make a move
     * @return list of coordinates which can make a move
     */
    public ArrayList<Pieces> findMovableCoords(){
        ArrayList<Pieces> list = new ArrayList<Pieces>();
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
                        if(current.getContent().getPlayer_ID() == player.getPlayer_ID()) {
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
        if(list.size()==0){
        	game.endgame();
        }
        System.out.println("listsize movables = " + list.size() + " ");
        return list;
    }
    
    /**
     * Makes a list of all available moves
     * @param board
     * @return list of all available moves
     */
    public ArrayList<Move> movesAvailable(Cell[][] board){
		ArrayList<Move> moves = new ArrayList<Move>();
		ArrayList<Pieces> movables = findMovableCoords();

		/*for(int i=0; i<movables.size(); i++)
		{
			System.out.println("movable x = " + movables.get(i).position[0] + " y = " + movables.get(i).position[1]);
		}*/

		for (int i=0; i<movables.size(); i++){
			Pieces piece = movables.get(i);
			int x = piece.getPosition()[0];
			int y = piece.getPosition()[1];
			
			
			if(piece.getRank()!= 2){
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
		return moves;
    }

	public Cell[][] getBoard()
	{
		return board;
	}
}
