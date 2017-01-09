import java.awt.Color;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;


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
	LinkedList<int[]> path= new LinkedList<int[]>();
	
	


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
		this.gameOver = false;
	}
	
	/**
	 * Sets the type of players, either human or AI
	 * @param playerTypeData the player types, AI Or human
	 * @return player??????
	 */

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
		currentPlayer.printKnown();
	}
	
	public void findPath(int x1, int y1, int x2, int y2)
	{
		//base cases
		int[] move = new int[2];
		
		if(path.isEmpty())
		{
			move[0]=x1;
			move[1]=y1;
			path.add(move);
		}
		
		if(x1 + 1 == x2 && y1 == y2)
		{
			move[0]=x2;
			move[1]=y2;
			path.add(move);
			//movePiece(x1,y1,x2,y2);
		}
		else if(x1 - 1 == x2 && y1 == y2)
		{
			move[0]=x2;
			move[1]=y2;
			path.add(move);
			//movePiece(x1,y1,x2,y2);
		}
		else if(x1 == x2 && y1 + 1 == y2)
		{
			move[0]=x2;
			move[1]=y2;
			path.add(move);
			//movePiece(x1,y1,x2,y2);
		}
		else if(x1 == x2 && y1 - 1 == y2)
		{
			move[0]=x2;
			move[1]=y2;
			path.add(move);
			//movePiece(x1,y1,x2,y2);
		}
		
		//recursive part, if piece 1 is located above piece 2
		
		/*else if(board[x1][y1+1].getCellState()!=0 && board[x1][y1-1].getCellState()!=0
				&& board[x1+1][y1].getCellState()!=0 && board[x1-1][y1].getCellState()!=0)
		{
			if(y2>y1)
			{
				if( board[x1][y1+2].getCellState() == 0 )
				{
					movePiece(x1,y1+1,x1,y1+2);
					changeTurnH();
					findPath(x1,y1,x2,y2);
				}
				else if( board[x1+1][y1+1].getCellState() == 0 )
				{
					movePiece(x1,y1+1,x1+1,y1+1);
					changeTurnH();
					findPath(x1,y1,x2,y2);
				}
				else if( board[x1-1][y1+1].getCellState() == 0 )
				{
					movePiece(x1,y1+1,x1-1,y1+1);
					changeTurnH();
					findPath(x1,y1,x2,y2);
				}
			}
			if(y2<y1)
			{
				if( board[x1][y1-2].getCellState() == 0 )
				{
					movePiece(x1,y1-1,x1,y1-2);
					changeTurnH();
					findPath(x1,y1,x2,y2);
				}
				else if( board[x1+1][y1-1].getCellState() == 0 )
				{
					movePiece(x1,y1-1,x1+1,y1-1);
					changeTurnH();
					findPath(x1,y1,x2,y2);
				}
				else if( board[x1-1][y1-1].getCellState() == 0 )
				{
					movePiece(x1,y1-1,x1-1,y1-1);
					changeTurnH();
					findPath(x1,y1,x2,y2);
				}
			}
		}*/
		
		//checks if the next move is surrounded by three pieces and tries to go around
		//for down
		else if(y2>y1 && board[x1][y1+1].getCellState()==0 &&
				board[x1-1][y1+1].getCellState()!=0 && board[x1+1][y1+1].getCellState() != 0
				&& board[x1][y1+2].getCellState() != 0)
		{
			if(x2>x1 && board[x1+1][y1].getCellState() == 0)
			{
				move[0]=x1+1;
				move[1]=y1;
				path.add(move);
				//movePiece(x1,y1,x1+1,y1);
				findPath(x1+1,y1,x2,y2);
			}
			else if(x2<x1 && board[x1-1][y1].getCellState() == 0)
			{
				move[0]=x1-1;
				move[1]=y1;
				path.add(move);
				//movePiece(x1,y1,x1-1,y1);
				findPath(x1-1,y1,x2,y2);
			}
			else if (board[x1+1][y1].getCellState() == 0)
			{
				move[0]=x1+1;
				move[1]=y1;
				path.add(move);
				//movePiece(x1,y1,x1+1,y1);
				findPath(x1+1,y1,x2,y2);
			}
			else if (board[x1-1][y1].getCellState() == 0)
			{
				move[0]=x1-1;
				move[1]=y1;
				path.add(move);
				//movePiece(x1,y1,x1-1,y1);
				findPath(x1-1,y1,x2,y2);
			}
			else if (board[x1][y1-1].getCellState() == 0)
			{
				move[0]=x1;
				move[1]=y1-1;
				path.add(move);
				//movePiece(x1,y1,x1,y1-1);
				findPath(x1,y1-1,x2,y2);
			}
		}
		
		//up
		else if(y2<y1 && board[x1][y1-1].getCellState()==0 &&
				board[x1-1][y1-1].getCellState()!=0 && board[x1+1][y1-1].getCellState() != 0
				&& board[x1][y1-2].getCellState() != 0)
		{
			if(x2>x1 && board[x1+1][y1].getCellState() == 0)
			{
				move[0]=x1+1;
				move[1]=y1;
				path.add(move);
				//movePiece(x1,y1,x1+1,y1);
				findPath(x1+1,y1,x2,y2);
			}
			else if(x2<x1 && board[x1-1][y1].getCellState() == 0)
			{
				move[0]=x1-1;
				move[1]=y1;
				path.add(move);
				//movePiece(x1,y1,x1-1,y1);
				findPath(x1-1,y1,x2,y2);
			}
			else if (board[x1+1][y1].getCellState() == 0)
			{
				move[0]=x1+1;
				move[1]=y1;
				path.add(move);
				//movePiece(x1,y1,x1+1,y1);
				findPath(x1+1,y1,x2,y2);
			}
			else if (board[x1-1][y1].getCellState() == 0)
			{
				move[0]=x1-1;
				move[1]=y1;
				path.add(move);
				//movePiece(x1,y1,x1-1,y1);
				findPath(x1-1,y1,x2,y2);
			}
			else if (board[x1][y1+1].getCellState() == 0)
			{
				move[0]=x1;
				move[1]=y1+1;
				path.add(move);
				//movePiece(x1,y1,x1,y1+1);
				findPath(x1,y1+1,x2,y2);
			}
		}
		
		//right
		else if(x2>x1 && board[x1+1][y1].getCellState()==0 &&
				board[x1+1][y1+1].getCellState()!=0 && board[x1+1][y1-1].getCellState() != 0
				&& board[x1+2][y1].getCellState() != 0)
		{
			if(y2>y1 && board[x1][y1+1].getCellState() == 0)
			{
				move[0]=x1;
				move[1]=y1+1;
				path.add(move);
				//movePiece(x1,y1,x1,y1+1);
				findPath(x1,y1+1,x2,y2);
			}
			else if(y2<y1 && board[x1][y1-1].getCellState() == 0)
			{
				move[0]=x1;
				move[1]=y1-1;
				path.add(move);
				//movePiece(x1,y1,x1,y1-1);
				findPath(x1,y1-1,x2,y2);
			}
			else if (board[x1][y1+1].getCellState() == 0)
			{
				move[0]=x1;
				move[1]=y1+1;
				path.add(move);
				//movePiece(x1,y1,x1,y1+1);
				findPath(x1,y1+1,x2,y2);
			}
			else if (board[x1][y1-1].getCellState() == 0)
			{
				move[0]=x1;
				move[1]=y1-1;
				path.add(move);
				//movePiece(x1,y1,x1,y1-1);
				findPath(x1,y1-1,x2,y2);
			}
			else if (board[x1-1][y1].getCellState() == 0)
			{
				move[0]=x1-1;
				move[1]=y1;
				path.add(move);
				//movePiece(x1,y1,x1-1,y1);
				findPath(x1-1,y1,x2,y2);
			}
		}
		
		//left
		else if(x2<x1 && board[x1-1][y1].getCellState()==0 &&
				board[x1-1][y1+1].getCellState()!=0 && board[x1-1][y1-1].getCellState() != 0
				&& board[x1-2][y1].getCellState() != 0)
		{
			if(y2>y1 && board[x1][y1+1].getCellState() == 0)
			{
				move[0]=x1;
				move[1]=y1+1;
				path.add(move);
				//movePiece(x1,y1,x1,y1+1);
				findPath(x1,y1+1,x2,y2);
			}
			else if(y2<y1 && board[x1][y1-1].getCellState() == 0)
			{
				move[0]=x1;
				move[1]=y1-1;
				path.add(move);
				//movePiece(x1,y1,x1,y1-1);
				findPath(x1,y1-1,x2,y2);
			}
			else if (board[x1][y1+1].getCellState() == 0)
			{
				move[0]=x1;
				move[1]=y1+1;
				path.add(move);
				//movePiece(x1,y1,x1,y1+1);
				findPath(x1,y1+1,x2,y2);
			}
			else if (board[x1][y1-1].getCellState() == 0)
			{
				move[0]=x1;
				move[1]=y1-1;
				path.add(move);
				//movePiece(x1,y1,x1,y1-1);
				findPath(x1,y1-1,x2,y2);
			}
			else if (board[x1+1][y1].getCellState() == 0)
			{
				move[0]=x1+1;
				move[1]=y1;
				path.add(move);
				//movePiece(x1,y1,x1+1,y1);
				findPath(x1+1,y1,x2,y2);
			}
		}
		
		else if(y2>y1 && board[x1][y1+1].getCellState()==0)
		{
			move[0]=x1;
			move[1]=y1+1;
			path.add(move);
			//movePiece(x1,y1,x1,y1+1);
			findPath(x1,y1+1,x2,y2);
		}//if piece 1 is located below piece 2
		else if(y2<y1 && board[x1][y1-1].getCellState()==0)
		{
			move[0]=x1;
			move[1]=y1-1;
			path.add(move);
			//movePiece(x1,y1,x1,y1-1);
			findPath(x1,y1-1,x2,y2);
		}//if piece 1 is located to the left of piece 2
		else if(x2>x1 && board[x1+1][y1].getCellState()==0)
		{
			move[0]=x1+1;
			move[1]=y1;
			path.add(move);
			//movePiece(x1,y1,x1+1,y1);
			findPath(x1+1,y1,x2,y2);
		}//if piece 1 is located to the right of piece 2
		else if(x2<x1 && board[x1-1][y1].getCellState()==0)
		{
			move[0]=x1-1;
			move[1]=y1;
			path.add(move);
			//movePiece(x1,y1,x1-1,y1);
			findPath(x1-1,y1,x2,y2);
		}
		
		//if there are obstacles in its path, move one to the right, if not possible, move to the left.
		else if(y2>y1 && board[x1][y1+1].getCellState()!=0)//block down
		{
			if(board[x1+1][y1].getCellState()==0)//move right
			{
				move[0]=x1+1;
				move[1]=y1;
				path.add(move);
				//movePiece(x1,y1,x1+1,y1);
				findPath(x1+1,y1,x2,y2);
			}
			else if(board[x1-1][y1].getCellState()==0)//move left
			{
				move[0]=x1-1;
				move[1]=y1;
				path.add(move);
				//movePiece(x1,y1,x1-1,y1);
				findPath(x1-1,y1,x2,y2);
			}
		}
		else if(y2<y1 && board[x1][y1-1].getCellState()!=0)//block up
		{
			if(board[x1+1][y1].getCellState()==0)
			{
				move[0]=x1+1;
				move[1]=y1;
				path.add(move);
				//movePiece(x1,y1,x1+1,y1);
				findPath(x1+1,y1,x2,y2);
			}
			else if(board[x1-1][y1].getCellState()==0)
			{
				move[0]=x1-1;
				move[1]=y1;
				path.add(move);
				//movePiece(x1,y1,x1-1,y1);
				findPath(x1-1,y1,x2,y2);
			}
		}//if there are obstacles in its path, move one downwards, if not possible, move upwards.
		else if(x2>x1 && board[x1+1][y1].getCellState()!=0)//block right
		{
			if(board[x1][y1+1].getCellState()==0)//move down
			{
				move[0]=x1;
				move[1]=y1+1;
				path.add(move);
				//movePiece(x1,y1,x1,y1+1);
				findPath(x1,y1+1,x2,y2);
			}
			else if(board[x1][y1-1].getCellState()==0)//move up
			{
				move[0]=x1;
				move[1]=y1-1;
				path.add(move);
				//movePiece(x1,y1,x1,y1-1);
				findPath(x1,y1-1,x2,y2);
			}
		}
		else if(x2<x1 && board[x1-1][y1].getCellState()!=0)//block left
		{
			if(board[x1][y1+1].getCellState()==0)
			{
				move[0]=x1;
				move[1]=y1+1;
				path.add(move);
				//movePiece(x1,y1,x1,y1+1);
				findPath(x1,y1+1,x2,y2);
			}
			else if(board[x1][y1-1].getCellState()==0)
			{
				move[0]=x1;
				move[1]=y1-1;
				path.add(move);
				//movePiece(x1,y1,x1,y1-1);
				findPath(x1,y1-1,x2,y2);
			}
		}
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
	
	/**
	 * Moves a random piece
	 */
	public void ranMovePiece(){
		//finds coordinates of movable pieces
		ArrayList<int[]> movables = findMovableCoords(currentPlayer);
		int[] movementData = new int[3];

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
			movePiece(coord[0], coord[1], (coord[0]+1), coord[1]);
		}
		if (directions.get(j) == 2){
			movePiece(coord[0], coord[1], (coord[0]-1), coord[1]);
		}
		if (directions.get(j) == 3){
			movePiece(coord[0], coord[1], coord[0], (coord[1]+1));
		}
		if (directions.get(j) == 4){
			movePiece(coord[0], coord[1], coord[0], (coord[1]-1));
		}
	}
	/**
	 * Finds all the coordinates of pieces which are able to move to another cell
	 * @param p player whose movable pieces coordinates who you want to find out
	 * @return list of coordinates of movable pieces
	 */
	public ArrayList<int[]> findMovableCoords(Player p){
		ArrayList<int[]> list = new ArrayList<int[]>();
		for (int x=1; x<board.length-1; x++){
			for (int y=1; y<board[0].length-1; y++){
				//looks for piece
				Cell current = board[x][y];
				if(board[x][y].getCellState() == 1){
					if(board[x][y].getContent().getRank() != 11) {
						Cell right = board[x + 1][y];
						Cell left = board[x - 1][y];
						Cell up = board[x][y - 1];
						Cell down = board[x][y + 1];
						//checks adjacent cells (if chosen piece is movable)
						//either a empty cell or opponent piece
						if ((right.getCellState() == 0 || left.getCellState() == 0 || down.getCellState() == 0 || up.getCellState() == 0) ||
								((right.getCellState() == 1 && (right.getContent().getPlayer_ID() != current.getContent().getPlayer_ID())) ||
										(left.getCellState() == 1 && (left.getContent().getPlayer_ID() != current.getContent().getPlayer_ID())) ||
										(down.getCellState() == 1 && (down.getContent().getPlayer_ID() != current.getContent().getPlayer_ID())) ||
										(up.getCellState() == 1 && (up.getContent().getPlayer_ID() != current.getContent().getPlayer_ID())))) {
							if (current.getContent().getPlayer_ID() == p.getPlayer_ID()) {
								int[] coord = {x, y};
								list.add(coord);
							}
						}
					}
				}
			}
		}
		//System.out.println(list.size() + " movable pieces for player " + currentPlayer.getPlayer_ID());
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
	/**
	 * Duplicates the board with only own pieces and known opponent pieces
	 * @param player
	 * @return the duplicate board
	 */
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
	/**
	 * Duplicates board with only the pieces of player
	 * @param player
	 * @return duplicate board
	 */
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

	public void clearPieces(){
		for (int x = 0; x<board.length; x++){
			for (int y = 0; y< board[0].length; y++){
				removePiece(x,y);
			}
		}
	}
}

