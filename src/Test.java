
public class Test {
	public static void main(String[] args){
		
		Player[] players = {new HumanPlayer("Apple"), new HumanPlayer("Banana")};
		
		Board board = new Board();
		Game game = new Game(board, players);
		
		game.init();
		
		Piece a = new Piece(players[0],3);
		Piece a2 = new Piece(players[1], 4);
		Position coord = new Position(0,0);
		Position coord2 = new Position(0,5);
		
		board.placePiece(coord, a);
		board.placePiece(coord2, a2);
		
		board.printBoard();
		System.out.println();
		System.out.println("(" + board.getContent(coord2).getPID().getName() + ", "  + board.getContent(coord2).getRank() + ")");
	}
}
