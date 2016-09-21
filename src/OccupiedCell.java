//Occupied Cell state, should contain a Piece.
public class OccupiedCell implements Cell {

	Cell cellState;
	Piece content;

	public OccupiedCell(Piece piece){
		content = piece;
	}
	@Override
	public void setState(Cell cellState) {
		this.cellState = cellState;
	}

}
