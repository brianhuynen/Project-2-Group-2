package Cell;

import java.awt.Color;

import GameBoard.Piece;

//fire@WILL!
// Impassable Cell state - shouldn't implement anything but the getters and setters (for the sake of applying the interface)
public class ImpassableCell extends Cell{
	
	public ImpassableCell(){
		setEnabled(false);
	}
	
	public Piece getContent(){
		return null;
	}

}
