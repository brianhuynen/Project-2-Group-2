import java.util.ArrayList;

public class Move {
	
	public Pieces piece;
	public int[] newCoords;
	
	public Move(Pieces piece)
	{
		this.piece = piece;
	}
	
	public Move(Pieces piece, int[] newCoords)
	{
		this.piece = piece;
		this.newCoords = newCoords;
	}
	
	public void setNewCoords(int[] newCoords)
	{
		this.newCoords = newCoords;
	}
	
		

	
		
}
