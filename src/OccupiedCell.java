//Occupied Cell state, should contain a Piece.
public class OccupiedCell implements Cell {

	private Cell cellState;
	private Piece content;

	public OccupiedCell(Piece piece){
		content = piece;
	}
	@Override
	public void setState(Cell cellState) {
		this.cellState = cellState;
	}

}
