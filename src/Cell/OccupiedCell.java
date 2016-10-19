package Cell;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import GameBoard.Piece;

//fire@WILL!
//Occupied Cell state, should contain a Piece.
public class OccupiedCell extends Cell {

	private Piece content;

	public OccupiedCell(Piece piece){
		content = piece;
		//if (piece.getPID()== 0){
			setBackground(Color.BLUE);
			//setIcon();
			
			
		
		}
		//setBackground()
	//}
	
	public void setContent(Piece p){
		content = p;
	}
	
	public Piece getContent(){
		return content;
	}

}
