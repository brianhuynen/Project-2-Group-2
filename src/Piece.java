public class Piece {
	
	private int rank, playerID;
	private Position position;
	
	public Piece(int playerID, int rank){
		this.playerID = playerID;
		this.rank = rank;
	}
	
	public void setPosition(Position pos){position = pos;}
	public Position getPosition(){return position;}
	public void setRank(int rank){this.rank = rank;}
	public int getRank(){return rank;}
	public int getPlayer(){return playerID;}
}
