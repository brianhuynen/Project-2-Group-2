
public class Test {
	public static void main(String[] args){
		Board board = new Board();
		Piece piece1 = new Piece("2", new Position(3,3));
		board.placePiece(piece1, piece1.getPosition());
		Piece piece2 = new Piece("3", new Position(3,5));
		board.placePiece(piece2, piece2.getPosition());
		Piece piece3 = new Piece("4", new Position(3,5));
		board.placePiece(piece3, piece3.getPosition());
		board.printboard();
		board.movePiece(piece1, new Position(3,4));
		board.printboard();
		board.movePiece(piece2, new Position(3,4));
		board.printboard();
	}
}
