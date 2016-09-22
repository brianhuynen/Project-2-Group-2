// Empty cell state, should be able to add a Piece and change the Empty cell to a Occupied cell
public class EmptyCell extends Cell {

	Cell cellState;
	
	public EmptyCell(){}
	
	public Piece getContent(){
		return null;
	}

}
