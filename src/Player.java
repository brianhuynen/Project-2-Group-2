// TODO add methods.
public abstract class Player {
	
	final int[] data = {1,1,8,5,4,4,4,3,2,1,1,6}; // F(1), 1(1), 2(8), 3(5), 4(4), 5(4), 6(4), 7(3), 8(2), 9(1), 10(1), B(6)
	
	private int[] piecesData;
	private Piece[] pieces;
	private String name;
	
	public void add(Piece p, Position pos){}
	public void remove(){}
	public void movePiece(Piece p, Position to){}
	public boolean done(){ return false; }
	
	public void setPieces(Piece[] pieces){ this.pieces = pieces; }

	public String getName(){ return name; }
	public Piece[] getPieces(){ return pieces; }
	public String listPieces(){ return ""; }
}
