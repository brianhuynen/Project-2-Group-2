import java.util.Scanner;
//Should contain all game logic
public class Game implements Runnable{
	
	private	int currentPlayer = 0, maxPlayers;
	private Player[] players = new Player[2];
	private Board board;
	
	public Game(Board board, Player[] players){
		this.board = board;
		this.players = players;
		maxPlayers = 2;
		
		init();
		System.out.println("done");
	}
	
	//TODO Setup pieces according to graphics
	public void init(){
		board.resetBoard();
		boolean setup = false;
		
		while(!setup){
			while (!players[currentPlayer].done()){
				Scanner s = new Scanner(System.in);
				System.out.println(players[currentPlayer].getName() + ", set your piece (Rank xCoord yCoord):");
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
	}
	
	public void run(){ /*Game logic*/ }
}
