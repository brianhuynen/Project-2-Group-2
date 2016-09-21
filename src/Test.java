
public class Test {
	public static void main(String[] args){
		Board board = new Board();
		Piece a = new Piece(1,3);
		Position coord = new Position(0,0);
		board.placePiece(coord,a);
		
		
		board.printBoard();
	}
}
