//Occupied Cell state, should contain a Piece.
public class OccupiedCell implements Cell {

	private Cell cellState;
	private Piece content;

	public OccupiedCell(Piece piece){
		content = piece;
	}
	
	public void setContent(Piece p){
		content = p;
	}
	
	public Piece getContent(){
		return content;
	}

}
