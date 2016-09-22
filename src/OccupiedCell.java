//Occupied Cell state, should contain a Piece.
public class OccupiedCell extends Cell {

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
