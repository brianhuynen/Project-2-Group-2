package Cell;

import java.awt.Color;

import GameBoard.Piece;

//fire@WILL!
// Empty cell state, should be able to add a Piece and change the Empty cell to a Occupied cell
public class EmptyCell extends Cell {

	
	
	public EmptyCell(){
		setBackground(Color.WHITE);
	}
	
	public Piece getContent(){
		return null;
	}

}
