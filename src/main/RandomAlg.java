package main;

import java.util.ArrayList;
import java.util.Random;

import astar.Pathfinding;

/**
 * Created by esther on 11/23/16.
 */
public class RandomAlg {

    private Cell[][] board;
    private Player player;
    private Game game;
    private ArrayList<Pieces> bombs, miners;
    public  Pieces bomb, miner;

    public RandomAlg(Game game, Player player){
        this.board = game.board;
        this.player = player;
        this.game = game;
        miners = getMiners(player);
        game.pathfinder = new Pathfinding(board, player.getPlayer_ID());
    }
    /**
     * Chooses a random move
     * @return random move
     */
    public Move generateMovement(){
        //finds coordinates of movable pieces
        ArrayList<Move> moves = movesAvailable(board);

        Random rand = new Random();
        if(moves.size() != 0){
	        int i = rand.nextInt(moves.size());
	        return moves.get(i);
        }
        else{
        	game.endgame();
        	return null;
        }
        
    }
    //starts heuristic ranalg
    public Move generateMovementHeur(){
        //finds coordinates of movable pieces
        ArrayList<Move> moves = movesAvailable(board);
        //Gets the bombs of the opposing player
        bombs = getBombs(oppositePlayer());

        Random rand = new Random();

		while(oppositePlayer().piecesCoord.size() > 15)
		{
			if(moves.size() != 0)
			{
		        int i = rand.nextInt(moves.size());
		        return moves.get(i);
	        }
	        else
			{
	        	game.endgame();
	        	return null;
	        }
		}

//        System.out.println(bomb == null);
//        System.out.println(miner == null);

		if(bomb == null && miner == null)
		{
            identifyBombAndMiner();
        }
        else if(isDefeated(miner)) {
            System.out.println(player.getPlayer_ID() + ": Miner was captured before reaching its goal.");
            miner = null;
            if (miners.size() > 0)
            {
                identifyBombAndMiner();
            }
        }
        else if(isDefeated(bomb))
        {
            System.out.println(oppositePlayer().getPlayer_ID() + ": Bomb captured.");
            bomb = null;
            if(bombs.size() > 0)
            {
                identifyBombAndMiner();
            }
            return lastMove(miner);
        }

        System.out.println("Miner at (" + miner.getPosition()[0] + "," + miner.getPosition()[1] + ")");
        System.out.println("Bomb at (" +  bomb.getPosition()[0] + "," + bomb.getPosition()[1] +  ")");

        int[] bposition = bomb.getPosition();
        int[] mposition = miner.getPosition();
        game.path.clear();
        game.findPath(mposition[0], mposition[1], bposition[0], bposition[1]);

		Move move = null;

		if(game.path.size() > 1)
		{
			Cell origin = game.path.remove(game.path.size()-1);
			Cell destination = game.path.get(game.path.size()-1);
			move = new Move(origin.getContent(), destination.getPosition());
		}
		else
        {
            System.out.println("Path too short - make random move.");
            int i = rand.nextInt(moves.size());
            move = moves.get(i);
        }

        return move;
    }

    public Move lastMove(Pieces p)
    {
        System.out.println("Calculating last move");
        Move mv;
        int[] pos = new int[2];
        if(player.getPlayer_ID() == 1)
        {
            if(p.getPosition()[1] == 9)
            {
                 pos[0] = p.getPosition()[0];
                 pos[1] = p.getPosition()[1] + 1;
            }
            else if(p.getPosition()[1] == 10 && p.getPosition()[0] > 7)
            {
                pos[0] = p.getPosition()[0] - 1;
                pos[1] = p.getPosition()[1];
            }
            else if(p.getPosition()[1] == 10 && p.getPosition()[0] < 7)
            {
                pos[0] = p.getPosition()[0] + 1;
                pos[1] = p.getPosition()[1];
            }
        }
        if(player.getPlayer_ID() == 2) {
            if (p.getPosition()[1] == 2)
            {
                pos[0] = p.getPosition()[0];
                pos[1] = p.getPosition()[1] - 1;
            }
            else if (p.getPosition()[1] == 1 && p.getPosition()[0] > 7)
            {
                pos[0] = p.getPosition()[0] - 1;
                pos[1] = p.getPosition()[1];
            }
            else if (p.getPosition()[1] == 1 && p.getPosition()[0] < 7)
            {
                pos[0] = p.getPosition()[0] + 1;
                pos[1] = p.getPosition()[1];
            }
        }
        mv = new Move(p, pos);
        return mv;
    }

    public boolean isDefeated(Pieces piece)
    {
        return !(game.board[piece.getPosition()[0]][piece.getPosition()[1]].getContent() == piece);
    }

    public Player oppositePlayer(){
//    	if(player == game.player_1){
//    		return game.player_2;
//		} else {
//    		return game.player_1;
//		}
        return game.oppositePlayer();
	}
    
    /**
     * Makes a random move
     */
    public void randomMove()
    {
    	Move move = generateMovementHeur();
        System.out.println(player.getPlayer_ID() + ": moving from (" + move.piece.position[0] + "," + move.piece.position[1]
                + ") to (" + move.newCoords[0] + "," + move.newCoords[1] + ")");
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
        //System.out.println("listsize movables = " + list.size() + " ");
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

    public ArrayList<Pieces> getBombs(Player player)
    {
        ArrayList<Pieces> bombs = new ArrayList<Pieces>();
        for (Pieces p: player.piecesCoord)
        {
            if(p.getRank() == 11)
            {
                if((player == game.player_1) && (p.getPosition()[1] <= 2)|| //Basically searches for the bombs in the first and second row.
                        ((player == game.player_2) && (p.getPosition()[1] >= 9))) //Basically searches for the bombs in the second to last and last row.
                {
                    bombs.add(p);
                }
            }
        }
        return bombs;
    }

    public ArrayList<Pieces> getMiners(Player player){
        ArrayList<Pieces> miners = new ArrayList<Pieces>();
        for (Pieces p: player.piecesCoord)
        {
            if(p.getRank() == 3)
            {
                miners.add(p);
            }
        }
        return miners;
    }
    
    /**
     * identifyBombAndMiner algorithm to run after player has 15 pieces or less
     */

    public void identifyBombAndMiner()
    {

        Random rand = new Random();

    	if(bomb == null)
    	{
            System.out.println("Choosing new bomb...");
            bombs = getBombs(oppositePlayer());
    	    int b = rand.nextInt(bombs.size());
    	    bomb = bombs.remove(b);
        }
        if(miner == null)
        {
            System.out.println("Choosing new miner...");
            miners = getMiners(player);
            int m = rand.nextInt(miners.size());
            miner = miners.remove(m);
        }

    }
    
	public Cell[][] getBoard()
	{
		return board;
	}
}
