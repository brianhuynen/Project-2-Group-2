import java.util.Scanner;

public class Test {
	
		final static boolean DEBUG = false;
		final static Player[] PLAYERS = {new HumanPlayer("Pretzel"), new HumanPlayer("Mustard")};
		
	public static void main(String[] args){
		
		Player[] players;
		
		if (DEBUG){
			players = PLAYERS;
			System.out.println("Hello " + players[0].getName() + " (Player 1) and " + players[1].getName() + " (Player 2)");
		} else {
			players = initPlayers();
		}
		
		
		Board board = new Board();
		Game game = new Game(board, players, DEBUG, 500);	
	}
	
	public static Player[] initPlayers(){
		Scanner s = new Scanner(System.in);
		System.out.println("Enter player 1 name:");
		String p1 = s.nextLine();
		System.out.println("Enter player 2 name:");
		String p2 = s.nextLine();
		System.out.println("Hello " + p1 + " (Player 1) and " + p2 + " (Player 2)");
		Player[] players = {new HumanPlayer(p1), new HumanPlayer(p2)};
		return players;
	}
}
