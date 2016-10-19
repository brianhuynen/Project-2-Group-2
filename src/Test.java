import java.util.Scanner;

public class Test {
	
		final static boolean DEBUG = true;
		final static Player[] PLAYERS = {new HumanPlayer("1"), new HumanPlayer("2")};
		
	public static void main(String[] args){
		
		Player[] players;
		
		if (DEBUG){
			players = PLAYERS;
		} else {
			players = initPlayers();
		}
		
		
		Board board = new Board();
		Game game = new Game(board, players, DEBUG);	
	}
	
	public static Player[] initPlayers(){
		Scanner s = new Scanner(System.in);
		System.out.println("Enter player 1 name:");
		String p1 = s.nextLine();
		System.out.println("Enter player 2 name:");
		String p2 = s.nextLine();
		System.out.println("Hello " + p1 + "(1) and " + p2 + "(2)\n");
		Player[] players = {new HumanPlayer(p1), new HumanPlayer(p2)};
		return players;
	}
}
