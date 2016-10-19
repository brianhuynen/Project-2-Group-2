public class Piece {
	
	private int rank;
	private Player pID;
	private Position position;
	
	private boolean visible; //TODO Make screen recognise piece and either hide it or not;
	
	public Piece(Player pID, int rank){
		this.pID = pID;
		this.rank = rank;
	}
	
	public void setPosition(Position pos){position = pos;}
	public Position getPosition(){return position;}
	public void setRank(int rank){this.rank = rank;}
	public int getRank(){return rank;}
	public Player getPID(){return pID;}
}
