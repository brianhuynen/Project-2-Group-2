
public class Test {
	public static void main(String[] args){
		Board board = new Board();
		
		Player p = new HumanPlayer("Apple");
		Player p2 = new HumanPlayer("Banana");
		Piece a = new Piece(p,3);
		Piece a2 = new Piece(p2, 4);
		Position coord = new Position(0,0);
		Position coord2 = new Position(0,5);
		
		board.placePiece(coord, a);
		board.placePiece(coord2, a2);
		
		board.printBoard();
		
		System.out.println(board.getContent(coord2).getPID().getName() + ","  + board.getContent(coord2).getRank());
	}
}
