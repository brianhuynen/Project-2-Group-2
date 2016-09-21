// Empty cell state, should be able to add a Piece and change the Empty cell to a Occupied cell
public class EmptyCell implements Cell {

	Cell cellState;
	
	public EmptyCell(){
	}

	@Override
	public void setState(Cell cellState) {
		this.cellState = cellState;
	}

}
