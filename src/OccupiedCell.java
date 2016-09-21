//Occupied Cell state, should contain a Piece.
public class OccupiedCell implements Cell {

	Cell cellState;

	@Override
	public void setState(Cell cellState) {
		this.cellState = cellState;
	}

}
