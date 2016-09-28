import java.util.*;

public class Test {
	public static void main(String[] args){
		
		Player[] players = initPlayers();
		
		Board board = new Board();
		Game game = new Game(board, players);
		
		game.init();
		
//		Piece a = new Piece(players[0],3);
//		Piece a2 = new Piece(players[1], 4);
//		Position coord = new Position(0,0);
//		Position coord2 = new Position(0,5);
//		
//		board.placePiece(coord, a);
//		board.placePiece(coord2, a2);
//		
//		board.printBoard();
//		System.out.println();
//		System.out.println("(" + board.getContent(coord2).getPID().getName() + ", "  + board.getContent(coord2).getRank() + ")");
		
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
