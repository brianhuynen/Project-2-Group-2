
public class Test {
	public static void main(String[] args){
		Board board = new Board();
		// Creates a piece at location (3,3)
		Piece piece1 = new Piece("2", new Position(3,3));
		// Place this piece on the board (pieces[][])
		board.placePiece(piece1, piece1.getPosition());
		// Creates a piece of a higher rank at location (3,5)
		Piece piece2 = new Piece("3", new Position(3,5));
		// Place on board
		board.placePiece(piece2, piece2.getPosition());
		// Creates a piece at location (3,5)
		Piece piece3 = new Piece("4", new Position(3,5));
		// Try to place on board - this will fail and terminal will notify.
		board.placePiece(piece3, piece3.getPosition());
		// Create a piece at location (4,3), which is an impassable space
		Piece piece4 = new Piece("1", new Position(4,3));
		// Try to place piece, will fail
		board.placePiece(piece4, piece4.getPosition());
		// Print the board
		board.printboard();
		// Move piece1 to the right
		board.movePiece(piece1, new Position(3,4));
		board.printboard();
		// Move piece 2 to the left - they will battle and piece2 will win.
		board.movePiece(piece2, new Position(3,4));
		board.printboard();
	}
}
