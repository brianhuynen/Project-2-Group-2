import java.util.Scanner;
//Should contain all game logic
public class Game implements Runnable{
	
	private	int currentPlayer = 0, maxPlayers;
	private Player[] players = new Player[2];
	private Board board;
	private boolean debug, running = true;
	// Hardcoded piece placement of player 1
	final int[][] PLAYER1 = {{ 1, 3, 5,11, 0, 5,11, 9, 8, 7},
			 				 { 3, 4, 4,11,11,11,11, 8, 7, 7},
			 				 { 3, 4, 4, 5, 5, 2, 6, 6, 6, 6},
			 				 { 2, 2, 2, 2, 3,10, 2, 2, 2, 3}};
	//Hardcoded piece placement of player 1
	final int[][] PLAYER2 = {{ 3, 2, 2, 2,11, 1, 2, 2, 2, 3},
				 			 { 5, 5, 4, 4, 4, 4, 3, 3, 3, 2},
				 			 { 5, 5, 6,11,11, 2,11, 7, 8,10},
				 			 { 6, 6, 6,11, 0, 7,11, 7, 8, 9}};
	//Initialises the board, the players and the game.
	public Game(Board board, Player[] players, boolean debug){
		this.board = board;
		this.players = players;
		this.debug = debug;
		maxPlayers = 2;
		
		init();
		//Prints out the stored pieces by the players before running the game (can be commented out).
		System.out.println("The placed pieces are: \n");
		listPieces();
		//runs the game
		run();
	}
	
	//TODO Setup pieces according to graphics
	public void init(){
		
		board.resetBoard();
		//Prompts the user to manually place the pieces by entering a string containing the rank, x and y.
		//These values are integers and are separated by a space.
		if(!debug){
			boolean setup = false;
			
			while(!setup){
				while (!players[currentPlayer].done()){
					Scanner s = new Scanner(System.in);
					System.out.println("\n" + players[currentPlayer].getName() + ", set your piece (Rank xCoord yCoord):");
					int r = s.nextInt();
					int x = s.nextInt();
					int y = s.nextInt();
					players[currentPlayer].add(new Piece(players[currentPlayer], r), new Position(x, y));
				}
				if (currentPlayer == maxPlayers-1) //termination restriction: once the max number of players have setup their boards,
					setup = true;				   //the setup phase is done.
				
				else 							   //Once one player is done setting up their pieces, you progress to the next player.
					currentPlayer++;
			}
		} else { //If the debug value is set to false, standard (hardcoded) placements are made.
			for (int i = 0; i<maxPlayers; i++){
				setStandard(i);
			}
		}
	}
	//Method to read hardcoded piece placements from a 4 x 10 matrix of integers per player.
	private void setStandard(int n){
		if (n == 0){
			for(int i = 0; i<PLAYER1.length; i++){
				for (int j = 0; j<PLAYER1[0].length; j++){
					players[n].add(new Piece(players[n], PLAYER1[i][j]), new Position(i, j));
				}
			}
		} else if (n == 1){
			for(int i = 0; i<PLAYER2.length; i++){
				for (int j = 0; j<PLAYER2[0].length; j++){
					players[n].add(new Piece(players[n], PLAYER2[i][j]), new Position(i+6, j));
				}
			}
		}
	}
	//Prints out the pieces placed and the exact location.
	public void listPieces(){
		for (int i = 0; i<players.length; i++){
			System.out.println(players[i].listPieces());
		}
	}
	
	public void reset(){
		currentPlayer = 0;
		board.setCurrentPlayer(currentPlayer);
	}
	
	public void turn(){
		if (currentPlayer == 0){
			currentPlayer = 1;
			board.setCurrentPlayer(currentPlayer);
		} else {
			currentPlayer = 0;
			board.setCurrentPlayer(currentPlayer);
		}
	}
	
	public void run(){
		//Starts off the loop by placing the pieces on the board (which has been input before).
		board.addPlayerData(players);
		board.placePlayerPieces(players);
		board.printBoard();
		//Sets current player to 0 (i.e. the first player)
		reset();
		//Start of game loop
		while(running){
			
			Scanner s = new Scanner(System.in);
			System.out.println("\n" + players[currentPlayer].getName() + ", move your piece from (x y):");
			
			int xFrom = s.nextInt();
			int yFrom = s.nextInt();
			
			Position p = new Position(xFrom, yFrom);
			//If the cell isn't empty
			if (board.getContent(p) != null && board.getPID(xFrom, yFrom) == players[currentPlayer].getName()){
			
				System.out.println("\nTo (x y):");
				
				int xTo = s.nextInt();
				int yTo = s.nextInt();
				
				Position newP = new Position(xTo, yTo);
				
				System.out.println("Moving (" + p.getX() + "," + p.getY() + ") to (" 
											+ newP.getX() + "," + newP.getY() + ")..");
				
				if(board.handleMovement(p, newP) == 1){
//					turn();
				} else if (board.handleMovement(p, newP) == -1){
					System.out.println("InvalidMovement\n");
				} else if (board.handleMovement(p, newP) == 0){
					running = !running;
				}
			} else {
				System.out.println("IllegalMovement\n");
			}
				
			board.printBoard();
			
		}
		
		System.out.println("Player " + players[currentPlayer].getName() + " wins!");
	}
}
