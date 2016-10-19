import java.util.Scanner;
//Should contain all game logic
public class Game implements Runnable{
	
	private	int currentPlayer = 0, maxPlayers;
	private Player[] players = new Player[2];
	private Board board;
	private boolean debug, running = true;
	
	final int[][] PLAYER1 = {{ 1, 3, 5,11, 0, 5,11, 9, 8, 7},
			 				 { 3, 4, 4,11,11,11,11, 8, 7, 7},
			 				 { 3, 4, 4, 5, 5, 2, 6, 6, 6, 6},
			 				 { 2, 2, 2, 2, 3,10, 2, 2, 2, 3}};
	
	final int[][] PLAYER2 = {{ 3, 2, 2, 2,11, 1, 2, 2, 2, 3},
				 			 { 5, 5, 4, 4, 4, 4, 3, 3, 3, 2},
				 			 { 5, 5, 6,11,11, 2,11, 7, 8,10},
				 			 { 6, 6, 6,11, 0, 7,11, 7, 8, 9}};
	
	public Game(Board board, Player[] players, boolean debug){
		this.board = board;
		this.players = players;
		this.debug = debug;
		maxPlayers = 2;
		
		init();
		
		System.out.println("The placed pieces are: \n");
		listPieces();
		
		run();
	}
	
	//TODO Setup pieces according to graphics
	public void init(){
		
		board.resetBoard();
		
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
				if (currentPlayer == maxPlayers-1)
					setup = true;
				else
					currentPlayer++;
			}
		} else {
			for (int i = 0; i<maxPlayers; i++){
				setStandard(i);
			}
		}
	}
	
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
	
	public void listPieces(){
		for (int i = 0; i<players.length; i++){
			System.out.println(players[i].listPieces());
		}
	}
	
	public void reset(){
		currentPlayer = 0;
	}
	
	public void turn(){
		if (currentPlayer == 0){
			currentPlayer = 1;
		} else {
			currentPlayer = 0;
		}
	}
	
	public void run(){ /*Game logic*/ 
		board.placePlayerPieces(players);
		board.printBoard();
		
		reset();
		
		while(running){
			
			Scanner s = new Scanner(System.in);
			System.out.println("\n" + players[currentPlayer].getName() + ", move your piece (xFrom yFrom xTo yTo):");
			int xFrom = s.nextInt();
			int yFrom = s.nextInt();
			int xTo = s.nextInt();
			int yTo = s.nextInt();
			
			board.movePiece(new Position(xFrom, yFrom), new Position(xTo, yTo));
			turn();
			
			board.printBoard();
			
			if(!running){ 
				running = !running; 
				System.out.println("GameEnd");
			}
		}
	}
}
