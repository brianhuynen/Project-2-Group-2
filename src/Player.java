// TODO add methods.
public abstract class Player {
	
	final int[] data = {1,1,8,5,4,4,4,3,2,1,1,6};
	
	private int[] piecesData;
	private Piece[] pieces;
	private String name;
	
	public void add(Piece p, Position pos){}
	public void remove(){}
	public void movePiece(Piece p, Position to){}
	public boolean done(){ return false; }
	
	public String getName(){
		return name;
	}
	public Piece[] getPieces(){
		return pieces;
	}
}
