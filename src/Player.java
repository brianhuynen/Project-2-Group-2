
import java.awt.Color;
import java.util.ArrayList;
public class Player {
	//h
	public int player_ID;
	public int pieces[];
	public Color pColor;
	public ArrayList<Pieces> piecesCoord = new ArrayList<Pieces>();
	public ArrayList<Pieces> knownPieces = new ArrayList<Pieces>();
	public int numberPieces;
	public int offBoard;
	public String type;
	
	
	public Player(int i, Color color){
		this.player_ID = i;
//		int pieces [] = {1,1,8,5,4,4,4,3,2,1,1,6}; //Stores how many pieces of each rank can be stored.
//		int pieces [] = {1,0,0,1,3,0,0,0,0,0,0,3}; //For testing purposes.
		int pieces [] = {1,0,0,5,0,0,0,0,0,0,0,6}; //test stuff
		this.pieces  = pieces;
		this.numberPieces = 0;
		this.pColor = color;
		this.offBoard = 0;

	}
	/**
	 * Checks if piece is available to be placed
	 * @param r rank of piece
	 * @return true if piece is still available
	 */
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
	/**
	 * Checks if all pieces where placed on the board
	 * @return true if piecesToBePlaced is empty
	 */
	public boolean piecesIsEmpty(){
		for(int i=0; i<12; i++){
			if(pieces[i]!=0){
				return false;
			}
		}
	
		return true;
	}
	
	public String getType(){
		return type;
	}
	
	public void setType(String a){
		type = a;
	}

	public void printPiecesCoord(){
		System.out.print(getPlayer_ID()+": ");
		for (Pieces p: piecesCoord){
			System.out.print(p.getRank() + " at (" + p.getPosition()[0] + "," + p.getPosition()[1] + "), ");
		}
		System.out.println();
	}
	
	public void printKnown()
	{
//		System.out.println("Known pieces by player " + player_ID + ": " );
		for (int i = 0; i< knownPieces.size(); i++)
		{
//			System.out.println("piece with rank " + knownPieces.get(i).rank );
		}
	}
}
