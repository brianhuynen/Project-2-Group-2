public class Piece {
	
	private String rank;
	private Position pos;
	
	public Piece(String rank, Position pos){
		if (isValid(rank)){
			this.rank = rank;
		}
		this.pos = pos;
	}
	
	private boolean isValid(String rank){
		return rank == "B" || rank == "F" || 
				(Integer.parseInt(rank) > 0 && Integer.parseInt(rank) <= 10);
	}
	
	public int compareTo(Piece piece){
		if (getRank() == "B" && getRank() != "3")
			return -1;
		else if (piece.getRank() == "B")
			return 1;
		else if (Integer.parseInt(getRank()) > Integer.parseInt(piece.getRank()))
			return 1;
		else if (Integer.parseInt(getRank()) == Integer.parseInt(piece.getRank()))
			return 0;
		else
			return -1;
	}
	
	public String getRank(){
		return rank;
	}	
	public Position getPosition(){
		return pos;
	}
	public void setPosition(Position pos){
		this.pos = pos;
	}
}
