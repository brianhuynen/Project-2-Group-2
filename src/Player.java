import java.awt.Color;
import java.util.ArrayList;
public class Player {
	
	public int player_ID;
	public int pieces[];
	public Color pColor;
	ArrayList<Pieces> piecesCoord;
	ArrayList<Pieces> knownPieces;
	int offBoard;
	
	
	public Player(int i, Color color){
		this.player_ID = i;
		int pieces [] = {1,1,8,5,4,4,4,3,2,1,1,6};
		this.pieces  = pieces;
		this.pColor = color;

	}
	
	public boolean pieceIsAvailable(int r){
		if(r<0 || r>11){
			//give error box, rank must be between 0 and 11
			return false;
		}
		if(pieces[r] != 0){
			return true;
		}
		else{
			return false;
		}
	}
	
	public int getPlayer_ID(){
		return player_ID;		
	}
	
	public boolean piecesIsEmpty(){
		for(int i=0; i<12; i++){
			if(pieces[i]!=0){
				return false;
			}
		}
	
		return true;
	}
}
