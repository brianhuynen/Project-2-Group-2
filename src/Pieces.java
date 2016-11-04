
public class Pieces {
	
	int rank;
	String name;
	Pieces piece;
	int player_ID;
	
	public Pieces(int rank, String name, int player_ID){
		this.rank = rank;
		this.name = name;
		this.player_ID = player_ID;
	}

	public int getPlayer_ID(){
		return player_ID;
	}
	public Pieces getPiece(){
		return piece;
	}
	
	public String getName(){
		return name;
	}
	
	public int getRank(){
		return rank;
	}
	
	public boolean isMovable(){
		if(getRank()==0|| getRank()==11){
			return false;
		}
		return true;
	}
	
}
