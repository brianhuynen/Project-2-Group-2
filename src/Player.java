
public class Player {
	
	public int player_ID;
	public int pieces[];
	
	public Player(int i){
		this.player_ID = i;
		int pieces [] = {1,1,8,5,4,4,4,3,2,1,1,6};
		this.pieces  = pieces;
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

}
